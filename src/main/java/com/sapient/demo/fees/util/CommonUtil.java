package com.sapient.demo.fees.util;

import com.sapient.demo.logger.TransLogMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class CommonUtil {

    public static Logger LOG = LoggerFactory.getLogger(CommonUtil.class);

    public static boolean checkDate(final String startDate,final String endDate) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setLenient(false);
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(sdf.parse(startDate));
        cal2.setTime(sdf.parse(endDate));
        return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
    }
}
