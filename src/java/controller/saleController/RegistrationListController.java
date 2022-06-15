/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.saleController;

import dal.CourseDBContext;
import dal.RegistrationDBContext;
import dal.UserDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Course;
import model.Registration;
import model.User;

/**
 *
 * @author long
 */
public class RegistrationListController extends HttpServlet {

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
        RegistrationDBContext rdbc = new RegistrationDBContext();

        int pageIndex;
        if (request.getParameter("page") == null) {
            pageIndex = 1;
        } else {
            pageIndex = Integer.parseInt(request.getParameter("page"));
        }
        String sortBy;
        if (request.getParameter("sortBy") == null) {
            sortBy = "registrationID";
        } else {
            sortBy = request.getParameter("sortBy");
        }
        String orderBy;
        if (request.getParameter("orderBy") == null) {
            orderBy = "asc";
        } else {
            orderBy = request.getParameter("orderBy");
        }
        ArrayList<Registration> list = rdbc.getRegistrations(pageIndex, 10, sortBy, orderBy);
        int totalPage = rdbc.countAll() / 10;
        if (rdbc.countAll() % 10 != 0) {
            totalPage += 1;
        }
        CourseDBContext cdbc = new CourseDBContext();
        ArrayList<Course> courses = cdbc.getCourses();
        UserDBContext udbc = new UserDBContext();
        ArrayList<User> users = udbc.getUsers();
        log(sortBy + ", " + orderBy);
        log("" + list.size());
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("orderBy", orderBy);
        request.setAttribute("courses", courses);
        request.setAttribute("users", users);
        request.setAttribute("list", list);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("target", "list");
        request.getRequestDispatcher("../view/sale/registration_list.jsp").forward(request, response);
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
