/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.slider;

import dal.SliderDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Slider;

/**
 *
 * @author Hai Duong
 */
public class SliderListController extends HttpServlet {

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
        if ((statusParam == null && title == null)) {
            count = dbSlider.count();
            totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize + 1);
            pageindex = Integer.parseInt(page);
            if (pageindex < 1) {
                pageindex = 1;
            }
            if (pageindex > totalpage) {
                pageindex = totalpage;
            }
            sliders = dbSlider.getPaginatedSliders(pageindex, pagesize);
        } else if (statusParam != null) {
            if (statusParam.equalsIgnoreCase("all")) {
                count = dbSlider.count();
                totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize + 1);
                pageindex = Integer.parseInt(page);
                if (pageindex < 1) {
                    pageindex = 1;
                }
                if (pageindex > totalpage) {
                    pageindex = totalpage;
                }
                sliders = dbSlider.getPaginatedSliders(pageindex, pagesize);
            } else {
                boolean status = statusParam.equalsIgnoreCase("active");
                count = dbSlider.countByStatus(status);
                totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize + 1);
                pageindex = Integer.parseInt(page);
                if (pageindex < 1) {
                    pageindex = 1;
                }
                if (pageindex > totalpage) {
                    pageindex = totalpage;
                }
                queryString = "&status=" + statusParam;
                sliders = dbSlider.getPaginatedSlidersByStatus(pageindex, pagesize, status);
                request.setAttribute("status", statusParam);
            }
        } else {
            if (title.trim().length() == 0) {
                count = dbSlider.count();
                totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize + 1);
                pageindex = Integer.parseInt(page);
                if (pageindex < 1) {
                    pageindex = 1;
                }
                if (pageindex > totalpage) {
                    pageindex = totalpage;
                }
                sliders = dbSlider.getPaginatedSliders(pageindex, pagesize);
            } else {
                String titleParts[] = title.split("\\s+");
                String titleSearch = "";
                for (String titlePart : titleParts) {
                    titleSearch += titlePart + " ";
                }
                count = dbSlider.countByTitle(titleSearch.trim());
                totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize + 1);
                pageindex = Integer.parseInt(page);
                String titleQuery = "";
                for(int i = 0; i < titleParts.length; i++){
                    titleQuery += titleParts[i];
                    if(i < titleParts.length - 1)
                        titleQuery += "+";
                }
                queryString = "&title=" + titleQuery;
                sliders = dbSlider.getPaginatedSlidersByTitle(pageindex, pagesize, title);
                request.setAttribute("title", title);
            }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
