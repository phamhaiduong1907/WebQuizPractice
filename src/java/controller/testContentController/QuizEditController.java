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
import dal.UserQuizDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Category;
import model.Quiz;
import model.QuizLevel;
import model.QuizType;
import util.Validation;

/**
 *
 * @author long
 */
public class QuizEditController extends HttpServlet {

    Validation v = new Validation();
    CategoryDBContext cadbc = new CategoryDBContext();
    SubCategoryDBContext scadbc = new SubCategoryDBContext();
    QuizAttributeDBContext qabdc = new QuizAttributeDBContext();
    CourseDBContext cdbc = new CourseDBContext();
    QuizDBContext qdbc = new QuizDBContext();
    QuizAttributeDBContext qadbc = new QuizAttributeDBContext();

    static final String updateOk = "Update Successfully!";
    static final String updateErr = "Err! Couldn't Update.";
    static final String insertOk = "Insert successfully!";
    static final String insertErr = "Err! Couldn't Insert.";

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
            out.println("<title>Servlet QuizEditController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuizEditController at " + request.getContextPath() + "</h1>");
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
        int ID;
        ArrayList<Category> cates = cadbc.getCategories(2);
        request.setAttribute("cates", cates);
        ArrayList<QuizLevel> levels = qabdc.getQuizLevels();
        request.setAttribute("levels", levels);
        ArrayList<QuizType> types = qabdc.getQuizTypes();
        request.setAttribute("types", types);
        if (request.getParameter("id") != null) {
            ID = Integer.parseInt(request.getParameter("id"));
            Quiz quiz = qdbc.getAQuiz(ID);
            request.setAttribute("quiz", quiz);
            if (quiz.getIsTaken() == true) {
                String prohibited = "This quiz is not available for editing!";
                response.sendRedirect("../quizzes/view?id=" + ID + "&mess=" + prohibited);
            } else {
                request.getRequestDispatcher("../view/test_content/QuizEdit.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("../view/test_content/QuizEdit.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int ID;
        String name, des, note;
        int courseID, level, numQ, duration, type;
        float pass;

        String raw_name = request.getParameter("name");
        String raw_courseID = request.getParameter("courseID");
        String raw_level = request.getParameter("level");
        String raw_numQ = request.getParameter("numOfQ");
        String raw_duration = request.getParameter("duration");
        String raw_pass = request.getParameter("passRate");
        String raw_type = request.getParameter("type");
        String raw_des = request.getParameter("des");
        String raw_note = request.getParameter("note");

        ArrayList<String> arr = new ArrayList<>();
        arr.add(raw_name);
        arr.add(raw_courseID);
        arr.add(raw_level);
        arr.add(raw_numQ);
        arr.add(raw_duration);
        arr.add(raw_pass);
        arr.add(raw_type);
        arr.add(raw_des);
        arr.add(raw_note);

        if (v.checkNullOrBlank(arr)) {
            name = raw_name;
            courseID = Integer.parseInt(raw_courseID);
            level = Integer.parseInt(raw_level);
            numQ = Integer.parseInt(raw_numQ);
            duration = Integer.parseInt(raw_duration);
            pass = Float.parseFloat(raw_pass);
            type = Integer.parseInt(raw_type);
            des = raw_des;
            note = raw_note;

            if (request.getParameter("ID") != null) {
                ID = Integer.parseInt(request.getParameter("ID"));
                if (qdbc.updateQuiz(ID, numQ, pass, level, duration, type, courseID, name, des, false, note) == true) {
                    log("true");
                    response.sendRedirect("../quizzes/view?id=" + ID + "&mess=" + updateOk);

                } else {
                    log("false");
                    response.sendRedirect("../quizzes/view?id=" + ID + "&mess=" + updateErr);
                }
            } else {
                if (qdbc.addQuiz(numQ, pass, level, duration, type, courseID, name, des, note)) {
                    ID = qdbc.getLatestID();
                    if (type == 1) {
                        UserQuizDBContext uqdbc = new UserQuizDBContext();
                        ArrayList<Integer> ques = uqdbc.getRandomQuestion(numQ, courseID);
                        log(ques.toString());
                        uqdbc.insertQuestion(ID, ques);
                    }
                    response.sendRedirect("../quizzes/view?id=" + ID + "&mess=" + insertOk);
                } else {
                    response.sendRedirect("../quizzes" + "?mess=" + insertErr);
                }
            }
        } else {
            log("Something is missing!");
            response.sendRedirect("../quizzes?mess=" + "Something is missing!");
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
