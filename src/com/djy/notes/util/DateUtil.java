package com.djy.notes.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author djy
 */
public class DateUtil {

    //将时间戳类型转换成字符串类型
    public static String convert2Str(Timestamp timestamp){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(timestamp);
    }

    //将date类型转成时间戳
    public static Timestamp convert2Timestamp(Date date){
        return new Timestamp(date.getTime());
    }
}
