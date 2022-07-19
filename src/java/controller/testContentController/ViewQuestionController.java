/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.testContentController;

import controller.AuthorizationController;
import dal.DimensionDBContext;
import dal.LevelDBContext;
import dal.QuestionDBContext;
import dal.TopicDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Dimension;
import model.Level;
import model.Question;
import model.Topic;

/**
 *
 * @author Hai Tran
 */
public class ViewQuestionController extends AuthorizationController {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        TopicDBContext dbTopic = new TopicDBContext();
        DimensionDBContext dbDimension = new DimensionDBContext();
        QuestionDBContext dbQuestion = new QuestionDBContext();
        LevelDBContext dbLevel = new LevelDBContext();
        int questionID = Integer.parseInt(request.getParameter("questionID"));
        Question question = dbQuestion.getQuestion(questionID);
        ArrayList<Topic> topics = dbTopic.getTopics(1);
        ArrayList<Level> levels = dbLevel.getAllLevel();
        ArrayList<Dimension> dimensions = dbDimension.getDimensionsByCourseID(1);
        request.setAttribute("topics", topics);
        request.setAttribute("dimensions", dimensions);
        request.setAttribute("question", question);
        request.setAttribute("levels", levels);
        request.getRequestDispatcher("/view/test_content/question_view.jsp").forward(request, response);
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
