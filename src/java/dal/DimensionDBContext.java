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
        String sql = "select * from Dimension \n"
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

    public boolean insertDimension(int typeID, String dimensionName, String dimensionDescription,
            int courseID) {
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
            stm.setInt(1, typeID);
            stm.setString(2, dimensionName);
            stm.setString(3, dimensionDescription);
            stm.setInt(4, courseID);
            return stm.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DimensionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Dimension> getDimensionPagination(int courseID, int pagesize, int pageindex) {
        String sql = "select d.* from Dimension d \n"
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
        String sql = "select count(*) as total from Dimension \n"
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

    public boolean deleteDimension(int courseID, int dimensionID) {
        String sql = "delete from Dimension \n"
                + "where courseID = ? and dimensionID = ?";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, courseID);
            stm.setInt(2, dimensionID);
            return stm.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DimensionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public ArrayList<DimensionType> getDimensionTypes() {
        ArrayList<DimensionType> d = new ArrayList<>();
        String sql = "select * from DimensionType";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                DimensionType dt = new DimensionType();
                dt.setTypeID(rs.getInt("typeID"));
                dt.setTypeName(rs.getString("typeName"));
                d.add(dt);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PricePackageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return d;

    }

    public Dimension getDimensionByDimensionID(int dimensionID) {
        String sql = "select * from Dimension where dimensionID = ?";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, dimensionID);
            rs = stm.executeQuery();
            if (rs.next()) {
                Dimension d = new Dimension();
                d.setDimensionID(rs.getInt("dimensionID"));
                d.setDimensionName(rs.getString("dimensionName"));
                d.setDimensionDescription(rs.getString("dimensionDescription"));
                d.setDimensionType(getDimensionType(rs.getInt("typeID")));
                return d;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DimensionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public boolean updateDimension(int typeID, String dimensionName, String dimensionDescription,
            int dimensionID) {
        String sql = "UPDATE [dbo].[Dimension]\n"
                + "   SET [typeID] = ?\n"
                + "      ,[dimensionName] = ?\n"
                + "      ,[dimensionDescription] = ?\n"
                + " WHERE dimensionID = ?";

        PreparedStatement stm = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, typeID);
            stm.setString(2, dimensionName);
            stm.setString(3, dimensionDescription);
            stm.setInt(4, dimensionID);
            return stm.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DimensionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkDuplicate(int courseID, String dimensionName) {
        String sql = "select count(*) as total from Dimension\n"
                + "where  dimensionName = ?  and courseID = ?";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, dimensionName);
            stm.setInt(2, courseID);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DimensionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkEdit(int dimensionID, String dimensionName, int courseID) {
        String sql = "select count(*) as total from Dimension \n"
                + "where dimensionID not in (?) and dimensionName = ? and courseID = ?";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, dimensionID);
            stm.setString(2, dimensionName);
            stm.setInt(3, courseID);

            rs = stm.executeQuery();

            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DimensionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

}
