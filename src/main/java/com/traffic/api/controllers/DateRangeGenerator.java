package com.traffic.api.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateRangeGenerator {

    public String generateStartDate(String period) {
        Calendar cal = Calendar.getInstance();
        if (period.equals("last_half")) {
            /*Пол года*/
            cal.add(Calendar.DATE, -183);
        } else if (period.equals("last_month")) {
            /*Месяц*/
            cal.add(Calendar.DATE, -30);
        } else if (period.equals("last_week")) {
            /*Неделя*/
            cal.add(Calendar.DATE, -7);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(cal.getTime());
        return date;
    }
}
