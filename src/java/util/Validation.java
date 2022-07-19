/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.ArrayList;
import org.apache.commons.io.filefilter.FileFilterUtils;

/**
 *
 *
 */
public class Validation {

    public boolean checkNullOrBlank(ArrayList<String> strings) {
        for (String str : strings) {
            if (str == null || str.trim().length() == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean checkNullOrBlank(String[] string) {
        for (String str : string) {
            if (str == null || str.trim().length() == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean checkMatches(String[] str1, String[] str2) {
        for (int i = 0; i < str1.length; i++) {
            if (str1[i].matches(str2[i])) {
                return false;
            }
        }
        return true;
    }

    public boolean checkFileType(String fileName, String[] allowedTypes) {
        for (String allowedType : allowedTypes) {
            if (fileName.matches(allowedType)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkQuestionMedia(String contentType, int mediaID) {
        switch (mediaID) {
            case 1:
                return contentType.contains("image/");
            case 2:
                return contentType.contains("video/");
            case 3:
                return contentType.contains("audio/");
            case 4:
                return true;
            default:
                return false;
        }
    }
}
