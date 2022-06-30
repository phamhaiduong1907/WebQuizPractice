/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.test;

import dal.QuestionDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Dimension;
import model.Lesson;

/**
 *
 * @author Hai Duong
 */
public class LoadAttributesController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        QuestionDBContext dbQuestions = new QuestionDBContext();
        int subjectID = Integer.parseInt(request.getParameter("subjectID"));
        ArrayList<Dimension> dimensions = dbQuestions.getDimensionsBySubjectID(subjectID);
        ArrayList<Lesson> lessons = dbQuestions.getLessonsBySubjectID(subjectID);
        String dimensionOptions = "";
        for (Dimension dimension : dimensions) {
            dimensionOptions += "<option value="+dimension.getDimensionID()+">"+dimension.getDimensionName()+"</option>\n";
        }
        String lessonOptions = "";
        for (Lesson lesson : lessons) {
            lessonOptions += "<option value="+lesson.getLessonID()+">"+lesson.getLessonName()+"</option>\n";
        }
        out.print("<div class=\"search__item\">\n" +
"                                        <label for=\"lessonSearch\">Lesson</label>\n" +
"                                        <select name=\"lesson\" id=\"lessonSearch\">\n" +
"                                            <option value=\"-1\">All Lessons</option>\n" + lessonOptions  +
"                                        </select>\n" +
"                                    </div>\n" +
"                                    <div class=\"search__item\">\n" +
"                                        <label for=\"dimensionSearch\">Dimension</label>\n" +
"                                        <select name=\"dimension\" id=\"dimensionSearch\">\n" +
"                                            <option value=\"-1\">All Dimensions</option>\n" + dimensionOptions +
"                                        </select>\n" +
"                                    </div>");
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
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
