/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.test;

import dal.CourseDBContext;
import dal.QuestionDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Course;
import model.Dimension;
import model.Lesson;
import model.Level;
import model.Question;

/**
 *
 * @author Hai Duong
 */
public class QuestionListController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CourseDBContext dbCourse = new CourseDBContext();
        QuestionDBContext dbQuestion = new QuestionDBContext();
        String content = request.getParameter("content");
        String subject = request.getParameter("subject");
        String lesson = request.getParameter("lesson");
        String dimension = request.getParameter("dimension");
        String level = request.getParameter("level");
        String statusParam = request.getParameter("status");
        int subjectID, lessonID, dimensionID, levelID;
        Boolean status = null;
        subjectID = lessonID = levelID = dimensionID = -1;
        ArrayList<Question> questions = null;
        if (content == null && subject == null && lesson == null && dimension == null && level == null && statusParam == null) {
            questions = dbQuestion.getAllQuestionsForList();
        } else {

            if (subject != null) {
                subjectID = Integer.parseInt(subject);
            }
            if (lesson != null) {
                lessonID = Integer.parseInt(lesson);
            }
            if (dimension != null) {
                dimensionID = Integer.parseInt(dimension);
            }
            if (level != null) {
                levelID = Integer.parseInt(level);
            }
            if (statusParam != null) {
                if (statusParam.equals("all")) {
                    status = null;
                } else {
                    status = statusParam.equalsIgnoreCase("active");
                }
            }
            
            if(content != null)
                content = content.trim().toLowerCase();
            questions =  dbQuestion.searchQuestionsByAttributesForList(subjectID, dimensionID, lessonID, status, levelID, content);
            
        }
        ArrayList<Course> courses = dbCourse.getCourseNameAndID();
        ArrayList<Level> levels = dbQuestion.getLevels();
        request.setAttribute("subjectID", subjectID);
        if(subjectID != -1){
            ArrayList<Dimension> dimensions = dbQuestion.getDimensionsBySubjectID(subjectID);
            ArrayList<Lesson> lessons = dbQuestion.getLessonsBySubjectID(subjectID);
            request.setAttribute("dimensions", dimensions);
            request.setAttribute("lessons", lessons);
        }
        request.setAttribute("dimensionID", dimensionID);
        request.setAttribute("lessonID", lessonID);
        request.setAttribute("levelID", levelID);
        request.setAttribute("status", statusParam);
        request.setAttribute("content", content);
        
        request.setAttribute("courses", courses);
        request.setAttribute("questions", questions);
        request.setAttribute("levels", levels);
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("../view/test_content/question_list.jsp").forward(request, response);
    }

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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
