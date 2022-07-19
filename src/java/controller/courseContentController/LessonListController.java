/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.courseContentController;

import controller.AuthorizationController;
import dal.CourseDBContext;
import dal.LessonDBContext;
import dal.PricePackageDBContext;
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
import model.PricePackage;
import model.Topic;

/**
 *
 * @author Zuys
 */
public class LessonListController extends AuthorizationController {
   
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
        LessonDBContext dbLesson = new LessonDBContext();
        CourseDBContext dbCourse = new CourseDBContext();
        PricePackageDBContext dbPrice = new PricePackageDBContext();
        
        int pagesize = 5;
        
        String raw_courseID = request.getParameter("courseID");
        int courseID = 0;
        if(raw_courseID != null  && raw_courseID.trim().length() != 0){
            courseID = Integer.parseInt(raw_courseID);
        }
        Course course = dbCourse.getCourse(courseID);
        ArrayList<PricePackage> pricePackages = dbPrice.getPricePackagesByCourseID(courseID);
        
        String page = request.getParameter("page");
        if (page == null || page.trim().length() == 0) {
            page = "1";
        }
        int pageindex = Integer.parseInt(page);
        
        int count = dbLesson.countLesson(courseID);
        int totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize) + 1;
        if (pageindex <= 0 || pageindex > totalpage) {
            pageindex = 1;
        }
        
        String topicID = "";
        ArrayList<Topic> topics = dbTopic.getTopics(Integer.parseInt(raw_courseID));
        for (Topic t : topics) {
            topicID += t.getTopicID() + ", ";
        }
        if(topics.isEmpty()){
            response.sendRedirect("managesubject");
        }
        topicID = topicID.substring(0, topicID.trim().length() - 1).trim();
        
        ArrayList<Lesson> lessons = dbLesson.getLessons(pageindex, pagesize, topicID);
        
        request.setAttribute("prices", pricePackages);
        request.setAttribute("page", pageindex);
        request.setAttribute("course", course);
        request.setAttribute("lessons", lessons);
        request.setAttribute("url", "lessonlist");
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("totalpage", totalpage);
        request.getRequestDispatcher("view/course_content/lesson_list.jsp").forward(request, response);
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
