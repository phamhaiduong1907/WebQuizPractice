/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Random;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Zuys
 */
public class MiscUtil {
    public String getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public String encryptString(String str) {
        return BCrypt.hashpw(str, BCrypt.gensalt(4));
    }

    public boolean checkEncryptString(String input, String hashed) {
        return BCrypt.checkpw(input, hashed);
    }
}
