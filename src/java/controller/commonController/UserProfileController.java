/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.commonController;

import dal.UserDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import model.Account;
import model.User;

/**
 *
 * @author Zuys
 */
@MultipartConfig(location = "E:\\Semester 5\\SWP391\\QuizWebsite-Iteration2-Complete\\summer2022-se1617-g6\\web\\images\\profile", fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class UserProfileController extends HttpServlet {

    final static String PICTUREPROFILEURI = "E:\\Semester 5\\SWP391\\QuizWebsite-Iteration2-Complete\\summer2022-se1617-g6\\web\\images\\profile";

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
        UserDBContext dbUser = new UserDBContext();
        Account account = (Account) request.getSession().getAttribute("account");
        //get parameter from profile form
        Part profilePic = request.getPart("profilePicture");
        String profilePicName = extractFileName(profilePic);
        profilePicName = new File(profilePicName).getName();
        String raw_firstName = request.getParameter("firstName");
        String raw_lastName = request.getParameter("lastName");
        String raw_gender = request.getParameter("gender");
        String raw_phone = request.getParameter("phone");
        String raw_address = request.getParameter("address");
        //validate input
        if (raw_firstName == null || raw_firstName.trim().length() == 0
                || raw_lastName == null || raw_lastName.trim().length() == 0
                || raw_gender == null || raw_gender.trim().length() == 0
                || raw_phone == null || raw_phone.trim().length() == 0
                || raw_address == null || raw_address.trim().length() == 0) {
            request.getSession().setAttribute("profile_status", "No input problem");
            response.sendRedirect("home");
        } else if (checkFileType(profilePicName)) {
            if (profilePicName.length() > 0) {
                request.getSession().setAttribute("profile_status", "You need to use a picture!");
                response.sendRedirect("home");
            } else {
                User user = new User();
                user.setAccount(account);
                user.setAddress(raw_address);
                user.setFirstName(raw_firstName);
                user.setLastName(raw_lastName);
                user.setGender(raw_gender.matches("male"));
                user.setPhoneNumber(raw_phone);
                user.setProfilePictureUrl("images/profile/" + account.getUsername() + ".png");

                dbUser.updateUser(user);
                request.getSession().setAttribute("profile_status", "Update successfully!");
                request.getSession().setAttribute("user", user);
                response.sendRedirect("home");
            }

        } else {//business logic 
            try {
                profilePic.write(this.getFolderUpload().getAbsolutePath() + File.separator + profilePicName);
            } catch (Exception e) {
                request.getSession().setAttribute("profile_status", "Write file to disk failed");
                response.sendRedirect("home");

            }

            File file = new File(PICTUREPROFILEURI+"\\" + profilePicName);
            if (file.exists()) {
                Path source = Paths.get(PICTUREPROFILEURI+"\\" + profilePicName);
                Files.move(source, source.resolveSibling(PICTUREPROFILEURI+"\\" + account.getUsername() + ".png"), StandardCopyOption.REPLACE_EXISTING);

                User user = new User();
                user.setAccount(account);
                user.setAddress(raw_address);
                user.setFirstName(raw_firstName);
                user.setLastName(raw_lastName);
                user.setGender(raw_gender.matches("male"));
                user.setPhoneNumber(raw_phone);
                user.setProfilePictureUrl("images/profile/" + account.getUsername() + ".png");

                dbUser.updateUser(user);
                request.getSession().setAttribute("profile_status", "Update successfully!");
                request.getSession().setAttribute("user", user);
                response.sendRedirect("home");
            } else {
                request.getSession().setAttribute("profile_status", "File saving problem");
                response.sendRedirect("home");
            }
        }
    }

    private boolean checkFileType(String fileName) {
        return !fileName.toLowerCase().endsWith("jpg") && !fileName.toLowerCase().endsWith("jpeg") && !fileName.toLowerCase().endsWith("png") && !fileName.toLowerCase().endsWith("gif");
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    public File getFolderUpload() {
        File folderUpload = new File("E:\\Semester 5\\SWP391\\QuizWebsite-Iteration2-Complete\\summer2022-se1617-g6\\web\\images\\profile");
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
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
