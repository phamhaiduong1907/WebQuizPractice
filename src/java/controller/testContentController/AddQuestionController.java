/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.testContentController;

import controller.AuthorizationController;
import dal.DimensionDBContext;
import dal.LessonDBContext;
import dal.QuestionDBContext;
import dal.TopicDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.util.ArrayList;
import model.Answer;
import model.Dimension;
import model.Topic;
import util.UploadFile;
import util.Validation;

/**
 *
 * @author Hai Tran
 */
@MultipartConfig
public class AddQuestionController extends AuthorizationController {

    private static final String WRONGFILETYPE = "Wrong file input format";
    private static final String MISSINGINPUT = "You must entered required fields";
    private static final String ERRORSQL = "Please try again";

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
        TopicDBContext dbTopic = new TopicDBContext();
        DimensionDBContext dbDimension = new DimensionDBContext();
        int courseID = Integer.parseInt(request.getParameter("courseID"));
        ArrayList<Topic> topics = dbTopic.getTopics(courseID);
        ArrayList<Dimension> dimensions = dbDimension.getDimensionsByCourseID(courseID);
        request.setAttribute("courseID", courseID);
        request.setAttribute("topics", topics);
        request.setAttribute("dimensions", dimensions);
        request.getRequestDispatcher("/view/test_content/question_detail.jsp").forward(request, response);
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
        String rawQuestionContent = request.getParameter("questioncontent");
        String rawLessonID = request.getParameter("lessonID");
        String rawDimensionID = request.getParameter("dimensionID");
        String rawLevelID = request.getParameter("levelID");
        String rawMediaID = request.getParameter("mediaID");
        String rawExplanation = request.getParameter("explanation");
        String[] rawAnswer = request.getParameterValues("answer");
        String[] rawIstrue = request.getParameterValues("istrue");
        Part mediaURL = request.getPart("mediafile");
        String rawMediaType = "." + mediaURL.getContentType().substring(6);
        ArrayList<Answer> answers = new ArrayList<>();
        ArrayList<String> rawParameter = new ArrayList<>();
        rawParameter.add(rawQuestionContent);
        rawParameter.add(rawLessonID);
        rawParameter.add(rawDimensionID);
        rawParameter.add(rawLevelID);
        rawParameter.add(rawExplanation);

        Validation validation = new Validation();

        if (validation.checkNullOrBlank(rawParameter)) {
            int lessonID = Integer.parseInt(rawLessonID);
            int dimensionID = Integer.parseInt(rawDimensionID);
            int levelID = Integer.parseInt(rawLevelID);
            int mediaID = Integer.parseInt(rawMediaID);
            if (!(rawAnswer.length == 0 || rawIstrue.length == 0)) {
                for (int i = 0; i < rawAnswer.length; i++) {
                    Answer answer = new Answer();
                    for (int j = 0; j < rawIstrue.length; j++) {
                        if (i == Integer.parseInt(rawIstrue[j].substring(0, 1)) - 1) {
                            answer.setAnswerContent(rawAnswer[i]);
                            answer.setIsTrue(true);
                            break;
                        } else {
                            answer.setAnswerContent(rawAnswer[i]);
                            answer.setIsTrue(false);
                        }
                    }
                    answers.add(answer);
                }
                if (validation.checkQuestionMedia(mediaURL.getContentType(), mediaID)) {
                    String realPath = null;
                    String realPathWeb = null;
                    switch (mediaID) {
                        case 1:
                            realPath = request.getServletContext().getRealPath("/media/image");
                            realPathWeb = realPath.substring(0, realPath.indexOf("build"));
                            realPathWeb += "web\\media\\image";
                            break;
                        case 2:
                            realPath = request.getServletContext().getRealPath("/media/video");
                            realPathWeb = realPath.substring(0, realPath.indexOf("build"));
                            realPathWeb += "web\\media\\video";
                            break;
                        case 3:
                            realPath = request.getServletContext().getRealPath("/media/audio");
                            realPathWeb = realPath.substring(0, realPath.indexOf("build"));
                            realPathWeb += "web\\media\\audio";
                            break;
                        default:
                            break;
                    }
                    QuestionDBContext dbQuestion = new QuestionDBContext();
                    int questionID = dbQuestion.insertQuestion(rawQuestionContent, mediaURL.getSubmittedFileName(), lessonID, dimensionID, levelID, rawExplanation, mediaID, rawMediaType, 1, answers);
                    String fileName = "question_media_" + questionID + rawMediaType;
                    if (mediaID != 4) {
                        UploadFile.copyPartToFile(mediaURL, realPath + "/" + fileName);
                        UploadFile.copyPartToFile(mediaURL, realPathWeb + "/" + fileName);
                    }
                    response.sendRedirect("viewquestion?questionID=" + questionID);
                }
            } else {
                TopicDBContext dbTopic = new TopicDBContext();
                DimensionDBContext dbDimension = new DimensionDBContext();
                int courseID = Integer.parseInt(request.getParameter("courseID"));
                ArrayList<Topic> topics = dbTopic.getTopics(courseID);
                ArrayList<Dimension> dimensions = dbDimension.getDimensionsByCourseID(courseID);
                request.setAttribute("topics", topics);
                request.setAttribute("courseID", courseID);
                request.setAttribute("dimensions", dimensions);
                request.setAttribute("message", WRONGFILETYPE);
                request.getRequestDispatcher("/view/test_content/question_detail.jsp").forward(request, response);
            }
        } else {
            TopicDBContext dbTopic = new TopicDBContext();
            DimensionDBContext dbDimension = new DimensionDBContext();
            int courseID = Integer.parseInt(request.getParameter("courseID"));
            ArrayList<Topic> topics = dbTopic.getTopics(courseID);
            ArrayList<Dimension> dimensions = dbDimension.getDimensionsByCourseID(courseID);
            request.setAttribute("topics", topics);
            request.setAttribute("courseID", courseID);
            request.setAttribute("dimensions", dimensions);
            request.setAttribute("message", MISSINGINPUT);
            request.getRequestDispatcher("/view/test_content/question_detail.jsp").forward(request, response);
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
