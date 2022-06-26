/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.courseContentController;

import dal.CategoryDBContext;
import dal.CourseDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.util.ArrayList;
import model.Category;
import model.Course;

/**
 *
 * @author ADMIN
 */
@MultipartConfig
public class EditSubjectController extends HttpServlet {

    private static final String EDITCOURSEDETAILURL = "../view/course_content/course_edit.jsp";

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
        int courseID = Integer.parseInt(request.getParameter("id"));
        CourseDBContext courseDBContext = new CourseDBContext();
        CategoryDBContext categoryDBContext = new CategoryDBContext();

        Course course = courseDBContext.getCourse(courseID);
        ArrayList<Category> categorys = categoryDBContext.getCategories(2); // get category from course
        Category category = categoryDBContext.getCategoryBySubCategoryID(course.getSubcategory().getSubcategoryID());

        request.setAttribute("course", course);
        request.setAttribute("category", category);
        request.setAttribute("categorys", categorys);

        request.getRequestDispatcher(EDITCOURSEDETAILURL).forward(request, response);
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
        String raw_courseName = request.getParameter("courseName");
        String raw_subcategoryID = request.getParameter("subcategoryID");
        String raw_isFeatured = request.getParameter("isFeatured");
        String raw_status = request.getParameter("status");
        String raw_description = request.getParameter("description");
        String raw_tagline = request.getParameter("tagline");
        String raw_briefInfo = request.getParameter("briefInfo");
        Part thumbnail = request.getPart("thumbnail");
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
