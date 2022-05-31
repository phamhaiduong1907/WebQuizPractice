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
}
