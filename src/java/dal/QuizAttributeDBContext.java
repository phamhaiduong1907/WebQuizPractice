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
import model.QuizLevel;
import model.QuizType;

/**
 *
 * @author long
 */
public class QuizAttributeDBContext extends DBContext {

    public QuizType getQuizType(int ID) {
        try {
            String sql = "SELECT * FROM [QuizType] WHERE [quizTypeID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, ID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                QuizType qt = new QuizType();
                qt.setQuizTypeID(rs.getInt("quizTypeID"));
                qt.setQuizTypeName(rs.getString("quizTypeName"));
                return qt;
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuizAttributeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<QuizType> getQuizTypes() {
        try {
            ArrayList<QuizType> arr = new ArrayList<>();
            String sql = "SELECT * FROM [QuizType]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                QuizType qt = new QuizType();
                qt.setQuizTypeID(rs.getInt("quizTypeID"));
                qt.setQuizTypeName(rs.getString("quizTypeName"));
                arr.add(qt);
            }
            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(QuizAttributeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public QuizLevel getQuizLevel(int ID) {
        try {
            String sql = "SELECT * FROM [QuizLevel] WHERE [levelID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, ID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                QuizLevel ql = new QuizLevel();
                ql.setLevelID(rs.getInt("levelID"));
                ql.setLevelName(rs.getString("levelName"));
                return ql;
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuizAttributeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<QuizLevel> getQuizLevels() {
        try {
            ArrayList<QuizLevel> arr = new ArrayList<>();
            String sql = "SELECT * FROM [QuizLevel]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                QuizLevel ql = new QuizLevel();
                ql.setLevelID(rs.getInt("levelID"));
                ql.setLevelName(rs.getString("levelName"));
                arr.add(ql);
            }
            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(QuizAttributeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
