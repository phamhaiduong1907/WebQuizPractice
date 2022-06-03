/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import model.Account;

/**
 *
 * @author Hai Duong
 */
public abstract class AuthorizationController extends HttpServlet {

    private boolean isAuthorized(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null) {
            return false;
        } else {
            AccountDBContext dbAccount = new AccountDBContext();
            String username = account.getUsername();
            String[] uri = request.getRequestURI().split("[?]");
            boolean permission = dbAccount.getPermission(username, uri[0]);
            return permission;
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
        if (isAuthorized(request)) {
            processGet(request, response);
        } else {
            Account account = (Account) request.getSession().getAttribute("account");
            if (account == null) {
                //require login
                response.setContentType("text/html;charset=utf-8");
                PrintWriter writer = response.getWriter();
                writer.println("<!DOCTYPE html>");
                writer.println("<html>");
                writer.println("<head>");
                writer.println("</head>");
                writer.println("<body>");
                writer.println("<script>\n"
                        + "		sessionStorage.setItem(\"requiredlogin\",\"true\");\n"
                        + "		history.back();\n"
                        + "	</script>");
                writer.println("</body>");
                writer.println("</html>");
            } else {
                response.setContentType("text/html;charset=utf-8");
                PrintWriter writer = response.getWriter();
                writer.println("<!DOCTYPE html>");
                writer.println("<html>");
                writer.println("<head>");
                writer.println("</head>");
                writer.println("<body>");
                writer.println("<script>\n"
                        + "		sessionStorage.setItem(\"unauthorizedAcess\",\"true\");\n"
                        + "		var u = history.go(-1);\n"
                        + "	</script>");
                writer.println("</body>");
                writer.println("</html>");
            }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (isAuthorized(request)) {
            processPost(request, response);
        } else {
            Account account = (Account) request.getSession().getAttribute("account");
            if (account == null) {
                //require login
                boolean requiredLogin = true;
                request.setAttribute("requiredLogin", requiredLogin);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                //Handling access deniance
                response.sendRedirect("/summer2022-se1617-g6/deny");
            }
        }
    }

    protected abstract void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    protected abstract void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}
