/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.courseContentController;

import controller.AuthorizationController;
import dal.CommonDBContext;
import dal.CourseDBContext;
import dal.PricePackageDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Course;
import model.ErrorMessage;
import model.PricePackage;
import util.Validation;

/**
 *
 * @author ADMIN
 */
public class PricePackageAddController extends AuthorizationController {

    private final static String PRICEPACKAGEADDURL = "../../view/course_content/pricepackage_add.jsp";

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

        int courseID = Integer.parseInt(request.getParameter("cid"));
        CourseDBContext courseDBContext = new CourseDBContext();
        PricePackageDBContext pricePackageDBContext = new PricePackageDBContext();
        Course course = courseDBContext.getCourse(courseID);
        request.setAttribute("course", course);
        request.getRequestDispatcher(PRICEPACKAGEADDURL).forward(request, response);

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
        String url = request.getHeader("referer");

        ErrorMessage errorMessage = new ErrorMessage();
        String raw_priceName = request.getParameter("priceName");
        String raw_description = request.getParameter("description");
        String raw_duration = request.getParameter("duration");
        String raw_listPrice = request.getParameter("listPrice");
        String raw_salePrice = request.getParameter("salePrice");

        Validation validation = new Validation();
        ArrayList<String> input = new ArrayList<>();
        input.add(raw_priceName);
        input.add(raw_description);
        input.add(raw_listPrice);
        input.add(raw_salePrice);

        float listPrice, salePrice;
        int courseID = Integer.parseInt(request.getParameter("courseID"));

        if (validation.checkNullOrBlank(input)) {
            listPrice = Float.parseFloat(raw_listPrice);
            salePrice = Float.parseFloat(raw_salePrice);
            PricePackageDBContext pricePackageDBContext = new PricePackageDBContext();
            CommonDBContext commonDBContext = new CommonDBContext();

            if (pricePackageDBContext.insertPricePackage(raw_priceName, raw_duration, false, 
                    listPrice, salePrice, raw_description, courseID, false)) {
                int id = commonDBContext.getIdentity("PricePackage");
                response.sendRedirect("pricepackageview?pid=" + id + "&cid=" + courseID);
            } else {
                response.sendRedirect(url + "&errormessage=" + errorMessage.ERRORSQL);
            }

        } else {
            response.sendRedirect(url + "&errormessage=" + errorMessage.MISSINGINPUT);

        }

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
