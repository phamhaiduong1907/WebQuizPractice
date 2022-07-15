/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.slider;

import controller.AuthorizationController;
import dal.SliderDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import model.Slider;

/**
 *
 * @author Hai Duong
 */
public class SliderListController extends AuthorizationController {

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
        SliderDBContext dbSlider = new SliderDBContext();

        // get default pagesize which is initialized as a context parameter
        int pagesize = Integer.parseInt(getServletContext().getInitParameter("pagesize"));

        // ==================================================================== 
        // =================== GET PARAMETER FROM REQUEST =====================
        /* 
        * get title
        *
        * title => null 
        * => list all
        * 
        * title => string of a specified backlink or title from user 
        * => return paginated list of sliders with respective title or backlink
        *
         */
        String title = request.getParameter("title");

        /* 
        * get status
        * 
        * status => all
        * => list all
        *
        * status => active => parseBoolean -> true
        * => return list of paginated active sliders
        *
        * status => inactive => parseBoolean -> false
        * => return list of paginated inactive sliders 
         */
        String statusParam = request.getParameter("status");

        // ====================================================================
        // ========================= END GET PARAMETER ========================
        // ====================================================================
        // ====================== REQUIRED ATTRIBUTES =========================
        String url = request.getRequestURI();
        String queryString = "";
        int count, totalpage;
        ArrayList<Slider> sliders;
        int pageindex = 0;
        Boolean status;

        /*
        * get pageindex
        * no pageindex => auto show page 1 of list
        * on the contrary => show specified page of list
         */
        String page = request.getParameter("page");
        if (page == null || page.trim().length() == 0) {
            page = "1";
        }

        // ====================================================================
        // ====================== REQUIRED ATTRIBUTES =========================
        // ====================================================================
        // ============================ LOGIC =================================
        if (statusParam == null && title == null) {
            count = dbSlider.count();
            totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize + 1);
            pageindex = Integer.parseInt(page);
            if (pageindex < 1) {
                pageindex = 1;
            }
            if (pageindex > totalpage) {
                pageindex = totalpage;
            }
            sliders = dbSlider.getSliders(null, null, pageindex, pagesize);
        } else {
            if (statusParam == null) {
                status = null;
            } else if (statusParam.equals("all")) {
                status = null;
                request.setAttribute("status", statusParam);
            } else {
                status = statusParam.equalsIgnoreCase("active");
                queryString += "&status=" + statusParam;
                request.setAttribute("status", statusParam);
            }

            if (title == null || title.trim().length() == 0) {
                title = "";
            } else {
                queryString += "&title=" + URLEncoder.encode(title.trim(), StandardCharsets.UTF_8.toString());
                request.setAttribute("title", title);
                title = title.trim();
            }

            count = dbSlider.countSlider(title, status);
            totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize + 1);
            pageindex = Integer.parseInt(page);
            if (pageindex < 1) {
                pageindex = 1;
            }
            if (pageindex > totalpage) {
                pageindex = totalpage;
            }
            sliders = dbSlider.getSliders(title, status, pageindex, pagesize);
        }
        // ====================================================================
        // ============================ LOGIC =================================

        // ====================================================================
        // ======================== SET ATTRIBUTES ============================
        request.setAttribute("url", url);
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("sliders", sliders);
        request.setAttribute("queryString", queryString);
        ArrayList<Slider> activeSliders = dbSlider.getSliders();
        if (activeSliders.size() <= 1) {
            request.setAttribute("deactiveLink", true);
        } else {
            request.setAttribute("deactiveLink", false);
        }

        // ====================================================================
        // ======================== SET ATTRIBUTES ============================
        // ====================================================================
        // =================== FORWARD REQUEST RESOURCES ======================
        request.getRequestDispatcher("../view/marketing/slider_list.jsp").forward(request, response);
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
