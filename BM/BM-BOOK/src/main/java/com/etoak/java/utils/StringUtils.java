package com.etoak.java.utils;

public class StringUtils {
    public static boolean isEmpty(String str){
        if(str != null && !"".equals(str)){
            return false;
        }
        return true;
    }

    public static boolean isNotEmpty(String str){
        if(str != null && !"".equals(str)){
            return true;
        }
        return false;
    }
}
