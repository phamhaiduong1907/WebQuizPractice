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
import model.Feature;
import model.Role;

/**
 *
 * @author Hai Duong
 */
public class AccountDBContext extends DBContext {

    /**
     *
     * @param username
     * @param uri
     * @param roleID (if account is blocked, roleID will be set to -1 and can
     * access any feature)
     * @return a boolean (yes means that the user can access the uri and the
     * otherwise)
     */
    public boolean getPermission(String username, int roleID, String uri) {
        try {
                String sql = "select count(*) as permission from Account a inner join\n"
                        + "[Role] r on a.roleID = r.roleID\n"
                        + "inner join [Authorization] auth\n"
                        + "on auth.roleID = r.roleID inner join\n"
                        + "Feature f on f.featureID = auth.featureID\n"
                        + "where f.featureID not in (select featureID from UserException \n"
                        + "where username = ?)\n"
                        + "and a.username = ? and a.roleID = ?\n"
                        + "and f.[URL] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, username);
            stm.setInt(3, roleID);
            stm.setString(4, uri);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("permission") > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     *
     * @param username
     * @return an account if it exists in the database
     */
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
     *
     * @param username
     * @return true if there is an account with the matching username in the
     * database
     */
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
                //connection.close();
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
                    //connection.close();
                }
            } catch (SQLException e) {
            }

        }
        return false;
    }

    /**
     *
     * @param username
     * @param password
     * @return true if the password has been changed by this function
     */
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

    /**
     *
     * @param username
     * @param password
     * @return an account with every related attributes
     */
    public Account getAccount(String username, String password) {
        String sql = "select a.username, a.[password], r.* from\n"
                + "Account a inner join [Role] r on r.roleID = a.roleID\n"
                + "where a.username = ? and a.[password] = ?";
        PreparedStatement stm;
        PreparedStatement stm_feature;
        try {
            connection.setAutoCommit(false);
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                Role r = new Role();
                r.setRoleID(rs.getInt("roleID"));
                r.setRoleName(rs.getString("roleName"));
                String sql_feature = "select f.* from \n"
                        + "Feature f inner join [Authorization] auth\n"
                        + "on f.featureID = auth.featureID where auth.roleID = ? and\n"
                        + "f.featureID not in (select ue.featureID from UserException ue where username\n"
                        + "= ? )";
                stm_feature = connection.prepareStatement(sql_feature);
                stm_feature.setInt(1, rs.getInt("roleID"));
                stm_feature.setString(2, rs.getString("username"));
                ResultSet rs_feature = stm_feature.executeQuery();
                ArrayList<Feature> features = new ArrayList<>();
                while (rs_feature.next()) {
                    Feature f = new Feature();
                    f.setFeatureID(rs_feature.getInt("featureID"));
                    f.setUrl(rs_feature.getString("URL"));
                    f.setFeatureName(rs_feature.getString("featureName"));
                    features.add(f);
                }
                r.setFeatures(features);
                account.setRole(r);
                connection.commit();
                return account;

            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public Account getAccount(String username) {
        try {
            String sql = "SELECT username,password,roleID FROM Account \n"
                    + "WHERE username = ?";
            RoleDBContext dbRole = new RoleDBContext();

            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setString(1, username);
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
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
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

    /**
     *
     * @param account
     * @return true if the password has been changed successfully
     */
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
            Logger.getLogger(AccountDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<Account> getAccountByRole(int roleid) {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            String sql = "select username from Account where roleID = ?";
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, roleid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                accounts.add(account);
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return accounts;
    }

    public ArrayList<Account> getAccountByRoleID(int roleID) {
        String sql = "select * from Account where roleID = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<Account> accounts = new ArrayList<>();
        RoleDBContext roleDBContext = new RoleDBContext();

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, roleID);
            rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setRole(roleDBContext.getRoleById(roleID));
                a.setPassword(rs.getString("password"));
                a.setUsername(rs.getString("username"));
                accounts.add(a);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return accounts;

    }

}
