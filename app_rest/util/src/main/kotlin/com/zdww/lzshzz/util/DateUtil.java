package com.zdww.lzshzz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日、星期（周）、旬、月、季度、年等时间工具类
 */
public class DateUtil {

    private final static SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat longHourSdf = new SimpleDateFormat("yyyy-MM-dd HH");
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");


    /**
     * 根据要求的格式，格式化时间，返回String
     *
     * @param format 默认：yyyy-MM-dd HH:mm:ss
     * @param time   要格式化的时间
     * @return 时间字符串
     */
    public static String toStr(String format, Date time) {
        SimpleDateFormat df = null;
        if (null == format) {
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        } else {
            df = new SimpleDateFormat(format);
        }
        try {
            return df.format(time);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 字符串转时间
     *
     * @param source yyyy-MM-dd HH:mm:ss.SSS 格式的字符串
     * @return
     */
    public static Date toDate(String source) {
        String formatString = "yyyy-MM-dd hh:mm:ss";
        if (source == null || "".equals(source.trim())) {
            return null;
        }
        source = source.trim();
        if (source.matches("^\\d{4}$")) {
            formatString = "yyyy";
        } else if (source.matches("^\\d{4}-\\d{1,2}$")) {
            formatString = "yyyy-MM";
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            formatString = "yyyy-MM-dd";
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}$")) {
            formatString = "yyyy-MM-dd hh";
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
            formatString = "yyyy-MM-dd hh:mm";
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            formatString = "yyyy-MM-dd hh:mm:ss";
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}$")) {
            formatString = "yyyy-MM-dd HH:mm:ss.SSS";
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formatString);
            Date date = sdf.parse(source);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得本小时的开始时间
     *
     * @return
     */
    public static Date getHourStartTime(Date date) {
        Date dt = null;
        try {
            dt = longHourSdf.parse(longHourSdf.format(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * 获得本小时的结束时间
     *
     * @return
     */
    public static Date getHourEndTime(Date date) {
        Date dt = null;
        try {
            dt = longSdf.parse(longHourSdf.format(date) + ":59:59.999");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * 获得本天的开始时间
     *
     * @return
     */
    public static Date getDayStartTime(Date date) {
        Date dt = null;
        try {
            dt = shortSdf.parse(shortSdf.format(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * 获得本天的结束时间
     *
     * @return
     */
    public static Date getDayEndTime(Date date) {
        Date dt = null;
        try {
            dt = longSdf.parse(shortSdf.format(date) + " 23:59:59.999");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * 当前时间是星期几
     *
     * @return
     */
    public static int getWeekDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int week_of_year = c.get(Calendar.DAY_OF_WEEK);
        return week_of_year - 1;
    }

    /**
     * 获得本周的第一天，周一
     *
     * @return
     */
    public static Date getWeekStartTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        try {
            int weekday = c.get(Calendar.DAY_OF_WEEK) - 2;
            c.add(Calendar.DATE, -weekday);
            c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00.000"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

    /**
     * 获得本周的最后一天，周日
     *
     * @return
     */
    public static Date getWeekEndTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        try {
            int weekday = c.get(Calendar.DAY_OF_WEEK);
            c.add(Calendar.DATE, 8 - weekday);
            c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59.999"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

    /**
     * 获得本月的开始时间
     *
     * @return
     */
    public static Date getMonthStartTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Date dt = null;
        try {
            c.set(Calendar.DATE, 1);
            dt = shortSdf.parse(shortSdf.format(c.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * 本月的结束时间
     *
     * @return
     */
    public static Date getMonthEndTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Date dt = null;
        try {
            c.set(Calendar.DATE, 1);
            c.add(Calendar.MONTH, 1);
            c.add(Calendar.DATE, -1);
            dt = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59.999");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * 当前年的开始时间
     *
     * @return
     */
    public static Date getYearStartTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Date dt = null;
        try {
            c.set(Calendar.MONTH, 0);
            c.set(Calendar.DATE, 1);
            dt = shortSdf.parse(shortSdf.format(c.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * 当前年的结束时间
     *
     * @return
     */
    public static Date getYearEndTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Date dt = null;
        try {
            c.set(Calendar.MONTH, 11);
            c.set(Calendar.DATE, 31);
            dt = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59.999");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * 当前季度的开始时间
     *
     * @return
     */
    public static Date getQuarterStartTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date dt = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 6);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            dt = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00.000");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * 当前季度的结束时间
     *
     * @return
     */
    public static Date getQuarterEndTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date dt = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            dt = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59.999");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * 获取前/后半年的开始时间
     *
     * @return
     */
    public static Date getHalfYearStartTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date dt = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 0);
            } else if (currentMonth >= 7 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 6);
            }
            c.set(Calendar.DATE, 1);
            dt = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00.000");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;

    }

    /**
     * 获取前/后半年的结束时间
     *
     * @return
     */
    public static Date getHalfYearEndTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date dt = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            dt = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59.999");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * 获取月旬 三旬: 上旬1-10日 中旬11-20日 下旬21-31日
     *
     * @param date
     * @return
     */
    public static int getTenDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int i = c.get(Calendar.DAY_OF_MONTH);
        if (i < 11)
            return 1;
        else if (i < 21)
            return 2;
        else
            return 3;
    }

    public static String getTenDayString(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int i = c.get(Calendar.DAY_OF_MONTH);
        if (i < 11)
            return "01";
        else if (i < 21)
            return "02";
        else
            return "03";
    }

    /**
     * 获取所属旬开始时间
     *
     * @param date
     * @return
     */
    public static Date getTenDayStartTime(Date date) {
        int ten = getTenDay(date);
        try {
            if (ten == 1) {
                return getMonthStartTime(date);
            } else if (ten == 2) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-11");
                return shortSdf.parse(df.format(date));
            } else {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-21");
                return shortSdf.parse(df.format(date));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;


    }

    /**
     * 获取所属旬结束时间
     *
     * @param date
     * @return
     */
    public static Date getTenDayEndTime(Date date) {
        int ten = getTenDay(date);
        try {
            if (ten == 1) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-10 23:59:59.999");
                return longSdf.parse(df.format(date));
            } else if (ten == 2) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-20 23:59:59.999");
                return longSdf.parse(df.format(date));
            } else {
                return getMonthEndTime(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;


    }


    /**
     * 属于本年第几天
     *
     * @return
     */
    public static int getYearDayIndex(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 属于本年第几周
     *
     * @return
     */
    public static int getYearWeekIndex(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 属于本年第几月
     *
     * @return
     */
    public static int getYearMonthIndex(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 当前属于本年第几个季度
     *
     * @return
     */
    public static int getYeartQuarterIndex(Date date) {
        int month = getYearMonthIndex(date);
        if (month <= 3)
            return 1;
        else if (month <= 6)
            return 2;
        else if (month <= 9)
            return 3;
        else
            return 4;
    }

    /**
     * 获取date所属年的所有天列表及开始/结束时间 开始时间：date[0]，结束时间：date[1]
     *
     * @param date
     * @return
     */
    public static List<Date[]> yearDayList(Date date) {
        List<Date[]> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date starttm = getYearStartTime(date);
        Date endtm = getYearEndTime(date);
        calendar.setTime(starttm);

        while (calendar.getTime().before(endtm)) {
            Date st = getDayStartTime(calendar.getTime());
            Date et = getDayEndTime(calendar.getTime());
            result.add(new Date[]{st, et});
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return result;

    }

    /**
     * 获取date所属年的所有星期列表及开始/结束时间 开始时间：date[0]，结束时间：date[1]
     *
     * @param date
     * @return
     */
    public static List<Date[]> yearWeekList(Date date) {
        List<Date[]> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date starttm = getYearStartTime(date);
        Date endtm = getYearEndTime(date);
        calendar.setTime(starttm);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        while (calendar.getTime().before(endtm)) {
            Date st = getWeekStartTime(calendar.getTime());
            Date et = getWeekEndTime(calendar.getTime());
            result.add(new Date[]{st, et});
            calendar.add(Calendar.WEEK_OF_YEAR, 1);
        }
        return result;

    }

    /**
     * 获取date所属年的所有月列表及开始/结束时间 开始时间：date[0]，结束时间：date[1]
     *
     * @param date
     * @return
     */
    public static List<Date[]> yearMonthList(Date date) {
        List<Date[]> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date starttm = getYearStartTime(date);
        Date endtm = getYearEndTime(date);
        calendar.setTime(starttm);
        while (calendar.getTime().before(endtm)) {
            Date tm = calendar.getTime();
            Date st = getMonthStartTime(tm);
            Date et = getMonthEndTime(tm);
            result.add(new Date[]{st, et});
            calendar.add(Calendar.MONTH, 1);
        }
        return result;
    }

    /**
     * 获取date所属年的所有季度列表及开始/结束时间 开始时间：date[0]，结束时间：date[1]
     *
     * @param date
     * @return
     */
    public static List<Date[]> yearQuarterList(Date date) {
        List<Date[]> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date starttm = getYearStartTime(date);
        Date endtm = getYearEndTime(date);
        calendar.setTime(starttm);
        while (calendar.getTime().before(endtm)) {
            Date st = getQuarterStartTime(calendar.getTime());
            Date et = getQuarterEndTime(calendar.getTime());
            result.add(new Date[]{st, et});
            calendar.add(Calendar.MONTH, 3);
        }
        return result;
    }

    /**
     * 获取date所属月份的所有旬列表及开始/结束时间 开始时间：date[0]，结束时间：date[1]
     *
     * @param date
     * @return
     */
    public static List<Date[]> monthTenDayList(Date date) {
        List<Date[]> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date starttm = getMonthStartTime(date);
        Date endtm = getMonthEndTime(date);
        calendar.setTime(starttm);

        while (calendar.getTime().before(endtm)) {
            Date st = getTenDayStartTime(calendar.getTime());
            Date et = getTenDayEndTime(calendar.getTime());
            result.add(new Date[]{st, et});
            calendar.add(Calendar.DAY_OF_MONTH, 11);
        }
        return result;
    }

    /**
     * 获取date所属年的所有月旬列表及开始/结束时间 开始时间：date[0]，结束时间：date[1]
     *
     * @param date
     * @return
     */
    public static List<Date[]> yearTenDayList(Date date) {
        List<Date[]> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date starttm = getYearStartTime(date);
        Date endtm = getYearEndTime(date);
        calendar.setTime(starttm);

        while (calendar.getTime().before(endtm)) {//
            result.addAll(monthTenDayList(calendar.getTime()));
            calendar.add(Calendar.MONTH, 1);
        }
        return result;
    }

    /**
     * 测试
     */
    private static void test() {
        Date date = new Date();
        System.out.println("当前小时开始：" + toStr(null, getHourStartTime(date)));
        System.out.println("当前小时结束：" + toStr(null, getHourEndTime(date)));
        System.out.println("当前天开始：" + toStr(null, getDayStartTime(date)));
        System.out.println("当前天时结束：" + toStr(null, getDayEndTime(date)));
        System.out.println("当前天是星期：" + getWeekDay(date));
        System.out.println("当前周开始：" + toStr(null, getWeekStartTime(date)));
        System.out.println("当前周结束：" + toStr(null, getWeekEndTime(date)));
        System.out.println("当前月开始：" + toStr(null, getMonthStartTime(date)));
        System.out.println("当前月结束：" + toStr(null, getMonthEndTime(date)));
        System.out.println("当前季度开始：" + toStr(null, getQuarterStartTime(date)));
        System.out.println("当前季度结束：" + toStr(null, getQuarterEndTime(date)));
        System.out.println("当前半年/后半年开始：" + toStr(null, getHalfYearStartTime(date)));
        System.out.println("当前半年/后半年结束：" + toStr(null, getHalfYearEndTime(date)));
        System.out.println("当前年开始：" + toStr(null, getYearStartTime(date)));
        System.out.println("当前年结束：" + toStr(null, getYearEndTime(date)));
        System.out.println("当前属于本年第：" + getYearDayIndex(date) + "天");
        System.out.println("当前属于本年第：" + getYearWeekIndex(date) + "周");
        System.out.println("当前属于本年第：" + getYearMonthIndex(date) + "月");
        System.out.println("当前属于本年第：" + getYeartQuarterIndex(date) + "季度");
        System.out.println("时间转换(yyyy)： " + toStr(null, toDate("2018")));
        System.out.println("时间转换(yyyy-MM)： " + toStr(null, toDate("2018-01")));
        System.out.println("时间转换(yyyy-MM-dd)： " + toStr(null, toDate("2018-01-01")));
        System.out.println("时间转换(yyyy-MM-dd hh)： " + toStr(null, toDate("2018-01-01 23")));
        System.out.println("时间转换(yyyy-MM-dd hh:mm)： " + toStr(null, toDate("2018-01-01 23:59")));
        System.out.println("时间转换(yyyy-MM-dd hh:mm:ss)： " + toStr(null, toDate("2018-01-01 23:59:59")));
        System.out.println("时间转换(yyyy-MM-dd HH:mm:ss.SSS)： " + toStr(null, toDate("2018-01-01 23:59:59.999")));
    }

    /**
     * 测试：获取当年所有日期列表
     */
    private static void testYearDayList() {
        List<Date[]> datas = yearDayList(new Date());
        for (int i = 0; i < datas.size(); i++) {
            Date[] date = datas.get(i);
            System.out.println("（一年的日期列表）第" + (i + 1) + "天：" + sdf.format(date[0]) + " " + sdf.format(date[1]));
        }
    }

    /**
     * 测试：获取当年所有星期列表
     */
    private static void testYearWeekList() {
        List<Date[]> datas = yearWeekList(new Date());
        for (int i = 0; i < datas.size(); i++) {
            Date[] date = datas.get(i);
            System.out.println("（一年的周列表）第" + (i + 1) + "周：" + sdf.format(date[0]) + " " + sdf.format(date[1]));
        }
    }

    /**
     * 测试：获取当年所有季度列表
     */
    private static void testYearQuarterList() {
        List<Date[]> datas = yearQuarterList(new Date());
        for (int i = 0; i < datas.size(); i++) {
            Date[] date = datas.get(i);
            System.out.println("（一年的季度列表）第" + (i + 1) + "季度：" + sdf.format(date[0]) + " " + sdf.format(date[1]));
        }
    }


    /**
     * 测试：获取当年所有月份列表
     */
    private static void testYearMonthList() {
        List<Date[]> datas = yearMonthList(new Date());
        for (int i = 0; i < datas.size(); i++) {
            Date[] date = datas.get(i);
            System.out.println("（一年的月列表）第" + (i + 1) + "月：" + sdf.format(date[0]) + " " + sdf.format(date[1]));
        }
    }


    /**
     * 测试：获取当月所有旬列表
     */
    private static void testMonthTenDayList() {
        //Date no= DateTimeTools.toDateTime("2018-02-01 15:38:15");
        List<Date[]> datas = monthTenDayList(new Date());
        for (int i = 0; i < datas.size(); i++) {
            Date[] date = datas.get(i);
            System.out.println("(一月的旬列表)第" + (i % 3 + 1) + "旬：" + sdf.format(date[0]) + " " + sdf.format(date[1]));
        }
    }

    /**
     * 测试：获取当年所有旬列表
     */
    private static void testyearTenDayList() {
        List<Date[]> datas = yearTenDayList(new Date());
        for (int i = 0; i < datas.size(); i++) {
            Date[] date = datas.get(i);
            System.out.println("（一年的旬列表）第" + (i / 3 + 1) + "月" + (i % 3 + 1) + "旬：" + sdf.format(date[0]) + " " + sdf.format(date[1]));
        }
    }

    public static void main(String[] args) {
        test();
        testYearDayList();
        testYearWeekList();
        testYearMonthList();
        testYearQuarterList();
        testyearTenDayList();
        testMonthTenDayList();
    }


}
 