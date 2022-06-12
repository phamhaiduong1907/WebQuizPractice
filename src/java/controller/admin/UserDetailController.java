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
public class UserDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        boolean gender = request.getParameter("gender").equalsIgnoreCase("male");
        String address = request.getParameter("address");
        int roleID = Integer.parseInt(request.getParameter("roleID"));
        String[] featureIDs = request.getParameterValues("featureID");
        boolean status = request.getParameter("status").equalsIgnoreCase("active");
        
        UserDBContext db = new UserDBContext();
        db.updateUser(email, firstName, lastName, featureIDs, phone, gender, address, status, roleID);
        response.sendRedirect("userdetail?username="+email);
//        response.getWriter().println(featureIDs == null);
    }
}
