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
public class LessonSearchController extends AuthorizationController {

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
        TopicDBContext dbTopic = new TopicDBContext();
        LessonDBContext dbLesson = new LessonDBContext();
        CourseDBContext dbCourse = new CourseDBContext();
        PricePackageDBContext dbPrice = new PricePackageDBContext();

        String raw_pricePackage = request.getParameter("price_package");
        String raw_lessonType = request.getParameter("lesson_type");
        String raw_lessonStatus = request.getParameter("lesson_status");
        String raw_lessonName = request.getParameter("lesson_name");

        int pricePackage = 0;
        int lessonType = 0;
        String lessonStatus = "All";
        String lessonName = "";

        if (raw_pricePackage != null && raw_pricePackage.trim().length() != 0) {
            pricePackage = Integer.parseInt(raw_pricePackage);
        }
        if (raw_lessonType != null && raw_lessonType.trim().length() != 0) {
            lessonType = Integer.parseInt(raw_lessonType);
        }
        if (raw_lessonStatus != null && raw_lessonStatus.trim().length() != 0) {
            lessonStatus = raw_lessonStatus;
        }
        if (raw_lessonName != null && raw_lessonName.trim().length() != 0) {
            lessonName = raw_lessonName;
        }

        String raw_courseID = request.getParameter("courseID");
        int courseID = 0;
        if (raw_courseID != null && raw_courseID.trim().length() != 0) {
            courseID = Integer.parseInt(raw_courseID);
        }
        Course course = dbCourse.getCourse(courseID);

        String topicID = "";
        ArrayList<Topic> topics = dbTopic.getTopics(courseID);
        for (Topic t : topics) {
            topicID += t.getTopicID() + ", ";
        }
        topicID = topicID.substring(0, topicID.trim().length() - 1).trim();

        ArrayList<PricePackage> pricePackages = dbPrice.getPricePackagesByCourseID(courseID);

        int pagesize = 5;
        String page = request.getParameter("page");
        if (page == null || page.trim().length() == 0) {
            page = "1";
        }
        int pageindex = Integer.parseInt(page);
        int count = 0;
        ArrayList<Lesson> countLessons = dbLesson.countSearchLesson(topicID);
        for (Lesson l : countLessons) {
            if ((l.getLessonType().getLessonTypeID() == lessonType || lessonType == 0)
                    && (l.isStatus() == lessonStatus.matches("1") || lessonStatus.matches("All"))
                    && (l.getLessonName().matches(lessonName) || lessonName.trim().length() == 0)) {
                if (pricePackage == 0) {
                    count++;
                } else {
                    for (PricePackage p : l.getPricePackages()) {
                        if (p.getPricePackageID() == pricePackage) {
                            count++;
                        }
                    }
                }
            }
        }
        int totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize) + 1;
        if (pageindex <= 0 || pageindex > totalpage) {
            pageindex = 1;
        }

        ArrayList<Lesson> lessons = dbLesson.getSearchLessons(pageindex, pagesize, topicID, pricePackage, lessonType, lessonStatus, lessonName);

        request.setAttribute("prices", pricePackages);
        request.setAttribute("page", pageindex);
        request.setAttribute("course", course);
        request.setAttribute("lessons", lessons);
        request.setAttribute("url", "searchlesson");
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("totalpage", totalpage);
        request.getRequestDispatcher("view/course_content/lesson_list.jsp").forward(request, response);
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
