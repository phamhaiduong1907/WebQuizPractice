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
import java.util.ArrayList;
import model.Role;
import model.User;

/**
 *
 * @author Hai Duong
 */
public class UserDetailController extends AuthorizationController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        UserDBContext dbUsers = new UserDBContext();
        User user = dbUsers.getUser(username);
        RoleDBContext dbRoles = new RoleDBContext();
        ArrayList<Role> roles = dbRoles.getRoles();
        Role roleById = dbRoles.getRoleById(user.getAccount().getRole().getRoleID());
        request.setAttribute("roleById", roleById);
        request.setAttribute("roles", roles);
        request.setAttribute("user", user);
        request.getRequestDispatcher("../view/admin/user_detail.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("email");
        int roleID = Integer.parseInt(request.getParameter("roleID"));
        boolean status = request.getParameter("status").equalsIgnoreCase("active");
        String confirmMessage = "Edit successfully!";
        
        UserDBContext db = new UserDBContext();
        db.updateUser(status, roleID, username);
        response.sendRedirect("userdetail?username="+username+"&confirmMessage="+confirmMessage);
    }
}
