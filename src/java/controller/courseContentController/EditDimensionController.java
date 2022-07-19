/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.courseContentController;

import controller.AuthorizationController;
import dal.CourseDBContext;
import dal.DimensionDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import model.Account;
import model.Course;
import model.Dimension;
import model.DimensionType;
import model.ErrorMessage;
import util.Validation;

/**
 *
 * @author ADMIN
 */
public class EditDimensionController extends AuthorizationController {

    private static final String EDITDIMENSIONURL = "../../view/course_content/dimension_edit.jsp";

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
        String url = request.getHeader("referer");

        int courseID = Integer.parseInt(request.getParameter("cid"));
        int dimensionID = Integer.parseInt(request.getParameter("did"));

        CourseDBContext courseDBContext = new CourseDBContext();
        Course course = courseDBContext.getCourse(courseID);
        DimensionDBContext dimensionDBContext = new DimensionDBContext();
        Account account = (Account) request.getSession().getAttribute("account");
        if (courseDBContext.authEdit(courseID, account.getUsername()) || account.getRole().getRoleID() == 1) {
            Dimension dimension = dimensionDBContext.getDimensionByDimensionID(dimensionID);
            ArrayList<DimensionType> dimensionTypes = dimensionDBContext.getDimensionTypes();

            request.setAttribute("course", course);
            request.setAttribute("dimension", dimension);
            request.setAttribute("dimensionTypes", dimensionTypes);

            request.getRequestDispatcher(EDITDIMENSIONURL).forward(request, response);
        } else {
            request.getSession().setAttribute("errormessage", ErrorMessage.AUTH_EDIT_COURSE);
            response.sendRedirect(url);
        }

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
        String url = request.getHeader("referer");
        String dimensionName = request.getParameter("dimensionName");
        String dimensionDescription = request.getParameter("dimensionDescription");
        String raw_typeID = request.getParameter("typeID");
        int courseID = Integer.parseInt(request.getParameter("courseID"));
        int typeID = -1;
        int dimensionID = Integer.parseInt(request.getParameter("dimensionID"));
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList(raw_typeID, dimensionDescription, dimensionName));

        Validation validation = new Validation();
        if (validation.checkNullOrBlank(inputs)) {
            typeID = Integer.parseInt(raw_typeID);
            DimensionDBContext dimensionDBContext = new DimensionDBContext();
            if (!dimensionDBContext.checkEdit(dimensionID, dimensionName, courseID)) {
                if (dimensionDBContext.updateDimension(typeID, dimensionName, dimensionDescription, dimensionID)) {
                    request.getSession().setAttribute("message", ErrorMessage.UPDATESUCESSFULLY);
                    response.sendRedirect(url);
                } else {
                    request.getSession().setAttribute("message", ErrorMessage.ERRORSQL);
                    response.sendRedirect(url);

                }
            } else {
                request.getSession().setAttribute("message", ErrorMessage.DUPLICATE_DIMENSION);
                response.sendRedirect(url);
            }

        } else {
            request.getSession().setAttribute("message", ErrorMessage.MISSINGINPUT);
            response.sendRedirect(url);

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
