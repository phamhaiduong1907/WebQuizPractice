/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customerControllers;

import controller.AuthorizationController;
import dal.QuizDBContext;
import dal.QuizHandleDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import model.Quiz;
import model.QuizHistory;

/**
 *
 * @author ADMIN
 */
public class ScoreQuizController extends AuthorizationController {

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
        int qhid = Integer.parseInt(request.getParameter("qhid"));

        QuizHandleDBContext quizHandleDBContext = new QuizHandleDBContext();
        QuizHistory qh = new QuizHandleDBContext().getQuizHistoryByID(qhid);
        Quiz quiz = new QuizDBContext().getAQuiz(qh.getQuizID().getQuizID());

        long diff = qh.getFinishTime().getTime() - qh.getStartTime().getTime();

        long minutes = (diff / 1000) / 60;

        // formula for conversion for
        // milliseconds to seconds
        long seconds = (diff / 1000) % 60;

        int rightQuestion = quiz.getNumOfQuestion() - quizHandleDBContext.getWrongQuestion(qhid, quiz.getQuizID());

        request.setAttribute("qh", qh);
        request.setAttribute("quiz", quiz);
        request.setAttribute("minutes", minutes);
        request.setAttribute("seconds", seconds);
        request.setAttribute("rightQuestion", rightQuestion);
        request.getRequestDispatcher("view/customer/score.jsp").forward(request, response);

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
        int qhid = Integer.parseInt(request.getParameter("qhid"));
        Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");

        Timestamp finishTime = new Timestamp(System.currentTimeMillis());

        QuizHandleDBContext quizHandleDBContext = new QuizHandleDBContext();

        quizHandleDBContext.scoreExam(finishTime, qhid, quiz);
        int rightQuestion = quiz.getNumOfQuestion() - quizHandleDBContext.getWrongQuestion(qhid, quiz.getQuizID());
        request.getSession().setAttribute("rightQuestion", rightQuestion);
        response.sendRedirect("score?qhid=" + qhid);

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
