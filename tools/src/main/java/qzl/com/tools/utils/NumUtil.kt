package qzl.com.tools.utils

import java.text.DecimalFormat

/**
 * @author 强周亮
 * @desc 数字转换
 * @email 2538096489@qq.com
 * @time 2018-09-17 16:37
 * @class hzz
 * @package com.gsww.hzz.tools.utils
 */
object NumUtil {
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
        val complate = NumUtil.doubleStrToDouble(a)
        val all = NumUtil.doubleStrToDouble(b)
        val df = DecimalFormat("0.00")
        return if (all <= 0 || complate <= 0) {
            "0.00"
        } else {
            df.format(complate / all * 100)
        }
    }
}
