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
 * @author Hai Tran
 */
public class BlogDBContext extends DBContext {

    public ArrayList<Post> getPosts(int pageindex, int pagesize) {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            String sql = "SELECT postID, categoryID, title, briefInfo, [description], isFeatured,\n"
                    + "[status], author, updatedDate, thumbnailURL \n"
                    + "FROM Post\n"
                    + "ORDER BY updatedDate DESC\n"
                    + "OFFSET (?-1) * ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageindex);
            stm.setInt(2, pagesize);
            stm.setInt(3, pagesize);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                CategoryDBContext dbCate = new CategoryDBContext();
                Post post = new Post();
                post.setPostID(rs.getInt("postID"));
                post.setCategory(dbCate.getCategory(rs.getInt("categoryID")));
                post.setTitle(rs.getString("title"));
                post.setBriefInfo(rs.getString("briefInfo"));
                post.setDescription(rs.getString("description"));
                post.setIsFeatured(rs.getBoolean("isFeatured"));
                post.setStatus(rs.getBoolean("status"));
                
                post.setUpdatedDate(rs.getDate("updatedDate"));
                post.setThumbnailUrl(rs.getString("thumbnailURL"));
                posts.add(post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return posts;
    }

    public int count() {
        try {
            String sql = "SELECT COUNT(*) as Total FROM Post";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
