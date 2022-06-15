/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.PricePackage;
import model.Registration;
import model.User;

/**
 *
 * @author ADMIN
 */
public class RegistrationDBContext extends DBContext {

    public Registration getARegistration(int id) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [Registration] \n"
                    + "  WHERE registrationID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Registration r = new Registration();
                UserDBContext userDBContext = new UserDBContext();
                User user = userDBContext.getUser(rs.getString("username"));
                User updater = userDBContext.getUser(rs.getString("updatedBy"));
                PricePackageDBContext pricePackageDBContext = new PricePackageDBContext();
                PricePackage p = pricePackageDBContext.getPricePackageByID(rs.getInt("pricePackageID"));
                CourseDBContext courseDBContext = new CourseDBContext();
                Course c = courseDBContext.getCourse(rs.getInt("courseID"));
                r.setRegistrationID(rs.getInt("registrationID"));
                r.setUser(user);
                r.setRegistrationTime(rs.getTimestamp("registrationTime"));
                r.setCourse(c);
                r.setPricePackages(p);
                r.setTotalCost(rs.getDouble("totalCost"));
                r.setStatus(rs.getBoolean("status"));
                r.setValidFrom(rs.getDate("validFrom"));
                r.setValidTo(rs.getDate("validTo"));
                r.setUpdatedBy(updater);
                return r;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int searchCount(String subject, String email, Date fromDate, Date toDate, Boolean status) {
        int count = 0;
        try {
            String sql = "Select COUNT(*) as IndexCount from Registration r join Course c\n"
                    + "  on r.courseID = c.courseID\n"
                    + "  where CAST(registrationTime as date) < ?\n"
                    + "  and CAST(registrationTime as date) > ?\n"
                    + "  and c.courseName like ?\n"
                    + "  and (r.username like ? or r.updatedBy like ?)\n";
            if (status != null) {
                sql += "and r.[status] = ?\n";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, toDate);
            stm.setDate(2, fromDate);
            stm.setString(3, "%" + subject + "%");
            stm.setString(4, "%" + email + "%");
            stm.setString(5, "%" + email + "%");
            if (status != null) {
                stm.setBoolean(6, status);
            }
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("IndexCount");
                return count;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public ArrayList<Registration> searchRegistration(String subject, String email, Date fromDate, Date toDate, Boolean status, int pageIndex, int pageSize, String sortBy, String orderBy) {
        ArrayList<Registration> list = new ArrayList<>();
        try {
            String sql = "Select r.*, c.courseName from Registration r join Course c\n"
                    + "  on r.courseID = c.courseID\n"
                    + "  where CAST(registrationTime as date) < ?\n"
                    + "  and CAST(registrationTime as date) > ?\n"
                    + "  and c.courseName like ?\n"
                    + "  and (r.username like ? or r.updatedBy like ?)\n";
            if (status != null) {
                sql += "and r.[status] = ?\n";
            }
            sql += "  ORDER BY " + sortBy + " " + orderBy
                    + "\n  OFFSET (?-1)*? ROWS\n"
                    + "  FETCH NEXT ? ROWS ONLY";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, toDate);
            stm.setDate(2, fromDate);
            stm.setString(3, "%" + subject + "%");
            stm.setString(4, "%" + email + "%");
            stm.setString(5, "%" + email + "%");
            if (status != null) {
                stm.setBoolean(6, status);
                stm.setInt(7, pageIndex);
                stm.setInt(8, pageSize);
                stm.setInt(9, pageSize);
            } else {
                stm.setInt(6, pageIndex);
                stm.setInt(7, pageSize);
                stm.setInt(8, pageSize);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Registration r = new Registration();
                UserDBContext userDBContext = new UserDBContext();
                User user = userDBContext.getUser(rs.getString("username"));
                User updater = userDBContext.getUser(rs.getString("updatedBy"));
                PricePackageDBContext pricePackageDBContext = new PricePackageDBContext();
                PricePackage p = pricePackageDBContext.getPricePackageByID(rs.getInt("pricePackageID"));
                CourseDBContext courseDBContext = new CourseDBContext();
                Course c = courseDBContext.getCourse(rs.getInt("courseID"));
                r.setRegistrationID(rs.getInt("registrationID"));
                r.setUser(user);
                r.setRegistrationTime(rs.getTimestamp("registrationTime"));
                r.setCourse(c);
                r.setPricePackages(p);
                r.setTotalCost(rs.getDouble("totalCost"));
                r.setStatus(rs.getBoolean("status"));
                r.setValidFrom(rs.getDate("validFrom"));
                r.setValidTo(rs.getDate("validTo"));
                r.setUpdatedBy(updater);
                list.add(r);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int countAll() {
        int count = 0;
        try {

            String sql = "SELECT COUNT(*) as IndexNum\n"
                    + "  FROM [Registration]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("IndexNum");
                return count;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public ArrayList<Registration> getRegistrations(int pageIndex, int pageSize, String sortBy, String orderBy) {
        ArrayList<Registration> list = new ArrayList<>();
        try {
            String sql = "SELECT *"
                    + "  FROM [Registration]\n";
            sql += "  ORDER BY " + sortBy + " " + orderBy;
            sql += "\n  OFFSET (?-1)*? ROWS\n"
                    + "  FETCH NEXT ? ROWS ONLY";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageIndex);
            stm.setInt(2, pageSize);
            stm.setInt(3, pageSize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Registration r = new Registration();
                UserDBContext userDBContext = new UserDBContext();
                User user = userDBContext.getUser(rs.getString("username"));
                User updater = userDBContext.getUser(rs.getString("updatedBy"));
                PricePackageDBContext pricePackageDBContext = new PricePackageDBContext();
                PricePackage p = pricePackageDBContext.getPricePackageByID(rs.getInt("pricePackageID"));
                CourseDBContext courseDBContext = new CourseDBContext();
                Course c = courseDBContext.getCourse(rs.getInt("courseID"));
                r.setRegistrationID(rs.getInt("registrationID"));
                r.setUser(user);
                r.setRegistrationTime(rs.getTimestamp("registrationTime"));
                r.setCourse(c);
                r.setPricePackages(p);
                r.setTotalCost(rs.getDouble("totalCost"));
                r.setStatus(rs.getBoolean("status"));
                r.setValidFrom(rs.getDate("validFrom"));
                r.setValidTo(rs.getDate("validTo"));
                r.setUpdatedBy(updater);
                list.add(r);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean insertRegistration(int courseID, PricePackage pricePackage, User user) {
        String sql_insert_registration = "INSERT INTO [dbo].[Registration]\n"
                + "           ([username]\n"
                + "           ,[registrationTime]\n"
                + "           ,[courseID]\n"
                + "           ,[pricePackageID]\n"
                + "           ,[totalCost]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?,GETDATE(),?,?,?,1)";
        String sql_insert_user = "INSERT INTO [dbo].[User]\n"
                + "           ([username]\n"
                + "           ,[gender]\n"
                + "           ,[firstName]\n"
                + "           ,[lastName]\n"
                + "           ,[phoneNumber])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";

        float totalPrice;

        if (pricePackage.isIsOnSale()) {
            totalPrice = pricePackage.getSalePrice();
        } else {
            totalPrice = pricePackage.getListPrice();
        }
        UserDBContext userDBContext = new UserDBContext();
        if (userDBContext.isUserExist(user.getAccount().getUsername()) == false) {
            try {
                connection.setAutoCommit(false);
                PreparedStatement stm_insert_registration = connection.prepareStatement(sql_insert_registration);
                stm_insert_registration.setString(1, user.getAccount().getUsername());
                stm_insert_registration.setInt(2, courseID);
                stm_insert_registration.setInt(3, pricePackage.getPricePackageID());
                stm_insert_registration.setFloat(4, totalPrice);
                stm_insert_registration.executeUpdate();

                PreparedStatement stm_insert_user = connection.prepareStatement(sql_insert_user);
                stm_insert_user.setString(1, user.getAccount().getUsername());
                stm_insert_user.setBoolean(2, user.isGender());
                stm_insert_user.setString(3, user.getFirstName());
                stm_insert_user.setString(4, user.getLastName());
                stm_insert_user.setString(5, user.getPhoneNumber());
                stm_insert_user.executeUpdate();
                connection.commit();

            } catch (SQLException ex) {
                Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    connection.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
                //close connection
            }
        } else {
            PreparedStatement stm_insert_registration;
            try {
                stm_insert_registration = connection.prepareStatement(sql_insert_registration);
                stm_insert_registration.setString(1, user.getAccount().getUsername());
                stm_insert_registration.setInt(2, courseID);
                stm_insert_registration.setInt(3, pricePackage.getPricePackageID());
                stm_insert_registration.setFloat(4, totalPrice);
                return stm_insert_registration.executeUpdate() >= 1;
            } catch (SQLException ex) {
                Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return false;
    }

    public boolean isRegistered(String username, int courseID) {
        String sql = "select count(*) as number from Registration\n"
                + "where username = ? and courseID = ?";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setInt(2, courseID);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("number") >= 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public boolean updateRegistration(int courseID, PricePackage pricePackage, Boolean status, int registrationID) {
        String sql = "update Registration\n"
                + "  set courseID = ?, pricePackageID = ?, totalCost = ?, [status] = ?, validFrom=GETDATE(),validTo= dateadd(MONTH,?,GETDATE())\n"
                + "  where registrationID = ?";

        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, courseID);
            stm.setInt(2, pricePackage.getPricePackageID());
            float totalCost = 0;
            if (pricePackage.isIsOnSale()) {
                totalCost = pricePackage.getSalePrice();
            } else {
                totalCost = pricePackage.getListPrice();
            }
            stm.setFloat(3, totalCost);
            stm.setBoolean(4, status);
            stm.setInt(5, pricePackage.getDuration());
            stm.setInt(6, registrationID);
            return stm.executeUpdate() >= 1;
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }
}
