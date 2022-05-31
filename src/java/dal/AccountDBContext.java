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

    public boolean getPermission(String username, String uri) {
        try {
            String sql = "select count(*) as permission from Account a inner join\n"
                    + "[Role] r on a.roleID = r.roleID\n"
                    + "inner join [Authorization] auth\n"
                    + "on auth.roleID = r.roleID inner join\n"
                    + "Feature f on f.featureID = auth.featureID\n"
                    + "where f.featureID not in (select ue.featureID from UserException ue inner join\n"
                    + "Feature fe on ue.featureID = fe.featureID)\n"
                    + "and a.username = ? \n"
                    + "and f.[URL] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, uri);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("permission") > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

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
                    f.setIsDisplayed(rs_feature.getBoolean("isDisplayed"));
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
}
