/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.commonController;

import dal.AccountDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.Account;
import util.MiscUtil;

/**
 *
 * @author Zuys
 */
public class ChangePasswordController extends HttpServlet {

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
        MiscUtil check = new MiscUtil();
        AccountDBContext dbAcc = new AccountDBContext();
        Account account = (Account) request.getSession().getAttribute("account");

        String raw_cpass = request.getParameter("currentPassword");
        String raw_npass = request.getParameter("newPassword");
        String raw_cnpass = request.getParameter("confirmNewPassword");

        if (raw_cpass == null || raw_cpass.trim().length() == 0
                || raw_npass == null || raw_npass.trim().length() == 0
                || raw_cnpass == null || raw_cnpass.trim().length() == 0) {
            request.getSession().setAttribute("changepass_status", "No input problem");
            response.sendRedirect("home");
        } else if (!check.checkEncryptString(raw_cpass, account.getPassword())) {
            request.getSession().setAttribute("changepass_status", "Current password not correct, please try again!");
            response.sendRedirect("home");
        } else if (raw_cpass.matches(raw_npass)) {
            request.getSession().setAttribute("changepass_status", "New password can't be the same as the old password");
            response.sendRedirect("home");
        } else if (!raw_npass.matches(raw_cnpass)) {
            request.getSession().setAttribute("changepass_status", "Confirm password must be the same as the new password");
            response.sendRedirect("home");
        } else {
            account.setPassword(check.encryptString(raw_npass));
            if (dbAcc.changePassword(account)) {
                request.getSession().setAttribute("changepass_status", "Change password sucessfully");
                response.sendRedirect("home");
            } else {
                request.getSession().setAttribute("changepass_status", "Something is wrong, please try again!");
                response.sendRedirect("home");
            }
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
