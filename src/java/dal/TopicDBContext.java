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
import model.Topic;

/**
 *
 * @author Zuys
 */
public class TopicDBContext extends DBContext{
    public ArrayList<Topic> getTopics(int courseID) {
        CourseDBContext dbCourse = new CourseDBContext();
        ArrayList<Topic> topics = new ArrayList<>();
        try {
            String sql = "SELECT topicID, topicName, courseID\n"
                    + "FROM Topic\n"
                    + "WHERE courseID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Topic t = new Topic();
                t.setTopicID(rs.getInt("topicID"));
                t.setTopicName(rs.getString("topicName"));
                t.setCourse(dbCourse.getCourse(courseID));
                topics.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TopicDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return topics;
    }
    
    public Topic getTopic(int topicID) {
        CourseDBContext dbCourse = new CourseDBContext();
        Topic t = new Topic();
        try {
            String sql = "SELECT topicID, topicName, courseID\n"
                    + "FROM Topic\n"
                    + "WHERE topicID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, topicID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                t.setTopicID(rs.getInt("topicID"));
                t.setTopicName(rs.getString("topicName"));
                t.setCourse(dbCourse.getCourse(rs.getInt("courseID")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TopicDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
}
