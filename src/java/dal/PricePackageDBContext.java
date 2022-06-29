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
        String sql = "SELECT [pricePackageID],[priceName],[duration],[status],[listPrice],[salePrice],[description],[courseID],isOnSale\n"
                + "                FROM [dbo].[PricePackage] p inner join LessonPricePackage lp on p.pricePackageID = lp.pricePackageID where lessonID = ? and [status] = 1 order by listPrice ASC";

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
    
}
