/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.slider;

import controller.AuthorizationController;
import dal.SliderDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Slider;
import util.UploadFile;

/**
 *
 * @author Hai Duong
 */
@MultipartConfig
public class SliderDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int sliderID = Integer.parseInt(request.getParameter("sliderID"));
        SliderDBContext dbSliders = new SliderDBContext();
        Slider sliderByID = dbSliders.getSliderByID(sliderID);
        request.setAttribute("sliderByID", sliderByID);
        request.getRequestDispatcher("../view/marketing/slider_detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SliderDBContext dbSLider = new SliderDBContext();
        int sliderID = Integer.parseInt(request.getParameter("sliderID"));
        String title = request.getParameter("title");
        String backlink = request.getParameter("backlink");
        boolean status = request.getParameter("status").equalsIgnoreCase("active");
        String note = request.getParameter("note");
        String imageURL = "";
        String imageURLDefault = request.getParameter("imageURLDefault");

        Part imageURLUpdate = request.getPart("imageURLUpdate");
        String imageContentType = imageURLUpdate.getContentType();
        if (imageContentType.equals("image/jpg") || imageContentType.equals("image/png") || imageContentType.equals("image/jpeg")) {
            String realPath = request.getServletContext().getRealPath("/images/slider");
            String realPathWeb = realPath.substring(0, realPath.indexOf("build"));
            realPathWeb += "web\\images\\slider";
            String filename = "slider_thumbnail_id" + sliderID + ".png";
            UploadFile.copyPartToFile(imageURLUpdate, realPath + "/" + filename);
            UploadFile.copyPartToFile(imageURLUpdate, realPathWeb + "/" + filename);

            imageURL = "images/slider/" + filename;
            dbSLider.updateSlider(sliderID, title, backlink, status, imageURL, note);
            response.sendRedirect("view?sliderID=" + sliderID);
        } else {
            imageURL = imageURLDefault;
            dbSLider.updateSlider(sliderID, title, backlink, status, imageURL, note);
            response.sendRedirect("view?sliderID=" + sliderID);
        }

    }

}
