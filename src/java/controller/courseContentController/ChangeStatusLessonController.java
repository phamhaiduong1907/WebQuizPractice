/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.courseContentController;

import controller.AuthorizationController;
import dal.CourseDBContext;
import dal.LessonDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Course;
import model.Lesson;

/**
 *
 * @author Zuys
 */
public class ChangeStatusLessonController extends AuthorizationController {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        LessonDBContext dbLesson = new LessonDBContext();
        
        String raw_page = request.getParameter("page");
        int page = 1;
        if(raw_page != null && raw_page.trim().length() != 0){
            page = Integer.parseInt(raw_page);
        }
        
        String raw_courseID = request.getParameter("courseID");
        int courseID = 0;
        if(raw_courseID != null && raw_courseID.trim().length() != 0){
            courseID = Integer.parseInt(raw_courseID);
        }
        
        String raw_lessonID = request.getParameter("lessonID");
        int lessonID = 0;
        if(raw_lessonID != null && raw_lessonID.trim().length() != 0){
            lessonID = Integer.parseInt(raw_lessonID);
        }
        
        Lesson lesson = dbLesson.getLesson(lessonID);
        if (dbLesson.changeStatus(lesson) && courseID != 0 && lessonID != 0) {
            if(page == 1){
                response.sendRedirect("lessonlist?courseID="+courseID);
            }else{
                response.sendRedirect("lessonlist?courseID="+courseID+"&page="+page);
            }
            
        }
        
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
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
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
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
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
