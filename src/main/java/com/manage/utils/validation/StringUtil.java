package com.manage.utils.validation;

public class StringUtil {
    public static boolean isNumeric (String str){
        try{
            Double.parseDouble(str);
            return  true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
