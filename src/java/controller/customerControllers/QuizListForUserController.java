/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customerControllers;

import dal.CourseDBContext;
import dal.QuizDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.Course;
import model.Quiz;
import model.QuizType;

/**
 *
 * @author Hai Tran
 */
public class QuizListForUserController extends HttpServlet {

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
        Account account = (Account) request.getSession().getAttribute("account");
        QuizDBContext dbQuiz = new QuizDBContext();
        CourseDBContext dbCourse = new CourseDBContext();
        ArrayList<Course> courses = dbCourse.getCourseNameAndIDForUser(account);
        ArrayList<Quiz> quizzes = dbQuiz.getQuizzesByUser(account);
        ArrayList<String> listName = new ArrayList<>();
        for (Quiz quiz : quizzes) {
            listName.add(quiz.getQuizName());
        }
        request.setAttribute("listName", listName);
        request.setAttribute("quizzes", quizzes);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("view/customer/userquizlist.jsp").forward(request, response);
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
        Account account = (Account) request.getSession().getAttribute("account");
        QuizDBContext dbQuiz = new QuizDBContext();
        CourseDBContext dbCourse = new CourseDBContext();
        String courseName, quizName;
        if (request.getParameter("course") != null) {
            courseName = (String) request.getParameter("course").trim();
        } else {
            courseName = "";
        }
        if (request.getParameter("quiz") != null) {
            quizName = (String) request.getParameter("quiz").trim();
        } else {
            quizName = "";
        }
        ArrayList<Course> courses = dbCourse.getCourseNameAndIDForUser(account);
        ArrayList<Quiz> quizzes = dbQuiz.searchQuizzes(quizName, courseName, "1");
        ArrayList<Quiz> quizzelist = dbQuiz.getQuizzesByUser(account);
        ArrayList<String> listName = new ArrayList<>();
        for (Quiz quiz : quizzelist) {
            listName.add(quiz.getQuizName());
        }
        request.setAttribute("listName", listName);
        request.setAttribute("quizzelist", quizzelist);
        request.setAttribute("quizzes", quizzes);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("view/customer/userquizlist.jsp").forward(request, response);
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
