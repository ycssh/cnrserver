package cn.cnr.admin.base.util;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by yuchao on 2017/4/11.
 */
public class TimeUtil {
    public static long getDayBeginTimestamp() {
        Date date = new Date();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        Date date2 = new Date(date.getTime() - gc.get(gc.HOUR_OF_DAY) * 60 * 60
                * 1000 - gc.get(gc.MINUTE) * 60 * 1000 - gc.get(gc.SECOND)
                * 1000);
        return date2.getTime();
    }
}
