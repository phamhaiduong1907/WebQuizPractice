/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import controller.AuthorizationController;
import dal.RoleDBContext;
import dal.UserDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import model.Feature;
import model.Role;
import model.User;

/**
 *
 * @author Hai Duong
 */
public class LoadFeatureController extends HttpServlet {

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
        String username = request.getParameter("username");
        int roleID = Integer.parseInt(request.getParameter("role"));
        RoleDBContext dbRoles = new RoleDBContext();
        UserDBContext dbUsers = new UserDBContext();
        User user = dbUsers.getUser(username);
        PrintWriter writer = response.getWriter();
        Role roleById = dbRoles.getRoleById(roleID);
//        writer.println("<h1>DIT ME TUANVM</h1>");
        if (roleID != user.getAccount().getRole().getRoleID()) {
            for (Feature feature : roleById.getFeatures()) {
                writer.println("<input type=\"checkbox\" name=\"featureID\" value=\""
                        + feature.getFeatureID() + "\" />" + feature.getFeatureName());
            }
        } else {
            for (Feature feature : roleById.getFeatures()) {
                boolean unauthorized = true;
                for (Feature f : user.getAccount().getRole().getFeatures()) {
                    if (feature.getFeatureID() == f.getFeatureID()) {
                        unauthorized = false;
                        break;
                    }
                }
                writer.println("<input type=\"checkbox\" name=\"featureID\" value=\""
                        + feature.getFeatureID() + "\" "+(unauthorized?"checked":"")+" />" + feature.getFeatureName());
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
