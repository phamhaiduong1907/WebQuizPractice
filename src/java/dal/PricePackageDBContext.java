/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DimensionType;
import model.PricePackage;

/**
 *
 * @author ADMIN
 */
public class PricePackageDBContext extends DBContext {

    public ArrayList<PricePackage> getPricePackagesByCourseID(int courseID) {
        ArrayList<PricePackage> pricePackages = new ArrayList<>();
        String sql = "SELECT [pricePackageID],[priceName],[duration],[status],[listPrice],[salePrice],[description],[courseID],isOnSale\n"
                + "                FROM [dbo].[PricePackage] where courseID = ? and [status] = 1 order by listPrice ASC";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, courseID);
            rs = stm.executeQuery();
            while (rs.next()) {
                PricePackage p = new PricePackage();
                p.setPricePackageID(rs.getInt("pricePackageID"));
                p.setPriceName(rs.getString("priceName"));
                p.setDuration(rs.getInt("duration"));
                p.setStatus(rs.getBoolean("status"));
                p.setListPrice(rs.getFloat("listPrice"));
                p.setSalePrice(rs.getFloat("salePrice"));
                p.setDescription(rs.getString("description"));
                p.setIsOnSale(rs.getBoolean("isOnSale"));
                pricePackages.add(p);

            }

        } catch (SQLException ex) {
            Logger.getLogger(PricePackageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pricePackages;
    }
    public ArrayList<PricePackage> getPricePackagesByLessonID(int lessonID) {
        ArrayList<PricePackage> pricePackages = new ArrayList<>();
        String sql = "SELECT p.[pricePackageID],[priceName],[duration],[status],[listPrice],[salePrice],[description],[courseID],isOnSale\n"
                + "   FROM [dbo].[PricePackage] p inner join LessonPricePackage lp on p.pricePackageID = lp.pricePackageID where lessonID = ? and [status] = 1 order by listPrice ASC";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, lessonID);
            rs = stm.executeQuery();
            while (rs.next()) {
                PricePackage p = new PricePackage();
                p.setPricePackageID(rs.getInt("pricePackageID"));
                p.setPriceName(rs.getString("priceName"));
                p.setDuration(rs.getInt("duration"));
                p.setStatus(rs.getBoolean("status"));
                p.setListPrice(rs.getFloat("listPrice"));
                p.setSalePrice(rs.getFloat("salePrice"));
                p.setDescription(rs.getString("description"));
                p.setIsOnSale(rs.getBoolean("isOnSale"));
                pricePackages.add(p);

            }

        } catch (SQLException ex) {
            Logger.getLogger(PricePackageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pricePackages;
    }

    public PricePackage getPricePackageByID(int id) {
        String sql = "SELECT [pricePackageID]\n"
                + "      ,[priceName]\n"
                + "      ,[duration]\n"
                + "      ,[status]\n"
                + "      ,[listPrice]\n"
                + "      ,[salePrice]\n"
                + "      ,[description]\n"
                + "      ,[courseID]\n"
                + "      ,[isOnSale]\n"
                + "  FROM [dbo].[PricePackage] where pricePackageID = ?";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                PricePackage p = new PricePackage();
                p.setPricePackageID(rs.getInt("pricePackageID"));
                p.setPriceName(rs.getString("priceName"));
                p.setDuration(rs.getInt("duration"));
                p.setStatus(rs.getBoolean("status"));
                p.setListPrice(rs.getFloat("listPrice"));
                p.setSalePrice(rs.getFloat("salePrice"));
                p.setDescription(rs.getString("description"));
                p.setIsOnSale(rs.getBoolean("isOnSale"));
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PricePackageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public ArrayList<PricePackage> basestm(PreparedStatement stm) {
        ArrayList<PricePackage> pricePackages = new ArrayList<>();
        try {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                PricePackage p = new PricePackage();
                p.setPricePackageID(rs.getInt("pricePackageID"));
                p.setPriceName(rs.getString("priceName"));
                p.setDuration(rs.getInt("duration"));
                p.setStatus(rs.getBoolean("status"));
                p.setListPrice(rs.getFloat("listPrice"));
                p.setSalePrice(rs.getFloat("salePrice"));
                p.setDescription(rs.getString("description"));
                p.setIsOnSale(rs.getBoolean("isOnSale"));
                pricePackages.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PricePackageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pricePackages;
    }

    public ArrayList<PricePackage> getPricePackagesPagination(int courseID, int pagesize, int pageindex) {
        String sql = "select * from PricePackage where courseID = ?\n"
                + "ORDER BY pricePackageID \n"
                + "OFFSET (?-1)*? ROWS\n"
                + "FETCH NEXT ? ROWS ONLY";
        PreparedStatement stm = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, courseID);
            stm.setInt(2, pageindex);
            stm.setInt(3, pagesize);
            stm.setInt(4, pagesize);
            return basestm(stm);

        } catch (SQLException ex) {
            Logger.getLogger(PricePackageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public int getQuantityPagination(int courseID) {
        String sql = "select count(pricePackageID) as total from PricePackage where courseID = ?";
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
            Logger.getLogger(PricePackageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public void changeStatusPricePackage(int pricePackageID, boolean status) {
        String sql = "update PricePackage\n"
                + "set [status] = ?\n"
                + "where pricePackageID = ?";

        PreparedStatement stm = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(2, pricePackageID);
            stm.setBoolean(1, status);
            stm.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(PricePackageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean updatePricePackage(String priceName, String raw_duration, boolean status,
            float listPrice, float salePrice, String description, boolean isOnSale, int pricePackageID) {
        String sql = "UPDATE [dbo].[PricePackage]\n"
                + "   SET [priceName] = ?\n"
                + "      ,[duration] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[listPrice] = ?\n"
                + "      ,[salePrice] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[isOnSale] = ?\n"
                + " WHERE [pricePackageID] = ?";

        PreparedStatement stm = null;
        int duration = -1;
        if (raw_duration != null && raw_duration.trim().length() > 0) {
            duration = Integer.parseInt(raw_duration);
        }

        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, priceName);
            if (duration > -1) {
                stm.setInt(2, duration);
            } else {
                stm.setNull(2, Types.INTEGER);
            }
            stm.setBoolean(3, status);
            stm.setFloat(4, listPrice);
            stm.setFloat(5, salePrice);
            stm.setString(6, description);
            stm.setBoolean(7, isOnSale);
            stm.setInt(8, pricePackageID);
            return stm.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PricePackageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean insertPricePackage(String priceName, String raw_duration, boolean status, float listPrice, float salePrice,
            String description, int courseID, boolean isOnSale) {
        String sql = "INSERT INTO [dbo].[PricePackage]\n"
                + "           ([priceName]\n"
                + "           ,[duration]\n"
                + "           ,[status]\n"
                + "           ,[listPrice]\n"
                + "           ,[salePrice]\n"
                + "           ,[description]\n"
                + "           ,[courseID]\n"
                + "           ,[isOnSale])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        PreparedStatement stm = null;

        int duration = -1;
        if (raw_duration != null && raw_duration.trim().length() > 0) {
            duration = Integer.parseInt(raw_duration);
        }

        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, priceName);

            if (duration > -1) {
                stm.setInt(2, duration);
            } else {
                stm.setNull(2, Types.INTEGER);
            }
            stm.setBoolean(3, status);
            stm.setFloat(4, listPrice);
            stm.setFloat(5, salePrice);
            stm.setString(6, description);
            stm.setInt(7, courseID);
            stm.setBoolean(8, isOnSale);
            return stm.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PricePackageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean isTurnOnable(int pricePackageID) {
        String sql = "select COUNT(*) as total from PricePackage p join LessonPricePackage l\n"
                + "on p.pricePackageID = l.pricePackageID\n"
                + "where p.pricePackageID = ?";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, pricePackageID);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PricePackageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

}
