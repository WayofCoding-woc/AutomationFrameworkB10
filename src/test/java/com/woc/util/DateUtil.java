package com.woc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String convertDate(Date date){
        if(null == date){
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }

}
