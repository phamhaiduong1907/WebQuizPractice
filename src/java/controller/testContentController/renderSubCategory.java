/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.testContentController;

import dal.CategoryDBContext;
import dal.CourseDBContext;
import dal.QuizAttributeDBContext;
import dal.QuizDBContext;
import dal.SubCategoryDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Subcategory;

/**
 *
 * @author long
 */
public class renderSubCategory extends HttpServlet {

    QuizAttributeDBContext qabdc = new QuizAttributeDBContext();
    CourseDBContext cdbc = new CourseDBContext();
    QuizDBContext qdbc = new QuizDBContext();
    QuizAttributeDBContext qadbc = new QuizAttributeDBContext();
    CategoryDBContext cadbc = new CategoryDBContext();
    SubCategoryDBContext scdbc = new SubCategoryDBContext();
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
            out.println("<title>Servlet renderSelect</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet renderSelect at " + request.getContextPath() + "</h1>");
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
    // url = /rendersubcate;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charater=UTF-8");
        response.setCharacterEncoding("utf-8");
        int categoryID = Integer.parseInt(request.getParameter("ID"));
        ArrayList<Subcategory> arr = scdbc.getSubcategories(categoryID);
        String result = "<select class = 'form-control' id='' name = 'subcateID'>";
        for (Subcategory s : arr) {
            result += "<option value = " + s.getSubcategoryID() + ">" + s.getSubcategoryName() + " </option>";
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
