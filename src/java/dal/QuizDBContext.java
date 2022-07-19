/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Quiz;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Course;
import model.QuizLevel;
import model.QuizType;

/**
 *
 * @author long
 */
public class QuizDBContext extends DBContext {

    QuizAttributeDBContext qabdc = new QuizAttributeDBContext();
    CourseDBContext cdbc = new CourseDBContext();

    public Quiz getAQuiz(int ID) {
        try {
            String sql = "SELECT * FROM [Quiz] WHERE [quizID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, ID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Quiz q = new Quiz();
                Course c = cdbc.getCourseByCourseID(rs.getInt("courseID"), null);
                QuizLevel ql = qabdc.getQuizLevel(rs.getInt("levelID"));
                QuizType qt = qabdc.getQuizType(rs.getInt("quizTypeID"));
                q.setQuizID(rs.getInt("quizID"));
                q.setNumOfQuestion(rs.getInt("numOfQuestion"));
                q.setPassRate(rs.getFloat("passRate"));
                q.setLevel(ql);
                q.setDuration(rs.getInt("duration"));
                q.setQuizType(qt);
                q.setCourse(c);
                q.setQuizName(rs.getString("quizName"));
                q.setDescription(rs.getString("description"));
                q.setIsTaken(rs.getBoolean("isTaken"));
                q.setNote(rs.getString("note"));
                return q;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Quiz> getQuizzes() {
        try {
            ArrayList<Quiz> arr = new ArrayList<>();
            String sql = "SELECT * FROM [Quiz] WHERE [ownerType] = 0";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Quiz q = new Quiz();
                Course c = cdbc.getCourseByCourseID(rs.getInt("courseID"), null);
                QuizLevel ql = qabdc.getQuizLevel(rs.getInt("levelID"));
                QuizType qt = qabdc.getQuizType(rs.getInt("quizTypeID"));
                q.setQuizID(rs.getInt("quizID"));
                q.setNumOfQuestion(rs.getInt("numOfQuestion"));
                q.setPassRate(rs.getFloat("passRate"));
                q.setLevel(ql);
                q.setDuration(rs.getInt("duration"));
                q.setQuizType(qt);
                q.setCourse(c);
                q.setQuizName(rs.getString("quizName"));
                q.setDescription(rs.getString("description"));
                q.setIsTaken(rs.getBoolean("isTaken"));
                q.setNote(rs.getString("note"));
                arr.add(q);
            }
            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(QuizDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Quiz> searchQuizzes(String quizName, String courseName, String quizType) {
        try {
            ArrayList<Quiz> arr = new ArrayList<>();
            String sql = "SELECT *, c.courseName from [Quiz] q join [Course] c\n"
                    + "ON q.courseID = c.courseID\n"
                    + "WHERE q.quizName LIKE ?\n"
                    + "AND [ownerType] = 0\n"
                    + "AND c.courseName LIKE ?\n";
            if (quizType.length() != 0) {
                sql += "AND q.quizTypeID IN (" + quizType + ");";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, ("%" + quizName + "%"));
            stm.setString(2, ("%" + courseName + "%"));

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Quiz q = new Quiz();
                Course c = cdbc.getCourseByCourseID(rs.getInt("courseID"), null);
                QuizLevel ql = qabdc.getQuizLevel(rs.getInt("levelID"));
                QuizType qt = qabdc.getQuizType(rs.getInt("quizTypeID"));
                q.setQuizID(rs.getInt("quizID"));
                q.setNumOfQuestion(rs.getInt("numOfQuestion"));
                q.setPassRate(rs.getFloat("passRate"));
                q.setLevel(ql);
                q.setDuration(rs.getInt("duration"));
                q.setQuizType(qt);
                q.setCourse(c);
                q.setQuizName(rs.getString("quizName"));
                q.setDescription(rs.getString("description"));
                q.setIsTaken(rs.getBoolean("isTaken"));
                q.setNote(rs.getString("note"));
                arr.add(q);
            }
            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(QuizDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Boolean updateQuiz(int ID, int numQ, float pass, int level, int duration, int type, int course, String name, String description, Boolean isTaken, String note) {

        String sql = "UPDATE [Quiz]\n"
                + "   SET [numOfQuestion] = ?\n"
                + "      ,[passRate] = ?\n"
                + "      ,[levelID] = ?\n"
                + "      ,[duration] = ?\n"
                + "      ,[quizTypeID] = ?\n"
                + "      ,[courseID] = ?\n"
                + "      ,[quizName] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[isTaken] = ?\n"
                + "      ,[note] = ?"
                + " WHERE [quizID] = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, numQ);
            stm.setFloat(2, pass);
            stm.setInt(3, level);
            stm.setInt(4, duration);
            stm.setInt(5, type);
            stm.setInt(6, course);
            stm.setString(7, name);
            stm.setString(8, description);
            stm.setBoolean(9, isTaken);
            stm.setString(10, note);
            stm.setInt(11, ID);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(QuizDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Boolean addQuiz(int numQ, float pass, int level, int duration, int type, int course, String name, String description, String note) {

        String sql = "INSERT INTO [dbo].[Quiz]\n"
                + "           ([numOfQuestion]\n"
                + "           ,[passRate]\n"
                + "           ,[levelID]\n"
                + "           ,[duration]\n"
                + "           ,[quizTypeID]\n"
                + "           ,[courseID]\n"
                + "           ,[quizName]\n"
                + "           ,[description]"
                + "           ,[isTaken]\n"
                + "           ,[note]"
                + "           ,[ownerType])"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, numQ);
            stm.setFloat(2, pass);
            stm.setInt(3, level);
            stm.setInt(4, duration);
            stm.setInt(5, type);
            stm.setInt(6, course);
            stm.setString(7, name);
            stm.setString(8, description);
            stm.setBoolean(9, false);
            stm.setString(10, note);
            stm.setBoolean(11, false);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(QuizDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Integer getLatestID() {
        int ID;
        try {
            String sql = "Select TOP 1 quizID from [Quiz] WHERE [ownerType] = 0\n"
                    + "ORDER BY quizID desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                ID = rs.getInt("quizID");
                return ID;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Quiz> getQuizzesByCourseID(int courseID) {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [Quiz] WHERE [courseID] = ? AND [ownerType] = 0";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Quiz q = new Quiz();
                Course c = cdbc.getCourseByCourseID(rs.getInt("courseID"), null);
                QuizLevel ql = qabdc.getQuizLevel(rs.getInt("levelID"));
                QuizType qt = qabdc.getQuizType(rs.getInt("quizTypeID"));
                q.setQuizID(rs.getInt("quizID"));
                q.setNumOfQuestion(rs.getInt("numOfQuestion"));
                q.setPassRate(rs.getFloat("passRate"));
                q.setLevel(ql);
                q.setDuration(rs.getInt("duration"));
                q.setQuizType(qt);
                q.setCourse(c);
                q.setQuizName(rs.getString("quizName"));
                q.setDescription(rs.getString("description"));
                q.setIsTaken(rs.getBoolean("isTaken"));
                quizzes.add(q);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quizzes;
    }

    public Boolean delQuiz(int ID) {
        try {
            String sql = "DELETE FROM [QuizQuestion]\n"
                    + "      WHERE [quizID] = ?;\n"
                    + "\n"
                    + "DELETE FROM [Quiz]\n"
                    + "      WHERE [quizID] = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, ID);
            stm.setInt(2, ID);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(QuizDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(QuizDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public int totalQuestion(int courseID) {
        try {
            String sql = "SELECT count(*) as total from [Question]\n"
                    + "where courseID = " + courseID;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Quiz> getQuizzesByUser(Account account) {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        try {
            String sql = "SELECT quizID, numOfQuestion, passRate, levelID, duration, quizTypeID, courseID, quizName, [description], isTaken, note \n"
                    + "FROM Quiz WHERE courseID IN\n"
                    + "(SELECT courseID FROM Registration \n"
                    + "WHERE username = ?\n"
                    + "AND validFrom <= GETDATE()\n"
                    + "AND GETDATE() <= validTo)\n"
                    + "AND quizTypeID = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, account.getUsername());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Quiz q = new Quiz();
                Course c = cdbc.getCourseByCourseID(rs.getInt("courseID"), null);
                QuizLevel ql = qabdc.getQuizLevel(rs.getInt("levelID"));
                QuizType qt = qabdc.getQuizType(rs.getInt("quizTypeID"));
                q.setQuizID(rs.getInt("quizID"));
                q.setNumOfQuestion(rs.getInt("numOfQuestion"));
                q.setPassRate(rs.getFloat("passRate"));
                q.setLevel(ql);
                q.setDuration(rs.getInt("duration"));
                q.setQuizType(qt);
                q.setCourse(c);
                q.setQuizName(rs.getString("quizName"));
                q.setDescription(rs.getString("description"));
                q.setIsTaken(rs.getBoolean("isTaken"));
                quizzes.add(q);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quizzes;
    }

}
