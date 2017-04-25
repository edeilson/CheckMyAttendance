package com.edge.checkmyattendance;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by U on 24/04/2017.
 */

public class Util {

    protected static String convertDate(String sDate) {
        String result = null;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            //Date newDate = (Date) formatter.parse(sDate.substring(0,10));
            Date newDate = (Date) formatter.parse(sDate.substring(0, 10));
            SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");
            result = newFormat.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    protected static String getWeekAgo() {
        String weekAgo = null;
        Calendar monday;
        //Date myDate = new Date();
        //System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(myDate));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -9);
        weekAgo = new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime());
        return weekAgo;
    }
}
