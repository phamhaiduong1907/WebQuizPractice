/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customerControllers;

import controller.AuthorizationController;
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
import model.User;

/**
 *
 * @author ADMIN
 */
public class CustomerRegistrationController extends AuthorizationController {

    final static String CUSTOMERREGISTRATIONURL = "view/customer/registration.jsp";
    final static int PAGESIZE = 2;

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
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RegistrationDBContext registrationDBContext = new RegistrationDBContext();
        CategoryDBContext dbCate = new CategoryDBContext();

        String page = request.getParameter("page");
        Account account = (Account) request.getSession().getAttribute("account");
        if (page == null || page.trim().length() == 0) {
            page = "1";
        }
        int pageindex = Integer.parseInt(page);
        ArrayList<Category> categories = dbCate.getCategories(2);

        ArrayList<Registration> registrations = new ArrayList<>();
        registrations = registrationDBContext.getRegistrations(pageindex, PAGESIZE, account.getUsername());
        int count = registrationDBContext.getQuantityOfRegistration(account.getUsername());
        int totalpage = (count % PAGESIZE == 0) ? (count / PAGESIZE) : (count / PAGESIZE) + 1;
        if (pageindex <= 0 || pageindex > totalpage) {
            pageindex = 1;
        }

        request.setAttribute("totalpage", totalpage);
        request.setAttribute("categories", categories);
        request.setAttribute("registrations", registrations);
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
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
