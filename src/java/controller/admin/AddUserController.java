/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import controller.AuthorizationController;
import dal.AccountDBContext;
import dal.RoleDBContext;
import dal.UserDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Email;
import model.Role;
import util.EmailUtils;
import util.MiscUtil;

/**
 *
 * @author Hai Duong
 */
public class AddUserController extends AuthorizationController {

    final static String COMPANYGMAIL = "yourquizwebsite@gmail.com";
    final static String COMPANYGMAIL_PASSWORD = "kfdhvpiafobpgllh";
    final static String PASSWORD_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@$!%*?&";

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
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RoleDBContext dbRoles = new RoleDBContext();
        ArrayList<Role> roles = dbRoles.getRoles();
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("../view/admin/add_user.jsp").forward(request, response);
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
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("email");
        String phone = request.getParameter("phone");
        boolean gender = request.getParameter("gender").equalsIgnoreCase("male");
        String address = request.getParameter("address");
        int roleID = Integer.parseInt(request.getParameter("roleID"));
        boolean status = request.getParameter("status").equalsIgnoreCase("active");
        String profilePictureURL = getServletContext().getInitParameter("profilePictureURL");

        UserDBContext dbUser = new UserDBContext();
        AccountDBContext dbAccount = new AccountDBContext();

        // generate password with specified regex then email to the user
        // code goes here .....
        Account account = dbAccount.isExistAccount(username);
        if (account == null) {
            StringBuilder sb_password = new StringBuilder();
            Random random = new Random();
            int length = random.nextInt(8) + 8;
            for (int i = 0; i < length; i++) {
                int index = random.nextInt(PASSWORD_CHARACTERS.length());
                char randomChar = PASSWORD_CHARACTERS.charAt(index);
                sb_password.append(randomChar);
            }
            String password = sb_password.toString();
            MiscUtil msUtil = new MiscUtil();
            String passwordEncrypt = msUtil.encryptString(password);

            dbUser.addUser(username, passwordEncrypt, firstName, lastName, phone, gender, address, roleID, status, profilePictureURL);
            Email email = new Email();
            email.setFrom(COMPANYGMAIL);
            email.setTo(username);
            email.setFromPassword(COMPANYGMAIL_PASSWORD);
            email.setSubject("First password to log in QuizWebsite");

            StringBuilder sb = new StringBuilder();
            sb.append("Your password is: ");
            sb.append(password);
            sb.append("<br> Please log in our website and change your password!");
            EmailUtils emailUtils = new EmailUtils();
            email.setContent(sb.toString());
            try {
                emailUtils.send(email);
            } catch (Exception ex) {
                RoleDBContext dbRoles = new RoleDBContext();
                ArrayList<Role> roles = dbRoles.getRoles();
                request.setAttribute("roles", roles);
                String error = "Something went wrong during sending email, add user failed";
                request.setAttribute("error", error);
                request.getRequestDispatcher("../view/admin/add_user.jsp").forward(request, response);
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            String confirm = "Add user successfully";
            response.sendRedirect("view?username=" + username + "&confirm=" + confirm);
        } else {
            // Processing if account is existed
            RoleDBContext dbRoles = new RoleDBContext();
            ArrayList<Role> roles = dbRoles.getRoles();
            request.setAttribute("roles", roles);
            String error = "Email " + account.getUsername() + " has already existed";
            request.setAttribute("error", error);
            request.getRequestDispatcher("../view/admin/add_user.jsp").forward(request, response);
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
