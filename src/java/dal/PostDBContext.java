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
import model.Post;

/**
 *
 * @author ADMIN
 */
public class PostDBContext extends DBContext{

//    public ArrayList<Post> getPostByPagination(int courseID, int pageindex, int pagesize) {
//        String sql = " select * from Topic \n"
//                + " where courseID = ?\n"
//                + " order by topicID\n"
//                + " OFFSET (?-1)*?ROWS\n"
//                + "FETCH NEXT ? ROWS ONLY";
//        
//        
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        
//        try {
//            stm = connection.prepareStatement(sql);
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
//        
//        
//    }
}
