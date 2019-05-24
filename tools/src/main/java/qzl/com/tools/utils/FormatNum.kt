package qzl.com.tools.utils

import java.math.BigDecimal
import java.text.DecimalFormat

/**
 * @desc 保留两位小数
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2018-06-07 14:51
 * @class hzz
 * @package gsww.com.riverckeckmodule.util
 */
object FormatNum {
    /**
     * @desc 保留两位有效数字
     * @author Qzl
     * @time 2018-06-07 14:54
     * @param num
     * @return
     */
    fun format2(num: Double): String {
        val df = DecimalFormat("#.00")
        return df.format(num)
    }

    /**
     * @desc 单位转化
     * @author Qzl
     * @time 2018-06-07 15:22
     * @param num
     * @return
     */
    fun m2Km(num: Any): String {
        return if (!StringHelper.isEmptyString(StringHelper.toString(num))) {
            try {
                getMtoKM(java.lang.Double.valueOf(StringHelper.toString(num)))
            } catch (e: Exception) {
                StringHelper.toString(num)
            }

        } else {
            ""
        }
    }

    private fun getMtoKM(num: Double): String {
        if (num >= 1000) {
            val bd = BigDecimal(Math.round(num / 1000 * 100) / 100.0)
            return if (bd.toPlainString().contains(".") && bd.toPlainString().length - bd.toPlainString().indexOf(".") > 2) {
                bd.toPlainString().substring(0, bd.toPlainString().indexOf(".") + 3) + " (km)"
            } else {
                bd.toPlainString() + " (km)"
            }
        }
        return Math.round(num).toString() + " (m)"
    }

    /**
     * @desc 将毫秒数转化为时间数
     * @author Qzl
     * @time 2018-06-07 16:35
     * @param time
     * @return
     */
    fun formatTimeStr(time: Any): String {
        return if (!StringHelper.isEmptyString(StringHelper.toString(time))) {
            try {
                if (StringHelper.toString(time).contains(".")) {
                    timeToYMD(
                        Integer.parseInt(
                            StringHelper.toString(time).substring(
                                0,
                                StringHelper.toString(time).indexOf(".")
                            )
                        )
                    )
                } else {
                    timeToYMD(Integer.parseInt(StringHelper.toString(time)))
                }
            } catch (e: Exception) {
                StringHelper.toString(time)
            }

        } else {
            ""
        }
    }

    private fun timeToYMD(time: Int): String {
        val d = time / (3600 * 24)
        //多少天
        val h = time % (3600 * 24) / 3600
        //多少小时
        val m = time % (3600 * 24) % 3600 / 60
        //多少分钟
        val s = time % (3600 * 24) % 3600 % 60
        //多少秒
        return when {
            d > 0 -> "$d 天 $h 时$m 分"
            h > 0 -> //return h + " 时 "+m +" 分 "+s+" 秒";
                "$h 时 $m 分"
            m > 0 -> "$m 分 $s 秒"
            else -> "$s 秒"
        }
    }

    /**
     * @desc 速度计算
     * @author Qzl
     * @time 2018-06-08 15:26
     * @param num 路程 （m）
     * @param time 时间 （mm)
     * @return 返回值 string
     */
    fun formatSpeed(num: Double, time: Double): Double {
        //return (Math.round(((num/1000)/(time/(1000 * 60 * 60)))*100)/100.0);
        val distense = num / 1000 * 100 / 100.0
        val times = time / 3600 * 100 / 100.0
        return if (times <= 0.0) {
            0.0
        } else {
            Math.round(distense / times * 100 / 100.0).toDouble()
        }
    }
    /**
     * @desc 将double类型的string转成int
     * @author 强周亮(Qzl)
     * @time 2018-09-17 16:45
     * @param douStr 需要转换的字符串
     * @return 转换后的返回值
     */
    fun doubleToInt(douStr: Any): Int {
        return if (!StringHelper.isEmptyString(StringHelper.toString(douStr))) {
            try {
                if (StringHelper.toString(douStr).contains(".")) {
                    val str = StringHelper.toString(douStr).substring(0, StringHelper.toString(douStr).indexOf("."))
                    Integer.parseInt(str)
                } else {
                    Integer.parseInt(StringHelper.toString(douStr))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                0
            }

        } else {
            0
        }
    }

    /**
     * @desc 将double类型的string转成int
     * @author 强周亮(Qzl)
     * @time 2018-09-17 16:45
     * @param douStr 需要转换的字符串
     * @return 转换后的返回值
     */
    fun doubleToIntStr(douStr: Any): String {
        return if (!StringHelper.isEmptyString(StringHelper.toString(douStr))) {
            try {
                val df = DecimalFormat("###,###,###,###")
                if (StringHelper.toString(douStr).contains(".")) {
                    df.format(
                        Integer.parseInt(
                            StringHelper.toString(douStr).substring(
                                0,
                                StringHelper.toString(douStr).indexOf(".")
                            )
                        ).toLong()
                    )
                } else {
                    df.format(Integer.parseInt(StringHelper.toString(douStr)).toLong())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                StringHelper.toString(douStr)
            }

        } else {
            "0"
        }
    }

    /**
     * @desc 将double转换为int形的字符串-没有格式
     * @author 强周亮
     * @time 2019-01-03 09:50
     */
    fun doubleToIntStrNoFormat(douStr: Any): String {
        return if (!StringHelper.isEmptyString(StringHelper.toString(douStr))) {
            try {
                StringHelper.toString(douStr).substring(0, StringHelper.toString(douStr).indexOf("."))
            } catch (e: Exception) {
                StringHelper.toString(douStr)
            }

        } else {
            "0"
        }
    }

    /**
     * @desc 将double类型的string转成double
     * @author 强周亮
     * @time 2018-10-25 15:08
     */
    fun doubleStrToDouble(douStr: Any): Double {

        return if (!StringHelper.isEmptyString(StringHelper.toString(douStr))) {
            try {
                java.lang.Double.valueOf(StringHelper.toString(douStr))
            } catch (e: Exception) {
                0.0
            }

        } else {
            0.0
        }
    }

    /**
     * @desc 计算百分比
     * @author 强周亮
     * @time 2019-01-04 14:12
     */
    fun percentage(a: Any, b: Any): String {
        val complate = doubleStrToDouble(a)
        val all = doubleStrToDouble(b)
        val df = DecimalFormat("0.00")
        return if (all <= 0 || complate <= 0) {
            "0.00"
        } else {
            df.format(complate / all * 100)
        }
    }
}
