package qzl.com.tools.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author 强周亮(Qzl)
 * @desc 事件帮助操作类
 * @email 2538096489@qq.com
 * @time 2019-05-24 15:46
 * @class TimeHelper
 * @package qzl.com.tools.utils
 */
object TimeHelper {
    private var CurrentTime: String? = null

    private var CurrentDate: String? = null

    /**
     * 得到当前的年份 返回格式:yyyy
     *
     * @return String
     */
    val currentYear: String
        get() {
            val nowDate = Date()

            val formatter = SimpleDateFormat("yyyy")
            return formatter.format(nowDate)
        }

    /**
     * 得到当前的月份 返回格式:yyyy-MM
     *
     * @return String
     */
    val currentYearAndMonth: String
        get() {
            val NowDate = Date()

            val formatter = SimpleDateFormat("yyyy-MM")
            return formatter.format(NowDate)
        }

    /**
     * 得到当前的月份 返回格式:MM
     *
     * @return String
     */
    val currentMonth: String
        get() {
            val nowDate = Date()

            val formatter = SimpleDateFormat("MM")
            return formatter.format(nowDate)
        }

    /**
     * 得到当前的日期 返回格式:dd
     *
     * @return String
     */
    val currentDay: String
        get() {
            val nowDate = Date()

            val formatter = SimpleDateFormat("dd")
            return formatter.format(nowDate)
        }
    /**
     * 得到当前的时间，精确到毫秒,共19位 返回格式:yyyy-MM-dd HH:mm:ss
     *
     * @return String
     */
    val currentTime: String?
        get() {
            val nowDate = Date()
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            CurrentTime = formatter.format(nowDate)
            return CurrentTime
        }

    val currentCompactTime: String?
        get() {
            val nowDate = Date()
            val formatter = SimpleDateFormat("yyyyMMddHHmmss")
            CurrentTime = formatter.format(nowDate)
            return CurrentTime
        }
    val yesterdayCompactTime: String?
        get() {
            val cal = Calendar.getInstance()
            cal.add(Calendar.DATE, -1)
            print(cal.time)
            val formatter = SimpleDateFormat("yyyyMMddHHmmss")
            CurrentTime = formatter.format(cal.time)
            return CurrentTime
        }
    val yesterdayCompactTimeForFileName: String?
        get() {
            val cal = Calendar.getInstance()
            cal.add(Calendar.DATE, -1)
            print(cal.time)
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            CurrentTime = formatter.format(cal.time)
            return CurrentTime
        }
    /**
     * 得到当前的日期,共10位 返回格式：yyyy-MM-dd
     *
     * @return String
     */
    val currentDate: String
        get() {
            val nowDate = Date()
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            CurrentDate = formatter.format(nowDate)
            return CurrentDate?:""
        }
    /**
     * 得到当前的日期,共10位 返回格式：yyyy-MM-dd
     *
     * @return String
     */
    val currentDate1: String?
        get() {
            val nowDate = Date()
            val formatter = SimpleDateFormat("yyyy年MM月dd日")
            CurrentDate = formatter.format(nowDate)
            return CurrentDate
        }
    /**
     * 得到当月的第一天,共10位 返回格式：yyyy-MM-dd
     *
     * @return String
     */
    val currentFirstDate: String?
        get() {
            val nowDate = Date()
            val formatter = SimpleDateFormat("yyyy-MM-01")
            CurrentDate = formatter.format(nowDate)
            return CurrentDate
        }
    /**
     * 得到当月的最后一天,共10位 返回格式：yyyy-MM-dd
     *
     * @return String
     * @throws ParseException
     */
    val currentLastDate: String
        get() {
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            var calendar: Calendar? = null
            try {
                val date = formatter.parse(currentFirstDate)
                calendar = Calendar.getInstance()
                calendar?.time = date
                calendar.add(Calendar.MONTH, 1)
                calendar.add(Calendar.DAY_OF_YEAR, -1)
                return formatDate(calendar.time)
            } catch (e: ParseException) {
                e.printStackTrace()
                return ""
            }

        }

    /*
	 * @Title: getCurrentMorningTime
	 * @Description: 获取当前日期0点
	 * @author lsn
	 * @date  2018/6/27 11:12
	 * @params
	 * @return
	 */
    val currentMorningTime: String
        get() {
            val nowDate = Date()
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            CurrentDate = formatter.format(nowDate)
            return "$CurrentDate 0:0:0"
        }
    /*
	 * @Title: getCurrentNightTime
	 * @Description: 获取当前日期24点
	 * @author lsn
	 * @date  2018/6/27 11:14
	 * @params
	 * @return
	 */
    val currentNightTime: String
        get() {
            val nowDate = Date()
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            CurrentDate = formatter.format(nowDate)
            return "$currentDate 23:59:59"
        }
    /*
	 * @Title: getCurrentDateForMonth
	 * @Description: 获取当前日期
	 * @author lsn
	 * @date  2018/7/2 15:37
	 * @params
	 * @return yyyyMM
	 */
    val currentDateForMonth: String?
        get() {
            val nowDate = Date()
            val formatter = SimpleDateFormat("yyyyMM")
            CurrentDate = formatter.format(nowDate)
            return CurrentDate
        }

    /**
     * @desc 获取当期年月 2018-08
     * @author 强周亮
     * @time 2018-10-16 15:41
     */
    val currentDateAndMonth: String?
        get() {
            val nowDate = Date()
            val formatter = SimpleDateFormat("yyyy-MM")
            CurrentDate = formatter.format(nowDate)
            return CurrentDate
        }


    /**
     * 常用的格式化日期
     *
     * @param date Date
     * @return String
     */
    fun formatDate(date: Date): String {
        return formatDateByFormat(date, "yyyy-MM-dd")
    }

    /**
     * 以指定的格式来格式化日期
     *
     * @param date Date
     * @param format String
     * @return String
     */
    fun formatDateByFormat(date: Date?, format: String): String {
        var result = ""
        if (date != null) {
            try {
                val sdf = SimpleDateFormat(format)
                result = sdf.format(date)
            } catch (ex: Exception) {

                ex.printStackTrace()
            }

        }
        return result
    }

    /**
     * 得到当前日期加上某一个整数的日期，整数代表天数 输入参数：currentdate : String 格式 yyyy-MM-dd add_day :
     * int 返回格式：yyyy-MM-dd
     */
    fun addDay(currentdate: String, addDay: Int): String? {
        var gc: GregorianCalendar? = null
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val year: Int
        val month: Int
        val day: Int

        try {
            year = Integer.parseInt(currentdate.substring(0, 4))
            month = Integer.parseInt(currentdate.substring(5, 7)) - 1
            day = Integer.parseInt(currentdate.substring(8, 10))

            gc = GregorianCalendar(year, month, day)
            gc.add(GregorianCalendar.DATE, addDay)

            return formatter.format(gc.time)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

    /**
     * 得到当前日期加上某一个整数的日期，整数代表月数 输入参数：currentdate : String 格式 yyyy-MM-dd add_month :
     * int 返回格式：yyyy-MM-dd
     */
    fun addMonth(currentdate: String, addMonth: Int): String? {
        var gc: GregorianCalendar? = null
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val year: Int
        val month: Int
        val day: Int

        try {
            year = Integer.parseInt(currentdate.substring(0, 4))
            month = Integer.parseInt(currentdate.substring(5, 7)) - 1
            day = Integer.parseInt(currentdate.substring(8, 10))

            gc = GregorianCalendar(year, month, day)
            gc.add(GregorianCalendar.MONTH, addMonth)

            return formatter.format(gc.time)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

    /**
     * 得到当前日期加上某一个整数的日期，整数代表年 输入参数：currentdate : String 格式 yyyy-MM-dd HH:mm addYear :
     * int 返回格式：yyyy-MM-dd HH:mm
     */
    fun addYead(currentdate: String, addYear: Int): String? {
        var gc: GregorianCalendar? = null
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val year: Int
        val month: Int
        val day: Int

        try {
            year = Integer.parseInt(currentdate.substring(0, 4))
            month = Integer.parseInt(currentdate.substring(5, 7)) - 1
            day = Integer.parseInt(currentdate.substring(8, 10))

            gc = GregorianCalendar(year, month, day)
            gc.add(GregorianCalendar.YEAR, addYear)

            return formatter.format(gc.time)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

    /**
     * 得到endTime比beforeTime晚几个月，整数代表月数 输入参数：endTime、beforeTime : String 格式前7位的格式为 yyyy-MM
     */
    fun monthDiff(beforeTime: String?, endTime: String?): Int {
        if (beforeTime == null || endTime == null) {
            return 0
        }
        val beforeYear: Int
        val endYear: Int
        val beforeMonth: Int
        val endMonth: Int
        try {
            beforeYear = Integer.parseInt(beforeTime.substring(0, 4))
            endYear = Integer.parseInt(endTime.substring(0, 4))
            beforeMonth = Integer.parseInt(beforeTime.substring(5, 7)) - 1
            endMonth = Integer.parseInt(endTime.substring(5, 7)) - 1
            return (endYear - beforeYear) * 12 + (endMonth - beforeMonth)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return 0
    }

    /**
     * 得到当前日期加上某一个整数的分钟 输入参数：currentdatetime : String 格式 yyyy-MM-dd HH:mm:ss
     * add_minute : int 返回格式：yyyy-MM-dd HH:mm:ss
     */
    fun addMinute(currentdatetime: String, addMinute: Int): String? {
        var gc: GregorianCalendar? = null
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val year: Int
        val month: Int
        val day: Int
        val hour: Int
        val minute: Int
        val second: Int

        try {
            year = Integer.parseInt(currentdatetime.substring(0, 4))
            month = Integer.parseInt(currentdatetime.substring(5, 7)) - 1
            day = Integer.parseInt(currentdatetime.substring(8, 10))

            hour = Integer.parseInt(currentdatetime.substring(11, 13))
            minute = Integer.parseInt(currentdatetime.substring(14, 16))
            second = Integer.parseInt(currentdatetime.substring(17, 19))

            gc = GregorianCalendar(year, month, day, hour, minute, second)
            gc.add(GregorianCalendar.MINUTE, addMinute)

            return formatter.format(gc.time)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

    /**
     * 得到当前日期加上某一个整数的秒 输入参数：currentdatetime : String 格式 yyyy-MM-dd HH:mm:ss
     * add_second : int 返回格式：yyyy-MM-dd HH:mm:ss
     */
    fun addSecond(currentdatetime: String?, addSecond: Int): String? {
        var gc: GregorianCalendar? = null
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val year: Int
        val month: Int
        val day: Int
        val hour: Int
        val minute: Int
        val second: Int

        try {
            year = Integer.parseInt(currentdatetime!!.substring(0, 4))
            month = Integer.parseInt(currentdatetime.substring(5, 7)) - 1
            day = Integer.parseInt(currentdatetime.substring(8, 10))

            hour = Integer.parseInt(currentdatetime.substring(11, 13))
            minute = Integer.parseInt(currentdatetime.substring(14, 16))
            second = Integer.parseInt(currentdatetime.substring(17, 19))

            gc = GregorianCalendar(year, month, day, hour, minute, second)
            gc.add(GregorianCalendar.SECOND, addSecond)

            return formatter.format(gc.time)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

    fun addMinute1(currentdatetime: String, addMinute: Int): String? {
        var gc: GregorianCalendar? = null
        val formatter = SimpleDateFormat("yyyyMMddHHmmss")
        val year: Int
        val month: Int
        val day: Int
        val hour: Int
        val minute: Int
        val second: Int

        try {
            year = Integer.parseInt(currentdatetime.substring(0, 4))
            month = Integer.parseInt(currentdatetime.substring(5, 7)) - 1
            day = Integer.parseInt(currentdatetime.substring(8, 10))

            hour = Integer.parseInt(currentdatetime.substring(8, 10))
            minute = Integer.parseInt(currentdatetime.substring(8, 10))
            second = Integer.parseInt(currentdatetime.substring(8, 10))

            gc = GregorianCalendar(year, month, day, hour, minute, second)
            gc.add(GregorianCalendar.MINUTE, addMinute)

            return formatter.format(gc.time)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

    fun parseDate(sDate: String): Date? {
        val bartDateFormat = SimpleDateFormat("yyyy-MM-dd")

        try {
            return bartDateFormat.parse(sDate)
        } catch (ex: Exception) {
            println(ex.message)
        }

        return null
    }

    /**
     * 解析日期及时间
     *
     * @param sDateTime
     * 日期及时间字符串
     * @return 日期
     */
    fun parseDateTime(sDateTime: String): Date? {
        val bartDateFormat = SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss"
        )

        try {
            return bartDateFormat.parse(sDateTime)
        } catch (ex: Exception) {
            println(ex.message)
        }

        return null
    }

    /**
     * 取得一个月的天数 date:yyyy-MM-dd
     *
     * @throws ParseException
     */
    fun getTotalDaysOfMonth(strDate: String): Int {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val calendar = GregorianCalendar()

        var date = Date()
        try {
            date = sdf.parse(strDate)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        calendar.time = date
        // 天数
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    }


    fun getDateSubDay(startDate: String, endDate: String): Long {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        var theday: Long = 0
        try {
            calendar.time = sdf.parse(startDate)
            val timethis = calendar.timeInMillis
            calendar.time = sdf.parse(endDate)
            val timeend = calendar.timeInMillis
            theday = (timethis - timeend) / (1000 * 60 * 60 * 24)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return theday
    }

    @JvmStatic
    fun main(args: Array<String>) {
        //long d = 0;
        println(TimeHelper.addSecond(TimeHelper.currentTime, -2))
    }

    /**
     * 得到二个日期间的间隔天数
     */
    fun getTwoDay(sj1: String, sj2: String): String {
        val myFormatter = SimpleDateFormat("yyyy-MM-dd")
        var day: Long = 0
        try {
            val date = myFormatter.parse(sj1)
            val mydate = myFormatter.parse(sj2)
            day = (date.time - mydate.time) / (24 * 60 * 60 * 1000)
        } catch (e: Exception) {
            return ""
        }

        return day.toString() + ""
    }
}
