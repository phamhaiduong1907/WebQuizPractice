/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.testContentController;

import dal.CategoryDBContext;
import dal.CourseDBContext;
import dal.QuizAttributeDBContext;
import dal.QuizDBContext;
import dal.SubCategoryDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Course;
import model.Subcategory;

/**
 *
 * @author long
 */
public class renderCourse extends HttpServlet {

    QuizAttributeDBContext qabdc = new QuizAttributeDBContext();
    CourseDBContext cdbc = new CourseDBContext();
    QuizDBContext qdbc = new QuizDBContext();
    QuizAttributeDBContext qadbc = new QuizAttributeDBContext();
    CategoryDBContext cadbc = new CategoryDBContext();
    SubCategoryDBContext scdbc = new SubCategoryDBContext();

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
            out.println("<title>Servlet renderCourse</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet renderCourse at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
    //url = /rendercourse
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charater=UTF-8");
        response.setCharacterEncoding("utf-8");
        int SubCategoryID = Integer.parseInt(request.getParameter("ID"));
        ArrayList<Course> courses = cdbc.getCoursesBySubcategory(SubCategoryID);
        String result = "<select class = 'form-control' id='' name = 'subcateID'>";
        for (Course s : courses) {
            result += "<option value = " + s.getCourseID() + ">" + s.getCourseName() + " </option>";
        }
        result += "</select>";

        response.getWriter().println(result);
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
