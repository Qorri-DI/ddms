package com.alpha.ddms.common;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checks {

    public static final String[] UNIT_STATUS_ENUM = new String[]
            {"IN_STOCK", "INDENT_PROCESS", "UNIT_RECEIVED",
                    "READY_FOR_DELIVERY", "RECEIPT_BY_CUSTOMER"};

    public static final String[] PAYMENT_STATUS_ENUM = new String[]
            {"FULLY_PAID","NOT_PAID","PARTIAL_PAID"};

    public static final String[] GENDER_ENUM = new String[]
            {"GTLK","GTPR"};

    public static final String[] STATUS_ENUM = new String[]
            {"ACTIVE", "INACTIVE"};

    public static final String[] IS_LEASING_STATUS_ENUM = new String[]
            {"YES","NO"};

    public static boolean isNullOrEmpty(String param){
        return (param == null) || (param.equals(""));
    }

    public static boolean validGender(String gender){
        return Arrays.stream(GENDER_ENUM).anyMatch(gender::equalsIgnoreCase) ? true :  false;
    }

    public static boolean validStatus(String status){
        return Arrays.stream(STATUS_ENUM).anyMatch(status::equalsIgnoreCase) ? true : false;
    }

    public static boolean validEmail(String email){
        Pattern patternEmail =
            Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(.[A-Za-z0-9_-]+)*@"
                    +"[^-][A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(.[A-Za-z]{2,})$");
        Matcher matcher = patternEmail.matcher(email);
        return matcher.find() ? true : false;
    }

    public static boolean validUnitStatus(String unitStatus, int unitStatusCode){
        return Arrays.stream(UNIT_STATUS_ENUM)
                .anyMatch(UNIT_STATUS_ENUM[unitStatusCode-1]::equalsIgnoreCase) ?
             true : false;
    }

    public static boolean validUnitStatus(int unitStatusCode){
        return Arrays.stream(UNIT_STATUS_ENUM)
                .anyMatch(UNIT_STATUS_ENUM[unitStatusCode-1]::equalsIgnoreCase) ?
                true : false;
    }

    public static boolean validUnitStatus(String unitStatus){
        return Arrays.stream(UNIT_STATUS_ENUM).anyMatch(unitStatus::equalsIgnoreCase) ? true : false;
    }


    public static boolean validPaymentStatus(String paymentStatus, int paymentStatusCode){
        return Arrays.stream(PAYMENT_STATUS_ENUM)
                .anyMatch(PAYMENT_STATUS_ENUM[paymentStatusCode-1]::equalsIgnoreCase) ? true : false;
    }

    public static boolean validPaymentStatus(String paymentStatus){
        return Arrays.stream(PAYMENT_STATUS_ENUM).anyMatch(paymentStatus::equalsIgnoreCase) ? true : false;
    }

    public static boolean validPaymentStatus(int paymentStatusCode){
        return Arrays.stream(PAYMENT_STATUS_ENUM)
                .anyMatch(PAYMENT_STATUS_ENUM[paymentStatusCode-1]::equalsIgnoreCase) ? true : false;
    }

}
