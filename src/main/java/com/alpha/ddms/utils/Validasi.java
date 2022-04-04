package com.alpha.ddms.utils;

public class Validasi {
    public static  boolean isNullorEmpty(String value){
        if (value == null || value.equals("")){
            return true;
        }
        else {
            return false;
        }
    }
}
