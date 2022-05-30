/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.User;

/**
 *
 * @author Zuys
 */
public class UserDBContext extends DBContext {

    public boolean insertUser(User user) {
        String sql = "INSERT INTO [User]\n"
                + "           ([username]\n"
                + "           ,[gender]\n"
                + "           ,[firstName]\n"
                + "           ,[lastName]\n"
                + "           ,[phoneNumber]\n"
                + "           ,[address]\n"
                + "           ,[profilePictureURL])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
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
            return stm.executeUpdate()>=1;
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null)
                try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (connection != null)
                try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
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
            return stm.executeUpdate()>=1;
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null)
                try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (connection != null)
                try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
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
                rs.close();
                stm.close();
                connection.close();
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
