/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.saleController;

import dal.AccountDBContext;
import dal.CategoryDBContext;
import dal.CourseDBContext;
import dal.PricePackageDBContext;
import dal.RegistrationDBContext;
import dal.RoleDBContext;
import dal.SubCategoryDBContext;
import dal.UserDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Session;
import model.Account;
import model.Category;
import model.Course;
import model.Email;
import model.PricePackage;
import model.Registration;
import model.Role;
import model.Subcategory;
import model.User;
import util.EmailUtils;
import util.MiscUtil;
import util.Validation;

/**
 *
 * @author long
 */
public class RegistrationEditController extends HttpServlet {

    final static String MISSINGINPUT = "Please fill in required fill";
    final static String ERRORSQL = "An accoutn with that username is already exists! Please consider using a different username.";
    final static String SUCESSFULLY = "Account has been sent successfully";
    final static String SENDEMAILERROR = "There were some problem contacting your email! Please try again.";

    final static String COMPANYGMAIL = "yourquizwebsite@gmail.com";
    final static String COMPANYGMAIL_PASSWORD = "kfdhvpiafobpgllh";
    final static String HOMESERVLETNAME = "HomePageController";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
//        User loginUser = (User) request.getSession().getAttribute("user");
//        if (loginUser == null || loginUser.getAccount().getRole().getRoleID() != 3) {
//            request.setAttribute("mess", "You do not have permission to access this site!");
//            request.getRequestDispatcher("../view/sale/notice.jsp").forward(request, response);
//        }

        int Id;
        CourseDBContext cdbc = new CourseDBContext();
        RegistrationDBContext rdbc = new RegistrationDBContext();
        CategoryDBContext cadbc = new CategoryDBContext();
        SubCategoryDBContext scdbc = new SubCategoryDBContext();
        ArrayList<Category> categories = cadbc.getCategories(2);
        int SubcategoryID;
        if (request.getParameter("id") != null) {
            Id = Integer.parseInt(request.getParameter("id"));
            Registration r = rdbc.getARegistration(Id);
//            if (!r.getUpdatedBy().getAccount().getUsername().equalsIgnoreCase(loginUser.getAccount().getUsername())) {
//                request.setAttribute("mess", "You do not have permission to edit this registration!");
//                request.getRequestDispatcher("../view/sale/notice.jsp").forward(request, response);
//            }
            Course course = cdbc.getCourseByCourseID(r.getCourse().getCourseID(), null);
            User u = r.getUser();
            PricePackageDBContext pdbc = new PricePackageDBContext();
            PricePackage pkg = pdbc.getPricePackageByID(r.getPricePackage().getPricePackageID());
            ArrayList<PricePackage> prices = pdbc.getPricePackagesByCourseID(course.getCourseID());
            SubcategoryID = course.getSubcategory().getSubcategoryID();
            Subcategory subcategory = scdbc.getSubcategory(SubcategoryID);
            request.setAttribute("subcategory", subcategory);
            request.setAttribute("user", u);
            request.setAttribute("registration", r);
            request.setAttribute("course", course);
            request.setAttribute("prices", prices);
        }
        request.setAttribute("categories", categories);
        ArrayList<Course> courses = cdbc.getCoursesForHomePage(null);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("../view/sale/registration_detail_edit.jsp").forward(request, response);
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
        String raw_username = request.getParameter("username");
        String raw_firstName = request.getParameter("firstName");
        String raw_lastName = request.getParameter("lastName");
        String raw_gender = request.getParameter("gender");
        String raw_phone = request.getParameter("phone");
        String raw_courseID = request.getParameter("courseID");
        String raw_pricePackageID = request.getParameter("pricePackage");
        String raw_status = request.getParameter("status");
        int registrationID;

        Validation validate = new Validation();

        ArrayList<String> str = new ArrayList<>();

        str.add(raw_username);
        str.add(raw_firstName);
        str.add(raw_lastName);
        str.add(raw_gender);
        str.add(raw_phone);
        str.add(raw_courseID);
        str.add(raw_pricePackageID);
        str.add(raw_status);

        Boolean gender;
        Boolean status;
        AccountDBContext accountDBContext = new AccountDBContext();
        RoleDBContext roleDBContext = new RoleDBContext();
        if (validate.checkNullOrBlank(str)) {
            gender = raw_gender.equals("male");
            status = raw_status.equals("paid");
            int courseID = Integer.parseInt(raw_courseID);
            int pricePackageID = Integer.parseInt(raw_pricePackageID);
            PricePackageDBContext pdbc = new PricePackageDBContext();
            PricePackage pricePackage = pdbc.getPricePackageByID(pricePackageID);
            RegistrationDBContext rdbc = new RegistrationDBContext();
            UserDBContext udbc = new UserDBContext();

            if (request.getParameter("registrationID") != null) {
                registrationID = Integer.parseInt(request.getParameter("registrationID"));
                rdbc.updateRegistration(courseID, pricePackage, status, registrationID);
                if (udbc.isUserExist(raw_username) == true) {
                    if (accountDBContext.isExistUser(raw_username) == true) {
                        User user = udbc.getUser(raw_username);
                        user.setFirstName(raw_firstName);
                        user.setLastName(raw_lastName);
                        user.setGender(gender);
                        user.setPhoneNumber(raw_phone);
                        udbc.updateUser(user);
                        response.sendRedirect("../sale/registrationview?id=" + registrationID);
                    } else {
                        MiscUtil miscUtil = new MiscUtil();
                        String password = miscUtil.getRandom();
                        String password_clone = password;
                        password = miscUtil.encryptString(password);
                        Account account = new Account();
                        account.setPassword(password);
                        account.setUsername(raw_username);
                        Role role = new Role();
                        role = roleDBContext.getRole(5);
                        account.setRole(role);
                        User user = new User();
                        user.setAccount(account);
                        user.setFirstName(raw_firstName);
                        user.setLastName(raw_lastName);
                        user.setGender(gender);
                        user.setPhoneNumber(raw_phone);
                        udbc.insertUser(user);

                        if (accountDBContext.insertAccount(account)) {
                            Email email = new Email();
                            email.setFrom(COMPANYGMAIL);
                            email.setTo(raw_username);
                            email.setFromPassword(COMPANYGMAIL_PASSWORD);
                            email.setSubject("New account");
                            StringBuilder sb = new StringBuilder();
                            sb.append("Your account username is: " + raw_username + "<br>");
                            sb.append("And password is " + password_clone);

                            EmailUtils emailUtils = new EmailUtils();

                            email.setContent(sb.toString());

                            try {
                                emailUtils.send(email);
                                request.setAttribute("mess", SUCESSFULLY);
                                request.getRequestDispatcher("../view/sale/notice.jsp").forward(request, response);
                            } catch (Exception ex) {
                                Logger.getLogger(RegistrationViewController.class.getName()).log(Level.SEVERE, null, ex);
                                request.setAttribute("mess", SENDEMAILERROR);
                                request.getRequestDispatcher("../view/sale/notice.jsp").forward(request, response);
                            }
                        } else {
                            log("Could not insert account!");
                            request.setAttribute("mess", ERRORSQL);
                            request.getRequestDispatcher("../view/sale/notice.jsp").forward(request, response);
                        }

                    }

                }
            } else {
                
                MiscUtil miscUtil = new MiscUtil();
                String password = miscUtil.getRandom();
                String password_clone = password;
                password = miscUtil.encryptString(password);
                Account account = new Account();
                account.setPassword(password);
                account.setUsername(raw_username);
                Role role = new Role();
                role = roleDBContext.getRole(5);
                account.setRole(role);
                log(account.getUsername() + "\n" + account.getPassword() + "\n" + account.getRole().getRoleID());
                User user = new User();
                user.setAccount(account);
                user.setFirstName(raw_firstName);
                user.setLastName(raw_lastName);
                user.setGender(gender);
                user.setPhoneNumber(raw_phone);
                udbc.insertUser(user);
                User loginUser = (User) request.getSession().getAttribute("user");
                rdbc.insertReg(user, courseID, pricePackage, status, loginUser.getAccount().getUsername());
                if (accountDBContext.insertAccount(account)) {
                    Email email = new Email();
                    email.setFrom(COMPANYGMAIL);
                    email.setTo(raw_username);
                    email.setFromPassword(COMPANYGMAIL_PASSWORD);
                    email.setSubject("New account");
                    StringBuilder sb = new StringBuilder();
                    sb.append("Your account is ready, Login at: " + "http://localhost:8080/SWP391-SE1617-NET_Group06-QuizWebsite/" + "<br>");
                    sb.append("Your account username is: " + raw_username + "<br>");
                    sb.append("And password is " + password_clone);

                    EmailUtils emailUtils = new EmailUtils();

                    email.setContent(sb.toString());

                    try {
                        emailUtils.send(email);
                        request.setAttribute("mess", SUCESSFULLY);
                        request.getRequestDispatcher("../view/sale/notice.jsp").forward(request, response);
                    } catch (Exception ex) {
                        Logger.getLogger(RegistrationViewController.class.getName()).log(Level.SEVERE, null, ex);
                        request.setAttribute("mess", SENDEMAILERROR);
                        request.getRequestDispatcher("../view/sale/notice.jsp").forward(request, response);
                    }
                } else {
                    log("Could not insert account!");
                    request.setAttribute("mess", ERRORSQL);
                    request.getRequestDispatcher("../view/sale/notice.jsp").forward(request, response);
                }
            }
        } else {
            response.sendRedirect("sale/registrationdetail" + "&errorMessage=" + MISSINGINPUT);
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
