/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.courseContentController;

import controller.AuthorizationController;
import dal.CourseDBContext;
import dal.LessonDBContext;
import dal.LessonTypeDBContext;
import dal.QuizDBContext;
import dal.TopicDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Course;
import model.Lesson;
import model.LessonType;
import model.Quiz;
import model.Topic;
import util.Validation;

/**
 *
 * @author Zuys
 */
public class AddLessonController extends AuthorizationController {
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
        QuizDBContext dbQuiz = new QuizDBContext();
        LessonDBContext dbLesson = new LessonDBContext();
        CourseDBContext dbCourse = new CourseDBContext();
        LessonTypeDBContext dbLType = new LessonTypeDBContext();
        TopicDBContext dbTopic = new TopicDBContext();
        
        String raw_courseID = request.getParameter("courseID");
        int courseID = 0;
        if(raw_courseID != null && raw_courseID.trim().length() != 0){
            courseID = Integer.parseInt(raw_courseID);
        }
        
        Course course = dbCourse.getCourse(courseID);
        ArrayList<Topic> topics = dbTopic.getTopics(courseID);
        ArrayList<LessonType> lessonTypes = dbLType.getLessonTypes();
        ArrayList<Quiz> quizzes = dbQuiz.getQuizzesByCourseID(courseID);
        
        request.setAttribute("lessonTypes", lessonTypes);
        request.setAttribute("topics", topics);
        request.setAttribute("course", course);
        request.setAttribute("quizzes", quizzes);
        request.getRequestDispatcher("view/course_content/add_lesson.jsp").forward(request, response);
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
        LessonDBContext dbLesson = new LessonDBContext();
        LessonTypeDBContext dbLType = new LessonTypeDBContext();
        
        String rcourseID = request.getParameter("courseID");
        String rlessonName = request.getParameter("lessonName");
        String rlessonTypeID = request.getParameter("lessonType");
        String rtopicID = request.getParameter("topic");
        String rlessonOrder = request.getParameter("lessonOrder");
        String rvideoLink = request.getParameter("videoLink");
        String rhtmlContent = request.getParameter("htmlContent");
        String rquizID = request.getParameter("quiz");

        int courseID = 0;
        int lessonTypeID = 0;
        int topicID = 0;
        int order = 0;
        int quizID = 0;

        String[] rawData = {rcourseID, rlessonName, rlessonTypeID, rtopicID, rlessonOrder};

        Validation v = new Validation();
        if (v.checkNullOrBlank(rawData)) {
            lessonTypeID = Integer.parseInt(rlessonTypeID);
            switch (lessonTypeID) {
                case 2:
                    if ((rvideoLink != null && rvideoLink.trim().length() != 0) && (rhtmlContent != null && rhtmlContent.trim().length() != 0)) {
                        courseID = Integer.parseInt(rcourseID);
                        topicID = Integer.parseInt(rtopicID);
                        order = Integer.parseInt(rlessonOrder);
                    } else {
                    }   break;
                case 3:
                    if ((rquizID != null && rquizID.trim().length() != 0) && (rhtmlContent != null && rhtmlContent.trim().length() != 0)) {
                        courseID = Integer.parseInt(rcourseID);
                        topicID = Integer.parseInt(rtopicID);
                        order = Integer.parseInt(rlessonOrder);
                        quizID = Integer.parseInt(rquizID);
                    } else {
                    }   break;
                default:
                    courseID = Integer.parseInt(rcourseID);
                    topicID = Integer.parseInt(rtopicID);
                    order = Integer.parseInt(rlessonOrder);
                    break;
            }
        } else {
        }

        Lesson l = new Lesson();
        l.setLessonType(dbLType.getLessonType(lessonTypeID));
        l.setTopicID(topicID);
        l.setLessonOrder(order);
        l.setLessonName(rlessonName);
        if(l.getLessonType().getLessonTypeID() == 2){
            l.setVideoLink(rvideoLink);
            l.setHtmlContent(rhtmlContent);
        }else if(l.getLessonType().getLessonTypeID() == 3){
            l.setHtmlContent(rhtmlContent);
            l.setQuizID(quizID);
        }
        
        if(dbLesson.addLesson(l)){
            request.getSession().setAttribute("mess", "Add lesson successfully");
            response.sendRedirect("addlesson?courseID="+courseID);
        }else{
            request.getSession().setAttribute("mess", "Add lesson failed");
            response.sendRedirect("addlesson?courseID="+courseID);
        }
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
