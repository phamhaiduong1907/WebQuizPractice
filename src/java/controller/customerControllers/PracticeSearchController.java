/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customerControllers;

import controller.AuthorizationController;
import dal.CourseDBContext;
import dal.UserQuizDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Course;
import model.TakenUserQuiz;

/**
 *
 * @author long
 */
public class PracticeSearchController extends AuthorizationController {

    public UserQuizDBContext tdbc = new UserQuizDBContext();
    public CourseDBContext cdbc = new CourseDBContext();

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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PracticeSearchController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PracticeSearchController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int index;
        if (request.getParameter("page") != null) {
            index = Integer.parseInt(request.getParameter("page"));
        } else {
            index = 1;
        }

        //String username = a.getUsername();
        String username = "hunglnhe161003@fpt.edu.vn";
        ArrayList<Course> courses = cdbc.getUserCourse(username);
        int subjectID = Integer.parseInt(request.getParameter("subjectID"));
        ArrayList<TakenUserQuiz> list = new ArrayList<>();
        if (subjectID == 0) {
            list = tdbc.getListFromUser(username);
        } else {
            list = tdbc.searchTaken(username, index, subjectID);
        }
        request.setAttribute("subject", subjectID);
        request.setAttribute("list", list);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("view/customer/practicelist.jsp").forward(request, response);
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
