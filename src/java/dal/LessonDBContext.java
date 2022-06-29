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
        TopicDBContext dbTopic = new TopicDBContext();
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

    public ArrayList<Lesson> getSearchLessons(int pageindex, int pagesize, String topicID, int pricePackages, int lessonType, String status, String lessonName) {
        ArrayList<Lesson> lessons = new ArrayList<>();
        LessonTypeDBContext dbLessonType = new LessonTypeDBContext();
        TopicDBContext dbTopic = new TopicDBContext();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        try {
            String sql = "SELECT l.lessonID, lessonTypeId, lessonName, [lessonOrder], topicID, videoLink, htmlContent, status\n"
                    + "FROM Lesson l inner join LessonPricePackage lp on l.lessonID = lp.lessonID\n"
                    + "WHERE topicID in (" + topicID + ")";
            sb.append(sql);
            if (pricePackages != 0 || lessonType != 0 || (status != null && status.trim().length() != 0)) {
                if(pricePackages != 0){
                    String and = " and lp.pricePackageID = ?";
                    sb.append(and);
                    count++;
                }
                if(lessonType != 0){
                    String and =" and l.lessonTypeId = ?";
                    sb.append(and);
                    count++;
                }
                if(!status.matches("All")){
                    String and =" and l.status = ?";
                    sb.append(and);
                    count++;
                }
                if(lessonName != null && lessonName.trim().length() != 0){
                    String and =" and l.lessonName like ?";
                    sb.append(and);
                    count++;
                }
            }
            sb.append(" GROUP BY l.lessonID, l.lessonTypeId, l.lessonName, l.[lessonOrder], l.topicID, l.videoLink, l.htmlContent, l.[status]\n"
                    + "ORDER BY l.lessonID ASC\n"
                    + "OFFSET (? - 1) * ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY");
            String sql_final = sb.toString();
            PreparedStatement stm = connection.prepareStatement(sql_final);
            
            if(pricePackages != 0){
                stm.setInt(1, pricePackages);
                if(lessonType != 0){
                    stm.setInt(2, lessonType);
                    if(!status.matches("All")){
                        stm.setBoolean(3, status.matches("1"));
                        if(lessonName != null && lessonName.trim().length() != 0){
                            stm.setString(4, "%"+lessonName+"%");
                        }
                    }else{
                        if(lessonName != null && lessonName.trim().length() != 0){
                            stm.setString(3, "%"+lessonName+"%");
                        }
                    }
                }else{
                    if(!status.matches("All")){
                        stm.setBoolean(2, status.matches("1"));
                        if(lessonName != null && lessonName.trim().length() != 0){
                            stm.setString(3, "%"+lessonName+"%");
                        }
                    }
                }
            }else{
                if(lessonType != 0){
                    stm.setInt(1, lessonType);
                    if(!status.matches("All")){
                        stm.setBoolean(2, status.matches("1"));
                        if(lessonName != null && lessonName.trim().length() != 0){
                            stm.setString(3, "%"+lessonName+"%");
                        }
                    }
                }else{
                    if(!status.matches("All")){
                        stm.setBoolean(1, status.matches("1"));
                        if(lessonName != null && lessonName.trim().length() != 0){
                            stm.setString(2, "%"+lessonName+"%");
                        }
                    }else{
                        if(lessonName != null && lessonName.trim().length() != 0){
                            stm.setString(1, "%"+lessonName+"%");
                        }
                    }
                }
            }
            stm.setInt(1+count, pageindex);
            stm.setInt(2+count, pagesize);
            stm.setInt(3+count, pagesize);
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
    
    public ArrayList<Lesson> countSearchLesson(String topicID) {
        ArrayList<Lesson> lessons = new ArrayList<>();
        LessonTypeDBContext dbLessonType = new LessonTypeDBContext();
        PricePackageDBContext dbPrice = new PricePackageDBContext();
        try {
            String sql = "SELECT lessonID, lessonTypeId, lessonName, [lessonOrder], topicID, videoLink, htmlContent, [status]\n"
                    + "FROM Lesson \n"
                    + "WHERE topicID in (" + topicID + ")"
                    + "ORDER BY lessonID ASC\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson();
                l.setLessonID(rs.getInt("lessonID"));
                l.setLessonType(dbLessonType.getLessonType(rs.getInt("lessonTypeID")));
                l.setLessonName(rs.getString("lessonName"));
                l.setLessonOrder(rs.getInt("lessonOrder"));
                l.setPricePackages(dbPrice.getPricePackagesByLessonID(rs.getInt("lessonID")));
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

    public Lesson getLesson(int lessonID) {
        LessonTypeDBContext dbLType = new LessonTypeDBContext();
        PricePackageDBContext dbPrice = new PricePackageDBContext();
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
                l.setPricePackages(dbPrice.getPricePackagesByLessonID(rs.getInt("lessonID")));
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
