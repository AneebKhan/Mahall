package utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zahmed on 12/11/2018.
 * ITF
 */

public class DateFormatter {
    public static final String DISPLAY_DATE_FORMAT = "dd MMM hh:mm";
    public static final String DISPLAY_DATE_BOTTOM_SHEET_FORMAT = "dd MMM yyyy";
    public static final String DATEPICKER_DISPLAY_DATE_FORMAT = "dd MM yyyy";
    public static final String DISPLAY_MONTH_FORMAT = "MMM";
    public static final String DISPLAY_DAY_FORMAT = "dd";
    public static final String DATA_DATE_FORMAT = "yyyy-MM-dd";
    public static final String UI_DATE_FORMAT = "yyyy-MM-dd";
    public static final String TRANSACTION_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String SERVER_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm a";
    public static final String TIME_FORMAT = "HH:mm a";
    public static final String MONTH_DISPLAY_FORMAT = "dd-MMM-yyyy";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm";

    public static String getDay(Calendar cal){
        String monthName = new SimpleDateFormat(DISPLAY_DAY_FORMAT).format(cal.getTime());
        return monthName;
    }

    public static String getMonth(Calendar cal){
        String monthName = new SimpleDateFormat(DISPLAY_MONTH_FORMAT).format(cal.getTime());
        return monthName;
    }

    public static String getDate(Calendar cal, String formatString){
        String date = new SimpleDateFormat(formatString).format(cal.getTime());
        return date;
    }

    public static String getDate(Calendar cal){
        return getDate(cal,DISPLAY_DATE_FORMAT);
    }

    public static String getDate(String stringDate, String formatString){
        SimpleDateFormat format = new SimpleDateFormat(DATA_DATE_FORMAT);
        Date date = null;
        try {
            date = format.parse(stringDate);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        return (getDate(cal,formatString));
    }

    public static String getDate(String stringDate){
        return getDate(stringDate, DISPLAY_DATE_FORMAT);
    }

    public static String getCurrentDateTime() {
        String currDate = new SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.getDefault()).format(new Date());
        return currDate;
    }

    public static String parseDateTimeFormat(String dateTime, String inputStrFormat, String outputStrFormat) {
        //String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String inputPattern = inputStrFormat;
        //String outputPattern = "dd-MMM-yyyy h:mm a";
        String outputPattern = outputStrFormat;
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(dateTime);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

}
