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
import model.PricePackage;
import model.Registration;
import model.User;

/**
 *
 * @author ADMIN
 */
public class RegistrationDBContext extends DBContext {

    public boolean insertRegistration(int courseID, PricePackage pricePackage, User user) {
        String sql_insert_registration = "INSERT INTO [dbo].[Registration]\n"
                + "           ([username]\n"
                + "           ,[registrationTime]\n"
                + "           ,[courseID]\n"
                + "           ,[pricePackageID]\n"
                + "           ,[totalCost]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?,GETDATE(),?,?,?,1)";
        String sql_insert_user = "INSERT INTO [dbo].[User]\n"
                + "           ([username]\n"
                + "           ,[gender]\n"
                + "           ,[firstName]\n"
                + "           ,[lastName]\n"
                + "           ,[phoneNumber])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";

        float totalPrice;

        if (pricePackage.isIsOnSale()) {
            totalPrice = pricePackage.getSalePrice();
        } else {
            totalPrice = pricePackage.getListPrice();
        }
        UserDBContext userDBContext = new UserDBContext();
        if (userDBContext.isUserExist(user.getAccount().getUsername()) == false) {
            try {
                connection.setAutoCommit(false);
                PreparedStatement stm_insert_registration = connection.prepareStatement(sql_insert_registration);
                stm_insert_registration.setString(1, user.getAccount().getUsername());
                stm_insert_registration.setInt(2, courseID);
                stm_insert_registration.setInt(3, pricePackage.getPricePackageID());
                stm_insert_registration.setFloat(4, totalPrice);
                stm_insert_registration.executeUpdate();

                PreparedStatement stm_insert_user = connection.prepareStatement(sql_insert_user);
                stm_insert_user.setString(1, user.getAccount().getUsername());
                stm_insert_user.setBoolean(2, user.isGender());
                stm_insert_user.setString(3, user.getFirstName());
                stm_insert_user.setString(4, user.getLastName());
                stm_insert_user.setString(5, user.getPhoneNumber());
                stm_insert_user.executeUpdate();
                connection.commit();

            } catch (SQLException ex) {
                Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    connection.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
                //close connection
            }
        } else {
            PreparedStatement stm_insert_registration;
            try {
                stm_insert_registration = connection.prepareStatement(sql_insert_registration);
                stm_insert_registration.setString(1, user.getAccount().getUsername());
                stm_insert_registration.setInt(2, courseID);
                stm_insert_registration.setInt(3, pricePackage.getPricePackageID());
                stm_insert_registration.setFloat(4, totalPrice);
                return stm_insert_registration.executeUpdate() >= 1;
            } catch (SQLException ex) {
                Logger.getLogger(RegistrationDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return false;
    }

}
