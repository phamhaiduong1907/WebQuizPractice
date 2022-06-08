/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.commonController;

import dal.AccountDBContext;
import dal.UserDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.Account;
import model.User;

/**
 *
 * @author Zuys
 */
public class RegisterNextStepController extends HttpServlet {

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
        request.getRequestDispatcher("view/common/validate_email.jsp").forward(request, response);
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
        UserDBContext dbUser = new UserDBContext();
        AccountDBContext dbAccount = new AccountDBContext();

        String activateCode = (String) request.getSession().getAttribute("activateCode");
        Account account = (Account) request.getSession().getAttribute("accountReg");
        User user = (User) request.getSession().getAttribute("userReg");

        String confirmCode = request.getParameter("confirmCode");
        if (confirmCode == null || confirmCode.trim().length() == 0 || !confirmCode.matches(activateCode)) {
            request.setAttribute("validate_email_status", "Wrong verification code, please try again!");
            request.getRequestDispatcher("view/common/validate_email.jsp").forward(request, response);
        } else {
            boolean checkUser = dbUser.insertUser(user);
            boolean checkAccount = dbAccount.insertAccount(account);
            if (!checkUser || !checkAccount) {
                request.getSession().setAttribute("register_status", "Error registering user! Please try again!");
                response.sendRedirect("home");
            } else {
                request.getSession().removeAttribute("activateCode");
                request.getSession().removeAttribute("accountReg");
                request.getSession().removeAttribute("userReg");
                
                request.getSession().setAttribute("register_status", "Register successfully");
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
