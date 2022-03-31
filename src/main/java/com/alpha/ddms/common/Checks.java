package com.alpha.ddms.common;

public class Checks {
    public static boolean isNullOrEmpty(String param){
        return (param == null) || (param.equals(""));
    }
}
