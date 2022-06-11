/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.slider;

import dal.SliderDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServlet;
import java.util.ArrayList;
import model.Slider;

/**
 *
 * @author Hai Duong
 */
public class StatusFilterController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        SliderDBContext dbSlider = new SliderDBContext();
        int pagesize = Integer.parseInt(getServletContext().getInitParameter("pagesize"));
        String statusParam = request.getParameter("status");
        // status all means show default page
        if (statusParam.equalsIgnoreCase("all")) {
            response.sendRedirect("list");
        }
        // status = active or inactive
        else {
            boolean status = statusParam.equalsIgnoreCase("active");
            String page = request.getParameter("page");
            if (page == null || page.trim().length() == 0) {
                page = "1";
            }
            int pageindex = Integer.parseInt(page);
            ArrayList<Slider> activeSliders = dbSlider.getSliders();
            if (activeSliders.size() <= 1) {
                request.setAttribute("deactiveLink", true);
            } else {
                request.setAttribute("deactiveLink", false);
            }
            ArrayList<Slider> sliders = dbSlider.getPaginatedSliders(pageindex, pagesize);
            int count = dbSlider.count();
            int totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize + 1);
            request.setAttribute("url", url);
            request.setAttribute("pageindex", pageindex);
            request.setAttribute("totalpage", totalpage);
            request.setAttribute("sliders", sliders);
            request.getRequestDispatcher("../view/marketing/slider_list.jsp").forward(request, response);
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
