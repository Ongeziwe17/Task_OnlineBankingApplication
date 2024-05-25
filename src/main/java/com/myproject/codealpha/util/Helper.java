package com.myproject.codealpha.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {
    public static Boolean isNullOrEmpty(Object object){
        if(object == null)  return true;
        // Validating for String objects
        if(object instanceof String) return ((String) object).isEmpty();
        // Validating for Number objects (including long, double)
        if (object instanceof Number) return ((Number) object).doubleValue() == 0;

        return false;
    }
    public static boolean isValidEmail(String emailAddress) {
        /* validate email using regex
         * matching the emailAddress with the RegEx pattern*/

        final String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        final Pattern pattern = Pattern.compile(emailPattern);
        final Matcher matcher = pattern.matcher(emailAddress);
        return matcher.matches();
    }
}
