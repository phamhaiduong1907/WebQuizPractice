/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.courseContentController;

import controller.AuthorizationController;
import dal.CourseDBContext;
import dal.TopicDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import model.ErrorMessage;

/**
 *
 * @author ADMIN
 */
public class AddTopicController extends AuthorizationController {

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
        String raw_postName = request.getParameter("topicName");
        String url = request.getHeader("referer");
        int courseID = Integer.parseInt(request.getParameter("courseID"));
        TopicDBContext topicDBContext = new TopicDBContext();
        Account account = (Account) request.getSession().getAttribute("account");
        CourseDBContext courseDBContext = new CourseDBContext();

        if (courseDBContext.authEdit(courseID, account.getUsername()) || account.getRole().getRoleID() == 1) {
            if (raw_postName != null && raw_postName.trim().length() > 0) {
                if (!topicDBContext.checkDuplicateAdd(raw_postName, courseID)) {
                    if (topicDBContext.insertTopic(courseID, raw_postName)) {
                        request.getSession().setAttribute("message", ErrorMessage.UPDATESUCESSFULLY);
                        response.sendRedirect(url);
                    } else {
                        request.getSession().setAttribute("message", ErrorMessage.ERRORSQL);
                        response.sendRedirect(url);
                    }
                } else {
                    request.getSession().setAttribute("message", ErrorMessage.DUPLICATE_TOPIC);
                    response.sendRedirect(url);
                }

            } else {
                request.getSession().setAttribute("message", ErrorMessage.MISSINGINPUT);
                response.sendRedirect(url);
            }
        } else {
            request.getSession().setAttribute("errormessage", ErrorMessage.AUTH_EDIT_COURSE);
            response.sendRedirect(url);
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
