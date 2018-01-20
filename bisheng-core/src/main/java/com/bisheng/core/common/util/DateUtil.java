/*
 * Copyright 2016 mljr.com All right reserved. This software is the
 * confidential and proprietary information of mljr.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with mljr.com .
 */
package com.bisheng.core.common.util;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 */
public class DateUtil {
    private static Logger log = LoggerFactory.getLogger(DateUtil.class);
    public static final SimpleDateFormat SIMPLE_SDF = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat FULL_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String dateStr = sdf.format(date);
        return dateStr;
    }

    public static String formatDate(Date date) {
        if (date == null)
            return null;

        String simpleDateStr = DateUtil.SIMPLE_SDF.format(date);
        return simpleDateStr;
    }

    public static String formatDateTime(Date date) {
        if (date == null)
            return null;

        String simpleDateStr = DateUtil.FULL_SDF.format(date);
        return simpleDateStr;
    }

    public static Date parseDate(String dateStr) throws ParseException {
        if (dateStr == null || dateStr.length() == 0) {
            return null;
        }
        Date date = DateUtil.SIMPLE_SDF.parse(dateStr);
        return date;
    }

    public static Date parse(String dateStr, String pattern) throws ParseException {
        if (dateStr == null || dateStr.length() == 0) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = sdf.parse(dateStr);
        return date;
    }

    public static Date parseDateTime(String dateStr) throws ParseException {
        if (dateStr == null || dateStr.length() == 0) {
            return null;
        }
        Date date = DateUtil.FULL_SDF.parse(dateStr);
        return date;
    }

    public static Date justParse(String dateStr, String pattern) {
        if (dateStr == null || dateStr.length() == 0) {
            return null;
        }

        Date date = null;
        try {
            date = DateUtil.parse(dateStr, pattern);
        } catch (Exception e) {
            log.error("parse date ERROR! dateStr is [" + dateStr + "], pattern is [" + pattern + "]", e);
        }
        return date;
    }

    public static Date justParseDate(String dateStr) {
        if (dateStr == null || dateStr.length() == 0) {
            return null;
        }

        Date date = null;
        try {
            date = DateUtil.parseDate(dateStr);
        } catch (ParseException e) {
            log.error("parse simple date ERROR! dateStr is [" + dateStr + "]", e);
        }
        return date;
    }

    public static Date justParseDateTime(String dateStr) {
        if (dateStr == null || dateStr.length() == 0) {
            return null;
        }
        Date date = null;
        try {
            date = DateUtil.parseDateTime(dateStr);
        } catch (ParseException e) {
            log.error("parse full date ERROR! dateStr is [" + dateStr + "]", e);
        }
        return date;
    }

    public static int calculateDays(Date startDate, Date endDate) {
        startDate = DateUtil.clearTimeOfDate(startDate);
        endDate = DateUtil.clearTimeOfDate(endDate);
        long diff = endDate.getTime() - startDate.getTime();
        int days = (int) (diff / (1000 * 60 * 60 * 24));

        return days;
    }

    /**
     * 获取今天还剩下多少秒
     *
     * @return
     */
    public static int getSeconds() {
        Calendar curDate = Calendar.getInstance();
        Calendar tomorrowDate = new GregorianCalendar(curDate
                .get(Calendar.YEAR), curDate.get(Calendar.MONTH), curDate
                .get(Calendar.DATE) + 1, 0, 0, 0);
        return (int) (tomorrowDate.getTimeInMillis() - curDate.getTimeInMillis()) / 1000;
    }

    /**
     * 取得当前时间戳
     *
     * @return 当前时间戳
     */
    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentHour(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
        return dateFormat.format(Calendar.getInstance().getTime());
    }

    /**
     * 取得当前天后，加上指定天数后的最小时间
     *
     * @param date   当前日期
     * @param addDay 天数
     * @return 当前天后，加上指定天数后的最小时间
     */
    public static Date getDayMin(Date date, int addDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DATE, addDay);

        return cal.getTime();
    }

    /**
     * 取得当前天 ,加上指定天数后的最大时间
     *
     * @param date   当前日期
     * @param addDay 天数
     * @return 当前天 ,加上指定天数后的最大时间
     */
    public static Date getDayMax(Date date, int addDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        cal.add(Calendar.DATE, addDay);

        return cal.getTime();
    }

    /**
     * 取得当前天后，减去指定天数后的最小时间
     *
     * @param date      当前日期
     * @param beforeDay 天数
     * @return 当前天后，减去指定天数后的最小时间
     */
    public static Date getBeforeDayMin(Date date, int beforeDay) {
        return getDayMin(date, -beforeDay);
    }

    public static Date getAfterDayMin(Date date, int afterDay) {
        return getDayMin(date, afterDay);
    }

    /**
     * 取得当前天后，减去指定天数后的最大时间
     *
     * @param date      当前日期
     * @param beforeDay 天数
     * @return 当前天后，减去指定天数后的最大时间
     */
    public static Date getBeforeDayMax(Date date, int beforeDay) {
        return getDayMax(date, -beforeDay);
    }

    public static Date getAfterDayMax(Date date, int afterDay) {
        return getDayMax(date, afterDay);
    }


    public static DateInterval calculateDateInterval(Date startDate, Date endDate) {
        DateInterval result = new DateInterval();

        startDate = DateUtil.clearTimeOfDate(startDate);
        endDate = DateUtil.clearTimeOfDate(endDate);
        //        if (startDate.compareTo(endDate) >= 0) {
        //            return result;
        //        }

        int totalDays = DateUtil.calculateDays(startDate, endDate);
        result.setTotalDays(totalDays);
        int wholeMonths = 0;
        Date nextMonth = DateUtil.addMonths(startDate, 1);
        Date prevNextMonth = startDate;
        while (true) {
            if (nextMonth.compareTo(endDate) < 0) {
                wholeMonths++;

                prevNextMonth = nextMonth;
                nextMonth = DateUtil.addMonths(nextMonth, 1);
                continue;
            } else if (nextMonth.compareTo(endDate) == 0) {
                result.setWholeMonths(wholeMonths + 1);
                break;
            } else {
                int leftDays = DateUtil.calculateDays(prevNextMonth, endDate);
                result.setWholeMonths(wholeMonths);
                result.setLeftDays(leftDays);
                break;
            }
        }

        return result;
    }

    public static Date clearTimeOfDate(Date date) {
        return DateUtil.justParseDate(DateUtil.formatDate(date));
    }

    public static class DateInterval {
        private int wholeMonths;
        private int leftDays;
        private int totalDays;

        public int getWholeMonths() {
            return wholeMonths;
        }

        public void setWholeMonths(int wholeMonths) {
            this.wholeMonths = wholeMonths;
        }

        public int getLeftDays() {
            return leftDays;
        }

        public void setLeftDays(int leftDays) {
            this.leftDays = leftDays;
        }

        public int getTotalDays() {
            return totalDays;
        }

        public void setTotalDays(int totalDays) {
            this.totalDays = totalDays;
        }
    }

    /**
     * 获取当天的开始时间0点
     *
     * @return
     */
    public static Date getStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     * 获取date的开始时间0点
     *
     * @return
     */
    public static Date getStartTime(Date date) {
        Calendar todayStart = Calendar.getInstance();
        todayStart.setTime(date);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     * 通过unit add time
     *
     * @param startDate
     * @param count
     * @param unit      0day,1MONTH,2YEAR
     * @return
     */
    public static Date addTimeByUnit(Date startDate, int count, int unit) {
        if (unit == 0) {
            return DateUtil.addDays(startDate, count);
        } else if (unit == 1) {
            return DateUtil.addMonths(startDate, count);
        } else {
            return DateUtil.addYears(startDate, count);
        }
    }

    public static Date addDays(Date startDate, int amount) {
        Date date = DateUtils.addDays(startDate, amount);
        return date;
    }

    public static Date addMilliseconds(Date startDate, int amount) {
        Date date = DateUtils.addMilliseconds(startDate, amount);
        return date;
    }

    public static Date addMonths(Date startDate, int amount) {
        Date date = DateUtils.addMonths(startDate, amount);
        return date;
    }

    public static Date addYears(Date startDate, int amount) {
        Date date = DateUtils.addYears(startDate, amount);
        return date;
    }

    public static int calculateDaysFromBeginningOfTheMonth(Date endDate, boolean includeEndDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(endDate);
        c.set(Calendar.DAY_OF_MONTH, 1);
        int days = DateUtil.calculateDays(c.getTime(), endDate);
        if (includeEndDate) {
            days = days + 1;
        }
        return days;
    }

    public static int calculateDaysOfCurrentMonth() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);//把日期设置为当月第一天
        c.roll(Calendar.DAY_OF_MONTH, -1);//日期回滚一天，也就是最后一天
        int days = c.get(Calendar.DATE);
        return days;
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static Date getCurrentDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String currDate = format.format(new Date());
        Date effectiveDate = null;
        try {
            effectiveDate = format.parse(currDate);
        } catch (ParseException e) {
            log.error("日期转换异常 [" + currDate + "]", e);
        }
        return effectiveDate;
    }

}
