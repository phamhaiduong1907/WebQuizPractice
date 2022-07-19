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
    public ArrayList<Course> getCourses(int pageindex, int pagesize, Account account) {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT courseID, courseName, subCategoryID, [status], isFeatured, \n"
                    + "[description], tagline, updatedDate, briefInfo, thumbnailURL, [owner]\n"
                    + "FROM Course where [status] = 1 \n"
                    + "ORDER BY updatedDate DESC\n"
                    + "OFFSET (? - 1) * ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageindex);
            stm.setInt(2, pagesize);
            stm.setInt(3, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                RegistrationDBContext registrationDBContext = new RegistrationDBContext();
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
                c.setOwner(rs.getString("owner"));
                c.setPricePackages(pricePackages);
                if (account != null) {
                    c.setIsRegistered(registrationDBContext.isRegistered(account.getUsername(), c.getCourseID()));
                }
                courses.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courses;
    }

    public ArrayList<Course> getManageCourses(int pageindex, int pagesize, Account account) {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT courseID, courseName, subCategoryID, [status], isFeatured, \n"
                    + "[description], tagline, updatedDate, briefInfo, thumbnailURL, [owner]\n"
                    + "FROM Course \n"
                    + "ORDER BY courseID ASC\n"
                    + "OFFSET (? - 1) * ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageindex);
            stm.setInt(2, pagesize);
            stm.setInt(3, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                RegistrationDBContext registrationDBContext = new RegistrationDBContext();
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
                c.setOwner(rs.getString("owner"));
                c.setPricePackages(pricePackages);
                if (account != null) {
                    c.setIsRegistered(registrationDBContext.isRegistered(account.getUsername(), c.getCourseID()));
                }
                courses.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courses;
    }

    public int countCourse() {
        int total = 0;
        try {
            String sql = "SELECT COUNT(*) AS Total\n"
                    + "FROM Course where status = 1";
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

    public ArrayList<Course> getCoursesForHomePage(Account account) {
        ArrayList<Course> courses = new ArrayList<>();
        RegistrationDBContext registrationDBContext = new RegistrationDBContext();
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
                + "	  s.categoryID, owner\n"
                + "  FROM [dbo].[Course] c JOIN Subcategory s\n"
                + "  on c.[subCategoryID] = s.[subcategoryID] where [status] = 1 ";
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
                c.setPricePackages(pricePackages);
                c.setOwner(rs.getString("owner"));

                if (account != null) {
                    c.setIsRegistered(registrationDBContext.isRegistered(account.getUsername(), c.getCourseID()));
                }
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
            String sql = "SELECT courseID, courseName, subcategoryID, [status], isFeatured, [description], tagline, updatedDate, briefInfo, thumbnailURL, owner\n"
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
                c.setOwner(rs.getString("owner"));

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

    public ArrayList<Course> searchCourse(String search, String subcateID, String sort, int pageindex, int pagesize,
            Account account) {
        ArrayList<Course> courses = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        try {
            String sql = "SELECT courseID, courseName, subCategoryID, [status], isFeatured, \n"
                    + "[description], tagline, updatedDate, briefInfo, thumbnailURL, [owner] \n"
                    + "FROM Course where [status] = 1 \n"
                    + "AND courseName LIKE ?";
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
                RegistrationDBContext registrationDBContext = new RegistrationDBContext();

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
                c.setOwner(rs.getString("owner"));
                c.setPricePackages(pricePackages);
                if (account != null) {
                    c.setIsRegistered(registrationDBContext.isRegistered(account.getUsername(), c.getCourseID()));
                }
                courses.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courses;
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
                c.setOwner(rs.getString("owner"));
                courses.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courses;
    }

    public ArrayList<Course> searchManageCourse(String search, String subcateID, String sort, int pageindex, int pagesize) {
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
            String offset = " AND [status] = ? order by courseID asc OFFSET ? * ? ROWS FETCH NEXT ? ROWS ONLY";
            sb.append(offset);
            String sql_final = sb.toString();
            PreparedStatement stm = connection.prepareStatement(sql_final);
            stm.setString(1, "%" + search + "%");
            stm.setString(2, sort);
            stm.setInt(3, pageindex - 1);
            stm.setInt(4, pagesize);
            stm.setInt(5, pagesize);
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
                c.setOwner(rs.getString("owner"));
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

    public int countManageSearchCourse(String search, String subcateID, String status) {
        try {
            StringBuilder sb = new StringBuilder();
            String sql = "SELECT COUNT(*) AS Total\n"
                    + "FROM Course\n";
            sb.append(sql);
            if (!search.trim().isEmpty() || !subcateID.trim().isEmpty() || !status.trim().isEmpty()) {
                sb.append("WHERE ");
                if (!search.trim().isEmpty()) {
                    String and = "courseName LIKE ? ";
                    sb.append(and);
                }
                if (!subcateID.trim().isEmpty()) {
                    if (!search.trim().isEmpty()) {
                        sb.append("AND");
                    }
                    String and = " subcategoryID IN(" + subcateID + ") ";
                    sb.append(and);
                }
                if (!status.trim().isEmpty()) {
                    if (!search.trim().isEmpty() || !subcateID.trim().isEmpty()) {
                        sb.append("AND");
                    }
                    String and = " [status] = " + status;
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

    public boolean updateCourse(Course course) {
        String sql = "UPDATE [dbo].[Course]\n"
                + "   SET [courseName] = ?\n"
                + "      ,[subCategoryID] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[isFeatured] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[tagline] = ?\n"
                + "      ,[updatedDate] = GETDATE()\n"
                + "      ,[briefInfo] = ?"
                + ", [owner] = ?\n"
                + " WHERE courseID = ?";

        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, course.getCourseName());
            stm.setInt(2, course.getSubcategory().getSubcategoryID());
            stm.setBoolean(3, course.isStatus());
            stm.setBoolean(4, course.isIsFeatured());
            stm.setString(5, course.getDescription());
            stm.setString(6, course.getTagline());
            stm.setString(7, course.getBriefInfo());
            stm.setString(8, course.getOwner());
            stm.setInt(9, course.getCourseID());
            return stm.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean isPublishable(int courseID) {
        String sql = "select count(*) as total from Course c join PricePackage p\n"
                + "on c.courseID = p.courseID\n"
                + "where c.courseID = ? and p.[status] = 1";
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, courseID);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean authEdit(int courseID, String owner) {
        String sql = "select count(*) as total from Course\n"
                + "where courseID = ? and [owner] = ?";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, courseID);
            stm.setString(2, owner);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Course> getCourseNameAndID() {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "Select courseID, courseName from Course";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setCourseID(rs.getInt("courseID"));
                course.setCourseName(rs.getString("courseName"));
                courses.add(course);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courses;
    }

    public ArrayList<Course> getCoursesLearners() {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "select c.courseID, c.courseName, s.subCategoryName\n"
                    + ",count(case when r.[status] is null or r.[status] \n"
                    + "= 0 then null else 1 end) as learners\n"
                    + "from Course c inner join SubCategory s\n"
                    + "on s.subCategoryID = c.subCategoryID\n"
                    + "left join Registration r on r.courseID = c.courseID\n"
                    + "group by c.courseID, c.courseName, s.subCategoryName\n"
                    + "order by c.courseID";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setCourseID(rs.getInt("courseID"));
                course.setCourseName(rs.getString("courseName"));
                Subcategory subcategory = new Subcategory();
                subcategory.setSubcategoryName(rs.getString("subCategoryName"));
                course.setSubcategory(subcategory);
                course.setLearners(rs.getInt("learners"));
                courses.add(course);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return courses;
    }

    public ArrayList<Course> getPopularSubjects() {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "select top 5 c.courseID, c.courseName, c.thumbnailURL, s.subCategoryName, \n"
                    + "count(r.username) as learners\n"
                    + "from Course c inner join Registration r on c.courseID = r.courseID\n"
                    + "inner join SubCategory s on s.subCategoryID = c.subCategoryID\n"
                    + "where r.[status] = 1 group by c.courseID, c.courseName, s.subCategoryName, c.thumbnailURL\n"
                    + "order by learners desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setCourseID(rs.getInt("courseID"));
                course.setCourseName(rs.getString("courseName"));
                course.setThumbnailUrl(rs.getString("thumbnailURL"));
                Subcategory subcategory = new Subcategory();
                subcategory.setSubcategoryName(rs.getString("subCategoryName"));
                course.setSubcategory(subcategory);
                course.setLearners(rs.getInt("learners"));
                courses.add(course);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return courses;
    }

    public ArrayList<Course> getCourseNameAndIDForUser(Account account) {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT courseID, courseName FROM Course\n"
                    + "WHERE courseID IN \n"
                    + "(SELECT courseID FROM Registration \n"
                    + "WHERE username = ?\n"
                    + "AND validFrom <= GETDATE()\n"
                    + "AND GETDATE() <= validTo)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, account.getUsername());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setCourseID(rs.getInt("courseID"));
                course.setCourseName(rs.getString("courseName"));
                courses.add(course);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courses;
    }

    public ArrayList<Course> getUserCourse(String username) {
        try {
            ArrayList<Course> list = new ArrayList<>();
            String sql = "SELECT * FROM Registration\n"
                    + "where username = ?\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                CourseDBContext cdbc = new CourseDBContext();
                Course c = new Course();
                c = cdbc.getCourseByCourseID(rs.getInt("courseID"), null);
                list.add(c);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
