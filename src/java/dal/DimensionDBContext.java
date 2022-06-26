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
        } catch (SQLException ex) {
            Logger.getLogger(DimensionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Dimension> getDimensionPagination(int courseID, int pagesize, int pageindex) {
        String sql = "select d.* from Dimension d join CourseDimension c\n"
                + "on d.dimensionID = c.dimensionID\n"
                + "where courseID = ?\n"
                + "ORDER BY d.dimensionID \n"
                + "OFFSET (?-1)*? ROWS\n"
                + "FETCH NEXT ? ROWS ONLY";

        ArrayList<Dimension> dimensions = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, courseID);
            stm.setInt(2, pageindex);
            stm.setInt(3, pagesize);
            stm.setInt(4, pagesize);
            rs = stm.executeQuery();
            while (rs.next()) {
                Dimension d = new Dimension();
                d.setDimensionID(rs.getInt("dimensionID"));
                d.setDimensionType(getDimensionType(rs.getInt("typeID")));
                d.setDimensionDescription(rs.getString("dimensionDescription"));
                d.setDimensionName(rs.getString("dimensionName"));
                dimensions.add(d);

            }

        } catch (SQLException ex) {
            Logger.getLogger(DimensionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dimensions;

    }

    public int getQuantityDimensionPagination(int courseID) {
        String sql = "select count(*) as total from Dimension d join CourseDimension c\n"
                + "on d.dimensionID = c.dimensionID\n"
                + "where courseID = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, courseID);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DimensionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public void deleteDimension(int courseID, int dimensionID) {
        String sql = "delete from CourseDimension \n"
                + "where courseID = ? and dimensionID = ?";
        
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, courseID);
            stm.setInt(2, dimensionID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DimensionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
