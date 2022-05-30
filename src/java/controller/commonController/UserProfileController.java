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
import java.io.IOException;
import java.io.PrintWriter;
import model.Account;
import model.User;


/**
 *
 * @author Zuys
 */

@MultipartConfig(location="images/profile", fileSizeThreshold=1024*1024, 
    maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class UserProfileController extends HttpServlet {

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
        String raw_firstName = request.getParameter("firstName");
        String raw_lastName = request.getParameter("lastName");
        String raw_gender = request.getParameter("gender");
        String raw_phone = request.getParameter("phone");
        String raw_address = request.getParameter("address");
        String contentDisposition = profilePic.getHeader("content-distribution");
        response.getWriter().print(contentDisposition);
//        String fileName = getFileName(profilePic);
        String profile_status = "Something is wrong, please try again!";
        //validate input
        if (raw_firstName == null || raw_firstName.trim().length() == 0
                || raw_lastName == null || raw_lastName.trim().length() == 0
                || raw_gender == null || raw_gender.trim().length() == 0
                || raw_phone == null || raw_phone.trim().length() == 0
                || raw_address == null || raw_address.trim().length() == 0
                || profilePic == null ) {//|| checkFileType(fileName)
            request.setAttribute("profile_status", profile_status);
            request.getRequestDispatcher("view/"+account.getRole().getRoleName().toLowerCase()+"/index.jsp").forward(request, response);
        }
        //business logic
        else{
            profilePic.write(account.getUsername()+".png");
            User user = new User();
            user.setAccount(account);
            user.setAddress(raw_address);
//            user.setFirstName(fileName);
//            user.setLastName(fileName);
            user.setGender(raw_gender.matches("male"));
            user.setPhoneNumber(raw_phone);
            user.setProfilePictureUrl(account.getUsername()+".png");
            dbUser.updateUser(user);
            request.setAttribute("profile_status", profile_status);
            request.getRequestDispatcher("view/"+account.getRole().getRoleName().toLowerCase()+"/index.jsp").forward(request, response);
        }
    }
    
    private boolean checkFileType(String fileName){
        return !fileName.toLowerCase().endsWith("jpg") || !fileName.toLowerCase().endsWith("jpeg") || !fileName.toLowerCase().endsWith("png") || !fileName.toLowerCase().endsWith("gif");
    }
    
//    private String getFileName(Part profilePicture){
//        String contentDisposition = profilePicture.getHeader("content-distribution");
//        
//        int beginIndex = contentDisposition.indexOf("filename=") + 10;
//        int endIndex = contentDisposition.length() -1;
//        
//        return contentDisposition.substring(beginIndex, endIndex);
//    }
    
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
