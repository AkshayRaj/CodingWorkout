package ark.coding.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Akshayraj on 3/9/16.
 */
public class TimeConverter {
    public static String dateFormat = "dd-MM-yyyy HH:mm:ss.SSS";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
    private static Calendar calendar = Calendar.getInstance();

    public static String millisToDate(String millis){
        String dateString = new String();
        calendar.setTimeInMillis(Long.parseLong(millis));
        return simpleDateFormat.format(calendar.getTime());
    }

}
