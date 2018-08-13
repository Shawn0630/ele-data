package com.ele.data.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EleDateFormat {
    public static final String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

}
