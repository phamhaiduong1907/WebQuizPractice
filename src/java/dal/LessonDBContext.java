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
import model.Course;
import model.Lesson;
import model.PricePackage;

/**
 *
 * @author Zuys
 */
public class LessonDBContext extends DBContext {

    public ArrayList<Lesson> getLessons(int pageindex, int pagesize, String topicID) {
        ArrayList<Lesson> lessons = new ArrayList<>();
        LessonTypeDBContext dbLessonType = new LessonTypeDBContext();
        try {
            String sql = "SELECT lessonID, lessonTypeId, lessonName, [lessonOrder], topicID, videoLink, htmlContent, status\n"
                    + "FROM Lesson \n"
                    + "WHERE topicID in (" + topicID + ")"
                    + "ORDER BY lessonID ASC\n"
                    + "OFFSET (? - 1) * ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageindex);
            stm.setInt(2, pagesize);
            stm.setInt(3, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson();
                l.setLessonID(rs.getInt("lessonID"));
                l.setLessonType(dbLessonType.getLessonType(rs.getInt("lessonTypeID")));
                l.setLessonName(rs.getString("lessonName"));
                l.setLessonOrder(rs.getInt("lessonOrder"));
                l.setTopicID(rs.getInt("topicID"));
                l.setVideoLink(rs.getString("videoLink"));
                l.setHtmlContent(rs.getString("htmlContent"));
                l.setStatus(rs.getBoolean("status"));
                lessons.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lessons;
    }

    public ArrayList<Lesson> getLessonByTopic(int topicID) {
        ArrayList<Lesson> lessons = new ArrayList<>();
        LessonTypeDBContext dbLessonType = new LessonTypeDBContext();
        try {
            String sql = "SELECT lessonID, lessonTypeId, lessonName, [lessonOrder], topicID, videoLink, htmlContent, [status]\n"
                    + "FROM Lesson\n"
                    + "WHERE topicID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, topicID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson();
                l.setLessonID(rs.getInt("lessonID"));
                l.setLessonType(dbLessonType.getLessonType(rs.getInt("lessonTypeID")));
                l.setLessonName(rs.getString("lessonName"));
                l.setLessonOrder(rs.getInt("lessonOrder"));
                l.setTopicID(rs.getInt("topicID"));
                l.setVideoLink(rs.getString("videoLink"));
                l.setHtmlContent(rs.getString("htmlContent"));
                l.setStatus(rs.getBoolean("status"));
                lessons.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lessons;
    }

    public ArrayList<Lesson> getSearchLessons(int pageindex, int pagesize, String topicID, int pricePackages, int lessonType, String status, String lessonName) {
        ArrayList<Lesson> lessons = new ArrayList<>();
        LessonTypeDBContext dbLessonType = new LessonTypeDBContext();
        TopicDBContext dbTopic = new TopicDBContext();
        StringBuilder sb = new StringBuilder();
        try {
            String sql = "SELECT lessonID, lessonTypeId, lessonName, [lessonOrder], topicID, videoLink, htmlContent, status\n"
                    + "FROM Lesson l inner join LessonPricePackage lp on l.lessonID = lp.lessonID\n"
                    + "WHERE topicID in (" + topicID + ") and l.lessonName like ?";
            sb.append(sql);
            if (pricePackages != 0 || lessonType != 0 || (status != null && status.trim().length() != 0)) {
                if (pricePackages != 0) {
                    String and = " and lp.pricePackageID = " + pricePackages;
                    sb.append(and);
                }
                if (lessonType != 0) {
                    String and = " and l.lessonTypeId = " + lessonType;
                    sb.append(and);
                }
                if (!status.matches("All")) {
                    String and = " and l.status = " + status.matches("1");
                    sb.append(and);
                }

            }
            sb.append(" ORDER BY lessonID ASC\n"
                    + "OFFSET (? - 1) * ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY");
            String sql_final = sb.toString();
            PreparedStatement stm = connection.prepareStatement(sql_final);

            stm.setString(1, "%" + lessonName + "%");
            stm.setInt(2, pageindex);
            stm.setInt(3, pagesize);
            stm.setInt(4, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson();
                l.setLessonID(rs.getInt("lessonID"));
                l.setLessonType(dbLessonType.getLessonType(rs.getInt("lessonTypeID")));
                l.setLessonName(rs.getString("lessonName"));
                l.setLessonOrder(rs.getInt("lessonOrder"));
                l.setTopicID(rs.getInt("topicID"));
                l.setVideoLink(rs.getString("videoLink"));
                l.setHtmlContent(rs.getString("htmlContent"));
                l.setStatus(rs.getBoolean("status"));
                lessons.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lessons;
    }

    public int countLesson(int courseID) {
        int total = 0;
        try {
            String sql = "select COUNT(*) as total from Lesson l \n"
                    + "inner join Topic t on l.topicID = t.topicID \n"
                    + "inner join Course c on t.courseID = c.courseID \n"
                    + "where c.courseID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                total = rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public Lesson getLesson(int lessonID) {
        LessonTypeDBContext dbLType = new LessonTypeDBContext();
        TopicDBContext dbTopic = new TopicDBContext();
        Lesson l = new Lesson();
        try {
            String sql = "SELECT lessonID, lessonTypeId, lessonName, [lessonOrder], topicID, videoLink, htmlContent, status\n"
                    + "FROM Lesson \n"
                    + "WHERE lessonID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lessonID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                l.setLessonID(lessonID);
                l.setLessonName(rs.getString("lessonName"));
                l.setLessonType(dbLType.getLessonType(rs.getInt("lessonTypeId")));
                l.setLessonOrder(rs.getInt("lessonOrder"));
                l.setTopicID(rs.getInt("topicID"));
                l.setVideoLink(rs.getString("videoLink"));
                l.setHtmlContent(rs.getString("htmlContent"));
                l.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    public boolean changeStatus(Lesson lesson) {
        String sql = "UPDATE [dbo].[Lesson]\n"
                + "   SET [status] = ?\n"
                + " WHERE lessonID = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setBoolean(1, !lesson.isStatus());
            stm.setInt(2, lesson.getLessonID());
            return stm.executeUpdate() >= 1;
        } catch (SQLException ex) {
            Logger.getLogger(LessonDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
