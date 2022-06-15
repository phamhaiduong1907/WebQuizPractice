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
import model.Account;
import model.Course;
import model.PricePackage;
import model.Subcategory;

/**
 *
 * @author ADMIN
 */
public class CourseDBContext extends DBContext {

    /**
     *
     * @return all the courses in the database
     */
    public ArrayList<Course> getCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        String sql = "Select * from Course";
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setCourseID(rs.getInt("courseID"));
                c.setCourseName(rs.getString("courseName"));
                SubCategoryDBContext db = new SubCategoryDBContext();
                Subcategory subcat = db.getSubcategory(rs.getInt("subCategoryID"));
                c.setSubcategory(subcat);
                c.setStatus(rs.getBoolean("status"));
                c.setIsFeatured(rs.getBoolean("isFeatured"));
                c.setDescription(rs.getString("description"));
                c.setTagline(rs.getString("tagline"));
                c.setUpdatedDate(rs.getDate("updatedDate"));
                c.setBriefInfo(rs.getString("briefInfo"));
                c.setThumbnailUrl(rs.getString("thumbnailURL"));
                courses.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return courses;
    }

    /**
     *
     * @param courseID
     * @return the course with the matching courseID
     */
    public Course getCourse(int courseID) {
        Course c = new Course();
        try {
            String sql = "SELECT courseID, courseName, subcategoryID, [status], isFeatured, [description], tagline, updatedDate, briefInfo, thumbnailURL\n"
                    + "FROM Course\n"
                    + "WHERE courseID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                ArrayList<PricePackage> pricePackages = new ArrayList<>();
                PricePackageDBContext pricePackageDBContext = new PricePackageDBContext();
                pricePackages = pricePackageDBContext.getPricePackagesByCourseID(rs.getInt("courseID"));
                SubCategoryDBContext dbSubCate = new SubCategoryDBContext();
                c.setSubcategory(dbSubCate.getSubcategory(rs.getInt("subcategoryID")));
                c.setCourseID(rs.getInt("courseID"));
                c.setCourseName(rs.getString("courseName"));
                c.setStatus(rs.getBoolean("status"));
                c.setIsFeatured(rs.getBoolean("isFeatured"));
                c.setDescription(rs.getString("description"));
                c.setTagline(rs.getString("tagline"));
                c.setUpdatedDate(rs.getDate("updatedDate"));
                c.setBriefInfo(rs.getString("briefInfo"));
                c.setThumbnailUrl(rs.getString("thumbnailURL"));
                c.setPricePackages(pricePackages);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public Course getCourseByCourseID(int id, Account account) {
        Course c = new Course();
        RegistrationDBContext registrationDBContext = new RegistrationDBContext();

        try {
            String sql = "SELECT courseID, courseName, subcategoryID, [status], isFeatured, [description], tagline, updatedDate, briefInfo, thumbnailURL\n"
                    + "FROM Course\n"
                    + "WHERE courseID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                ArrayList<PricePackage> pricePackages = new ArrayList<>();
                PricePackageDBContext pricePackageDBContext = new PricePackageDBContext();
                pricePackages = pricePackageDBContext.getPricePackagesByCourseID(rs.getInt("courseID"));
                SubCategoryDBContext dbSubCate = new SubCategoryDBContext();
                c.setSubcategory(dbSubCate.getSubcategory(rs.getInt("subcategoryID")));
                c.setCourseID(rs.getInt("courseID"));
                c.setCourseName(rs.getString("courseName"));
                c.setStatus(rs.getBoolean("status"));
                c.setIsFeatured(rs.getBoolean("isFeatured"));
                c.setDescription(rs.getString("description"));
                c.setTagline(rs.getString("tagline"));
                c.setUpdatedDate(rs.getDate("updatedDate"));
                c.setBriefInfo(rs.getString("briefInfo"));
                c.setThumbnailUrl(rs.getString("thumbnailURL"));
                c.setPricePackages(pricePackages);
                if (account != null) {
                    c.setIsRegistered(registrationDBContext.isRegistered(account.getUsername(), c.getCourseID()));
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public ArrayList<Course> getCoursesBySubcategory(int subcategoryID) {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [Course] WHERE subCategoryID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subcategoryID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setCourseID(rs.getInt("courseID"));
                c.setCourseName(rs.getString("courseName"));
                SubCategoryDBContext db = new SubCategoryDBContext();
                Subcategory subcat = db.getSubcategory(rs.getInt("subCategoryID"));
                c.setSubcategory(subcat);
                c.setStatus(rs.getBoolean("status"));
                c.setIsFeatured(rs.getBoolean("isFeatured"));
                c.setDescription(rs.getString("description"));
                c.setTagline(rs.getString("tagline"));
                c.setUpdatedDate(rs.getDate("updatedDate"));
                c.setBriefInfo(rs.getString("briefInfo"));
                c.setThumbnailUrl(rs.getString("thumbnailURL"));
                courses.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courses;
    }
}
