/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Feature;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Role;

/**
 *
 * @a
 *
 * uthor Hai Duong
 */
public class RoleDBContext extends DBContext {

    public ArrayList<Role> getRoles() {
        ArrayList<Role> roles = new ArrayList<>();;
        String sql = "select r.roleID, r.roleName from [Role] r";
        PreparedStatement stm;
        PreparedStatement stm_feature;
        try {
            connection.setAutoCommit(false);
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setRoleID(rs.getInt("roleID"));
                role.setRoleName(rs.getString("roleName"));
                String sql_feature = "select f.* from [Authorization] auth\n"
                        + "inner join Feature f on f.featureID = auth.featureID\n"
                        + "where auth.roleID = ?";
                stm_feature = connection.prepareStatement(sql_feature);
                stm_feature.setInt(1, rs.getInt("roleID"));
                ResultSet rs_feature = stm_feature.executeQuery();
                ArrayList<Feature> features = new ArrayList<>();
                while (rs_feature.next()) {
                    Feature f = new Feature();
                    f.setFeatureID(rs_feature.getInt("featureID"));
                    f.setUrl(rs_feature.getString("URL"));
                    f.setFeatureName(rs_feature.getString("featureName"));
                    features.add(f);
                }
                role.setFeatures(features);
                roles.add(role);
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(RoleDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(RoleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(RoleDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return roles;
    }
    
    public Role getRole(int roleid){
        try {
            String sql = "SELECT roleID,roleName FROM [Role] \n" +
                    "WHERE roleID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, roleid);
            ResultSet rs = stm.executeQuery();
            if(rs.next())
            {
                Role role = new Role();
                role.setRoleID(roleid);
                role.setRoleName(rs.getString("roleName"));
                return role;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Role getRoleById(int roleID) {
        String sql = "select r.roleID, r.roleName from [Role] r where r.roleID = ?";
        PreparedStatement stm;
        PreparedStatement stm_feature;
        try {
            connection.setAutoCommit(false);
            stm = connection.prepareStatement(sql);
            stm.setInt(1, roleID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Role role = new Role();
                role.setRoleID(rs.getInt("roleID"));
                role.setRoleName(rs.getString("roleName"));
                String sql_feature = "select f.* from [Authorization] auth\n"
                        + "inner join Feature f on f.featureID = auth.featureID\n"
                        + "where auth.roleID = ?";
                stm_feature = connection.prepareStatement(sql_feature);
                stm_feature.setInt(1, rs.getInt("roleID"));
                ResultSet rs_feature = stm_feature.executeQuery();
                ArrayList<Feature> features = new ArrayList<>();
                while (rs_feature.next()) {
                    Feature f = new Feature();
                    f.setFeatureID(rs_feature.getInt("featureID"));
                    f.setUrl(rs_feature.getString("URL"));
                    f.setFeatureName(rs_feature.getString("featureName"));
                    features.add(f);
                }
                role.setFeatures(features);
                return role;
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(RoleDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(RoleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(RoleDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
