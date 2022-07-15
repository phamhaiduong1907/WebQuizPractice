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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import model.Role;
import model.User;

/**
 *
 * @author Hai Duong
 */
public class UserListController extends AuthorizationController {

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

        UserDBContext dbUsers = new UserDBContext();
        RoleDBContext dbRoles = new RoleDBContext();
        ArrayList<Role> roles = dbRoles.getRoles();
        ArrayList<User> users;
        String role = request.getParameter("role");
        String statusParam = request.getParameter("status");
        String genderParam = request.getParameter("gender");
        String combination = request.getParameter("combination");
        int roleID = -1;
        Boolean status, gender;

        if (role == null && statusParam == null && genderParam == null && combination == null) {
            users = dbUsers.getUsers(-1, null, null, "");
        } else {
            if(role == null)
                role = "-1";
            roleID = Integer.parseInt(role);
            
            if(statusParam == null || statusParam.equals("all")){
                status = null;
            }else{
                status = statusParam.equalsIgnoreCase("active");
            }
            
            if(genderParam == null || genderParam.equals("all")){
                gender = null;
            }else{
                gender = genderParam.equalsIgnoreCase("male");
            }
            
            if(combination != null || combination.trim().length() != 0){
                combination = combination.trim();
            }
            
            users = dbUsers.getUsers(roleID, status, gender, combination);
        }
        
        request.setAttribute("role", roleID);
        request.setAttribute("status", statusParam);
        request.setAttribute("gender", genderParam);
        request.setAttribute("combination", combination);
        
        request.setAttribute("roles", roles);
        request.setAttribute("users", users);
        request.getRequestDispatcher("../view/admin/user_list.jsp").forward(request, response);
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
