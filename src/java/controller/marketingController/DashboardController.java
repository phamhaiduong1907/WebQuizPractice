/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.marketingController;

import controller.AuthorizationController;
import dal.CategoryDBContext;
import dal.CourseDBContext;
import dal.RegistrationDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Category;
import model.Course;
import model.OrderData;

/**
 *
 * @author Hai Duong
 */
public class DashboardController extends AuthorizationController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CourseDBContext dbCourse = new CourseDBContext();
        CategoryDBContext dbCategory = new CategoryDBContext();
        RegistrationDBContext dbRegistration = new RegistrationDBContext();
        ArrayList<Course> courses = dbCourse.getCoursesLearners();
        ArrayList<Course> popularCourses = dbCourse.getPopularSubjects();
        ArrayList<Category> categories = dbCategory.getSubjectCategoriesWithRevenue();
        float totalRevenues = 0;
        for (Category category : categories) {
            totalRevenues += category.getRevenue();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date current = new Date();
        Date previous7Days = new Date();
        previous7Days.setTime(current.getTime() - 24 * 3600 * 1000 * 6);
        String startdate = sdf.format(previous7Days);
        String enddate = sdf.format(current);
        ArrayList<OrderData> orderDatas = dbRegistration.getOrderDataInPeriod(startdate, enddate);
        request.setAttribute("totalRevenues", totalRevenues);
        request.setAttribute("courses", courses);
        request.setAttribute("popularCourses", popularCourses);
        request.setAttribute("categories", categories);
        request.setAttribute("orderDatas", orderDatas);
        request.getRequestDispatcher("view/marketing/dashboard.jsp").forward(request, response);
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
