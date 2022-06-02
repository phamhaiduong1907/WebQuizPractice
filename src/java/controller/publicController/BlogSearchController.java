/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.publicController;

import dal.BlogDBContext;
import dal.CategoryDBContext;
import dal.SubCategoryDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Category;
import model.Post;
import model.Subcategory;

/**
 *
 * @author Hai Tran
 */
public class BlogSearchController extends HttpServlet {

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
        BlogDBContext dbBlogforSearch = new BlogDBContext();
        CategoryDBContext dbCate = new CategoryDBContext();
        SubCategoryDBContext db = new SubCategoryDBContext();
        Subcategory sc = db.getSubcategory(10);
        int pagesize = 3;
        String page = request.getParameter("page");
        if (page == null || page.trim().length() == 0) {
            page = "1";
        }
        int pageindex = Integer.parseInt(page);
        if (pageindex <= 0) {
            pageindex = 1;
        }
        ArrayList<Category> categories = dbCate.getCategories(1);
        String search = request.getParameter("search");
        String sort = request.getParameter("sort");
        if(sort == null || sort.trim().length() == 0){
            sort = "DESC";
        }
        String string = "";
        String subcateID = "";
        if (request.getParameterValues("subcategory") != null) {
            String[] subcategory = request.getParameterValues("subcategory");
            if (subcategory.length != 0) {
                for (int i = 0; i < subcategory.length; i++) {
                    string += subcategory[i] + ", ";
                }
            }
            subcateID = string.substring(0, string.trim().length() - 1);
            search = search.trim();
        }
        subcateID = subcateID.trim();
        ArrayList<Post> searchPost = dbBlogforSearch.searchPost(search, subcateID, sort, pageindex, pagesize);
        int count = searchPost.size();
        int totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize) + 1;
        log(pageindex + " " + totalpage + " " + count);
        request.setAttribute("categories", categories);
        request.setAttribute("posts", searchPost);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("count", count);
        request.setAttribute("search", search);
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
