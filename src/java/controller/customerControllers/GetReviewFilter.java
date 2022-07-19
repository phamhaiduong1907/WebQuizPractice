/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.customerControllers;

import controller.AuthorizationController;
import dal.QuizHandleDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.ResultQuestion;

/**
 *
 * @author Zuys
 */
public class GetReviewFilter extends AuthorizationController {

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
        response.setContentType("text/html;charater=UTF-8");
        response.setCharacterEncoding("utf-8");
        int ID = Integer.parseInt(request.getParameter("ID"));
        int qhid = Integer.parseInt(request.getParameter("qhid"));
        int quizID = Integer.parseInt(request.getParameter("quizID"));

        ArrayList<ResultQuestion> resultQuestions = new QuizHandleDBContext().getQuestionByFilter(ID, qhid,quizID);
        String result = "";

        for (ResultQuestion r : resultQuestions) {
            String isAnswered = r.isIsAnswered() ? "answered" : "";
            String isMarked = r.isIsMarked() ? "<i class=\"fa-solid fa-lightbulb\"></i>" : "";

            result += " <div class=\"question__box\" id=\"" + isAnswered + "\""
                    + "                                <p> " + isMarked + "<a href=\"qreview?order=" + r.getOrder() + "&qhid=" + qhid + "\">" + r.getOrder() + "</a></p>\n"
                    + "                            </div>";
        }

        response.getWriter().println(result);
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
