/*
 * This class provides some utils that may help you to finish this lab.
 * getToday() is finished, you can use this method to get the current date.
 * The other four methods getDaysInMonth(), isValid(), isFormatted() and isLeapYear() are not finished,
 * you should implement them before you use.
 *
 * */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateUtil {

    /*
     * can not construct a DateUtil object
     * */
    private DateUtil() {
    }

    /**
     * get a CalendarDate instance point to today
     *
     * @return a CalendarDate object
     */
    public static CalendarDate getToday() {
        Calendar calendar = Calendar.getInstance();
        return new CalendarDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * get all dates in the same month with given date
     *
     * @param date the given date
     * @return a list of days in a whole month
     */
    public static List<CalendarDate> getDaysInMonth(CalendarDate date) {
        if (date != null && isValid(date)) {
            int y = date.getYear();
            int m = date.getMonth();
            int num = numberOfDays(m, isLeapYear(y));
            List<CalendarDate> days = new ArrayList<>();
            for (int i = 1; i <= num; i++)
                days.add(new CalendarDate(y, m, i));
            return days;
        }
        return null;
    }

    /**
     * Judge whether the input date is valid. For example, 2018-2-31 is not valid
     *
     * @param date the input date
     * @return true if the date is valid, false if the date is not valid.
     */
    public static boolean isValid(CalendarDate date) {
        if (date == null)
            return false;
        int m = date.getMonth();
        int d = date.getDay();
        return m >= 1 && m <= 12 && d >= 28 && d <= 31 && d == numberOfDays(m, isLeapYear(date.getYear()));
    }

    /*
     * get a month's number of days
     * */
    private static int numberOfDays(int m, boolean isLeap) {
        if ((m % 2 == 1 && m < 8) || (m % 2 == 0 && m >= 8))
            return 31;
        if ((m % 2 == 0 && m < 8 && m != 2) || (m % 2 == 1 && m >= 8))
            return 30;
        return isLeap ? 29 : 28;
    }

    /**
     * Judge whether the input is formatted.
     * For example, 2018/2/1 is not valid and 2018-2-1 is valid.
     *
     * @param dateString
     * @return true if the input is formatted, false if the input is not formatted.
     */
    public static boolean isFormatted(String dateString) {
        return dateString.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$");
    }

    /**
     * Judge whether the input year is a leap year or not.
     * For example, year 2000 is a leap year, and 1900 is not.
     *
     * @param year
     * @return true if the input year is a leap year, false if the input is not.
     */
    public static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

}

