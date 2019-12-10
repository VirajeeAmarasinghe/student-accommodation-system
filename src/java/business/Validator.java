/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Virajee
 */
public class Validator {

    //this method is for mandatory field validation
    public boolean isPresent(String fieldValue) {  
        if (fieldValue.length() == 0 || fieldValue == null) {
            return false;
        }
        return true;
    }
     //pass-07222 555555|||(07222) 555555|||+44 7222 555 555
    //fail-7222 555555|||+44 07222 555555|||(+447222) 555555
    //for telephone no validation 
    public boolean isValidTele(String fieldValue) {
        Pattern pattern = Pattern.compile("^(\\+44\\s?7\\d{3}|\\(?07\\d{3}\\)?)\\s?\\d{3}\\s?\\d{3}$");
        Matcher matcher = pattern.matcher(fieldValue);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    //for email validation 
    public boolean isValidEmail(String fieldValue) {
        Pattern pattern = Pattern.compile("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
        Matcher matcher = pattern.matcher(fieldValue);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    /*
     minimum 8 characters string with at least one digit, 
     one upper case letter, one lower case letter and one special symbol (“@#$%”),no whitespace allowed in the entire string*/

    public boolean isValidWithPasswordPolicy(String fieldValue) {
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
        Matcher matcher = pattern.matcher(fieldValue);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidDate(String date) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
            String after = df.format(df.parse(date));
            if (after.equals(date)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }

    }
    
    public boolean isValidTime(String fieldValue) {
        Pattern pattern = Pattern.compile("(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)");
        Matcher matcher = pattern.matcher(fieldValue);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isValidNumber(String number){
        try {
            int num=Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
