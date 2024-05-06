package Utils;


import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static LocalDate convertToLocalDateUsingCalendar(Date dateToConvert) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateToConvert);
        return LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
    }
}
