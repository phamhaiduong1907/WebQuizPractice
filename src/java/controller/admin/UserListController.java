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
import java.util.ArrayList;
import model.Role;
import model.User;
import util.Validation;

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
//        UserDBContext dbUsers = new UserDBContext();
//        RoleDBContext dbRoles = new RoleDBContext();

        // get default pagesize which is initialized as a context parameter
//        int pagesize = Integer.parseInt(getServletContext().getInitParameter("pagesize"));
        // ==================================================================== 
        // =================== GET PARAMETER FROM REQUEST =====================
//        String roleParam = request.getParameter("role");
//        String statusParam = request.getParameter("status");
//        String genderParam = request.getParameter("gender");
//        String query = request.getParameter("query");
        // ====================================================================
        // ========================= END GET PARAMETER ========================
        // ====================================================================
        // ====================== REQUIRED ATTRIBUTES =========================
//        ArrayList<Role> roles = dbRoles.getRoles();
//        String url = request.getRequestURI();
//        String queryString = "";
//        int count, totalpage;
//        ArrayList<User> users;
//        int pageindex;

        /*
        * get pageindex
        * no pageindex => auto show page 1 of list
        * on the contrary => show specified page of list
         */
//        String page = request.getParameter("page");
//        if (page == null || page.trim().length() == 0) {
//            page = "1";
//        }
        // ====================================================================
        // ====================== REQUIRED ATTRIBUTES =========================
        // ====================================================================
        // ============================ LOGIC =================================
//        if (roleParam == null && statusParam == null
//                && genderParam == null && query == null) {
//            count = dbUsers.count();
//            totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize + 1);
//            pageindex = Integer.parseInt(page);
//            if (pageindex < 1) {
//                pageindex = 1;
//            }
//            if (pageindex > totalpage) {
//                pageindex = totalpage;
//            }
//            users = dbUsers.getPaginatedUsers(pageindex, pagesize);
//        } else if (statusParam != null) {
//            if (statusParam.equalsIgnoreCase("all")) {
//                count = dbUsers.count();
//                totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize + 1);
//                pageindex = Integer.parseInt(page);
//                if (pageindex < 1) {
//                    pageindex = 1;
//                }
//                if (pageindex > totalpage) {
//                    pageindex = totalpage;
//                }
//                users = dbUsers.getPaginatedUsers(pageindex, pagesize);
//            } else {
//                response.getWriter().println("This else condition is reached");
//                boolean status = statusParam.equalsIgnoreCase("active");
//                response.getWriter().println(status);
//                count = dbUsers.countByStatus(status);
//                totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize + 1);
//                pageindex = Integer.parseInt(page);
//                if (pageindex < 1) {
//                    pageindex = 1;
//                }
//                if (pageindex > totalpage) {
//                    pageindex = totalpage;
//                }
//                queryString += "&status="+statusParam;
//                users = dbUsers.getPaginatedUsersByStatus(pageindex, pagesize, status);
//                request.setAttribute("status", statusParam);
//            }
//        } else if (genderParam != null) {
//            if (genderParam.equalsIgnoreCase("all")) {
//                count = dbUsers.count();
//                totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize + 1);
//                pageindex = Integer.parseInt(page);
//                if (pageindex < 1) {
//                    pageindex = 1;
//                }
//                if (pageindex > totalpage) {
//                    pageindex = totalpage;
//                }
//                users = dbUsers.getPaginatedUsers(pageindex, pagesize);
//            } else {
//                boolean gender = genderParam.equalsIgnoreCase("male");
//                count = dbUsers.countByGender(gender);
//                response.getWriter().println(gender);
//                totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize + 1);
//                pageindex = Integer.parseInt(page);
//                if (pageindex < 1) {
//                    pageindex = 1;
//                }
//                if (pageindex > totalpage) {
//                    pageindex = totalpage;
//                }
//                queryString = "&gender="+genderParam;
//                users = dbUsers.getPaginatedUsersByGender(pageindex, pagesize, gender);
//                request.setAttribute("gender", genderParam);
//            }
//        } else {
//            if(query.trim().length() == 0){
//                count = dbUsers.count();
//                totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize + 1);
//                pageindex = Integer.parseInt(page);
//                if (pageindex < 1) {
//                    pageindex = 1;
//                }
//                if (pageindex > totalpage) {
//                    pageindex = totalpage;
//                }
//                users = dbUsers.getPaginatedUsers(pageindex, pagesize);
//            } else {
//                response.getWriter().println("Query as Parameter: "+query);
//                String[] queryParts = query.split("\\s+");
//                String querySearch = "";
//                for (String queryPart : queryParts) {
//                    querySearch += queryPart + " ";
//                }
//                response.getWriter().println("Query to search in db: "+querySearch);
//                count = dbUsers.countByQuery(querySearch.trim());
//                totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize + 1);
//                pageindex = Integer.parseInt(page);
//                if (pageindex < 1) {
//                    pageindex = 1;
//                }
//                if (pageindex > totalpage) {
//                    pageindex = totalpage;
//                }
//                String queryParamUrl = "";
//                for(int i = 0; i < queryParts.length; i++){
//                    queryParamUrl += queryParts[i];
//                    if(i < queryParts.length - 1)
//                        queryParamUrl += "+";
//                }
//                response.getWriter().println("Query Parameter on Url: "+queryParamUrl);
//                queryString += "&query="+queryParamUrl;
//                users = dbUsers.getPaginatedUsersByQuery(pageindex, pagesize, query);
//                request.setAttribute("query", query);
//            }
//        }
        // ====================================================================
        // ============================ LOGIC =================================
        // ====================================================================
        // ======================== SET ATTRIBUTES ============================
//        request.setAttribute("roles", roles);
//        request.setAttribute("url", url);
//        request.setAttribute("count", count);
//        request.setAttribute("pageindex", pageindex);
//        request.setAttribute("totalpage", totalpage);
//        request.setAttribute("users", users);
//        request.setAttribute("queryString", queryString);
        // ====================================================================
        // ======================== SET ATTRIBUTES ============================
        // ====================================================================
        // =================== FORWARD REQUEST RESOURCES ======================
//        response.getWriter().println("total records: "+count);
//        response.getWriter().println("total page: "+totalpage);
//        response.getWriter().println("query parameter: "+query);
//        response.getWriter().println("pagesize: "+pagesize);
//        response.getWriter().println("page: "+page);
//        response.getWriter().println("pageindex: "+pageindex);
//        response.getWriter().println(users.size());
//        request.getRequestDispatcher("../view/admin/user_list.jsp").forward(request, response);
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
        ArrayList<String> strings = new ArrayList<>();
        strings.add(role);
        strings.add(statusParam);
        strings.add(genderParam);
        strings.add(combination);
        strings.add(sortBy);
        strings.add(order);

        if (role == null && statusParam == null && genderParam == null && combination == null && sortBy == null&& order == null) {
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
