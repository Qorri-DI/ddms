package com.alpha.ddms.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;


public class Utils {

    public static String getCurrentDateTimeString(){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        dateFormat.setLenient(false);
        String strDateTimeNow = dateFormat.format(Calendar.getInstance().getTime());
        return strDateTimeNow;
    }

    public static String generateLocalDateId(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssnnnn");
        String date = LocalDateTime.now().format(formatter);
        return date;
    }

    public static Boolean checkId(String id){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssnnnn");
        LocalDateTime.parse(id);
        try{
            LocalDateTime.parse(id,formatter);
            return true;
        }catch (DateTimeParseException e){
            return false;
        }
    }

    public static String generateLatestId(String id){
        int sequence = Integer.parseInt(id);

        StringBuffer s = new StringBuffer();
        for(int i = String.valueOf(sequence).length() ; i<7; i++){
            s.append('0');
        }
        if(sequence > 0){
            s.append(sequence);
        }else{
            s.append('1');
        }
        return getCurrentDateTimeString()+s;
    }

}
