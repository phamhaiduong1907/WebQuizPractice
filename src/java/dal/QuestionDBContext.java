/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import jakarta.servlet.jsp.jstl.sql.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Question;

/**
 *
 * @author Hai Tran
 */
public class QuestionDBContext extends DBContext {

    public Question getQuestion(int questionID) {
        Question question = new Question();
        try {
            String sql = "SELECT [questionID]\n"
                    + "      ,[questionContent]\n"
                    + "      ,[status]\n"
                    + "      ,[mediaURL]\n"
                    + "      ,[lessonID]\n"
                    + "      ,[dimensionID]\n"
                    + "      ,[levelID]\n"
                    + "      ,[explanation]\n"
                    + "      ,[mediaID]\n"
                    + "FROM [dbo].[Question]\n"
                    + "WHERE questionID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, questionID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                LessonDBContext dbLesson = new LessonDBContext();
                LevelDBContext dbLevel = new LevelDBContext();
                DimensionDBContext dbDimension = new DimensionDBContext();
                MediaTypeDBContext dbMediaType = new MediaTypeDBContext();
                AnswerDBContext dbAnswer = new AnswerDBContext();
                question.setQuestionID(rs.getInt("questionID"));
                question.setQuestionContent(rs.getString("questionContent"));
                question.setStatus(rs.getBoolean("status"));
                question.setMediaURL(rs.getString("mediaURL"));
                question.setLesson(dbLesson.getLesson(rs.getInt("lessonID")));
                question.setLevel(dbLevel.getLevel(rs.getInt("levelID")));
                question.setDimension(dbDimension.getDimensionByDimensionID(rs.getInt("dimensionID")));
                question.setExplanation(rs.getString("explanation"));
                question.setMediaType(dbMediaType.getMediaType(rs.getInt("mediaID")));
                question.setAnswers(dbAnswer.getAnswerForQuestion(questionID));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return question;
    }

    public int insertQuestion(String questionContent, String mediaUrl, int lessonID, int dimensionID, int levelID, String explanation, int mediaID, String rawMediaType, int courseID, ArrayList<Answer> answers) {

        String generatedColumns[] = {"ID"};
        String sql_insert_question = "INSERT INTO [dbo].[Question]\n"
                + "           ([questionContent]\n"
                + "           ,[status]\n"
                + "           ,[mediaURL]\n"
                + "           ,[lessonID]\n"
                + "           ,[dimensionID]\n"
                + "           ,[levelID]\n"
                + "           ,[explanation]\n"
                + "           ,[mediaID]\n"
                + "           ,[courseID])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)\n"
                + "\n"
                + "DECLARE @id1 AS int SET @id1 = (SELECT SCOPE_IDENTITY());\n"
                + "UPDATE Question\n"
                + "SET mediaURL = 'question_media_' + CAST(@id1 AS varchar(max)) + ?\n"
                + "WHERE questionID = @id1";
        String sql_insert_answer = "INSERT INTO [dbo].[Answer]\n"
                + "           ([questionID]\n"
                + "           ,[answerContent]\n"
                + "           ,[isTrue])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            connection.setAutoCommit(false);
            PreparedStatement stm_insert_question = connection.prepareStatement(sql_insert_question, generatedColumns);
            stm_insert_question.setString(1, questionContent);
            stm_insert_question.setBoolean(2, false);
            stm_insert_question.setString(3, null);
            stm_insert_question.setInt(4, lessonID);
            stm_insert_question.setInt(5, dimensionID);
            stm_insert_question.setInt(6, levelID);
            stm_insert_question.setString(7, explanation);
            stm_insert_question.setInt(8, mediaID);
            stm_insert_question.setInt(9, courseID);
            stm_insert_question.setString(10, rawMediaType);
            stm_insert_question.executeUpdate();
            ResultSet rs = stm_insert_question.getGeneratedKeys();
            int questionID = 0;
            if (rs.next()) {
                questionID = rs.getInt(1);
            }
            for (Answer a : answers) {
                PreparedStatement stm_insert_answer = connection.prepareStatement(sql_insert_answer);
                stm_insert_answer.setInt(1, questionID);
                stm_insert_answer.setString(2, a.getAnswerContent());
                stm_insert_answer.setBoolean(3, a.isIsTrue());
                stm_insert_answer.executeUpdate();
            }
            connection.commit();
            return questionID;
        } catch (SQLException ex) {
            try {
                Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return -1;
    }

    public int updateQuestion(int questionID, String questionContent, String mediaUrl, int lessonID, int dimensionID, int levelID, String explanation, int mediaID, String rawMediaType, int courseID, ArrayList<Answer> answers) {

        String sql_update_question = "UPDATE [dbo].[Question]\n"
                + "   SET [questionContent] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[mediaURL] = ?\n"
                + "      ,[lessonID] = ?\n"
                + "      ,[dimensionID] = ?\n"
                + "      ,[levelID] = ?\n"
                + "      ,[explanation] = ?\n"
                + "      ,[mediaID] = ?\n"
                + "      ,[courseID] = ?\n"
                + " WHERE questionID = ?\n" 
                + "\n"
                + "DECLARE @id1 AS int SET @id1 = ?;\n"
                + "UPDATE Question\n"
                + "SET mediaURL = 'question_media_' + CAST(@id1 AS varchar(max)) + ?\n"
                + "WHERE questionID = @id1";
        String sql_insert_answer = "INSERT INTO [dbo].[Answer]\n"
                + "           ([questionID]\n"
                + "           ,[answerContent]\n"
                + "           ,[isTrue])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";
        String sql_delete_answer = "DELETE FROM [dbo].[Answer]\n"
                + "      WHERE questionID = ?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement stm_update_question = connection.prepareStatement(sql_update_question);
            stm_update_question.setString(1, questionContent);
            stm_update_question.setBoolean(2, false);
            stm_update_question.setString(3, null);
            stm_update_question.setInt(4, lessonID);
            stm_update_question.setInt(5, dimensionID);
            stm_update_question.setInt(6, levelID);
            stm_update_question.setString(7, explanation);
            stm_update_question.setInt(8, mediaID);
            stm_update_question.setInt(9, courseID);
            stm_update_question.setInt(10, questionID);
            stm_update_question.setInt(11, questionID);
            stm_update_question.setString(12, rawMediaType);
            stm_update_question.executeUpdate();
            PreparedStatement stm_delete_answer = connection.prepareStatement(sql_delete_answer);
            stm_delete_answer.setInt(1, questionID);
            stm_delete_answer.executeUpdate();
            for (Answer a : answers) {
                PreparedStatement stm_insert_answer = connection.prepareStatement(sql_insert_answer);
                stm_insert_answer.setInt(1, questionID);
                stm_insert_answer.setString(2, a.getAnswerContent());
                stm_insert_answer.setBoolean(3, a.isIsTrue());
                stm_insert_answer.executeUpdate();
            }
            connection.commit();
            return questionID;
        } catch (SQLException ex) {
            try {
                Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return -1;

    }
}
