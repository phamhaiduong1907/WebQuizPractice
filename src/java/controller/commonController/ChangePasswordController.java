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
        AccountDBContext dbAcc = new AccountDBContext();
        Account account = (Account) request.getSession().getAttribute("account");

        String raw_cpass = request.getParameter("currentPassword");
        String raw_npass = request.getParameter("newPassword");
        String raw_cnpass = request.getParameter("confirmNewPassword");

        if (raw_cpass == null || raw_cpass.trim().length() == 0
                || raw_npass == null || raw_npass.trim().length() == 0
                || raw_cnpass == null || raw_cnpass.trim().length() == 0) {
            request.setAttribute("changepass_status", "No input problem");
            request.getRequestDispatcher("view/" + account.getRole().getRoleName().toLowerCase() + "/index.jsp").forward(request, response);
        } else if (raw_cpass.matches(raw_npass)) {
            request.setAttribute("changepass_status", "New password can't be the same as the old password");
            request.getRequestDispatcher("view/" + account.getRole().getRoleName().toLowerCase() + "/index.jsp").forward(request, response);
        } else if (!raw_npass.matches(raw_cnpass)) {
            request.setAttribute("changepass_status", "Confirm password must be the same as the new password");
            request.getRequestDispatcher("view/" + account.getRole().getRoleName().toLowerCase() + "/index.jsp").forward(request, response);
        } else {
            account.setPassword(raw_npass);
            if (dbAcc.changePassword(account)) {
                request.setAttribute("changepass_status", "Change password sucessfully");
                request.getRequestDispatcher("view/" + account.getRole().getRoleName().toLowerCase() + "/index.jsp").forward(request, response);
            } else {
                request.setAttribute("changepass_status", "Something is wrong, please try again!");
                request.getRequestDispatcher("view/" + account.getRole().getRoleName().toLowerCase() + "/index.jsp").forward(request, response);
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
