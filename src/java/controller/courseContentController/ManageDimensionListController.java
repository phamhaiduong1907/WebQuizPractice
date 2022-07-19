/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.courseContentController;

import controller.AuthorizationController;
import dal.CourseDBContext;
import dal.DimensionDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.Course;
import model.Dimension;
import model.ErrorMessage;

/**
 *
 * @author ADMIN
 */
public class ManageDimensionListController extends AuthorizationController {

    final static private String DIMENSIONLISTURL = "../../view/course_content/dimension_list.jsp";
    final static private int PAGESIZE = 5;

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
        String url = request.getHeader("referer");
        CourseDBContext courseDBContext = new CourseDBContext();

        Account account = (Account) request.getSession().getAttribute("account");
        String queryString = request.getQueryString();

        DimensionDBContext dimensionDBContext = new DimensionDBContext();
        int courseID = Integer.parseInt(request.getParameter("id"));

        if (courseDBContext.authEdit(courseID, account.getUsername()) || account.getRole().getRoleID() == 1) {
            String page = request.getParameter("page");
            if (page == null || page.trim().length() == 0) {
                page = "1";
            }
            int pageindex = Integer.parseInt(page);
            int count = dimensionDBContext.getQuantityDimensionPagination(courseID);
            int totalpage = (count % PAGESIZE == 0) ? (count / PAGESIZE) : (count / PAGESIZE) + 1;
            if (pageindex <= 0 || pageindex > totalpage) {
                pageindex = 1;
            }

            Course course = courseDBContext.getCourse(courseID);
            ArrayList<Dimension> dimensions = dimensionDBContext.getDimensionPagination(courseID, PAGESIZE, pageindex);

            request.setAttribute("dimensions", dimensions);
            request.setAttribute("course", course);
            request.setAttribute("pageindex", pageindex);
            request.setAttribute("totalpage", totalpage);
            request.setAttribute("queryString", queryString);

            request.getRequestDispatcher(DIMENSIONLISTURL).forward(request, response);
        } else {
            request.getSession().setAttribute("message", ErrorMessage.AUTH_EDIT_COURSE);
            response.sendRedirect(url);
        }

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
