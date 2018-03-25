/**
 * We have finished part of this class yet, you should finish the rest.
 * 1. A constructor that can return a CalendarDate object through the given string.
 * 2. A method named getDayOfWeek() that can get the index of a day in a week.
 */
class CalendarDate {
    private int year;
    private int month;
    private int day;

    CalendarDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * a constructor that can return a CalendarDate object through the given string.
     *
     * @param dateString format: 2018-3-18
     */
    CalendarDate(String dateString) throws UnformattedDateStringException {
        if (DateUtil.isFormatted(dateString)) {
            extractFormattedDateString(dateString);
        } else {
            throw new UnformattedDateStringException("unformatted date string");
        }
    }

    /**
     * @param formattedDateString the formatted date string
     *                            extract the year,the month and the day from it
     */
    private void extractFormattedDateString(String formattedDateString) {
        String temp[] = formattedDateString.split("-");
        this.year = Integer.parseInt(temp[0]);
        this.month = Integer.parseInt(temp[1]);
        this.day = Integer.parseInt(temp[2]);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    /**
     * Get index of the day in a week for one date.
     * <p>
     * Don't use the existing implement like Calendar.setTime(),
     * try to implement your own algorithm.
     *
     * @return 0-7, 0 stands for a invalid date, 1 stands for Monday
     *          and 7 stands for Sunday
     */
    public int getDayOfWeek() {
        if (DateUtil.isValid(this)) {
            boolean isJanuaryOrFebruary = month == 1 || month == 2;
            if (isJanuaryOrFebruary) {
                month += 12;
                year--;
            }

            // compute day of week
            int index = (day + 2 * month + 3 * (month + 1) / 5 +
                    year + year / 4 - year / 100 + year / 400) % 7 + 1;

            if (isJanuaryOrFebruary) {
                month -= 12;
                year++;
            }
            return index;
        } else
            return 0;
    }
}
