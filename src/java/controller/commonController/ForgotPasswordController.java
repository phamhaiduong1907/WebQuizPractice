/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.commonController;

import dal.AccountDBContext;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Email;
import util.EmailUtils;

/**
 *
 * @author ADMIN
 */
public class ForgotPasswordController extends HttpServlet {

    final static String COMPANYGMAIL = "yourquizwebsite@gmail.com";
    final static String COMPANYGMAIL_PASSWORD = "kfdhvpiafobpgllh";
    final static String HOMESERVLETNAME = "HomePageController";

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
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("home");
        requestDispatcher.forward(request, response);

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
        String username = request.getParameter("email");
        AccountDBContext accountDB = new AccountDBContext();
        Account account = accountDB.isExistAccount(username);
        String sendEmailsuccessfullyMessage = "Visit your Gmail account to reset password";
        String sendEmailFailedmessage = "An error occured please try again";
        if (account != null) {
            Email email = new Email();
            email.setFrom(COMPANYGMAIL);
            email.setTo(username);
            email.setFromPassword(COMPANYGMAIL_PASSWORD);
            email.setSubject("Reset password");

            StringBuilder sb = new StringBuilder();
            sb.append("Click this link to reset your password. <br>");

            EmailUtils emailUtils = new EmailUtils();
            String token = emailUtils.createToken(username);

            sb.append("http://localhost:9999/SWP391-SE1617-NET_Group06-QuizWebsite/forgotPasswordNext?token="
                    + token);
            email.setContent(sb.toString());

            try {
                EmailUtils.send(email);
                response.sendRedirect("home?resetPasswordMessage=" + sendEmailsuccessfullyMessage);

            } catch (Exception ex) {

                Logger.getLogger(ForgotPasswordController.class.getName()).log(Level.SEVERE, null, ex);
            }
//            request.getRequestDispatcher("index.jsp").forward(request, response);

////            response.sendRedirect("home");
        } else {
            response.sendRedirect("home?resetPasswordMessage=" + sendEmailFailedmessage);

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
