/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.courseContentController;

import controller.AuthorizationController;
import dal.CategoryDBContext;
import dal.CourseDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Category;
import model.Course;

/**
 *
 * @author Hai Tran
 */
public class ManageSearchController extends AuthorizationController {

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
        CourseDBContext dbCourse = new CourseDBContext();
        CategoryDBContext dbCate = new CategoryDBContext();
        int pagesize = 8;
        String page = request.getParameter("page");
        if (page == null || page.trim().length() == 0) {
            page = "1";
        }
        int pageindex = Integer.parseInt(page);
        if (pageindex <= 0) {
            pageindex = 1;
        }
        ArrayList<Category> categories = dbCate.getCategories(2);
        String string = "";
        String subcateID = "";
        String search = "";
        String sort = request.getParameter("sort");
        if (sort == null || sort.trim().length() == 0) {
            sort = "1";
        }
        if (!(request.getParameter("search").trim().equals(""))) {
            search = request.getParameter("search");
        }
        if (request.getParameterValues("subcategory") != null) {
            String[] subcategory = request.getParameterValues("subcategory");
            if (subcategory.length != 0) {
                for (int i = 0; i < subcategory.length; i++) {
                    string += subcategory[i] + ", ";
                }
            }
            subcateID = string.substring(0, string.trim().length() - 1);
        }
        search = search.trim();
        String queryString = request.getQueryString();
        subcateID = subcateID.trim();
        ArrayList<Course> searchCourse = dbCourse.searchManageCourse(search, subcateID, sort, pageindex, pagesize);
        int count = dbCourse.countManageSearchCourse(search, subcateID, sort);
        int totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize) + 1;
        request.setAttribute("categories", categories);
        request.setAttribute("courses", searchCourse);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("count", count);
        request.setAttribute("search", search);
        request.setAttribute("url", "managesearch");
        request.setAttribute("querystring", queryString);
        request.getRequestDispatcher("/view/course_content/subject_list.jsp").forward(request, response);
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
