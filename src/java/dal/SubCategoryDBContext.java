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
import model.Subcategory;

/**
 *
 * @author Hai Tran
 */
public class SubCategoryDBContext extends DBContext {

    public ArrayList<Subcategory> getSubcategories(int categoryID) {
        ArrayList<Subcategory> subcategories = new ArrayList<>();
        try {
            String sql = "SELECT sc.subcategoryID, sc.subcategoryName, sc.categoryID\n"
                    + "FROM SubCategory sc inner join Category c\n"
                    + "ON sc.categoryID = c.categoryID\n"
                    + "WHERE sc.categoryID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, categoryID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subcategory sc = new Subcategory();
                sc.setSubcategoryID(rs.getInt("subcategoryID"));
                sc.setSubcategoryName(rs.getString("subcategoryName"));
                sc.setCategoryID(categoryID);
                subcategories.add(sc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubCategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subcategories;
    }

    public Subcategory getSubcategory(int subcategoryID) {
        Subcategory sc = new Subcategory();
        try {
            String sql = "SELECT subcategoryID, categoryID, subcategoryName \n"
                    + "FROM SubCategory\n"
                    + "WHERE subcategoryID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subcategoryID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                sc.setSubcategoryID(rs.getInt("subcategoryID"));
                sc.setCategoryID(rs.getInt("categoryID"));
                sc.setSubcategoryName(rs.getString("subcategoryName"));
                return sc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubCategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sc;
    }
}
