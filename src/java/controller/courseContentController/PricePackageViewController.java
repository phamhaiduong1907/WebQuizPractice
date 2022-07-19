/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.courseContentController;

import controller.AuthorizationController;
import dal.CourseDBContext;
import dal.PricePackageDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Course;
import model.PricePackage;

/**
 *
 * @author ADMIN
 */
public class PricePackageViewController extends AuthorizationController {
    private final static String PRICEPACKAGEVIEWURL = "../../view/course_content/pricepackage_view.jsp";
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int pricepackageID = Integer.parseInt(request.getParameter("pid"));
        int courseID = Integer.parseInt(request.getParameter("cid"));
        CourseDBContext courseDBContext = new CourseDBContext();
        PricePackageDBContext pricePackageDBContext = new PricePackageDBContext();
        Course course = courseDBContext.getCourse(courseID);
        PricePackage pricePackage = pricePackageDBContext.getPricePackageByID(pricepackageID);
        
        
        
        request.setAttribute("course", course);
        request.setAttribute("pricePackage", pricePackage);
        request.getRequestDispatcher(PRICEPACKAGEVIEWURL).forward(request, response);
        
        
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
