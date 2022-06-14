/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.AccountDBContext;
import dal.RoleDBContext;
import dal.UserDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Email;
import model.Role;
import util.EmailUtils;

/**
 *
 * @author Hai Duong
 */
public class AddUserController extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String firstName = request.getParameter("firstName");
        out.println("firstName: " + firstName);
        String lastName = request.getParameter("lastName");
        out.println("lastName: " + lastName);
        String username = request.getParameter("email");
        out.println("email: " + username);
        String phone = request.getParameter("phone");
        out.println("phone: " + phone);
        boolean gender = request.getParameter("gender").equalsIgnoreCase("male");
        out.println("gender: " + gender);
        String address = request.getParameter("address");
        out.println("address: " + address);
        int roleID = Integer.parseInt(request.getParameter("roleID"));
        out.println("roleID: " + roleID);
        boolean status = request.getParameter("status").equalsIgnoreCase("active");
        out.println("status: " + status);
        String profilePictureURL = getServletContext().getInitParameter("profilePictureURL");
        out.println("avatar: " + profilePictureURL);

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
            out.println("generated password: " + password);
            
            dbUser.addUser(username, password, firstName, lastName, phone, gender, address, roleID, status, profilePictureURL);
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
            out.println("Email Content: " + sb.toString());
            try {
                emailUtils.send(email);
            } catch (Exception ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("userdetail?username=" + username);
        } else {
            response.getWriter().println("account existed");
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
