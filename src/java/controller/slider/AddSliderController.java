/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.slider;

import dal.SliderDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.nio.file.Path;
import java.util.ArrayList;
import model.Slider;
import util.UploadFile;

/**
 *
 * @author Hai Duong
 */
@MultipartConfig
public class AddSliderController extends HttpServlet {

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
        request.getRequestDispatcher("../view/marketing/add_slider.jsp").forward(request, response);
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
        SliderDBContext dbSLiders = new SliderDBContext();
        String title = request.getParameter("title");
        String backlink = request.getParameter("backlink");
        boolean status = request.getParameter("status").equalsIgnoreCase("active");
        String note = request.getParameter("note");
        String imageURL;
        Part part = request.getPart("image");
        String contentType = part.getContentType();
        if (contentType.equals("image/jpg") || contentType.equals("image/png") || contentType.equals("image/jpeg")) {
            String realPath = request.getServletContext().getRealPath("/images/slider");
            String realPathWeb = realPath.substring(0, realPath.indexOf("build"));

            realPathWeb += "web\\images\\slider";

            String filename = part.getSubmittedFileName();
            UploadFile.copyPartToFile(part, realPath + "/" + filename);
            UploadFile.copyPartToFile(part, realPathWeb + "/" + filename);
            imageURL = "images/slider/" + filename;

            dbSLiders.insertSlider(title, backlink, status, imageURL, note);
            ArrayList<Slider> sliders = dbSLiders.getAllSliders();
            int sliderID = sliders.get(sliders.size() - 1).getSliderID();
            response.sendRedirect("view?sliderID=" + sliderID);
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
