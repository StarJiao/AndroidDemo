package com.star.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期处理类（未完成）
 */
public class DateUtil
{
    /**
     * pattern like 2000-01-01 00:00:00
     */
    public static final String PATTERN1 = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * pattern like 2000-01-01
     */
    public static final String PATTERN2 = "yyyy-MM-dd";
    
    /**
     * pattern like 2000-01-01 星期一 上午
     */
    public static final String PATTERN3 = "yyyy-MM-dd www am";
    
    private static DateFormat f1 = new SimpleDateFormat(PATTERN1,
            Locale.getDefault());
    
    private static DateFormat f2 = new SimpleDateFormat(PATTERN2,
            Locale.getDefault());
    
    private static Calendar calendar = Calendar.getInstance();
    
    private static String[] days = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
            "星期六" };
    
    public static String formatDateStringTo(String from, String pattern)
    {
        switch (pattern)
        {
            case DateUtil.PATTERN1:
                return null;
            case DateUtil.PATTERN2:
                return null;
            case DateUtil.PATTERN3:
                return formatDateTo(from);
            default:
                return null;
        }
    }
    
    public static String formatDateTo(Date from, String pattern)
    {
        return null;
    }
    
    /**
     * 获取“2014-08-25 星期一 上午”格式的日期字符串
     */
    private static String formatDateTo(String from)
    {
        /*时间、星期、午别   */
        String time, weekDay, amOrPm;
        
        Date date = null;
        try
        {
            date = f1.parse(from);
        }
        catch (ParseException e)
        {
            return null;
        }
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        
        time = f2.format(date);
        weekDay = days[dayOfWeek];
        if (hourOfDay > 12)
        {
            amOrPm = "下午";
        }
        else
        {
            amOrPm = "上午";
        }
        
        return time + " " + weekDay + " " + amOrPm;
    }
    /**
     * 获取“”
     */
}
