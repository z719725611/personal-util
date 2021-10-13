package com.personal.personalutil.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Author: zengqb
 * @Description: 日期工具类
 * @Date: Created in 2021/10/11
 * @Modified By:
 */
public class DateUtil extends DateUtils {

    /**
     * 获取当天还剩多少秒
     *
     * @return
     */
    public static long getDayEndSec() {
        LocalDateTime midnight = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        return ChronoUnit.SECONDS.between(LocalDateTime.now(), midnight);
    }

    /**
     * 获取本周还剩多少秒
     *
     * @return
     */
    public static long getWeekEndSec() {
        LocalDateTime midnight = getEndDayOfWeek(LocalDateTime.now()).plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        return ChronoUnit.SECONDS.between(LocalDateTime.now(), midnight);
    }

    /***
     * 获取本周最后一天
     * @param date
     * @return
     */
    public static LocalDateTime getEndDayOfWeek(TemporalAccessor date) {
        TemporalField fieldIso = WeekFields.of(DayOfWeek.MONDAY, 1).dayOfWeek();
        LocalDate localDate = LocalDate.from(date);
        localDate = localDate.with(fieldIso, 7);
        return localDate.atStartOfDay();
    }

    public static long getToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获取指定天数之前的零点零分零秒
     *
     * @return
     */
    public static Long zero(int day) {
        long time = System.currentTimeMillis(); // 当前时间的时间戳
        return time / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset() - day * (1000 * 3600 * 24);// 今天零点零分零秒的毫秒数
    }


    /**
     * @param pattern -格式 <br>
     *                Date date -日期对象
     * @return String -日期字符串
     * @description 将日期对象date转化成格式pattern的日期字符串
     */
    public static String dateToString(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(pattern).format(date);
    }


    /**
     * 昨天。
     */
    public static int yesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.get(Calendar.YEAR) * 10000 + (cal.get(Calendar.MONTH) + 1) * 100 + cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 今天。
     */
    public static int today() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR) * 10000 + (cal.get(Calendar.MONTH) + 1) * 100 + cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 明天。
     */
    public static int tomorrow() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        return cal.get(Calendar.YEAR) * 10000 + (cal.get(Calendar.MONTH) + 1) * 100 + cal.get(Calendar.DAY_OF_MONTH);
    }

    public static String dateToString(Integer dayNumber) {
        if (null == dayNumber) {
            return null;
        }
        int year = dayNumber / 10000;
        int month = (dayNumber - year * 10000) / 100;
        int day = dayNumber - year * 10000 - month * 100;
        String date = String.valueOf(year);
        if (month < 10) {
            date += "-0" + month;
        } else {
            date += "-" + month;
        }
        if (day < 10) {
            date += "-0" + day;
        } else {
            date += "-" + day;
        }
        return date;
    }

    /**
     * 获取指定某一天的开始时间戳
     */
    public static Long getDailyBegin(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取指定某一天的结束时间戳
     */
    public static Long getDailyEnd(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }

    public static int getDateNumber(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        return cal.get(Calendar.YEAR) * 10000 + (cal.get(Calendar.MONTH) + 1) * 100 + cal.get(Calendar.DAY_OF_MONTH);
    }
}
