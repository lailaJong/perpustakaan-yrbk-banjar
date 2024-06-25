package tugasakhir.library.utils.orderdetail;

import java.util.Calendar;
import java.util.Date;

public class TakingDate {

    public static Date setTakingDates(Date orderDate) {
        Date takingDate;
        Calendar calendar = Calendar.getInstance(); // Gets a calendar using the default time zone and locale.
        calendar.setTime(orderDate); // Sets this Calendar's time with the given Date

        if (calendar.get(Calendar.HOUR_OF_DAY) <= 16) {
            takingDate = orderDate; // Taking date is the same as order date
        } else {
            calendar.add(Calendar.DAY_OF_MONTH, 1); // Adds one day to the calendar
            takingDate = calendar.getTime(); // Sets taking date to the next day
        }
        return takingDate;
    }
}
