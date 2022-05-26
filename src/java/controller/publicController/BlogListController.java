/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.publicController;

import dal.BlogDBContext;
import dal.CategoryDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Category;
import model.Post;

/**
 *
 * @author Hai Tran
 */
public class BlogListController extends HttpServlet {

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
        BlogDBContext dbBlog = new BlogDBContext();
        CategoryDBContext dbCate = new CategoryDBContext();
        int pagesize = 3;
        String page = request.getParameter("page");
        if (page == null || page.trim().length() == 0) {
            page = "1";
        }
        int pageindex = Integer.parseInt(page);
        ArrayList<Post> posts = dbBlog.getPosts(pageindex, pagesize);
        ArrayList<Category> categories = dbCate.getCategories();
        int count = dbBlog.count();
        int totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize) + 1;
        request.setAttribute("categories", categories);
        request.setAttribute("posts", posts);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("pageindex", pageindex);
        request.getRequestDispatcher("/view/blog/list.jsp").forward(request, response);
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
