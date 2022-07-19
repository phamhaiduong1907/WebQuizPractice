/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.QuizHistory;

/**
 *
 * @author ADMIN
 */
public class QuizHistoryDBContext extends DBContext {

    public QuizHistory getQuizHistory(int id) {
        String sql = "select * from QuizHistory\n"
                + "where quizHistoryID = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                QuizHistory qh = new QuizHistory();
                qh.setAccount(new AccountDBContext().getAccount(rs.getString("username")));
                qh.setQuizID(new QuizDBContext().getAQuiz(rs.getInt("quizID")));
                qh.setQuizHistoryID(id);
                qh.setEndTime(rs.getTimestamp("endTime"));
                qh.setStartTime(rs.getTimestamp("startTime"));
                qh.setFinishTime(rs.getTimestamp("finishTime"));
                qh.setMark(rs.getFloat("mark"));
                return qh;

            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizHistoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

}
