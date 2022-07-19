/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.customerControllers;

import controller.AuthorizationController;
import dal.CommonDBContext;
import dal.QuestionDBContext;
import dal.QuizDBContext;
import dal.QuizHandleDBContext;
import dal.QuizHistoryDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.Question;
import model.Quiz;
import model.QuizHistory;
import model.ResultQuestion;

/**
 *
 * @author Zuys
 */
public class QuizReviewController extends AuthorizationController {
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
        QuizHistoryDBContext dbQHistory = new QuizHistoryDBContext();
        int quizHistoryID = Integer.parseInt(request.getParameter("quizHistoryID"));
        QuizHistory quizHistory = dbQHistory.getQuizHistory(quizHistoryID);
        Quiz quiz = new QuizDBContext().getAQuiz(quizHistory.getQuizID().getQuizID());

        
        QuizHandleDBContext quizHandleDBContext = new QuizHandleDBContext();
        ArrayList<ResultQuestion> rquestions = quizHandleDBContext.getQuestionsFromQuizQuestion(quizHistory.getQuizID().getQuizID(), quizHistoryID);
        
        request.getSession().setAttribute("rquestions", rquestions);
        request.getSession().setAttribute("quiz", quiz);
        response.sendRedirect("qreview?order=1&qhid=" + quizHistoryID);
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
