package com.alpha.ddms.common;

import java.text.*;
import java.util.*;


public class Utils {

    public static String getCurrentDateTimeString(){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        dateFormat.setLenient(false);
        String strDateTimeNow = dateFormat.format(Calendar.getInstance().getTime());
        return strDateTimeNow;
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