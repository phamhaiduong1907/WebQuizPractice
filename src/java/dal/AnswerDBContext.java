/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;

/**
 *
 * @author Hai Tran
 */
public class AnswerDBContext extends DBContext {

    public void insertAnswer(int questionID, String answerContent, boolean isTrue) {
        try {
            String sql = "INSERT INTO [dbo].[Answer]\n"
                    + "           ([questionID]\n"
                    + "           ,[answerContent]\n"
                    + "           ,[isTrue])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, questionID);
            stm.setString(2, answerContent);
            stm.setBoolean(3, isTrue);
        } catch (SQLException ex) {
            Logger.getLogger(AnswerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Answer> getAnswerForQuestion(int questionID) {
        ArrayList<Answer> answers = new ArrayList<>();
        try {
            String sql = "SELECT a.answerID, a.answerContent, a.isTrue \n"
                    + "FROM Answer a INNER JOIN Question q\n"
                    + "ON a.questionID = q.questionID\n"
                    + "WHERE a.questionID = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setInt(1, questionID);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Answer answer = new Answer();
                answer.setQuestionID(questionID);
                answer.setAnswerID(rs.getInt("answerID"));
                answer.setAnswerContent(rs.getString("answerContent"));
                answer.setIsTrue(rs.getBoolean("isTrue"));
                answers.add(answer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnswerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answers;
    }
}
