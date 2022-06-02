/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.publicController;

import dal.CourseDBContext;
import dal.PricePackageDBContext;
import dal.RegistrationDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import model.Course;
import model.PricePackage;
import model.Registration;
import model.User;
import util.Validation;

/**
 *
 * @author ADMIN
 */
public class CourseRegistrationController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
     * //
     * <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
     * /**
     * Handles the HTTP <code>GET</code> method.
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
        Validation validation = new Validation();

        PricePackageDBContext pricePackageDBContext = new PricePackageDBContext();
        RegistrationDBContext registrationDBContext = new RegistrationDBContext();
        String rawFirstName = request.getParameter("firstName");
        String rawLastName = request.getParameter("lastName");
        String rawEmail = request.getParameter("email");
        String rawPhoneNumber = request.getParameter("phoneNumber");
        String rawGender = request.getParameter("gender");
        String rawPricePackageID = request.getParameter("pricePackageID");
        String rawCourseID = request.getParameter("courseID");
        String data[] = {rawFirstName, rawLastName, rawEmail, rawPhoneNumber, rawGender, rawPricePackageID};
        if (validation.checkNullOrBlank(data)) {
            int pricePackageID = Integer.parseInt(rawPricePackageID);
            PricePackage pricePackage = pricePackageDBContext.getPricePackageByID(pricePackageID);
            User user = new User();
            user.setFirstName(rawFirstName);
            user.setLastName(rawLastName);
            user.setGender(rawGender.equals("male"));
            Account a = new Account();
            a.setUsername(rawEmail);
            user.setAccount(a);
            user.setPhoneNumber(rawPhoneNumber);
            int courseID = Integer.parseInt(rawCourseID);
            boolean isSucessfull = registrationDBContext.insertRegistration(courseID, pricePackage, user);
            if (isSucessfull) {

                response.sendRedirect("subjectdetail?subjectID=" + request.getParameter("courseID") + "&registerSucessfully=true");

            } else {
                response.sendRedirect("subjectdetail?subjectID=" + request.getParameter("courseID") + "&registerSucessfully=false");

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
