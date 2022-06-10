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
import model.Registration;
import model.User;

/**
 *
 * @author ADMIN
 */
public class RegistrationDBContext extends DBContext {

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
                    return false;
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

        return true;
    }

    public ArrayList<Registration> getRegistrations(int pageindex, int pagesize, String username) {
        ArrayList<Registration> registrations = new ArrayList<>();
        CourseDBContext courseDBContext = new CourseDBContext();
        PricePackageDBContext pricePackageDBContext = new PricePackageDBContext();
        String sql = "SELECT * FROM Registration\n"
                + "where username = ?\n"
                + "ORDER BY registrationTime desc\n"
                + "OFFSET (?-1)*? ROWS\n"
                + "FETCH NEXT ? ROWS ONLY";
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setInt(2, pageindex);
            stm.setInt(3, pagesize);
            stm.setInt(4, pagesize);

            rs = stm.executeQuery();
            while (rs.next()) {
                Registration r = new Registration();
                Course c = courseDBContext.getCourse(rs.getInt("courseID"));
                User u = new User();
                User u2 = new User();

                Account a = new Account();
                Account a2 = new Account();

                PricePackage p = pricePackageDBContext.getPricePackageByID(rs.getInt("pricePackageID"));
                r.setRegistrationID(rs.getInt("registrationID"));
                a.setUsername(rs.getString("username"));
                a2.setUsername(rs.getString("updatedBy"));
                u.setAccount(a);
                u2.setAccount(a2);
                r.setUser(u);
                r.setRegistrationTime(rs.getTimestamp("registrationTime"));
                r.setCourse(c);
                r.setPricePackage(p);
                r.setTotalCost(rs.getFloat("totalCost"));
                r.setStatus(rs.getBoolean("status"));

//                
                r.setValidTo(rs.getDate("validTo"));
                r.setValidFrom(rs.getDate("validFrom"));

                if (rs.getString("updatedBy") != null) {
                    r.setUpdatedBy(u2);

                }
                registrations.add(r);

            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return registrations;

    }

    public int getQuantityOfRegistration(String username) {
        String sql = "select count(*) as number   from Registration\n"
                + "where username = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("number");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;

    }

    public ArrayList<Registration> getRegistrationsByFilter(ArrayList<Integer> subcategoryIDs, String courseName, int pageindex, int pagesize, String username) {
        ArrayList<Registration> registrations = new ArrayList<>();
        String sub = ""; // subcategory sql string 

        for (int i = 0; i < subcategoryIDs.size(); i++) { // put them into in sql
            if (i == subcategoryIDs.size() - 1) {
                sub += "?";
            } else {
                sub += "?,";
            }
        }

        String sql = "select r.registrationID,r.username,r.registrationTime,r.courseID,r.pricePackageID,r.totalCost,r.[status]\n"
                + ",r.validFrom,r.validTo,r.updatedBy,c.subCategoryID "
                + " from Registration r join Course c on r.courseID = c.courseID"; // start of the query
        PreparedStatement stm = null;
        CourseDBContext courseDBContext = new CourseDBContext();
        PricePackageDBContext pricePackageDBContext = new PricePackageDBContext();
        if (courseName != null) {// course name filter
            sql += " where upper(courseName) like upper(?) ";
        }

        if (subcategoryIDs.size() > 0) { // subcategory filter
            String sub_sql = " and subCategoryID in (" + sub + ")";
            sql += sub_sql;
        }

        sql += " and username = ? "; // username registration course

        sql += " ORDER BY registrationTime desc OFFSET (?-1)*? ROWS\n"
                + "FETCH NEXT ? ROWS ONLY"; // pagination

        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            if (courseName.length() == 0) {
                stm.setString(1, "%"); // % means it will match all
            } else {
                stm.setString(1, "%" + courseName + "%");

            }

            if (subcategoryIDs.size() == 0) {
                stm.setString(2, username);
                stm.setInt(3, pageindex);
                stm.setInt(4, pagesize);
                stm.setInt(5, pagesize);
            } else {
                for (int i = 1; i <= subcategoryIDs.size(); i++) {
                    stm.setInt(1 + i, subcategoryIDs.get(i - 1));
                    if (i == subcategoryIDs.size()) {
                        stm.setString(i + 2, username);
                        stm.setInt(i + 3, pageindex);
                        stm.setInt(i + 4, pagesize);
                        stm.setInt(i + 5, pagesize);
                    }
                }
            }
            rs = stm.executeQuery();
            while (rs.next()) {
                Registration r = new Registration();
                r.setRegistrationID(rs.getInt("registrationID"));
                UserDBContext userDBContext = new UserDBContext();
                User u = userDBContext.getUser(rs.getString("username"));
                r.setRegistrationTime(rs.getTimestamp("registrationTime"));
                Course course = courseDBContext.getCourse(rs.getInt("courseID"));
                r.setCourse(course);
                PricePackage pricePackageByID = pricePackageDBContext.getPricePackageByID(rs.getInt("pricePackageID"));
                r.setPricePackage(pricePackageByID);
                r.setTotalCost(rs.getFloat("totalCost"));
                r.setStatus(rs.getBoolean("status"));
                r.setValidFrom(rs.getDate("validFrom"));
                r.setValidTo(rs.getDate("validTo"));
                if (rs.getString("updatedBy") != null) {
                    r.setUpdatedBy(userDBContext.getUser(rs.getString("updatedBy")));
                }
                

                registrations.add(r);

            }

        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return registrations;
    }

    public int getCountByFilter(ArrayList<Integer> subcategoryIDs, String courseName, String username) {
        String sub = ""; // subcategory sql string 

        for (int i = 0; i < subcategoryIDs.size(); i++) { // put them into in sql
            if (i == subcategoryIDs.size() - 1) {
                sub += "?";
            } else {
                sub += "?,";
            }
        }

        String sql = "select count(*) as number from Registration r join Course c on r.courseID = c.courseID"; // start of the query
        PreparedStatement stm = null;
        if (courseName != null) {// course name filter
            sql += " where upper(courseName) like upper(?) ";
        }

        if (subcategoryIDs.size() > 0) { // subcategory filter
            String sub_sql = " and subCategoryID in (" + sub + ")";
            sql += sub_sql;
        }

        sql += " and username = ? "; // username registration course

        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            if (courseName.length() == 0) {
                stm.setString(1, "%"); // % means it will match all
            } else {
                stm.setString(1, "%" + courseName + "%");

            }

            for (int i = 1; i <= subcategoryIDs.size(); i++) {
                stm.setInt(1 + i, subcategoryIDs.get(i - 1));
                if (i == subcategoryIDs.size()) {
                    stm.setString(i + 2, username);

                }
            }
            if (subcategoryIDs.size() == 0) {
                stm.setString(2, username);
            }
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("number");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    public void deleteRegistration(int registrationID) {
        String sql = "delete from Registration \n"
                + "where registrationID = ?";
        PreparedStatement stm = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, registrationID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            if(rs.next()){
                return rs.getInt("number") >= 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

}
