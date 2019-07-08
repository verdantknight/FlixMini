package com.example.flixmini;

import android.util.Log;

import com.example.flixmini.utils.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * TODO Redo
 */
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
            // TODO Redo
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            String month = calendar.getDisplayName(Calendar.MONTH,
                    Calendar.LONG, Constants.currentLocale);
            return String.format("%s %s %s",
                    calendar.get(Calendar.DAY_OF_MONTH),
                    month,
                    calendar.get(Calendar.YEAR));
        }
    }

}
