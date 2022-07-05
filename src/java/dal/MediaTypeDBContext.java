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
import model.MediaType;

/**
 *
 * @author Hai Tran
 */
public class MediaTypeDBContext extends DBContext {

    public MediaType getMediaType(int mediaID) {
        MediaType mediaType = new MediaType();
        try {
            String sql = "SELECT [mediaID]\n"
                    + "      ,[mediaName]\n"
                    + "FROM [dbo].[MediaType]\n"
                    + "WHERE mediaID  = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, mediaID);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                mediaType.setMediaID(mediaID);
                mediaType.setMediaName(rs.getString("mediaName"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MediaTypeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mediaType;
    }
}
