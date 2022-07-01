/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import model.Level;

/**
 *
 * @author Hai Tran
 */
public class LevelDBContext extends DBContext {

    public Level getLevel(int levelID) {
        Level level = new Level();
        try {
            String sql = "SELECT [levelID], [levelName]\n"
                    + "FROM [dbo].[Level]\n"
                    + "WHERE levelID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, levelID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                level.setLevelID(levelID);
                level.setLevelName(rs.getString("levelName"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LevelDBContext.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return level;
    }

    public ArrayList<Level> getAllLevel() {
        ArrayList<Level> levels = new ArrayList<>();
        try {
            String sql = "SELECT levelID, levelName \n"
                    + "FROM Level";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Level level = new Level();
                level.setLevelID(rs.getInt("levelID"));
                level.setLevelName(rs.getString("levelName"));
                levels.add(level);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LevelDBContext.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return levels;
    }
}
