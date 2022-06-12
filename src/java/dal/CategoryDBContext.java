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
import model.Category;

/**
 *
 * @author Hai Tran
 */
public class CategoryDBContext extends DBContext {

    /**
     *
     * @param categoryTypeID
     * @return get sub categories by typeID
     */
    public ArrayList<Category> getCategories(int categoryTypeID) {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            String sql = "SELECT categoryID, categoryName "
                    + "FROM Category\n"
                    + "WHERE categoryTypeID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, categoryTypeID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SubCategoryDBContext dbSubCate = new SubCategoryDBContext();
                Category category = new Category();
                category.setCategoryID(rs.getInt("categoryID"));
                category.setCategoryName(rs.getString("categoryName"));
                category.setSubcategories(dbSubCate.getSubcategories(rs.getInt("categoryID")));
                categories.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }

    /**
     *
     * @param categoryID
     * @return a category with the matching categoryID
     */
    public Category getCategory(int categoryID) {
        try {
            String sql = "SELECT categoryID, categoryName \n"
                    + "FROM Category\n"
                    + "WHERE categoryTypeID = 1\n"
                    + "AND categoryID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, categoryID);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Category category = new Category();
                category.setCategoryID(rs.getInt("categoryID"));
                category.setCategoryName(rs.getString("categoryName"));
                return category;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
