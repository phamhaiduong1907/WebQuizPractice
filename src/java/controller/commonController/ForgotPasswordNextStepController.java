/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.commonController;

import dal.AccountDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.EmailUtils;

/**
 *
 * @author ADMIN
 */
public class ForgotPasswordNextStepController extends HttpServlet {

    private final String RESETTINGPAGE = "view/common/resetting_page.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(RESETTINGPAGE).forward(request, response);
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
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String messageSuccess = "The password has been changed successfully";
        String messageFailMatchPassword = "The confirm password does not match \n";
        String messageFailRegexPassword = "The password is not strong enough. The password must have at least 8 characters in which contain at least an capital, a special character and a number \n";
        String messageFailUpdate = "Can't update new password";
        String messageFailTokenExpired = "The link has been expired";
        String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        try {
            if (password.equals(confirmPassword) && password.matches(regexPassword)) {
                EmailUtils emailUtils = new EmailUtils();
                String username = emailUtils.readUsernameFromToken(token);
                AccountDBContext accountDBContext = new AccountDBContext();
                boolean isUpdate = accountDBContext.changePassword(username, password);
                if (isUpdate) {
                    request.setAttribute("messageUpdate", messageSuccess);
                } else {
                    request.setAttribute("messageUpdate", messageFailUpdate);
                }

            } else {
                if (password.equals(confirmPassword) == false) {
                    request.setAttribute("messageFailMatchPassword", messageFailMatchPassword);
                }
                if (password.matches(regexPassword) == false) {
                    request.setAttribute("messageFailRegexPassword", messageFailRegexPassword);

                }
            }
        } catch (Exception IllegalArgumentException) {
            request.setAttribute("messageExpired", messageFailTokenExpired);

        }

        request.getRequestDispatcher(RESETTINGPAGE).forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
