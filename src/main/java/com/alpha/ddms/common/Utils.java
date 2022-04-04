package com.alpha.ddms.common;

import java.text.*;
import java.time.*;
import java.time.format.*;
import java.util.*;


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
        String first = date.substring(0,9);
        String second = date.substring(10,17);
        String  complete = first + "24" + second;
        return complete;
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
