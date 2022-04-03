package com.alpha.ddms.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utils {

    public static String getCurrentDateTimeString(){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        dateFormat.setLenient(false);
        String strDateTimeNow = dateFormat.format(Calendar.getInstance().getTime());
        return strDateTimeNow;
    }

    public static String generateLocalDateId(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddhhmmssnnnn");
        String date = LocalDateTime.now().format(formatter);
        String first = date.substring(0,10);
        String second = date.substring(10,17);
        String  complete = first + "24" + second;
        return complete;
    }

    public static Boolean checkId(String id){
        Pattern pattern = Pattern.compile("[0-9]{10}24[0-9]{7}");
        Matcher matcher = pattern.matcher(id);
        boolean matchFound = matcher.find();
        if(matchFound) {
            return true;
        } else {
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
