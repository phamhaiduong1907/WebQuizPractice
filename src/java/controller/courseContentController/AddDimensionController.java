/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.courseContentController;

import dal.CourseDBContext;
import dal.DimensionDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import model.Course;
import model.DimensionType;
import model.ErrorMessage;
import util.Validation;

/**
 *
 * @author ADMIN
 */
public class AddDimensionController extends HttpServlet {

    private static final String ADDDIMENSIONURL = "../../view/course_content/dimension_add.jsp";

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int courseID = Integer.parseInt(request.getParameter("id"));
        CourseDBContext courseDBContext = new CourseDBContext();
        Course course = courseDBContext.getCourse(courseID);

        DimensionDBContext dimensionDBContext = new DimensionDBContext();
        ArrayList<DimensionType> dimensionTypes = dimensionDBContext.getDimensionTypes();

        request.setAttribute("course", course);
        request.setAttribute("dimensionTypes", dimensionTypes);

        request.getRequestDispatcher(ADDDIMENSIONURL).forward(request, response);

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
        String url = request.getHeader("referer");
        int courseID = Integer.parseInt(request.getParameter("id"));

        String raw_dimensionName = request.getParameter("dimensionName");
        String raw_dimensionDescription = request.getParameter("dimensionDescription");
        String raw_typeID = request.getParameter("typeID");
        ErrorMessage errorMessage = new ErrorMessage();
        Validation validation = new Validation();
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList(raw_dimensionName, raw_dimensionDescription, raw_typeID));
        int typeID = -1;

        if (validation.checkNullOrBlank(inputs)) {
            typeID = Integer.parseInt(raw_typeID);
            DimensionDBContext dimensionDBContext = new DimensionDBContext();
            if (dimensionDBContext.insertDimension(typeID, raw_dimensionName, raw_dimensionDescription, courseID)) {
                response.sendRedirect("dimension?id=" + courseID);
            }
        } else {
            response.sendRedirect(url + "&errorMessage=" + errorMessage.MISSINGINPUT);
        }

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
