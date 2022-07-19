/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.marketingController;

import controller.AuthorizationController;
import dal.BlogDBContext;
import dal.CategoryDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.util.ArrayList;
import model.Category;
import model.Post;
import model.User;
import util.UploadFile;
import util.Validation;

/**
 *
 * @author ADMIN
 */
@MultipartConfig
public class ModifyPostController extends AuthorizationController {

    private static final String WRONGFILETYPE = "The format must be png, jpg or jpeg";
    private static final String MISSINGINPUT = "You must entered required fields";
    private static final String ERRORSQL = "Please try again";

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
        CategoryDBContext dbCate = new CategoryDBContext();
        BlogDBContext blogDBContext = new BlogDBContext();
        int postID = Integer.parseInt(request.getParameter("postID"));

        Post post = blogDBContext.getPost(postID);
        ArrayList<Category> categories = dbCate.getCategories(1);
        request.setAttribute("categories", categories);
        request.setAttribute("post", post);
        request.getRequestDispatcher("../view/marketing/modify_post.jsp").forward(request, response);
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
        String raw_title = request.getParameter("title");
        String raw_briefInfo = request.getParameter("briefInfo");
        String raw_subcategoryID = request.getParameter("subcategoryID");
        String raw_description = request.getParameter("description");
        String raw_isStatus = request.getParameter("isStatus");
        String raw_isFeatured = request.getParameter("isFeatured");
        Part thumbnail = request.getPart("thumbnail");

        boolean isFeatured;
        boolean isStatus;
        int subCategoryID;
        try {
            int postID = Integer.parseInt(request.getParameter("postID"));

            User user = (User) request.getSession().getAttribute("user");
            ArrayList<String> strings = new ArrayList<>();
            strings.add(raw_title);
            strings.add(raw_briefInfo);
            strings.add(raw_subcategoryID);
            strings.add(raw_description);
            strings.add(raw_isFeatured);
            strings.add(raw_isStatus);

            Validation validation = new Validation();

            if (validation.checkNullOrBlank(strings)) { // check other attributes other than thumbnail to be not null
                if (thumbnail.getContentType().equals("image/jpg")
                        || thumbnail.getContentType().equals("image/png")
                        || thumbnail.getContentType().equals("image/jpeg")
                        && thumbnail.getSize() > 0) {// update thumbnail 
                    isFeatured = raw_isFeatured.equals("true");
                    isStatus = raw_isStatus.equals("true");
                    subCategoryID = Integer.parseInt(raw_subcategoryID);

                    BlogDBContext blogDBContext = new BlogDBContext();

                    boolean updateSucessfully = blogDBContext.updatePost(subCategoryID, raw_title, raw_briefInfo,
                            raw_description, isFeatured, isStatus, postID);
                    if (updateSucessfully) {
                        String fileName = "post_thumbnail_id" + postID + ".png";
                        String realPath = request.getServletContext().getRealPath("/images/blog");
                        String realPathWeb = realPath.substring(0, realPath.indexOf("build"));
                        realPathWeb += "web\\images\\blog";

                        UploadFile.copyPartToFile(thumbnail, realPath + "/" + fileName);
                        UploadFile.copyPartToFile(thumbnail, realPathWeb + "/" + fileName);

//                        thumbnail.write(realPathWeb + "/" + fileName); // inserting to the local file destination
                        response.sendRedirect("view?postID=" + postID);
                    } else {
                        response.sendRedirect("view?postID=" + postID + "&errorMessage=" + ERRORSQL);

                    }

                } else if (thumbnail.getSize() == 0) {// update other attributes except thumbnail
                    isFeatured = raw_isFeatured.equals("true");
                    isStatus = raw_isStatus.equals("true");
                    subCategoryID = Integer.parseInt(raw_subcategoryID);

                    BlogDBContext blogDBContext = new BlogDBContext();

                    boolean updateSucessfully = blogDBContext.updatePost(subCategoryID, raw_title, raw_briefInfo,
                            raw_description, isFeatured, isStatus, postID);
                    if (updateSucessfully) {
                        response.sendRedirect("view?postID=" + postID);
                    } else {
                        response.sendRedirect("view?postID=" + postID + "&errorMessage=" + ERRORSQL);

                    }
                } else {
                    CategoryDBContext dbCate = new CategoryDBContext();

                    ArrayList<Category> categories = dbCate.getCategories(1);
                    request.setAttribute("categories", categories);
                    request.setAttribute("errorMessage", WRONGFILETYPE);
                    request.getRequestDispatcher("../view/marketing/add_post.jsp").forward(request, response);

                }

            } else {
                response.sendRedirect("view?postID=" + postID + "&errorMessage=" + MISSINGINPUT);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
