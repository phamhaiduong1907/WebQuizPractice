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
import model.Course;
import model.PricePackage;
import model.Subcategory;

/**
 *
 * @author ADMIN
 */
public class CourseDBContext extends DBContext {

    public ArrayList<Course> getCourses(int pageindex, int pagesize) {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT courseID, courseName, subCategoryID, [status], isFeatured, \n"
                    + "[description], tagline, updatedDate, briefInfo, thumbnailURL, [owner]\n"
                    + "FROM Course \n"
                    + "ORDER BY updatedDate DESC\n"
                    + "OFFSET (? - 1) * ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageindex);
            stm.setInt(2, pagesize);
            stm.setInt(3, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ArrayList<PricePackage> pricePackages = new ArrayList<>();
                PricePackageDBContext pricePackageDBContext = new PricePackageDBContext();
                pricePackages = pricePackageDBContext.getPricePackagesByCourseID(rs.getInt("courseID"));
                SubCategoryDBContext dbSubCate = new SubCategoryDBContext();
                Course c = new Course();
                c.setCourseName(rs.getString("courseName"));
                c.setCourseID(rs.getInt("courseID"));
                c.setSubcategory(dbSubCate.getSubcategory(rs.getInt("subCategoryID")));
                c.setStatus(rs.getBoolean("status"));
                c.setIsFeatured(rs.getBoolean("isFeatured"));
                c.setDescription(rs.getString("description"));
                c.setTagline(rs.getString("tagline"));
                c.setUpdatedDate(rs.getDate("updatedDate"));
                c.setBriefInfo(rs.getString("briefInfo"));
                c.setThumbnailUrl(rs.getString("thumbnailURL"));
                c.setPricePackages(pricePackages);
                courses.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courses;
    }

    public ArrayList<Course> getCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        String sql = "SELECT [courseID]\n"
                + "      ,[courseName]\n"
                + "      ,s.[subcategoryID]\n"
                + "      ,[status]\n"
                + "      ,[isFeatured]\n"
                + "      ,[description]\n"
                + "      ,[tagline]\n"
                + "      ,[updatedDate]\n"
                + "      ,[briefInfo]\n"
                + "      ,[thumbnailURL],\n"
                + "	  s.subcategoryName,\n"
                + "	  s.categoryID\n"
                + "  FROM [dbo].[Course] c JOIN Subcategory s\n"
                + "  on c.[subCategoryID] = s.[subcategoryID]";
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                ArrayList<PricePackage> pricePackages = new ArrayList<>();
                PricePackageDBContext pricePackageDBContext = new PricePackageDBContext();
                pricePackages = pricePackageDBContext.getPricePackagesByCourseID(rs.getInt("courseID"));
                Course c = new Course();
                Subcategory ca = new Subcategory();
                c.setCourseName(rs.getString("courseName"));
                c.setCourseID(rs.getInt("courseID"));
                ca.setCategoryID(rs.getInt("categoryID"));
                ca.setSubcategoryID(rs.getInt("subCategoryID"));
                ca.setSubcategoryName(rs.getString("subCategoryName"));
                c.setSubcategory(ca);
                c.setStatus(rs.getBoolean("status"));
                c.setIsFeatured(rs.getBoolean("isFeatured"));
                c.setDescription(rs.getString("description"));
                c.setTagline(rs.getString("tagline"));
                c.setUpdatedDate(rs.getDate("updatedDate"));
                c.setBriefInfo(rs.getString("briefInfo"));
                c.setThumbnailUrl(rs.getString("thumbnailURL"));
                c.setPricePackages(pricePackages);
                courses.add(c);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return courses;
    }

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

    public Course getCourseByCourseID(int id) {
        String sql = "SELECT [courseID]\n"
                + "      ,[courseName]\n"
                + "      ,[subCategoryID]\n"
                + "      ,[status]\n"
                + "      ,[isFeatured]\n"
                + "      ,[description]\n"
                + "      ,[tagline]\n"
                + "      ,[updatedDate]\n"
                + "      ,[briefInfo]\n"
                + "      ,[thumbnailURL]\n"
                + "  FROM [dbo].[Course] where courseID = ?";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                ArrayList<PricePackage> pricePackages = new ArrayList<>();
                PricePackageDBContext pricePackageDBContext = new PricePackageDBContext();
                pricePackages = pricePackageDBContext.getPricePackagesByCourseID(rs.getInt("courseID"));
                Course c = new Course();
                Subcategory ca = new Subcategory();
                c.setCourseName(rs.getString("courseName"));
                c.setCourseID(rs.getInt("courseID"));
                ca.setCategoryID(rs.getInt("categoryID"));
                ca.setCategoryID(rs.getInt("subCategoryID"));
                ca.setSubcategoryName(rs.getString("subCategoryName"));
                c.setSubcategory(ca);
                c.setStatus(rs.getBoolean("status"));
                c.setIsFeatured(rs.getBoolean("isFeatured"));
                c.setDescription(rs.getString("description"));
                c.setTagline(rs.getString("tagline"));
                c.setUpdatedDate(rs.getDate("updatedDate"));
                c.setBriefInfo(rs.getString("briefInfo"));
                c.setThumbnailUrl(rs.getString("thumbnailURL"));
                c.setPricePackages(pricePackages);

                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public boolean insertCourse(Course course) {
        String sql = "INSERT INTO [dbo].[Course]\n"
                + "           ([courseID]\n"
                + "           ,[courseName]\n"
                + "           ,[subCategoryID]\n"
                + "           ,[status]\n"
                + "           ,[isFeatured]\n"
                + "           ,[description]\n"
                + "           ,[updatedDate]\n"
                + "           ,[thumbnailURL]\n"
                + "           ,[owner])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?)";
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, course.getCourseID());
            stm.setString(2, course.getCourseName());
            stm.setInt(3, course.getSubcategory().getSubcategoryID());
            stm.setBoolean(4, course.isStatus());
            stm.setBoolean(5, course.isIsFeatured());
            stm.setString(6, course.getDescription());
            stm.setDate(7, sqlDate);
            stm.setString(8, course.getThumbnailUrl());
            stm.setString(9, course.getOwner());
            return stm.executeUpdate() >= 1;

        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public int getTopCourseID() {
        String sql = "select top 1 courseID from Course order by courseID desc";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("courseID") + 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
        return 0;
    }

    public int countCourse() {
        int total = 0;
        try {
            String sql = "SELECT COUNT(*) AS Total\n"
                    + "FROM Course";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                total = rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public ArrayList<Course> searchCourse(String search, String subcateID, String sort, int pageindex, int pagesize) {
        ArrayList<Course> courses = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        try {
            String sql = "SELECT courseID, courseName, subCategoryID, [status], isFeatured, \n"
                    + "[description], tagline, updatedDate, briefInfo, thumbnailURL, [owner] \n"
                    + "FROM Course\n"
                    + "WHERE courseName LIKE ?";
            sb.append(sql);
            if (!subcateID.isEmpty()) {
                String and = " AND subcategoryID IN(" + subcateID + ")";
                sb.append(and);
            }
            String offset = " ORDER BY [updatedDate] " + sort + " OFFSET ? * ? ROWS FETCH NEXT ? ROWS ONLY";
            sb.append(offset);
            String sql_final = sb.toString();
            PreparedStatement stm = connection.prepareStatement(sql_final);
            stm.setString(1, "%" + search + "%");
            stm.setInt(2, pageindex - 1);
            stm.setInt(3, pagesize);
            stm.setInt(4, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ArrayList<PricePackage> pricePackages = new ArrayList<>();
                PricePackageDBContext pricePackageDBContext = new PricePackageDBContext();
                pricePackages = pricePackageDBContext.getPricePackagesByCourseID(rs.getInt("courseID"));
                SubCategoryDBContext dbSubCate = new SubCategoryDBContext();
                Course c = new Course();
                c.setCourseName(rs.getString("courseName"));
                c.setCourseID(rs.getInt("courseID"));
                c.setSubcategory(dbSubCate.getSubcategory(rs.getInt("subCategoryID")));
                c.setStatus(rs.getBoolean("status"));
                c.setIsFeatured(rs.getBoolean("isFeatured"));
                c.setDescription(rs.getString("description"));
                c.setTagline(rs.getString("tagline"));
                c.setUpdatedDate(rs.getDate("updatedDate"));
                c.setBriefInfo(rs.getString("briefInfo"));
                c.setThumbnailUrl(rs.getString("thumbnailURL"));
                c.setPricePackages(pricePackages);
                courses.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courses;
    }

    public int countSearchCourse(String search, String subcateID) {
        try {
            StringBuilder sb = new StringBuilder();
            String sql = "SELECT COUNT(*) AS Total\n"
                    + "FROM Course\n";
            sb.append(sql);
            if (!search.trim().isEmpty() || !subcateID.isEmpty()) {
                sb.append("WHERE ");
                if (!search.trim().isEmpty()) {
                    String and = "courseName LIKE ? ";
                    sb.append(and);
                }
                if (!subcateID.isEmpty()) {
                    if (!search.trim().isEmpty()) {
                        sb.append("AND");
                    }
                    String and = " subcategoryID IN(" + subcateID + ")";
                    sb.append(and);
                }
            }
            String sql_final = sb.toString();
            PreparedStatement stm = connection.prepareStatement(sql_final);
            if (!search.trim().isEmpty()) {
                stm.setString(1, "%" + search + "%");
            }
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

}
