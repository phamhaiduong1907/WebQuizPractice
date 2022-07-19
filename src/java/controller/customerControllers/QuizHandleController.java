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
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import model.Account;
import model.Question;
import model.Quiz;
import model.ResultQuestion;

/**
 *
 * @author ADMIN
 */
public class QuizHandleController extends AuthorizationController {
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
        int quizID = Integer.parseInt(request.getParameter("quizID"));
        ArrayList<Question> questions = new QuestionDBContext().getQuestionsFromQuizQuestion(quizID);
        Quiz quiz = new QuizDBContext().getAQuiz(quizID);

        Account account = (Account) request.getSession().getAttribute("account");
        Timestamp startTime = new Timestamp(System.currentTimeMillis());
        Timestamp endTime = new Timestamp(System.currentTimeMillis());

        endTime.setTime(startTime.getTime() + (quiz.getDuration() * 60) * 1000);

        QuizHandleDBContext quizHandleDBContext = new QuizHandleDBContext();
        if (quiz.getQuizType().getQuizTypeID() == 1) {
            quizHandleDBContext.insertQuizHistory(quizID, account.getUsername(), startTime, endTime, null, questions);

        } else {
            quizHandleDBContext.insertQuizHistory(quizID, account.getUsername(), startTime, null, null, questions);

        }

        ArrayList<ResultQuestion> rquestions = quizHandleDBContext.getQuestionsFromQuizQuestion(quizID, new CommonDBContext().getIdentity("QuizHistory"));

        request.getSession().setAttribute("rquestions", rquestions);
        request.getSession().setAttribute("quiz", quiz);
        response.sendRedirect("qhandle?order=1&qhid=" + new CommonDBContext().getIdentity("QuizHistory"));

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
