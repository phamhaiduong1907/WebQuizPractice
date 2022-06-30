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
import model.LessonType;

/**
 *
 * @author Zuys
 */
public class LessonTypeDBContext extends DBContext {

    public LessonType getLessonType(int lessonTypeID) {
        LessonType lt = new LessonType();
        try {
            String sql = "SELECT lessonTypeId, lessonTypeName\n"
                    + "FROM LessonType\n"
                    + "WHERE lessonTypeId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lessonTypeID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                lt.setLessonTypeID(lessonTypeID);
                lt.setLessonTypeName(rs.getString("lessonTypeName"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonTypeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lt;
    }

    public ArrayList<LessonType> getLessonTypes() {
        ArrayList<LessonType> lessonTypes = new ArrayList<>();
        try {
            String sql = "SELECT lessonTypeId, lessonTypeName\n"
                    + "FROM LessonType\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                LessonType lt = new LessonType();
                lt.setLessonTypeID(rs.getInt("lessonTypeID"));
                lt.setLessonTypeName(rs.getString("lessonTypeName"));
                lessonTypes.add(lt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonTypeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lessonTypes;
    }
}
