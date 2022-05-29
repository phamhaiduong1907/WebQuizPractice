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
import model.Role;

/**
 *
 * @author Hai Duong
 */
public class RoleDBContext extends DBContext {

    public ArrayList<Role> getRoles() {
        ArrayList<Role> roles = new ArrayList<>();;
        String sql = "select r.roleID, r.roleName from [Role] r";
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                if(rs.getInt("roleID") == 1)
                    continue;
                Role role = new Role();
                role.setRoleID(rs.getInt("roleID"));
                role.setRoleName(rs.getString("roleName"));
                roles.add(role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roles;
    }
}
