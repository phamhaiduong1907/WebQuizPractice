/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.publicController;

import dal.CategoryDBContext;
import dal.CourseDBContext;
import jakarta.security.auth.message.callback.PrivateKeyCallback;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.PageContext;
import java.util.ArrayList;
import model.Account;
import model.Category;
import model.Course;

/**
 *
 * @author ADMIN
 */
public class SubjectListController extends HttpServlet {
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
        CourseDBContext courseDBContext = new CourseDBContext();
        Account account = (Account) request.getSession().getAttribute("account");
        CategoryDBContext dbCate = new CategoryDBContext();
        int pagesize = 3;
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
        ArrayList<Course> courses = courseDBContext.getCourses(pageindex, pagesize,account);
        log("" + courses.size());
        request.setAttribute("categories", categories);
        request.setAttribute("courses", courses);
        request.setAttribute("url", "subjectList");
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("totalpage", totalpage);
        request.getRequestDispatcher("view/subject/subject_list.jsp").forward(request, response);

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
