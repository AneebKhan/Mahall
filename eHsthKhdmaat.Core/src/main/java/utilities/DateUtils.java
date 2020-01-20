package utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.itf.phatbooskiandroid.enums.IntervalType;
import static utilities.DateFormatter.DISPLAY_DATE_FORMAT;

/**
 * Created by zahmed on 12/11/2018.
 * ITF
 */

public class DateUtils {
    public static Calendar[] getDateIntervals(IntervalType type, Calendar reference) {
        if (reference == null) {
            reference = Calendar.getInstance();
        }
        Calendar startDate = (Calendar) reference.clone();
        Calendar endDate = (Calendar) reference.clone();
        if (type == IntervalType.CurrentYear) {
            // first date of the Year
            startDate.set(Calendar.DAY_OF_YEAR, 1);

            // current date of the Year
            endDate.set(Calendar.YEAR, endDate.get(Calendar.YEAR));
        } else if (type == IntervalType.LastYear) {
            // first date of the Year
            startDate.set(Calendar.YEAR, startDate.get(Calendar.YEAR) - 1);

            // Last date of the Year
            startDate.set(Calendar.DAY_OF_YEAR, 1);

            endDate.add(Calendar.YEAR, -1);
            endDate.set(Calendar.DAY_OF_YEAR, endDate.getActualMaximum(Calendar.DAY_OF_YEAR));
        } else if (type == IntervalType.CurrentMonth) {
            // first date of the month
            startDate.set(Calendar.DAY_OF_MONTH, 1);

            // first date of the month
            endDate.set(Calendar.YEAR, endDate.get(Calendar.YEAR));
        } else if (type == IntervalType.LastMonth) {
            // previous month
            startDate.add(Calendar.MONTH, -1);
            startDate.set(Calendar.DATE, 1);
            // previous month, last date
            endDate.add(Calendar.MONTH, -1);
            endDate.set(Calendar.DAY_OF_MONTH, endDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        } else if (type == IntervalType.YesterDay) {
            startDate.add(Calendar.DATE, -1);
            endDate.add(Calendar.DATE, -1);
        } else if (type == IntervalType.Today) {
            startDate.add(Calendar.DATE, 0);
            endDate.add(Calendar.DATE, 0);
        } else if (type == IntervalType.Last3Month) {
            // previous month
            startDate.add(Calendar.MONTH, -3);
            startDate.set(Calendar.DATE, 1);
            // previous month, last date
            endDate.add(Calendar.MONTH, -1);
            endDate.set(Calendar.DAY_OF_MONTH, endDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        } else if (type == IntervalType.Last6Month) {
            // previous month
            startDate.add(Calendar.MONTH, -6);
            startDate.set(Calendar.DATE, 1);
            // previous month, last date
            endDate.add(Calendar.MONTH, -1);
            endDate.set(Calendar.DAY_OF_MONTH, endDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        } else if (type == IntervalType.CurrentWeek) {

            // current Week
            startDate.add(Calendar.DAY_OF_WEEK, startDate.getFirstDayOfWeek() - startDate.get(Calendar.DAY_OF_WEEK));
            // previous Week, last date
            endDate.set(Calendar.YEAR, endDate.get(Calendar.YEAR));

        } else if (type == IntervalType.LastWeek) {
            // previous week by convention (monday ... sunday)
            // you will have to adjust this a bit if you want
            // sunday to be considered as the first day of the week.
            // start date : decrement until first sunday then
            // down to monday
            int dayOfWeek = startDate.get(Calendar.DAY_OF_WEEK);
            while (dayOfWeek != Calendar.SUNDAY) {
                startDate.add(Calendar.DATE, -1);
                dayOfWeek = startDate.get(Calendar.DAY_OF_WEEK);
            }
            while (dayOfWeek != Calendar.MONDAY) {
                startDate.add(Calendar.DATE, -1);
                dayOfWeek = startDate.get(Calendar.DAY_OF_WEEK);
            }

            // end date , decrement until the first sunday
            dayOfWeek = endDate.get(Calendar.DAY_OF_WEEK);
            while (dayOfWeek != Calendar.SUNDAY) {
                endDate.add(Calendar.DATE, -1);
                dayOfWeek = endDate.get(Calendar.DAY_OF_WEEK);
            }
        } else {
            new Exception();
        }
        return new Calendar[]{startDate, endDate};
    }

    public static Date[] getCurrentWeek() {
        Calendar[] results = DateUtils.getDateIntervals(IntervalType.CurrentWeek, null);
        return new Date[]{getFormattedFromDateTime(results[0].getTime()), getFormattedToDateTime(results[1].getTime())};
    }

    public static Date[] getLastWeek() {
        Calendar[] results = DateUtils.getDateIntervals(IntervalType.LastWeek, null);
        return new Date[]{getFormattedFromDateTime(results[0].getTime()), getFormattedToDateTime(results[1].getTime())};
    }

    public static Date[] getCurrentMonth() {
        Calendar[] results = DateUtils.getDateIntervals(IntervalType.CurrentMonth, null);
        return new Date[]{getFormattedFromDateTime(results[0].getTime()), getFormattedToDateTime(results[1].getTime())};
    }

    public static Date[] getLastMonth() {
        Calendar[] results = DateUtils.getDateIntervals(IntervalType.LastMonth, null);
        return new Date[]{getFormattedFromDateTime(results[0].getTime()), getFormattedToDateTime(results[1].getTime())};
    }

    public static Date[] getLast3Month() {
        Calendar[] results = DateUtils.getDateIntervals(IntervalType.Last3Month, null);
        return new Date[]{getFormattedFromDateTime(results[0].getTime()), getFormattedToDateTime(results[1].getTime())};
    }

    public static Date[] getLast6Month() {
        Calendar[] results = DateUtils.getDateIntervals(IntervalType.Last6Month, null);
        return new Date[]{getFormattedFromDateTime(results[0].getTime()), getFormattedToDateTime(results[1].getTime())};
    }

    public static Date[] getCurrentYear() {
        Calendar[] results = DateUtils.getDateIntervals(IntervalType.CurrentYear, null);
        return new Date[]{getFormattedFromDateTime(results[0].getTime()), getFormattedToDateTime(results[1].getTime())};
    }

    public static Date[] getLastYear() {
        Calendar[] results = DateUtils.getDateIntervals(IntervalType.LastYear, null);
        return new Date[]{getFormattedFromDateTime(results[0].getTime()), getFormattedToDateTime(results[1].getTime())};
    }

    public static Date[] getYesterDay() {
        Calendar[] results = DateUtils.getDateIntervals(IntervalType.YesterDay, null);
        return new Date[]{getFormattedFromDateTime(results[0].getTime()), getFormattedToDateTime(results[1].getTime())};
    }

    public static Date[] getToday() {
        Calendar[] results = DateUtils.getDateIntervals(IntervalType.Today, null);
        return new Date[]{getFormattedFromDateTime(results[0].getTime()), getFormattedToDateTime(results[1].getTime())};
    }

    public static String getDateRangeNameByIntervalType(String intervalType) {
        String dateName = "";
        if (IntervalType.CurrentWeek.toString().equals(intervalType)) {
            dateName = IntervalType.CurrentWeek.name();
        } else if (IntervalType.LastWeek.toString().equals(intervalType)) {
            dateName = IntervalType.LastWeek.name();
        } else if (IntervalType.CurrentMonth.toString().equals(intervalType)) {
            dateName = IntervalType.CurrentMonth.name();
        } else if (IntervalType.LastMonth.toString().equals(intervalType)) {
            dateName = IntervalType.LastMonth.name();
        } else if (IntervalType.Last3Month.toString().equals(intervalType)) {
            dateName = IntervalType.Last3Month.name();
        } else if (IntervalType.Last6Month.toString().equals(intervalType)) {
            dateName = IntervalType.Last6Month.name();
        } else if (IntervalType.CurrentYear.toString().equals(intervalType)) {
            dateName = IntervalType.CurrentYear.name();
        } else if (IntervalType.LastYear.toString().equals(intervalType)) {
            dateName = IntervalType.LastYear.name();
        }
        return dateName;
    }

    public static Date[] getDatesByIntervalType(String intervalType) {

        Date[] dateRange = new Date[1];
        if (IntervalType.CurrentWeek.equals(intervalType)) {
            dateRange = DateUtils.getCurrentWeek();
        } else if (IntervalType.LastWeek.equals(intervalType)) {
            dateRange = DateUtils.getLastWeek();
        } else if (IntervalType.CurrentMonth.equals(intervalType)) {
            dateRange = DateUtils.getCurrentMonth();
        } else if (IntervalType.LastMonth.equals(intervalType)) {
            dateRange = DateUtils.getLastMonth();
        } else if (IntervalType.Last3Month.equals(intervalType)) {
            dateRange = DateUtils.getLast3Month();
        } else if (IntervalType.Last6Month.equals(intervalType)) {
            dateRange = DateUtils.getLast6Month();
        } else if (IntervalType.CurrentYear.equals(intervalType)) {
            dateRange = DateUtils.getCurrentYear();
        } else if (IntervalType.LastYear.equals(intervalType)) {
            dateRange = DateUtils.getLastYear();
        } else if (IntervalType.YesterDay.equals(intervalType)) {
            dateRange = DateUtils.getYesterDay();
        } else if (IntervalType.Today.equals(intervalType)) {
            dateRange = DateUtils.getToday();
        }

        return dateRange;
    }

    private static Date getFormattedFromDateTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    private static Date getFormattedToDateTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    public static int getDaysForMonth(int month, int year) {

        // month is 0-based

        if (month == 1) {
            boolean is29Feb = false;

            if (year < 1582)
                is29Feb = (year < 1 ? year + 1 : year) % 4 == 0;
            else if (year > 1582)
                is29Feb = year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);

            return is29Feb ? 29 : 28;
        }

        if (month == 3 || month == 5 || month == 8 || month == 10)
            return 30;
        else
            return 31;
    }

    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date[] getFakeTodayDate() {
        DateFormat df = new SimpleDateFormat(DISPLAY_DATE_FORMAT);
        String date = "12/07/2018";
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = df.parse(date);
            endDate = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Date[]{getFormattedFromDateTime(startDate), getFormattedToDateTime(endDate)};
    }

}
