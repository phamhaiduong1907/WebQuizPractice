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
import model.Slider;

/**
 *
 * @author Hai Tran
 */
public class SliderDBContext extends DBContext {

    public ArrayList<Slider> getSliders() {
        ArrayList<Slider> sliders = new ArrayList<>();
        try {
            String sql = "SELECT sliderID, title, backlink, [status], imageURL, note \n"
                    + "FROM Slider\n"
                    + "WHERE [status] = 'True'";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Slider s = new Slider();
                s.setSliderID(rs.getInt("sliderID"));
                s.setTitle(rs.getString("title"));
                s.setBacklink(rs.getString("backlink"));
                s.setStatus(rs.getBoolean("status"));
                s.setImageUrl(rs.getString("imageURL"));
                s.setNote(rs.getString("note"));
                sliders.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sliders;
    }
    
    public Slider getSliderByID(int sliderID) {
        try {
            String sql = "SELECT sliderID, title, backlink, [status], imageURL, note \n"
                    + "FROM Slider\n"
                    + "WHERE sliderID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sliderID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Slider s = new Slider();
                s.setSliderID(rs.getInt("sliderID"));
                s.setTitle(rs.getString("title"));
                s.setBacklink(rs.getString("backlink"));
                s.setStatus(rs.getBoolean("status"));
                s.setImageUrl(rs.getString("imageURL"));
                s.setNote(rs.getString("note"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int count() {
        try {
            String sql = "select count(*) as Total from Slider";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int countByStatus(boolean status) {
        try {
            String sql = "select count(*) as Total from Slider where [status] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, status);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int countByTitle(String title) {
        try {
            String sql = "select count(*) as Total from Slider where title = ? or backlink = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, title);
            stm.setString(2, title);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<Slider> getAllSliders() {
        ArrayList<Slider> sliders = new ArrayList<>();
        try {
            String sql = "SELECT sliderID, title, backlink, [status], imageURL, note \n"
                    + "FROM Slider";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Slider s = new Slider();
                s.setSliderID(rs.getInt("sliderID"));
                s.setTitle(rs.getString("title"));
                s.setBacklink(rs.getString("backlink"));
                s.setStatus(rs.getBoolean("status"));
                s.setImageUrl(rs.getString("imageURL"));
                s.setNote(rs.getString("note"));
                sliders.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sliders;
    }
    
    public ArrayList<Slider> getPaginatedSlidersByTitle(int pageindex, int pagesize, String title){
        ArrayList<Slider> sliders = new ArrayList<>();
        try {
            String sql = "select * from Slider where title = ? or backlink = ? order by sliderID\n"
                    + "offset (?-1)*? rows fetch next ? rows only";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, title);
            stm.setString(2, title);
            stm.setInt(3, pageindex);
            stm.setInt(4, pagesize);
            stm.setInt(5, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Slider slider = new Slider();
                slider.setSliderID(rs.getInt("sliderID"));
                slider.setBacklink(rs.getString("backlink"));
                slider.setImageUrl(rs.getString("imageURL"));
                slider.setTitle(rs.getString("title"));
                slider.setStatus(rs.getBoolean("status"));
                slider.setNote(rs.getString("note"));
                sliders.add(slider);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sliders;
    }
    
    public ArrayList<Slider> getPaginatedSlidersByStatus(int pageindex, int pagesize, boolean status) {
        ArrayList<Slider> sliders = new ArrayList<>();
        try {
            String sql = "select * from Slider where [status] = ? order by sliderID\n"
                    + "offset (?-1)*? rows fetch next ? rows only";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, status);
            stm.setInt(2, pageindex);
            stm.setInt(3, pagesize);
            stm.setInt(4, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Slider slider = new Slider();
                slider.setSliderID(rs.getInt("sliderID"));
                slider.setBacklink(rs.getString("backlink"));
                slider.setImageUrl(rs.getString("imageURL"));
                slider.setTitle(rs.getString("title"));
                slider.setStatus(rs.getBoolean("status"));
                slider.setNote(rs.getString("note"));
                sliders.add(slider);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sliders;
    }

    public ArrayList<Slider> getPaginatedSliders(int pageindex, int pagesize) {
        ArrayList<Slider> sliders = new ArrayList<>();
        try {
            String sql = "select * from Slider order by sliderID asc\n"
                    + "offset (?-1) * ? rows fetch next ? rows only";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageindex);
            stm.setInt(2, pagesize);
            stm.setInt(3, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Slider slider = new Slider();
                slider.setSliderID(rs.getInt("sliderID"));
                slider.setBacklink(rs.getString("backlink"));
                slider.setImageUrl(rs.getString("imageURL"));
                slider.setTitle(rs.getString("title"));
                slider.setStatus(rs.getBoolean("status"));
                slider.setNote(rs.getString("note"));
                sliders.add(slider);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sliders;
    }

    public void updateSlider(int sliderID, String title, String backlink, boolean status, String imageURL, String note) {
        String sql = "update Slider\n"
                + "set title = ?, backlink = ?,\n"
                + "status = ?, imageURL = ?, note = ? where sliderID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, title);
            stm.setString(2, backlink);
            stm.setBoolean(3, status);
            stm.setString(4, imageURL);
            stm.setString(5, note);
            stm.setInt(6, sliderID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SliderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertSlider(String title, String backlink, boolean status, String imageURL, String note) {
        String sql = "insert into Slider values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, title);
            stm.setString(2, backlink);
            stm.setBoolean(3, status);
            stm.setString(4, imageURL);
            stm.setString(5, note);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SliderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void changeStatus(boolean status, int sliderID){
        String sql = "update Slider\n"
                + "set status = ? where sliderID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, status);
            stm.setInt(2, sliderID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SliderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Slider> getSliders(String combination, Boolean status, int pageindex, int pagesize){
        ArrayList<Slider> sliders = new ArrayList<>();
        String sql = " select * from Slider ";
        
        String sql_base = " select * from Slider ";
        
        String intersect = " \n intersect \n";
        
        String sql_status = " where [status] = ? ";
        
        String sql_combination = " where LOWER(title) like LOWER(?) or LOWER(backlink) like LOWER(?) ";

        String pagination = " order by sliderID asc OFFSET (?-1)*? ROWS\n"
                + "FETCH NEXT ? ROWS ONLY ";
        
        if(status != null){
            sql += sql_status;
        }
        
        if(combination != null && combination.trim().length() > 0){
            sql += intersect;
            sql += sql_base + sql_combination;
        }
        
        sql += pagination;
        int i = 1;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            if(status != null){
                stm.setBoolean(i++, status);
            }
            
            if(combination != null && combination.trim().length() > 0){
                stm.setString(i++, "%"+combination+"%");
                stm.setString(i++, "%"+combination+"%");
            }
            
            stm.setInt(i++, pageindex);
            stm.setInt(i++, pagesize);
            stm.setInt(i++, pagesize);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Slider slider = new Slider();
                slider.setSliderID(rs.getInt("sliderID"));
                slider.setBacklink(rs.getString("backlink"));
                slider.setImageUrl(rs.getString("imageURL"));
                slider.setTitle(rs.getString("title"));
                slider.setStatus(rs.getBoolean("status"));
                slider.setNote(rs.getString("note"));
                sliders.add(slider);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return sliders;
    }
    
    public int countSlider(String combination, Boolean status){
        String finalSql = "select count(*) as Total from ( "; 
        String sql = " select * from Slider ";
        
        String sql_base = " select * from Slider ";
        
        String intersect = " \n intersect \n";
        
        String sql_status = " where [status] = ? ";
        
        String sql_combination = " where LOWER(title) like LOWER(?) or LOWER(backlink) like LOWER(?) ";
        
        if(status != null){
            sql += sql_status;
        }
        
        if(combination != null && combination.trim().length() > 0){
            sql += intersect;
            sql += sql_base + sql_combination;
        }
        
        finalSql += sql + " ) t";
        
        int i = 1;
        try {
            PreparedStatement stm = connection.prepareStatement(finalSql);
            if(status != null){
                stm.setBoolean(i++, status);
            }
            
            if(combination != null && combination.trim().length() > 0){
                stm.setString(i++, "%"+combination+"%");
                stm.setString(i++, "%"+combination+"%");
            }
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

}
