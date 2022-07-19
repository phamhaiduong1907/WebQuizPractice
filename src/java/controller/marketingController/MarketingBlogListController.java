/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.marketingController;

import controller.AuthorizationController;
import dal.BlogDBContext;
import dal.CategoryDBContext;
import dal.SubCategoryDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import model.Category;
import model.Post;

/**
 *
 * @author ADMIN
 */
public class MarketingBlogListController extends AuthorizationController {

    static private final String POSTLISTURL = "../view/marketing/post_list.jsp";
    static private final String FROMGREATERTHANTOERROR = "From date must be smaller than To date";
    static private final int PAGESIZE = 5;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BlogDBContext dbBlogforSearch = new BlogDBContext();
        CategoryDBContext dbCate = new CategoryDBContext();
        String page = request.getParameter("page");
        if (page == null || page.trim().length() == 0) {
            page = "1";
        }
        int pageindex = Integer.parseInt(page);
        if (pageindex <= 0) {
            pageindex = 1;
        }
        ArrayList<Category> categories = dbCate.getCategories(1);
        String string = "";
        String subcateID = "";
        String search = "";
        String sort = request.getParameter("sort");
        if (sort == null || sort.trim().length() == 0) {
            sort = "DESC";
        }
        if (request.getParameter("search").trim() != null) {
            search = request.getParameter("search");
        }
        if (request.getParameterValues("subcategory") != null) {
            String[] subcategory = request.getParameterValues("subcategory");
            if (subcategory.length != 0) {
                for (int i = 0; i < subcategory.length; i++) {
                    string += subcategory[i] + ", ";
                }
            }
            subcateID = string.substring(0, string.trim().length() - 1);
        }
        search = search.trim();
        String queryString = request.getQueryString();
        subcateID = subcateID.trim();
        ArrayList<Integer> subcategories = new ArrayList<>();

        String[] subcategory = request.getParameterValues("subcategory");
        if (subcategory != null) {
            for (int i = 0; i < subcategory.length; i++) {
                subcategories.add(Integer.parseInt(subcategory[i]));
            }
        }
        String author = request.getParameter("author");
        String raw_status = request.getParameter("status");
        String raw_isFeatured = request.getParameter("isFeatured");
        String raw_from = request.getParameter("from");
        String raw_to = request.getParameter("to");

        Boolean status = null;
        Boolean isFeatured = null;
        Date from = null;
        Date to = null;

        if (author == null) {
            author = "";
        }

        if (raw_from != null && raw_from.trim().length() > 0) {
            from = Date.valueOf(request.getParameter("from"));
        }
        if (raw_to != null && raw_to.trim().length() > 0) {
            to = Date.valueOf(request.getParameter("to"));
        }

        if (from != null && to != null) { // send error if from date is greater than to date
            if (from.compareTo(to) > 0) {
                request.setAttribute("errorMessage", FROMGREATERTHANTOERROR);

                request.getSession().setAttribute("author", author);
                request.getSession().setAttribute("status", raw_status);
                request.getSession().setAttribute("isFeatured", raw_isFeatured);
                request.getSession().setAttribute("from", from);
                request.getSession().setAttribute("to", to);
                request.getSession().setAttribute("title", search);
                request.getSession().setAttribute("subcategory", subcategory);

                request.setAttribute("categories", categories);
                request.setAttribute("pageindex", pageindex);
                request.setAttribute("search", search);
                request.setAttribute("url", "bloglist");
                request.setAttribute("querystring", queryString);

                request.getRequestDispatcher(POSTLISTURL).forward(request, response);
                return;
            }
        }

        if (raw_status != null) {
            status = raw_status.equals("true");
        }
        if (raw_isFeatured != null) {
            isFeatured = raw_isFeatured.equals("true");
        }

        ArrayList<Post> posts = dbBlogforSearch.getPostByFilter(subcategories, author, search, status, isFeatured,
                from, to, "", sort, pageindex, PAGESIZE);

        int count = dbBlogforSearch.countSearchBlog(search, subcateID);
        int totalpage = (count % PAGESIZE == 0) ? (count / PAGESIZE) : (count / PAGESIZE) + 1;

        request.getSession().setAttribute("author", author);
        request.getSession().setAttribute("status", raw_status);
        request.getSession().setAttribute("isFeatured", raw_isFeatured);
        request.getSession().setAttribute("from", from);
        request.getSession().setAttribute("to", to);
        request.getSession().setAttribute("title", search);
        request.getSession().setAttribute("subcategory", subcategory);

        request.setAttribute("categories", categories);
        request.setAttribute("posts", posts);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("count", count);
        request.setAttribute("search", search);
        request.setAttribute("url", "bloglist");
        request.setAttribute("querystring", queryString);

        request.getRequestDispatcher(POSTLISTURL).forward(request, response);
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
