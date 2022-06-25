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
import model.Dimension;
import model.DimensionType;

/**
 *
 * @author ADMIN
 */
public class DimensionDBContext extends DBContext {

    public DimensionType getDimensionType(int typeID) {
        String sql = "select * from DimensionType where typeID = ?";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, typeID);
            rs = stm.executeQuery();
            if (rs.next()) {
                DimensionType d = new DimensionType();
                d.setTypeID(rs.getInt("typeID"));
                d.setTypeName(rs.getString("typeName"));
                return d;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DimensionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Dimension> getDimensionsByCourseID(int courseID) {
        ArrayList<Dimension> dimensions = new ArrayList<>();
        String sql = "select * from Dimension d join CourseDimension c\n"
                + "on d.dimensionID = c.dimensionID\n"
                + "where c.courseID = ?";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, courseID);
            while (rs.next()) {
                Dimension d = new Dimension();
                d.setDimensionID(rs.getInt("dimensionID"));
                d.setDimensionType(getDimensionType(rs.getInt("typeID")));
                d.setDimensionDescription(rs.getString("dimensionDescription"));
                d.setDimensionName(rs.getString("dimensionName"));
                d.setCourseID(rs.getInt("courseID"));
                dimensions.add(d);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DimensionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dimensions;

    }

    public boolean checkExist(int typeID, String dimensionName, String dimensionDescription, int courseID) {
        String sql = "select count(*) as total from Dimension \n"
                + "where typeID = ? and dimensionName = ? and dimensionDescription = ?\n"
                + "and courseid = ?";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, typeID);
            stm.setString(2, dimensionName);
            stm.setString(3, dimensionDescription);
            stm.setInt(4, courseID);
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DimensionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public void insertDimension(Dimension dimension) {
        String sql = "INSERT INTO [dbo].[Dimension]\n"
                + "           ([typeID]\n"
                + "           ,[dimensionName]\n"
                + "           ,[dimensionDescription]\n"
                + "           ,[courseID])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";

        PreparedStatement stm = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, dimension.getDimensionType().getTypeID());
            stm.setString(2, dimension.getDimensionName());
            stm.setString(3, dimension.getDimensionDescription());
            stm.setInt(4, dimension.getCourseID());
        } catch (SQLException ex) {
            Logger.getLogger(DimensionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
