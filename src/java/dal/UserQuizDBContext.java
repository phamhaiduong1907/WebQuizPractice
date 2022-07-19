/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Quiz;
import model.TakenUserQuiz;

/**
 *
 * @author long
 */
public class UserQuizDBContext extends DBContext {

    public TakenUserQuiz getATakenQuiz(int ID) {
        try {
            String sql = "SELECT qh.[quizHistoryID]\n"
                    + "                             ,qh.[quizID]\n"
                    + "                             ,[username]\n"
                    + "                             ,CONVERT(date, startTime) as TakenDate\n"
                    + "                             ,DATEDIFF(ss,startTime, endTime) as Duration\n"
                    + "                             ,[mark]\n"
                    + "                             ,[questionGroup]\n"
                    + "                             ,[TopicID]\n"
                    + "                             ,[DimensionID]\n"
                    + "                             FROM [QuizHistory] qh join [Quiz] q on qh.quizID = q.quizID\n"
                    + "                            WHERE qh.[QuizHistoryID] = ?\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, ID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                QuizDBContext qdbc = new QuizDBContext();
                TopicDBContext tdbc = new TopicDBContext();
                DimensionDBContext ddbc = new DimensionDBContext();
                TakenUserQuiz t = new TakenUserQuiz();
                Quiz q = new Quiz();
                q = qdbc.getAQuiz(rs.getInt("quizID"));
                t.setUserQuizID(rs.getInt("quizHistoryID"));
                t.setQuiz(q);
                t.setTakenDate(rs.getDate("TakenDate"));
                t.setMark(rs.getInt("mark"));
                t.setDuration(LocalTime.ofSecondOfDay(rs.getInt("duration")));
                t.setQuestionType(rs.getBoolean("questionGroup"));
                if (rs.getBoolean("questionGroup") == false) {
                    t.setTopic(tdbc.getTopic(rs.getInt("TopicID")));
                } else {
                    t.setDimension(ddbc.getDimensionByDimensionID(rs.getInt("DimensionID")));
                }
                return t;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserQuizDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<TakenUserQuiz> getListFromUser(String username) {
        try {
            ArrayList<TakenUserQuiz> list = new ArrayList<>();
            String sql = "SELECT [quizHistoryID]\n"
                    + "         ,[quizID]\n"
                    + "         ,[username]\n"
                    + "         ,CONVERT(date, startTime) as TakenDate\n"
                    + "         ,DATEDIFF(ss,startTime, endTime) as Duration\n"
                    + "         ,[mark]\n"
                    + "         FROM [QuizHistory]\n"
                    + "         WHERE username like '%" + username + "%'\n"
                    + "         ORDER BY startTime desc\n";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                QuizDBContext qdbc = new QuizDBContext();
                TakenUserQuiz t = new TakenUserQuiz();
                Quiz q = new Quiz();
                q = qdbc.getAQuiz(rs.getInt("quizID"));
                t.setUserQuizID(rs.getInt("quizHistoryID"));
                t.setQuiz(q);
                t.setTakenDate(rs.getDate("TakenDate"));
                t.setMark(rs.getInt("mark"));
                t.setDuration(LocalTime.ofSecondOfDay(rs.getInt("duration")));
                list.add(t);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(UserQuizDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<TakenUserQuiz> searchTaken(String username, int pageindex, int subjectID) {
        try {
            ArrayList<TakenUserQuiz> list = new ArrayList<>();
            String sql = "SELECT [quizHistoryID]\n"
                    + "         ,q.[quizID]\n"
                    + "         ,[username]\n"
                    + "         ,CONVERT(date, startTime) as TakenDate\n"
                    + "         ,DATEDIFF(ss,startTime, endTime) as Duration\n"
                    + "         ,[mark]\n"
                    + "         FROM [QuizHistory] qh join [Quiz] q\n"
                    + "         ON qh.quizID = q.quizID"
                    + "         WHERE username like '%" + username + "%'\n"
                    + "         AND q.courseID = ?\n"
                    + "         ORDER BY endTime desc\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                QuizDBContext qdbc = new QuizDBContext();
                TakenUserQuiz t = new TakenUserQuiz();
                Quiz q = new Quiz();
                q = qdbc.getAQuiz(rs.getInt("quizID"));
                t.setUserQuizID(rs.getInt("quizHistoryID"));
                t.setQuiz(q);
                t.setTakenDate(rs.getDate("TakenDate"));
                t.setMark(rs.getInt("mark"));
                t.setDuration(LocalTime.ofSecondOfDay(rs.getInt("duration")));
                list.add(t);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(UserQuizDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    public int countTaken(String username) {
//        try {
//            int count = 0;
//            String sql = "SELECT COUNT(*) as counter"
//                    + "         FROM [QuizHistory]\n"
//                    + "         WHERE username like '%" + username + "%'";
//            PreparedStatement stm = connection.prepareStatement(sql);
//            ResultSet rs = stm.executeQuery();
//            if (rs.next()) {
//                count = rs.getInt("counter");
//                return count;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserQuizDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return 0;
//    }
//    public int countSearch(String username, int subjectID) {
//        try {
//            int count = 0;
//            String sql = "SELECT COUNT(*) as counter"
//                    + "         FROM [QuizHistory] qh join [Quiz] q\n"
//                    + "         ON qh.quizID = q.quizID\n"
//                    + "         WHERE username like '%" + username + "%'"
//                    + "         AND q.courseID = ?";
//            PreparedStatement stm = connection.prepareStatement(sql);
//            stm.setInt(1, subjectID);
//            ResultSet rs = stm.executeQuery();
//            if (rs.next()) {
//                count = rs.getInt("counter");
//                return count;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserQuizDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return 0;
//    }
    public ArrayList<Integer> getRandomQuestion(int numQ, int courseID) {
        try {
            ArrayList<Integer> list = new ArrayList<>();
            String sql = "SELECT TOP " + numQ + " *\n"
                    + "  FROM Question WHERE courseID = ?\n"
                    + "  ORDER BY NEWID()";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("questionID");
                list.add(ID);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(UserQuizDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Boolean insertQuestion(int QuizID, ArrayList<Integer> questionIDs) {
        try {
            int order = 1;
            String sql = "INSERT INTO [QuizQuestion]\n"
                    + "            ([QuizID]\n"
                    + "           ,[questionID]\n"
                    + "           ,[order])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            //connection.setAutoCommit(false);
            for (Integer ID : questionIDs) {
                stm.setInt(1, QuizID);
                stm.setInt(2, ID);
                stm.setInt(3, order++);
                stm.addBatch();
            }
            stm.executeBatch();
            //connection.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserQuizDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int getLatestID() {
        try {
            String sql = "select top 1 * from Quiz order by quizID desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("quizID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserQuizDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Boolean InsertUserQuiz(Quiz quiz) {
        int last = getLatestID() + 1;
        try {
            String sql = "INSERT INTO [Quiz]\n"
                    + "           ([numOfQuestion]\n"
                    + "           ,[levelID]\n"
                    + "           ,[quizTypeID]\n"
                    + "           ,[courseID]\n"
                    + "           ,[quizName]\n"
                    + "           ,[questionGroup]\n"
                    + "           ,[TopicID]\n"
                    + "           ,[DimensionID]\n"
                    + "           ,[ownerType])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,1\n"
                    + "           ,1\n"
                    + "           ,?\n"
                    + "           ,'Practice Test \n" + last + "'"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,1)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, quiz.getNumOfQuestion());
            stm.setInt(2, quiz.getCourse().getCourseID());
            stm.setBoolean(3, quiz.getQuestionType());
            if (quiz.getQuestionType() == false && quiz.getTopic() == null) {
                stm.setNull(4, Types.INTEGER);
                stm.setNull(5, Types.INTEGER);
            } else if (quiz.getQuestionType() == false && quiz.getTopic() != null) {
                stm.setInt(4, quiz.getTopic().getTopicID());
                stm.setNull(5, Types.INTEGER);
            }
            if (quiz.getQuestionType() == true && quiz.getDimension() == null) {
                stm.setNull(4, Types.INTEGER);
                stm.setNull(5, Types.INTEGER);
            } else if (quiz.getQuestionType() == true && quiz.getDimension() != null) {
                stm.setNull(4, Types.INTEGER);
                stm.setInt(5, quiz.getDimension().getDimensionID());
            }
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserQuizDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
