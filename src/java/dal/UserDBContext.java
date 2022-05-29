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
                    + "on u.username = a.username";
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
    
    public User getUser(String username){
        try {
            String sql = "select u.*, a.[password] from [User] u inner join Account a\n"
                    + "on u.username = a.username where a.username = ?";
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                User user = new User();
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setGender(rs.getBoolean("gender"));
                user.setAddress(rs.getString("address"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setProfilePictureUrl(rs.getString("profilePictureURL"));
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
}
