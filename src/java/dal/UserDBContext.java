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
import model.Role;
import model.User;

/**
 *
 * @author Hai Duong
 */
public class UserDBContext extends DBContext {

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            String sql = "select u.*, a.[password] from [User] u inner join Account a\n"
                    + "on u.username = a.username order by a.roleID asc";
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setGender(rs.getBoolean("gender"));
                user.setAddress(rs.getString("address"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setProfilePictureUrl(rs.getString("profilePictureURL"));
                user.setStatus(rs.getBoolean("status"));
                Account account = new AccountDBContext().getAccount(rs.getString("username"), rs.getString("password"));
                user.setAccount(account);
                users.add(user);
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return users;
    }

    public int count() {
        try {
            String sql = "select count(*) as Total from [User]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<User> getPaginatedUsers(int pageindex, int pagesize) {
        ArrayList<User> users = new ArrayList<>();
        try {
            String sql = "select u.*, a.[password] from [User] u inner join Account a\n"
                    + "on u.username = a.username order by a.roleID\n"
                    + "offset (? - 1 ) * ? rows fetch next ? rows only";
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageindex);
            stm.setInt(2, pagesize);
            stm.setInt(3, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setGender(rs.getBoolean("gender"));
                user.setAddress(rs.getString("address"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setProfilePictureUrl(rs.getString("profilePictureURL"));
                user.setStatus(rs.getBoolean("status"));
                Account account = new AccountDBContext().getAccount(rs.getString("username"), rs.getString("password"));
                user.setAccount(account);
                users.add(user);
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return users;
    }

    public User getUser(String username) {
        try {
            String sql = "select u.*, a.[password] from [User] u inner join Account a\n"
                    + "on u.username = a.username where a.username = ?";
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setGender(rs.getBoolean("gender"));
                user.setAddress(rs.getString("address"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setProfilePictureUrl(rs.getString("profilePictureURL"));
                user.setStatus(rs.getBoolean("status"));
                Account account = new AccountDBContext().getAccount(rs.getString("username"), rs.getString("password"));
                user.setAccount(account);
                connection.commit();
                return user;
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public int countByStatus(boolean status) {
        try {
            String sql = "select count(*) as Total from [User] where [status] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, status);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<User> getPaginatedUsersByStatus(int pageindex, int pagesize, boolean status) {
        ArrayList<User> users = new ArrayList<>();
        try {
            String sql = "select u.*, a.[password] from [User] u inner join Account a\n"
                    + "on u.username = a.username where u.[status] = ? order by u.gender asc\n"
                    + "offset (? - 1 ) * ? rows fetch next ? rows only";
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, status);
            stm.setInt(2, pageindex);
            stm.setInt(3, pagesize);
            stm.setInt(4, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setGender(rs.getBoolean("gender"));
                user.setAddress(rs.getString("address"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setProfilePictureUrl(rs.getString("profilePictureURL"));
                user.setStatus(rs.getBoolean("status"));
                Account account = new AccountDBContext().getAccount(rs.getString("username"), rs.getString("password"));
                user.setAccount(account);
                users.add(user);
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return users;
    }

    public int countByGender(boolean gender) {
        try {
            String sql = "select count(*) as Total from [User] where [gender] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, gender);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<User> getPaginatedUsersByGender(int pageindex, int pagesize, boolean gender) {
        ArrayList<User> users = new ArrayList<>();
        try {
            String sql = "select u.*, a.[password] from [User] u inner join Account a\n"
                    + "on u.username = a.username where u.[gender] = ? order by u.gender asc\n"
                    + "offset (? - 1 ) * ? rows fetch next ? rows only";
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, gender);
            stm.setInt(2, pageindex);
            stm.setInt(3, pagesize);
            stm.setInt(4, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setGender(rs.getBoolean("gender"));
                user.setAddress(rs.getString("address"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setProfilePictureUrl(rs.getString("profilePictureURL"));
                user.setStatus(rs.getBoolean("status"));
                Account account = new AccountDBContext().getAccount(rs.getString("username"), rs.getString("password"));
                user.setAccount(account);
                users.add(user);
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return users;
    }

    public int countByQuery(String query) {
        try {
            String sql = "select count(*) from [User] u where u.lastName + ' ' + u.firstName = ?\n"
                    + "or u.username = ? or u.phoneNumber = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, query);
            stm.setString(2, query);
            stm.setString(3, query);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<User> getPaginatedUsersByQuery(int pageindex, int pagesize, String query) {
        ArrayList<User> users = new ArrayList<>();
        try {
            String sql = "select u.*, a.[password] from [User] u inner join Account a\n"
                    + "on u.username = a.username where u.lastName + ' ' + u.firstName = ?\n"
                    + "or u.username = ? or u.phoneNumber = ? order by u.username asc\n"
                    + "offset (? - 1 ) * ? rows fetch next ? rows only";
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, query);
            stm.setString(2, query);
            stm.setString(3, query);
            stm.setInt(4, pageindex);
            stm.setInt(5, pagesize);
            stm.setInt(6, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setGender(rs.getBoolean("gender"));
                user.setAddress(rs.getString("address"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setProfilePictureUrl(rs.getString("profilePictureURL"));
                user.setStatus(rs.getBoolean("status"));
                Account account = new AccountDBContext().getAccount(rs.getString("username"), rs.getString("password"));
                user.setAccount(account);
                users.add(user);
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return users;
    }

    public void updateUser(String username, String firstName, String lastName, String[] featureIDs,
            String phone, boolean gender, String address, boolean status, int roleID) {
        String sql_update_user = "update [User]\n"
                + "set gender = ?, firstName = ?, lastName = ?,\n"
                + "phoneNumber = ?, [address] = ?, [status] = ?\n"
                + "where username = ?";
        String sql_update_role = "update Account\n"
                + "set roleID = ? where username = ?";
        String sql_delete_ue = "delete UserException where username = ?";
        String sql_deactive_feature = "insert into UserException values\n"
                + "(?,?)";
        PreparedStatement stm_update_user = null;
        PreparedStatement stm_update_role = null;
        PreparedStatement stm_delete_ue = null;
        PreparedStatement stm_deactive_feature = null;
        try {
            connection.setAutoCommit(false);
            stm_update_user = connection.prepareStatement(sql_update_user);
            stm_update_user.setBoolean(1, gender);
            stm_update_user.setString(2, firstName);
            stm_update_user.setString(3, lastName);
            stm_update_user.setString(4, phone);
            stm_update_user.setString(5, address);
            stm_update_user.setBoolean(6, status);
            stm_update_user.setString(7, username);
            stm_update_user.executeUpdate();

            stm_update_role = connection.prepareStatement(sql_update_role);
            stm_update_role.setInt(1, roleID);
            stm_update_role.setString(2, username);
            stm_update_role.executeUpdate();

            stm_delete_ue = connection.prepareStatement(sql_delete_ue);
            stm_delete_ue.setString(1, username);
            stm_delete_ue.executeUpdate();

            if (featureIDs != null) {
                for (String featureID : featureIDs) {
                    stm_deactive_feature = connection.prepareStatement(sql_deactive_feature);
                    stm_deactive_feature.setString(1, username);
                    stm_deactive_feature.setInt(2, Integer.parseInt(featureID.trim()));
                    stm_deactive_feature.executeUpdate();
                }
            }

            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm_update_user != null || stm_update_role != null || stm_deactive_feature != null) {

            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void updateUser(boolean status, int roleID, String username) {
        String sql_update_user = "update [User]\n"
                + "set [status] = ?\n"
                + "where username = ?";
        String sql_update_role = "update Account\n"
                + "set roleID = ? where username = ?";
        PreparedStatement stm_update_user;
        PreparedStatement stm_update_role;

        try {
            connection.setAutoCommit(false);
            stm_update_user = connection.prepareStatement(sql_update_user);
            stm_update_user.setBoolean(1, status);
            stm_update_user.setString(2, username);
            stm_update_user.executeUpdate();

            stm_update_role = connection.prepareStatement(sql_update_role);
            stm_update_role.setInt(1, roleID);
            stm_update_role.setString(2, username);
            stm_update_role.executeUpdate();

            connection.commit();

        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean updateUser(User user) {
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET [firstName] = ?\n"
                + "      ,[lastName] = ?\n"
                + "      ,[gender] = ?\n"
                + "      ,[phoneNumber] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[profilePictureURL] = ?\n"
                + " WHERE username = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(7, user.getAccount().getUsername());
            stm.setString(1, user.getFirstName());
            stm.setString(2, user.getLastName());
            stm.setBoolean(3, user.isGender());
            stm.setString(4, user.getPhoneNumber());
            stm.setString(5, user.getAddress());
            stm.setString(6, user.getProfilePictureUrl());
            return stm.executeUpdate() >= 1;

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null)
                try {
                stm.close();

            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            if (connection != null)
                try {
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public User getUser(Account account) {
        try {
            String sql = "SELECT username,gender,firstName,lastName,phoneNumber,address,profilePictureURL FROM [User] \n"
                    + "WHERE username = ?";

            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setString(1, account.getUsername());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setAccount(account);
                user.setGender(rs.getBoolean("gender"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setAddress(rs.getString("address"));
                user.setProfilePictureUrl(rs.getString("profilePictureURL"));
                return user;

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void addUser(String username, String password, String firstName, String lastName, String phone,
            boolean gender, String address, int roleID, boolean status, String profilePictureURL) {
        String sql_insert_account = "INSERT INTO [dbo].[Account]\n"
                + "           ([username]\n"
                + "           ,[password]\n"
                + "           ,[roleID])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";
        String sql_insert_user = "insert into [User] values (?,?,?,?,?,?,?,?)";

        PreparedStatement stm_insert_account;
        PreparedStatement stm_insert_user;
        try {
            connection.setAutoCommit(false);

            stm_insert_user = connection.prepareStatement(sql_insert_user);
            stm_insert_user.setString(1, username);
            stm_insert_user.setBoolean(2, gender);
            stm_insert_user.setString(3, firstName);
            stm_insert_user.setString(4, lastName);
            stm_insert_user.setString(5, phone);
            stm_insert_user.setString(6, address);
            stm_insert_user.setString(7, profilePictureURL);
            stm_insert_user.setBoolean(8, status);
            stm_insert_user.executeUpdate();

            stm_insert_account = connection.prepareStatement(sql_insert_account);
            stm_insert_account.setString(1, username);
            stm_insert_account.setString(2, password);
            stm_insert_account.setInt(3, roleID);
            stm_insert_account.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean insertUser(User user) {
        String sql = "INSERT INTO [User]\n"
                + "           ([username]\n"
                + "           ,[gender]\n"
                + "           ,[firstName]\n"
                + "           ,[lastName]\n"
                + "           ,[phoneNumber]\n"
                + "           ,[address]\n"
                + "           ,[profilePictureURL], [status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?, ?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, user.getAccount().getUsername());
            stm.setBoolean(2, user.isGender());
            stm.setString(3, user.getFirstName());
            stm.setString(4, user.getLastName());
            stm.setString(5, user.getPhoneNumber());
            stm.setString(6, user.getAddress());
            stm.setString(7, user.getProfilePictureUrl());
            stm.setBoolean(8, user.getStatus());
            return stm.executeUpdate() >= 1;

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null)
                try {
                stm.close();

            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            if (connection != null)
                try {
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean isUserExist(String username) {
        String sql = "select username from [User]\n"
                + "where [username] = ?";

        PreparedStatement stm;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public ArrayList<User> getUsers(int roleID, Boolean status, Boolean gender, String combination) {
        ArrayList<User> users = new ArrayList<>();
        String sql = " select  u.gender, u.firstName,\n"
                + "u.lastName, u.phoneNumber, u.[address],u.profilePictureURL,u.[status],\n"
                + "a.*\n"
                + "from [User] u join Account a\n"
                + "on u.username = a.username ";
        
        String sql_base = " select  u.gender, u.firstName,\n"
                + "u.lastName, u.phoneNumber, u.[address],u.profilePictureURL,u.[status],\n"
                + "a.*\n"
                + "from [User] u join Account a\n"
                + "on u.username = a.username ";

        String sql_roleID = " where a.roleID = ? ";

        String sql_status = " where u.[status] = ? ";

        String sql_gender = " where gender = ? ";

        String sql_combination = " where LOWER(u.username) = LOWER(?) \n"
                + "or LOWER(phoneNumber) = LOWER(?) or LOWER((lastName + ' ' + firstName)) like LOWER(?) ";

        String intersect = " \n intersect \n";

        if (roleID >= 1) {
            sql += sql_roleID;

        }
        if (status != null) {
            sql += intersect;
            sql += sql_base + sql_status;
        }
        if (gender != null) {
            sql += intersect;
            sql += sql_base + sql_gender;
        }
        if (combination != null && combination.trim().length() > 0) {
            sql += intersect;
            sql += sql_base + sql_combination;
        }

        PreparedStatement stm = null;
        ResultSet rs = null;
        int i = 1;
        try {
            stm = connection.prepareStatement(sql);
            if (roleID >= 1) {
                stm.setInt(i, roleID);
                i++;
            }
            if (status != null) {
                stm.setBoolean(i, status);
                i++;
            }
            if (gender != null) {
                stm.setBoolean(i, gender);
                i++;
            }
            if (combination != null && combination.length() > 0) {
                stm.setString(i++, combination);
                stm.setString(i++, combination);
                stm.setString(i++, "%"+combination+"%");
            }
            
            rs = stm.executeQuery();
            
            while(rs.next()){
                User user = new User();
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setGender(rs.getBoolean("gender"));
                user.setAddress(rs.getString("address"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setProfilePictureUrl(rs.getString("profilePictureURL"));
                user.setStatus(rs.getBoolean("status"));
                Account account = new AccountDBContext().getAccount(rs.getString("username"), rs.getString("password"));
                user.setAccount(account);
                users.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;

    }
    
    public int count(int roleID, Boolean status, Boolean gender, String combination, String sortBy, String order){
        String finalSql = "select count(*) as Total from ( ";
        String sql = " select  u.gender, u.firstName,\n"
                + "u.lastName, u.phoneNumber, u.[address],u.profilePictureURL,u.[status],\n"
                + "a.*\n"
                + "from [User] u join Account a\n"
                + "on u.username = a.username ";
        
        String sql_base = " select  u.gender, u.firstName,\n"
                + "u.lastName, u.phoneNumber, u.[address],u.profilePictureURL,u.[status],\n"
                + "a.*\n"
                + "from [User] u join Account a\n"
                + "on u.username = a.username ";

        String sql_roleID = " where a.roleID = ? ";

        String sql_status = " where u.[status] = ? ";

        String sql_gender = " where gender = ? ";

        String sql_combination = " where LOWER(u.username) = LOWER(?) \n"
                + "or LOWER(phoneNumber) = LOWER(?) or LOWER((lastName + ' ' + firstName)) like LOWER(?) ";

        String intersect = " \n intersect \n";

        if (roleID >= 1) {
            sql += sql_roleID;

        }
        if (status != null) {
            sql += intersect;
            sql += sql_base + sql_status;
        }
        if (gender != null) {
            sql += intersect;
            sql += sql_base + sql_gender;
        }
        if (combination != null && combination.trim().length() > 0) {
            sql += intersect;
            sql += sql_base + sql_combination;
        }
        
        finalSql += sql + " ) t";

        PreparedStatement stm = null;
        ResultSet rs = null;
        int i = 1;
        try {
            stm = connection.prepareStatement(finalSql);
            if (roleID >= 1) {
                stm.setInt(i, roleID);
                i++;
            }
            if (status != null) {
                stm.setBoolean(i, status);
                i++;
            }
            if (gender != null) {
                stm.setBoolean(i, gender);
                i++;
            }
            if (combination != null && combination.length() > 0) {
                stm.setString(i, combination);
                stm.setString(i + 1, combination);
                stm.setString(i + 2, "%"+combination+"%");
                i = i + 3;
            }
            rs = stm.executeQuery();
            
            if(rs.next()){
                return rs.getInt("Total");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return -1;
    }
}
