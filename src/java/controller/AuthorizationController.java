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
            String uri = request.getRequestURI();
            boolean permission = dbAccount.getPermission(username, uri);
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
            //Handling access deniance
            Account account = (Account) request.getSession().getAttribute("account");
            if (account == null) {
                response.sendRedirect("login");
            } else {
                //redirect to access deniance page
                response.sendRedirect("deny");
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
            //Handling access deniance
            Account account = (Account) request.getSession().getAttribute("account");
            if (account == null) {
                response.sendRedirect("login");
            } else {
                //redirect to access deniance page
                response.sendRedirect("deny");
            }
        }
    }

    protected abstract void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    protected abstract void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}
