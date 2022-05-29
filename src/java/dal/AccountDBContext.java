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
            String sql = "select count(*) as permission from Account a inner join \n"
                    + "[Role] r on a.roleID = r.roleID\n"
                    + "inner join [Authorization] auth\n"
                    + "on auth.roleID = r.roleID inner join\n"
                    + "Feature f on f.featureID = auth.featureID\n"
                    + "where f.[URL] not in (select * from UserException) \n"
                    + "and a.username = ? and f.[URL] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, uri);
            ResultSet rs = stm.executeQuery();
            return rs.getInt("permission") > 0;
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
        PreparedStatement stm_inactive_feature;
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
                String sql_feature = "select f.featureID, f.[URL] from Feature f inner join [Authorization] auth\n"
                        + "on f.featureID = auth.featureID where auth.roleID = ?";
                stm_feature = connection.prepareStatement(sql_feature);
                stm_feature.setInt(1, rs.getInt("roleID"));
                ResultSet rs_feature = stm_feature.executeQuery();
                ArrayList<Feature> features = new ArrayList<>();
                while (rs_feature.next()) {
                    Feature f = new Feature();
                    f.setFeatureID(rs.getInt("featureID"));
                    f.setUrl(rs.getString("URL"));
                    features.add(f);
                }
                r.setFeatures(features);
                account.setRole(r);
                String sql_inactive_feature = "select f.featureID, f.[URL] from UserException ue inner join Feature f\n"
                        + "on f.featureID = ue.featureID where ue.username = ?";
                stm_inactive_feature = connection.prepareStatement(sql_inactive_feature);
                stm_inactive_feature.setString(1, rs.getString("username"));
                ResultSet rs_inactive_feature = stm_inactive_feature.executeQuery();
                ArrayList<Feature> inactive_features = new ArrayList<>();
                while(rs_inactive_feature.next()){
                    Feature inactive_feature = new Feature();
                    inactive_feature.setFeatureID(rs_inactive_feature.getInt("featureID"));
                    inactive_feature.setUrl(rs_inactive_feature.getString("URL"));
                    inactive_features.add(inactive_feature);
                }
                account.setInactiveFeatures(inactive_features);
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
