/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.courseContentController;

import controller.AuthorizationController;
import dal.AccountDBContext;
import dal.CategoryDBContext;
import dal.CourseDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.Category;
import model.Course;
import model.ErrorMessage;

/**
 *
 * @author ADMIN
 */
public class ManageSubjectDetailController extends AuthorizationController {

    private static final String COURSEDETAILURL = "../view/course_content/course_detail.jsp";

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
        int courseID = Integer.parseInt(request.getParameter("id"));
        CourseDBContext courseDBContext = new CourseDBContext();
        CategoryDBContext categoryDBContext = new CategoryDBContext();
        AccountDBContext accountDBContext = new AccountDBContext();

        Course course = courseDBContext.getCourse(courseID);
        Account account = (Account) request.getSession().getAttribute("account");

        if (courseDBContext.authEdit(courseID, account.getUsername()) || account.getRole().getRoleID() == 1) {
            Category category = categoryDBContext.getCategoryBySubCategoryID(course.getSubcategory().getSubcategoryID());
            ArrayList<Account> accounts = accountDBContext.getAccountByRoleID(2);

            request.setAttribute("course", course);
            request.setAttribute("category", category);
            request.setAttribute("accounts", accounts);
            request.getRequestDispatcher(COURSEDETAILURL).forward(request, response);
        } else {
            request.getSession().setAttribute("errormessage", ErrorMessage.AUTH_EDIT_COURSE);
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
