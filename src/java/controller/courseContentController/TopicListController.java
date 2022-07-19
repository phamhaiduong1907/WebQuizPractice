/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.courseContentController;

import controller.AuthorizationController;
import dal.CourseDBContext;
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
import model.PricePackage;
import model.Topic;

/**
 *
 * @author ADMIN
 */
public class TopicListController extends AuthorizationController {
    final static private String TOPICLISTURL = "../../view/course_content/topic_list.jsp";
    final static private int PAGESIZE = 5;

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
        String queryString = request.getQueryString();
        
        TopicDBContext topicDBContext = new TopicDBContext();
        int courseID = Integer.parseInt(request.getParameter("id"));

        String page = request.getParameter("page");
        if (page == null || page.trim().length() == 0) {
            page = "1";
        }
    int pageindex = Integer.parseInt(page);
        int count = topicDBContext.getCountTopicForPagination(courseID);
        int totalpage = (count % PAGESIZE == 0) ? (count / PAGESIZE) : (count / PAGESIZE) + 1;
        if (pageindex <= 0 || pageindex > totalpage) {
            pageindex = 1;
        }

        CourseDBContext courseDBContext = new CourseDBContext();
        Course course = courseDBContext.getCourse(courseID);
        ArrayList<Topic> topics = topicDBContext.getTopicByPagination(courseID, pageindex, PAGESIZE);

        request.setAttribute("topics", topics);
        request.setAttribute("course", course);
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("queryString", queryString);
        
        request.getRequestDispatcher(TOPICLISTURL).forward(request, response);
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
