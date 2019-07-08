package com.example.flixmini;

import android.text.format.DateUtils;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateLocalizationService {

    public static String TAG = DateLocalizationService.class.getCanonicalName();
    /**
     * TODO Dagger?
     */
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public String localizeDate(String dateString) {
        Date date = null;

        try {
            date = mDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null) {
            Log.wtf(TAG, "Impossible: called DateLocalizationService.localizeDate() with wrong date type");
            return "";
        } else {
            return DateFormat.getDateInstance(DateFormat.SHORT).format(date);
        }
    }

}
