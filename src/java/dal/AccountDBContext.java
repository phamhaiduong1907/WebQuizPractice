/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Role;

/**
 *
 * @author Zuys
 */
public class AccountDBContext extends DBContext {

    public Account isExistAccount(String username) {
        String sql = "Select username, password, r.roleID, r.roleName"
                + " from Account a join Role r on a.roleID = r.roleID where username = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                Role role = new Role();
                account.setUsername(username);
                account.setPassword(rs.getString("password"));
                role.setRoleID(rs.getInt("roleID"));
                role.setRoleName(rs.getString("roleName"));
                account.setRole(role);
                return account;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    /**
     * Check the permission for an account to a specific function/url
     *
     * @param username username
     * @param url url/path of the target feature
     * @return an int in which a positive value represents authorized access, 0
     * or negative means unauthorized access or system error
     */
    public int getPermission(String username, String url) {
        try {
            String sql = "select count(*) as permission from Account a inner join \n"
                    + "[Role] r on a.roleID = r.roleID\n"
                    + "inner join [Authorization] auth\n"
                    + "on auth.roleID = r.roleID inner join\n"
                    + "Feature f on f.featureID = auth.featureID\n"
                    + "where a.username = ? and f.[URL] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            return rs.getInt("permission");
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    public boolean isExistUser(String username) {
        String sql = "Select username, password from Account where username = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                rs.close();
                stm.close();
                connection.close();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
            }

        }
        return false;
    }

    public boolean changePassword(String username, String password) {
        String sql = "update Account\n"
                + "set [password] = ?\n"
                + "where username = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, password);
            stm.setString(2, username);
            return stm.executeUpdate() >= 1;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Account getAccount(String username, String password) {
        try {
            String sql = "SELECT username,password,roleID FROM Account \n"
                    + "WHERE username = ? AND password = ?";
            RoleDBContext dbRole = new RoleDBContext();

            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setRole(dbRole.getRole(rs.getInt("roleID")));
                rs.close();
                stm.close();
                connection.close();
                return account;

            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean insertAccount(Account account) {
        String sql = "INSERT INTO [Account]\n"
                + "           ([username]\n"
                + "           ,[password]\n"
                + "           ,[roleID])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, account.getUsername());
            stm.setString(2, account.getPassword());
            stm.setInt(3, account.getRole().getRoleID());
            return stm.executeUpdate() >= 1;

        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public boolean changePassword(Account account) {
        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET [password] = ?\n"
                + " WHERE [username] = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, account.getPassword());
            stm.setString(2, account.getUsername());
            return stm.executeUpdate() >= 1;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null)
                try {
                stm.close();

            } catch (SQLException ex) {
                Logger.getLogger(AccountDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            if (connection != null)
                try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

//    public boolean insertAccount(Account account) {
//        String sql = "INSERT INTO [Account]\n"
//                + "           ([username]\n"
//                + "           ,[password]\n"
//                + "           ,[roleID])\n"
//                + "     VALUES\n"
//                + "           (?\n"
//                + "           ,?\n"
//                + "           ,?)";
//        PreparedStatement stm = null;
//        try {
//            stm = connection.prepareStatement(sql);
//            stm.setString(1, account.getUsername());
//            stm.setString(2, account.getPassword());
//            stm.setInt(3, account.getRole().getRoleID());
//            return stm.executeUpdate() >= 1;
//        } catch (SQLException ex) {
//            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            if (stm != null)
//                try {
//                stm.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            if (connection != null)
//                try {
//                connection.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return false;
//    }
}
