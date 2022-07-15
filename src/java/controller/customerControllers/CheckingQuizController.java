/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customerControllers;

import dal.AnswerDBContext;
import dal.CommonDBContext;
import dal.QuizHandleDBContext;
import dal.QuizHistoryDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Answer;
import model.Quiz;
import model.QuizHistory;
import model.ResultQuestion;

/**
 *
 * @author Zuys
 */
public class CheckingQuizController extends HttpServlet {

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
        int qhid = Integer.parseInt(request.getParameter("qhid"));
        QuizHistory quizHistory = new QuizHistoryDBContext().getQuizHistory(qhid);
        Quiz quiz = quizHistory.getQuizID();
        QuizHandleDBContext quizHandleDBContext = new QuizHandleDBContext();
        AnswerDBContext dbAnswer = new AnswerDBContext();
        ArrayList<Answer> correctAnswer = dbAnswer.getCorrectAnswer();

        ArrayList<ResultQuestion> rquestions = quizHandleDBContext.getQuestionsFromQuizQuestion(quiz.getQuizID(), qhid);
        request.setAttribute("quiz", quiz);
        request.getSession().setAttribute("rquestions", rquestions);
        request.getSession().setAttribute("correctAnswer", correctAnswer);

        request.getRequestDispatcher("view/customer/quizreview.jsp").forward(request, response);
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
