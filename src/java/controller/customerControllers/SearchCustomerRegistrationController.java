/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customerControllers;

import com.oracle.wls.shaded.org.apache.bcel.generic.AALOAD;
import static controller.customerControllers.CustomerRegistrationController.CUSTOMERREGISTRATIONURL;
import static controller.customerControllers.CustomerRegistrationController.PAGESIZE;
import dal.CategoryDBContext;
import dal.RegistrationDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.Category;
import model.Registration;

/**
 *
 * @author ADMIN
 */
public class SearchCustomerRegistrationController extends HttpServlet {
    
    final static int PAGESIZE = 3;
    final static String CUSTOMERREGISTRATIONURL = "view/customer/registration.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String raw_pageindex = request.getParameter("page");
        String search = request.getParameter("search");
        CategoryDBContext dbCate = new CategoryDBContext();
        String queryString = request.getQueryString();
        Account account = (Account) request.getSession().getAttribute("account");
        
        if (raw_pageindex == null || raw_pageindex.trim().length() == 0) {
            raw_pageindex = "1";
        }
        int pageindex = Integer.parseInt(raw_pageindex);
        ArrayList<Category> categories = dbCate.getCategories(2);
        
        RegistrationDBContext registrationDBContext = new RegistrationDBContext();
        ArrayList<Integer> subcategories = new ArrayList<>();
        
        String[] subcategory = request.getParameterValues("subcategory");
        if (subcategory != null) {
            for (int i = 0; i < subcategory.length; i++) {
                subcategories.add(Integer.parseInt(subcategory[i]));
            }
        }
        int count = registrationDBContext.getCountByFilter(subcategories, search, account.getUsername());
        int totalpage = (count % PAGESIZE == 0) ? (count / PAGESIZE) : (count / PAGESIZE) + 1;
        
        
        ArrayList<Registration> registrations = registrationDBContext.getRegistrationsByFilter(subcategories, search, pageindex, PAGESIZE, account.getUsername());
        request.setAttribute("queryString", queryString);
        request.setAttribute("registrations", registrations);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("categories", categories);
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("pagesize", PAGESIZE);
        request.getRequestDispatcher(CUSTOMERREGISTRATIONURL).forward(request, response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int registrationID = Integer.parseInt(request.getParameter("registrationID"));
        String queryString = request.getParameter("queryString");
        
        RegistrationDBContext registrationDBContext = new RegistrationDBContext();
        registrationDBContext.deleteRegistration(registrationID);
        response.sendRedirect("registrationsearch?" + queryString);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
