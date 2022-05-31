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
import model.Post;
import model.Subcategory;

/**
 *
 * @author Hai Tran
 */
public class BlogDBContext extends DBContext {

    public ArrayList<Post> getPosts(int pageindex, int pagesize) {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            String sql = "SELECT postID, subcategoryID, title, briefInfo, [description], isFeatured,\n"
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
            while (rs.next()) {
                SubCategoryDBContext dbSubCate = new SubCategoryDBContext();
                Post post = new Post();
                post.setPostID(rs.getInt("postID"));
                post.setSubcategory(dbSubCate.getSubcategory(rs.getInt("subcategoryID")));
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

    public ArrayList<Post> getPostForHome(int postID1, int postID2, int postID3, int postID4) {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            String sql = "SELECT postID, subcategoryID, title, briefInfo, [description], isFeatured,\n"
                    + "[status], author, updatedDate, thumbnailURL\n"
                    + "FROM Post\n"
                    + "WHERE postID IN (?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, postID1);
            stm.setInt(2, postID2);
            stm.setInt(3, postID3);
            stm.setInt(4, postID4);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SubCategoryDBContext dbSubCate = new SubCategoryDBContext();
                Post post = new Post();
                post.setPostID(rs.getInt("postID"));
                post.setSubcategory(dbSubCate.getSubcategory(rs.getInt("subcategoryID")));
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

    public Post getPost(int postID) {
        try {
            String sql = "SELECT postID, p.subcategoryID, sc.subcategoryName, title, briefInfo, [description], isFeatured,[status], author, updatedDate, thumbnailURL\n"
                    + "FROM Post p join SubCategory sc\n"
                    + "ON p.subcategoryID = sc.subcategoryID\n"
                    + "WHERE P.postID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, postID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Subcategory sc = new Subcategory();
                Account a = new Account();
                Post p = new Post();
                sc.setCategoryID(rs.getInt("subcategoryID"));
                sc.setSubcategoryName(rs.getString("subcategoryName"));
                a.setUsername(rs.getString("author"));
                p.setSubcategory(sc);
                p.setAuthor(a);
                p.setTitle(rs.getString("title"));
                p.setBriefInfo(rs.getString("briefInfo"));
                p.setDescription(rs.getString("description"));
                p.setIsFeatured(rs.getBoolean("isFeatured"));
                p.setStatus(rs.getBoolean("status"));
                p.setPostID(rs.getInt("postID"));
                p.setUpdatedDate(rs.getDate("updatedDate"));
                p.setThumbnailUrl(rs.getString("thumbnailURL"));
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Post> searchPost(String search, String subcateID, int pageindex, int pagesize) {
        ArrayList<Post> posts = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        try {
            String sql = "SELECT postID, title, subcategoryID, briefInfo, [description], isFeatured, [status], author, updatedDate, thumbnailURL \n"
                    + "FROM Post\n"
                    + "WHERE title LIKE ?";
            sb.append(sql);
            if (!subcateID.isEmpty()) {
                String and = " AND subcategoryID IN(" + subcateID + ")";
                sb.append(and);
            }
            String offset = " ORDER BY [updatedDate] DESC OFFSET ? * ? ROWS FETCH NEXT ? ROWS ONLY";
//            sql += offset;
            sb.append(offset);
            String sql_final = sb.toString();
            PreparedStatement stm = connection.prepareStatement(sql_final);
            stm.setString(1, "%" + search + "%");
            stm.setInt(2, pageindex - 1);
            stm.setInt(3, pagesize);
            stm.setInt(4, pagesize);;
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SubCategoryDBContext dbSubCate = new SubCategoryDBContext();
                Post post = new Post();
                post.setPostID(rs.getInt("postID"));
                post.setSubcategory(dbSubCate.getSubcategory(rs.getInt("subcategoryID")));
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

}
