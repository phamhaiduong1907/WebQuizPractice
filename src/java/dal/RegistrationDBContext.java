/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Course;
import model.OrderData;
import model.PricePackage;
import model.Registration;
import model.User;

/**
 *
 * @author ADMIN
 */
public class RegistrationDBContext extends DBContext {
    
    private final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

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
                r.setPricePackage(p);
                r.setTotalCost(rs.getFloat("totalCost"));
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

    public ArrayList<Registration> searchRegistration(String subject, String email, Date fromDate, Date toDate, Boolean status) {
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
                r.setPricePackage(p);
                r.setTotalCost(rs.getFloat("totalCost"));
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

    public ArrayList<Registration> getRegistrations() {
        ArrayList<Registration> list = new ArrayList<>();
        try {
            String sql = "SELECT *"
                    + "  FROM [Registration]\n";

            PreparedStatement stm = connection.prepareStatement(sql);
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
                r.setPricePackage(p);
                r.setTotalCost(rs.getFloat("totalCost"));
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
                + "           (?,GETDATE(),?,?,?,0)";
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

    public boolean updateRegistration(int pricePackageID, String username, int registrationID) {
        String sql = "update Registration\n"
                + "set registrationTime = GETDATE(), pricePackageID = ?, totalCost = ?\n"
                + ",updatedBy = ?\n"
                + "where registrationID = ?";

        PreparedStatement stm = null;

        try {
            PricePackageDBContext pricePackageDBContext = new PricePackageDBContext();
            PricePackage pricePackage = pricePackageDBContext.getPricePackageByID(pricePackageID);

            float totalCost = 0;
            if (pricePackage.isIsOnSale()) {
                totalCost = pricePackage.getSalePrice();
            } else {
                totalCost = pricePackage.getListPrice();
            }

            stm = connection.prepareStatement(sql);
            stm.setInt(1, pricePackageID);
            stm.setFloat(2, totalCost);
            stm.setString(3, username);
            stm.setInt(4, registrationID);
            return stm.executeUpdate() >= 1;

        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
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

    public Boolean insertReg(User user, int courseID, PricePackage price, Boolean status, String updatedBy) {
        try {
            String sql = "INSERT INTO [dbo].[Registration]\n"
                    + "           ([username]\n"
                    + "           ,[registrationTime]\n"
                    + "           ,[courseID]\n"
                    + "           ,[pricePackageID]\n"
                    + "           ,[totalCost]\n"
                    + "           ,[status]\n"
                    + "           ,[validFrom]\n"
                    + "           ,[validTo]\n"
                    + "           ,[updatedBy])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,GETDATE()\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,GETDATE()\n"
                    + "           ,DATEADD(Month,?,GETDATE())\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user.getAccount().getUsername());
            stm.setInt(2, courseID);
            stm.setInt(3, price.getPricePackageID());
            stm.setFloat(4, price.getSalePrice());
            stm.setBoolean(5, status);
            stm.setInt(6, price.getDuration());
            stm.setString(7, updatedBy);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public ArrayList<OrderData> getOrderDataInPeriod(String startDate, String enddate) {
        ArrayList<OrderData> orderDatas = new ArrayList<>();
        try {
            String sql = "WITH DateRange(DateData) AS \n"
                    + "(\n"
                    + "    SELECT cast(? as date) as [Date]\n"
                    + "    UNION ALL\n"
                    + "    SELECT DATEADD(d,1,DateData)\n"
                    + "    FROM DateRange \n"
                    + "    WHERE DateData < cast(? as date)\n"
                    + ")\n"
                    + "SELECT dr.DateData as dateInPeriod,\n"
                    + "COUNT(case when r.[status] is null then null else 1 end) as totalRegistration, \n"
                    + "COUNT(case r.[status] when 'true' then 1 else null end) as successRegistration\n"
                    + "FROM DateRange dr left join Registration r on cast(r.registrationTime as date) = dr.DateData\n"
                    + "group by dr.DateData\n"
                    + "OPTION (MAXRECURSION 0)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, startDate);
            stm.setString(2, enddate);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                OrderData orderData = new OrderData();
                orderData.setDate(sdf.format(rs.getDate("dateInPeriod")));
                orderData.setAll(rs.getInt("totalRegistration"));
                orderData.setSuccess(rs.getInt("successRegistration"));
                orderDatas.add(orderData);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderDatas;
    }
}
