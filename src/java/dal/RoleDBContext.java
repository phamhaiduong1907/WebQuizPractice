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
import model.Role;

/**
 *
 * @author Zuys
 */
public class RoleDBContext extends DBContext{
    public Role getRole(int roleid){
        try {
            String sql = "SELECT roleID,roleName FROM Role \n" +
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
}
