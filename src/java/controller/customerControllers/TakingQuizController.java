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
import java.sql.Timestamp;
import java.util.ArrayList;
import model.Question;
import model.Quiz;
import model.QuizHistory;
import model.ResultQuestion;

/**
 *
 * @author ADMIN
 */
public class TakingQuizController extends AuthorizationController {
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
        int qhid = Integer.parseInt(request.getParameter("qhid"));
        QuizHistory quizHistory = new QuizHistoryDBContext().getQuizHistory(qhid);

        if (quizHistory.getFinishTime() != null) {
            response.sendRedirect("score?qhid=" + qhid);
        } else {

            Quiz quiz = quizHistory.getQuizID();
            QuizHandleDBContext quizHandleDBContext = new QuizHandleDBContext();

            ArrayList<ResultQuestion> rquestions = quizHandleDBContext.getQuestionsFromQuizQuestion(quiz.getQuizID(), new CommonDBContext().getIdentity("QuizHistory"));

            Timestamp endTime = quizHistory.getEndTime();

            if (quiz.getQuizType().getQuizTypeID() == 2) {
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                long diff = (currentTime.getTime() - quizHistory.getStartTime().getTime()) / 1000;
                request.setAttribute("diff", diff);
            }
            int totalAnswered = quizHandleDBContext.getResultQuestionByIsAnswered(true, qhid);
            
            request.setAttribute("quiz", quiz);
            request.setAttribute("totalAnswered", totalAnswered);
            request.setAttribute("endTime", endTime);
            request.getSession().setAttribute("rquestions", rquestions);

            request.getRequestDispatcher("view/customer/quizhandle.jsp").forward(request, response);
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
        Boolean isMarked = null;
        if (request.getParameter("isMarked") != null) {
            isMarked = request.getParameter("isMarked").equals("true");
        }
        String[] raw_answerids = request.getParameterValues("answer");
        int questionID = Integer.parseInt(request.getParameter("questionID"));
        ArrayList<Integer> answerIDs = new ArrayList<>();
        if (raw_answerids != null) {
            for (String raw_answerid : raw_answerids) {
                answerIDs.add(Integer.parseInt(raw_answerid));
            }
        }

        int qhid = Integer.parseInt(request.getParameter("qhid"));
        QuizHandleDBContext quizHandleDBContext = new QuizHandleDBContext();

        quizHandleDBContext.insertAnswers(answerIDs, qhid, questionID, isMarked);

        boolean isPrev = request.getParameter("prev").equals("true");
        if (isPrev) {
            response.sendRedirect(request.getParameter("prevLink"));
        } else {
            response.sendRedirect(request.getParameter("nextLink"));
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
