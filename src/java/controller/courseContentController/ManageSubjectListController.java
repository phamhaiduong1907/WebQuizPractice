/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.courseContentController;

import controller.AuthorizationController;
import dal.CategoryDBContext;
import dal.CourseDBContext;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Category;
import model.Course;

/**
 *
 * @author Zuys
 */
public class ManageSubjectListController extends AuthorizationController { 
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
        CourseDBContext courseDBContext = new CourseDBContext();
        CategoryDBContext dbCate = new CategoryDBContext();
        
        int pagesize = 8;
        String page = request.getParameter("page");
        if (page == null || page.trim().length() == 0) {
            page = "1";
        }
        int pageindex = Integer.parseInt(page);
        int count = courseDBContext.countCourse();
        int totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize) + 1;
        if (pageindex <= 0 || pageindex > totalpage) {
            pageindex = 1;
        }
        ArrayList<Category> categories = dbCate.getCategories(2);
        ArrayList<Course> courses = courseDBContext.getManageCourses(pageindex, pagesize,null);

        request.setAttribute("categories", categories);
        request.setAttribute("courses", courses);
        request.setAttribute("url", "managesubject");
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("totalpage", totalpage);

        request.getRequestDispatcher("view/course_content/subject_list.jsp").forward(request, response);
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
