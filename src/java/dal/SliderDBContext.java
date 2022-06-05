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

}
