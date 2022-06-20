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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import model.Role;
import model.User;

/**
 *
 * @author Hai Duong
 */
public class UserListController extends HttpServlet {

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
        String sortBy = request.getParameter("sortBy");
        String order = request.getParameter("order");
        String page = request.getParameter("page");
        if (page == null || page.trim().length() == 0) {
            page = "1";
        }
        int pageindex = Integer.parseInt(page);
        int pagesize = Integer.parseInt(getServletContext().getInitParameter("pagesize"));
        String url = request.getRequestURI();
        int count;
        int totalpage;
        int roleID = -1;
        Boolean status, gender;
        String queryString = "";

        if (role == null && statusParam == null && genderParam == null && combination == null && sortBy == null&& order == null) {
//            users = dbUsers.get
            users = dbUsers.getUsers(-1, null, null, "", "firstName", "asc", pagesize, pageindex);
            count = dbUsers.count();
            totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize + 1);
        } else {
            if(role == null)
                role = "-1";
            roleID = Integer.parseInt(role);
            if(roleID != -1){
                queryString += "&role="+role;
            }
            
            if(statusParam == null || statusParam.equals("all")){
                status = null;
            }else{
                status = statusParam.equalsIgnoreCase("active");
                queryString += "&status="+statusParam;
            }
            
            if(genderParam == null || genderParam.equals("all")){
                gender = null;
            }else{
                gender = genderParam.equalsIgnoreCase("male");
                queryString += "&gender="+genderParam;
            }
            
            if(sortBy == null){
                sortBy = "firstName";
                queryString += "&sortBy="+sortBy;
            } else {
                queryString += "&sortBy="+sortBy;
            }

            if (order == null) {
                order = "asc";
                queryString += "&order="+order;
            }else{
                queryString += "&order="+order;
            }
            
            if(combination != null || combination.trim().length() != 0){
                combination = combination.trim();
                queryString += "&combination="+ URLEncoder.encode(combination.trim(), StandardCharsets.UTF_8.toString());
            }
            
            users = dbUsers.getUsers(roleID, status, gender, combination, sortBy, order, pagesize, pageindex);
            count = dbUsers.count(roleID, status, gender, combination, sortBy, order);
            totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize + 1);
        }
        
        request.setAttribute("role", roleID);
        request.setAttribute("status", statusParam);
        request.setAttribute("gender", genderParam);
        request.setAttribute("combination", combination);
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("order", order);
        
        request.setAttribute("roles", roles);
        request.setAttribute("users", users);
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("queryString", queryString);
        request.setAttribute("url", url);
        request.getRequestDispatcher("../view/admin/user_list.jsp").forward(request, response);
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
