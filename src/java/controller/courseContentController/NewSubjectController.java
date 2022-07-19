/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.courseContentController;

import controller.AuthorizationController;
import dal.AccountDBContext;
import dal.CategoryDBContext;
import dal.CourseDBContext;
import dal.SubCategoryDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import model.Account;
import model.Category;
import model.Course;
import model.Subcategory;
import util.Validation;

/**
 *
 * @author Zuys
 */
@MultipartConfig(location = "E:\\Semester 5\\SWP391\\QuizWebsite-Iteration2-Complete\\summer2022-se1617-g6\\web\\images\\thumbnails", fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class NewSubjectController extends AuthorizationController {

    final static String SUBJECTPICTUREURI = "E:\\Semester 5\\SWP391\\QuizWebsite-Iteration2-Complete\\summer2022-se1617-g6\\web\\images\\thumbnails";

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
        AccountDBContext dbAccount = new AccountDBContext();
        CategoryDBContext dbCate = new CategoryDBContext();
        SubCategoryDBContext dbSubcate = new SubCategoryDBContext();
        int categoryid = 1;

        ArrayList<Category> categories = dbCate.getCategories(2);
        ArrayList<Subcategory> subcategories = dbSubcate.getSubcategories(categoryid);
        ArrayList<Account> accounts = dbAccount.getAccountByRole(2);
        if (accounts != null) {
            request.setAttribute("cid", categoryid);
            request.setAttribute("categories", categories);
            request.setAttribute("subcategories", subcategories);
            request.setAttribute("expertList", accounts);
            request.getRequestDispatcher("view/course_content/new_subject.jsp").forward(request, response);
        }
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
        Validation v = new Validation();
        AccountDBContext dbAccount = new AccountDBContext();
        CategoryDBContext dbCate = new CategoryDBContext();
        SubCategoryDBContext dbSubcate1 = new SubCategoryDBContext();
        SubCategoryDBContext dbSubcate2 = new SubCategoryDBContext();
        CourseDBContext dbCourse1 = new CourseDBContext();
        CourseDBContext dbCourse2 = new CourseDBContext();
        int categoryid = 1;
        int courseID = dbCourse1.getTopCourseID();

        Part subjectPic = request.getPart("profilePicture");
        String subjectPicName = extractFileName(subjectPic);
        subjectPicName = new File(subjectPicName).getName();
        String raw_category = request.getParameter("category");
        String raw_subjectName = request.getParameter("name");
        String raw_owner = request.getParameter("owner");
        String raw_subcategoryid = request.getParameter("subcategory");
        String raw_featured = request.getParameter("featured");
        String raw_description = request.getParameter("description");
        String[] input = {raw_subjectName, raw_owner, raw_subcategoryid, raw_featured, raw_description};

        if ((raw_subjectName == null || raw_subjectName.trim().length() == 0)
                && (raw_owner == null || raw_owner.trim().length() == 0) && (raw_description == null || raw_description.trim().length() == 0)) {
            if (raw_category != null && raw_category.trim().length() != 0) {
                categoryid = Integer.parseInt(raw_category);
            }

            ArrayList<Category> categories = dbCate.getCategories(2);
            ArrayList<Subcategory> subcategories = dbSubcate2.getSubcategories(categoryid);
            ArrayList<Account> accounts = dbAccount.getAccountByRole(2);
            if (accounts != null) {
                request.setAttribute("cid", categoryid);
                request.setAttribute("categories", categories);
                request.setAttribute("subcategories", subcategories);
                request.setAttribute("expertList", accounts);
                request.getRequestDispatcher("view/course_content/new_subject.jsp").forward(request, response);
            }
        } else if (v.checkNullOrBlank(input)) {
            Account owner = dbAccount.isExistAccount(raw_owner);
            if (owner != null && owner.getRole().getRoleID() == 2) {
                if (checkFileType(subjectPicName)) {
                    try {
                        subjectPic.write(this.getFolderUpload().getAbsolutePath() + File.separator + subjectPicName);
                    } catch (IOException e) {
                        request.setAttribute("create_subject_status", "Write file to disk failed");
                    }

                    File file = new File(SUBJECTPICTUREURI + "\\" + subjectPicName);
                    if (file.exists()) {
                        Path source = Paths.get(SUBJECTPICTUREURI + "\\" + subjectPicName);
                        Files.move(source, source.resolveSibling(SUBJECTPICTUREURI + "\\" + courseID + ".jpeg"), StandardCopyOption.REPLACE_EXISTING);
                        Course course = new Course();
                        course.setCourseID(courseID);
                        course.setCourseName(raw_subjectName);
                        course.setOwner(raw_owner);
                        course.setSubcategory(dbSubcate1.getSubcategory(Integer.parseInt(raw_subcategoryid)));
                        course.setStatus(false);
                        course.setIsFeatured(raw_featured.matches("1"));
                        course.setDescription(raw_description);
                        course.setThumbnailUrl(courseID + ".jpeg");

                        if (dbCourse2.insertCourse(course)) {
                            request.setAttribute("create_subject_status", "Add course successfully!");
                        } else {
                            request.setAttribute("create_subject_status", "Add course failed! Please try again!");
                        }
                    } else {
                        request.setAttribute("create_subject_status", "Error saving file!");
                    }
                } else {
                    request.setAttribute("create_subject_status", "You need to use an picture!");
                }
            } else {
                request.setAttribute("create_subject_status", "Owner does not exist or is not an expert!");
            }
        } else {
            request.setAttribute("create_subject_status", "No input problem!");
        }

        if (raw_category != null && raw_category.trim().length() != 0) {
            categoryid = Integer.parseInt(raw_category);
        }

        ArrayList<Category> categories = dbCate.getCategories(2);
        ArrayList<Subcategory> subcategories = dbSubcate2.getSubcategories(categoryid);
        ArrayList<Account> accounts = dbAccount.getAccountByRole(2);
        if (accounts != null) {
            request.setAttribute("cid", categoryid);
            request.setAttribute("categories", categories);
            request.setAttribute("subcategories", subcategories);
            request.setAttribute("expertList", accounts);
            request.getRequestDispatcher("view/course_content/new_subject.jsp").forward(request, response);
        }
    }

    private boolean checkFileType(String fileName) {
        return fileName.toLowerCase().endsWith("jpg") || fileName.toLowerCase().endsWith("jpeg") || fileName.toLowerCase().endsWith("png") || fileName.toLowerCase().endsWith("gif");
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    public File getFolderUpload() {
        File folderUpload = new File(SUBJECTPICTUREURI);
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
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
