/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.courseContentController;

import controller.AuthorizationController;
import dal.CourseDBContext;
import dal.PricePackageDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Course;
import model.ErrorMessage;
import model.PricePackage;
import util.Validation;

/**
 *
 * @author ADMIN
 */
public class PricePackageEditController extends AuthorizationController {

    private final static String PRICEPACKAGEEDITURL = "../../view/course_content/pricepackage_edit.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
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
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pricepackageID = Integer.parseInt(request.getParameter("pid"));
        ErrorMessage errorMessage = new ErrorMessage();
        int courseID = Integer.parseInt(request.getParameter("cid"));
        CourseDBContext courseDBContext = new CourseDBContext();
        PricePackageDBContext pricePackageDBContext = new PricePackageDBContext();
        Course course = courseDBContext.getCourse(courseID);
        PricePackage pricePackage = pricePackageDBContext.getPricePackageByID(pricepackageID);
        boolean isTurnOnable = pricePackageDBContext.isTurnOnable(pricepackageID);
        if (!isTurnOnable) {
            request.setAttribute("notifyMessage", errorMessage.PRICEPACKAGE_TURNON_NONLESSONPRICEPACKAGE);
        }
        request.setAttribute("isTurnOnable", isTurnOnable);
        request.setAttribute("course", course);
        request.setAttribute("pricePackage", pricePackage);
        request.getRequestDispatcher(PRICEPACKAGEEDITURL).forward(request, response);
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
        ErrorMessage errorMessage = new ErrorMessage();
        String raw_priceName = request.getParameter("priceName");
        String raw_description = request.getParameter("description");
        String raw_duration = request.getParameter("duration");
        String raw_listPrice = request.getParameter("listPrice");
        String raw_salePrice = request.getParameter("salePrice");
        String raw_status = request.getParameter("status");
        String raw_pricePackageID = request.getParameter("pricePackageID");
        String raw_isOnSale = request.getParameter("isOnSale");
        String raw_indefinite = request.getParameter("indefinite");

        Validation validation = new Validation();
        ArrayList<String> input = new ArrayList<>();
        input.add(raw_priceName);
        input.add(raw_description);
        input.add(raw_listPrice);
        input.add(raw_salePrice);
        input.add(raw_status);
        input.add(raw_pricePackageID);

        int pricePackageID;
        float listPrice, salePrice;
        Boolean status, isOnSale;
        int courseID = Integer.parseInt(request.getParameter("courseID"));
        if (validation.checkNullOrBlank(input)) {
            listPrice = Float.parseFloat(raw_listPrice);
            salePrice = Float.parseFloat(raw_salePrice);
            pricePackageID = Integer.parseInt(raw_pricePackageID);
            status = raw_status.equals("true");

            if (raw_isOnSale == null) {
                isOnSale = false;
            } else {
                isOnSale = raw_isOnSale.equals("true");

            }
            PricePackageDBContext pricePackageDBContext = new PricePackageDBContext();
            if (pricePackageDBContext.updatePricePackage(raw_priceName, raw_duration, status,
                    listPrice, salePrice, raw_description, isOnSale, pricePackageID)) {
                response.sendRedirect("pricepackageview?pid=" + pricePackageID + "&cid=" + courseID);
            } else {
                response.sendRedirect(request.getHeader("referer") + "&errorMessage=" + errorMessage.ERRORSQL);

            }

        } else {

            response.sendRedirect(request.getHeader("referer") + "&errorMessage=" + errorMessage.MISSINGINPUT);
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
