/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.courseContentController;

import controller.AuthorizationController;
import dal.AccountDBContext;
import dal.CategoryDBContext;
import dal.CourseDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.util.ArrayList;
import model.Account;
import model.Category;
import model.Course;
import model.ErrorMessage;
import model.Subcategory;
import util.UploadFile;
import util.Validation;

/**
 *
 * @author ADMIN
 */
@MultipartConfig
public class EditSubjectController extends AuthorizationController {

    private static final String EDITCOURSEDETAILURL = "../view/course_content/course_edit.jsp";
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
        int courseID = Integer.parseInt(request.getParameter("id"));
        CourseDBContext courseDBContext = new CourseDBContext();
        CategoryDBContext categoryDBContext = new CategoryDBContext();
        ErrorMessage errorMessage = new ErrorMessage();

        Course course = courseDBContext.getCourse(courseID);
        ArrayList<Category> categorys = categoryDBContext.getCategories(2); // get category from course
        Category category = categoryDBContext.getCategoryBySubCategoryID(course.getSubcategory().getSubcategoryID());
        boolean isPublishable = courseDBContext.isPublishable(courseID);
        AccountDBContext accountDBContext = new AccountDBContext();

        ArrayList<Account> accounts = accountDBContext.getAccountByRoleID(2);

        if (!isPublishable) {
            request.setAttribute("notifymessage", errorMessage.COURSE_TURNONSTATUS);

        }
        request.setAttribute("accounts", accounts);

        request.setAttribute("isPublishable", isPublishable);
        request.setAttribute("course", course);
        request.setAttribute("category", category);
        request.setAttribute("categorys", categorys);

        request.getRequestDispatcher(EDITCOURSEDETAILURL).forward(request, response);
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
        String raw_courseName = request.getParameter("courseName");
        String raw_subcategoryID = request.getParameter("subcategoryID");
        String raw_isFeatured = request.getParameter("isFeatured");
        String raw_status = request.getParameter("status");
        String raw_description = request.getParameter("description");
        String raw_tagline = request.getParameter("tagline");
        String raw_briefInfo = request.getParameter("briefInfo");
        String raw_courseID = request.getParameter("courseID");
        String raw_owner = request.getParameter("owner");
        Part thumbnail = request.getPart("thumbnail");

        ArrayList<String> input = new ArrayList<>();
        input.add(raw_courseName);
        input.add(raw_subcategoryID);
        input.add(raw_isFeatured);
        input.add(raw_status);
        input.add(raw_description);
        input.add(raw_tagline);
        input.add(raw_briefInfo);
        input.add(raw_owner);
        Validation validation = new Validation();

        int subcategoryID;
        int courseID;
        boolean isFeatured;
        boolean status;
        courseID = Integer.parseInt(raw_courseID);

        if (validation.checkNullOrBlank(input)) { // check other attributes other than thumbnail to be not null
            subcategoryID = Integer.parseInt(raw_subcategoryID);
            isFeatured = raw_isFeatured.equals("true");
            status = raw_status.equals("true");

            Course c = new Course();
            Subcategory s = new Subcategory();
            s.setSubcategoryID(subcategoryID);
            c.setCourseID(courseID);
            c.setCourseName(raw_courseName);
            c.setBriefInfo(raw_briefInfo);
            c.setDescription(raw_description);
            c.setIsFeatured(isFeatured);
            c.setStatus(status);
            c.setSubcategory(s);
            c.setTagline(raw_tagline);
            c.setOwner(raw_owner);

            CourseDBContext courseDBContext = new CourseDBContext();
            boolean updateSuccessfully = false;

            if (thumbnail.getContentType().equals("image/jpg") // Case that that the user want to upload image
                    || thumbnail.getContentType().equals("image/png")
                    || thumbnail.getContentType().equals("image/jpeg")
                    && thumbnail.getSize() > 0) {
                updateSuccessfully = courseDBContext.updateCourse(c);
                if (updateSuccessfully) {
                    String fileName = courseID + ".jpeg";
                    String realPath = request.getServletContext().getRealPath("/images/thumbnails");
                    String realPathWeb = realPath.substring(0, realPath.indexOf("build"));
                    realPathWeb += "web\\images\\thumbnails";

                    UploadFile.copyPartToFile(thumbnail, realPath + "/" + fileName);
                    UploadFile.copyPartToFile(thumbnail, realPathWeb + "/" + fileName);

                    response.sendRedirect("subjectdetail?id=" + courseID);
                } else {
                    response.sendRedirect("subjectedit?id=" + courseID + "&errormessage=" + ERRORSQL);

                }
            } else if (thumbnail.getSize() == 0) { // update other attributes except thumbnail
                updateSuccessfully = courseDBContext.updateCourse(c);
                if (updateSuccessfully) {
                    response.sendRedirect("subjectdetail?id=" + courseID);
                } else {
                    response.sendRedirect("subjectedit?id=" + courseID + "&errormessage=" + ERRORSQL);

                }
            } else {
                // wrong file type
                response.sendRedirect("subjectedit?id=" + courseID + "&errormessage=" + WRONGFILETYPE);

            }

        } else {
            response.sendRedirect("subjectedit?id=" + courseID + "&errormessage=" + MISSINGINPUT);

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
