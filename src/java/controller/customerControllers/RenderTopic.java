/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customerControllers;

import dal.DimensionDBContext;
import dal.TopicDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Course;
import model.Dimension;
import model.Topic;

/**
 *
 * @author long
 */
public class RenderTopic extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RenderTopic</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RenderTopic at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        TopicDBContext tdbc = new TopicDBContext();
        DimensionDBContext ddbc = new DimensionDBContext();

        response.setContentType("text/html;charater=UTF-8");
        response.setCharacterEncoding("utf-8");
        String qType = request.getParameter("qType").trim();
        log("-" + qType + "-");
        int courseID = Integer.parseInt(request.getParameter("ID"));
        String result = "<select class = 'form-control' id='' name = 'group'>";
        if (qType.equalsIgnoreCase("topic")) {
            ArrayList<Topic> topics = tdbc.getTopics(courseID);
            result += "<option value = " + "all" + ">" + "All" + " </option>";
            for (Topic t : topics) {
                result += "<option value = " + t.getTopicID() + ">" + t.getTopicName() + " </option>";
            }
        } else if (qType.equalsIgnoreCase("dimension")) {
            ArrayList<Dimension> dimensions = ddbc.getDimensionsByCourseID(courseID);
            result += "<option value = " + "all" + ">" + "All" + " </option>";
            for (Dimension d : dimensions) {
                result += "<option value = " + d.getDimensionID() + ">" + d.getDimensionName() + " </option>";
            }

        }

        result += "</select>";
        response.getWriter().println(result);
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
