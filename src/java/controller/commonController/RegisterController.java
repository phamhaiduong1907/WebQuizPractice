/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.commonController;

import dal.AccountDBContext;
import dal.RoleDBContext;
import dal.UserDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Email;
import model.User;
import util.EmailUtils;
import util.MiscUtil;

/**
 *
 * @author Zuys
 */
public class RegisterController extends HttpServlet {

    final static String COMPANYGMAIL = "yourquizwebsite@gmail.com";
    final static String COMPANYGMAIL_PASSWORD = "kfdhvpiafobpgllh";

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
        MiscUtil encpt = new MiscUtil();
        AccountDBContext dbAccount = new AccountDBContext();
        RoleDBContext dbRole = new RoleDBContext();
        // get parameter from register form
        String raw_firstName = request.getParameter("firstName");
        String raw_lastName = request.getParameter("lastName");
        String raw_gender = request.getParameter("gender");
        String raw_email = request.getParameter("email");
        String raw_phone = request.getParameter("phone");
        String raw_address = request.getParameter("address");
        String raw_passwordReg = request.getParameter("passwordReg");
        String raw_confirmPasswordReg = request.getParameter("confirmPasswordReg");

        //validate data
        String formatEmail = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        String formatPass = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        String formatPhone = "[0-9]{9,10}";
        String register_status = "Something is wrong, please try again!";
        if (raw_firstName == null || raw_firstName.trim().length() == 0
                || raw_lastName == null || raw_lastName.trim().length() == 0
                || raw_gender == null || raw_gender.trim().length() == 0
                || raw_email == null || raw_email.trim().length() == 0
                || raw_phone == null || raw_phone.trim().length() == 0
                || raw_address == null || raw_address.trim().length() == 0
                || raw_passwordReg == null || raw_passwordReg.trim().length() == 0
                || raw_confirmPasswordReg == null || raw_confirmPasswordReg.trim().length() == 0) {
            request.getSession().setAttribute("register_status", register_status);
            response.sendRedirect("home");
        } else {
            if (!raw_email.matches(formatEmail) || !raw_phone.matches(formatPhone) || !raw_passwordReg.matches(formatPass) || !raw_confirmPasswordReg.matches(raw_passwordReg)) {
                request.getSession().setAttribute("register_status", register_status);
                response.sendRedirect("home");
            } else if (dbAccount.isExistUser(raw_email)) {
                request.getSession().setAttribute("register_status", "This email have already been registered, please try another one!");
                response.sendRedirect("home");
            } //business logic
            else {
                String firstName = raw_firstName;
                String lastName = raw_lastName;
                boolean gender = (raw_gender.matches("male"));
                String email = raw_email;
                String phone = raw_phone;
                String passwordReg = raw_passwordReg;
                String address = raw_address;

                Account account = new Account();
                account.setUsername(email);
                account.setPassword(encpt.encryptString(passwordReg));
                account.setRole(dbRole.getRoleById(5));
                request.getSession().setAttribute("accountReg", account);

                User user = new User();
                user.setAccount(account);
                user.setGender(gender);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setPhoneNumber(phone);
                user.setAddress(address);
                user.setProfilePictureUrl("none");
                user.setStatus(true);
                request.getSession().setAttribute("userReg", user);

                //send verification email
                Email e = new Email();
                e.setFrom(COMPANYGMAIL);
                e.setTo(account.getUsername());
                e.setFromPassword(COMPANYGMAIL_PASSWORD);
                e.setSubject("Verify your email");

                MiscUtil rand = new MiscUtil();
                String activateCode = rand.getRandom();
                StringBuilder sb = new StringBuilder();
                sb.append("This is your verification code: " + activateCode);

                EmailUtils emailUtils = new EmailUtils();
                e.setContent(sb.toString());

                try {
                    EmailUtils.send(e);
                    request.getSession().setAttribute("activateCode", activateCode);
                    response.sendRedirect("registernext");
                } catch (Exception ex) {
                    Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
                    request.getSession().setAttribute("register_status", "Send mail failed, please try again later!");
                    response.sendRedirect("home");
                }
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
