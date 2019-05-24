package qzl.com.tools.utils

import java.util.*

/**
 * @author Qzl
 * @desc 行政区划相关的操作类
 * @email 2538096489@qq.com
 * @time 2019-01-17 16:14
 * @class hzz
 * @package com.gsww.hzz.tools.utils
 */
object AreaCodeUtil {
    /**
     * @desc 获取所有父级编码
     * @author 强周亮
     * @time 2019-01-17 16:15
     */
    fun getAllParentAreaCode(areaCode: String): List<String> {
        val list = ArrayList<String>()
        //获取将code截取之后的长度
        when (getAreaCode(areaCode).length) {
            4 -> list.add(areaCode.substring(0, 2) + "0000000000")
            6 -> {
                list.add(areaCode.substring(0, 2) + "0000000000")
                list.add(areaCode.substring(0, 4) + "00000000")
            }
            9 -> {
                list.add(areaCode.substring(0, 2) + "0000000000")
                list.add(areaCode.substring(0, 4) + "00000000")
                list.add(areaCode.substring(0, 6) + "000000")
            }
            12 -> {
                list.add(areaCode.substring(0, 2) + "0000000000")
                list.add(areaCode.substring(0, 4) + "00000000")
                list.add(areaCode.substring(0, 6) + "000000")
                list.add(areaCode.substring(0, 8) + "0000")
            }
        }
        list.add(areaCode)
        return list
    }

    /**
     * @desc 行政区划截取后面的0
     * @author 强周亮
     * @time 2019-05-24 15:33
     */
    fun getAreaCode(areaCode: String): String {
        var areaCode = areaCode
        areaCode =
            if (areaCode.replace("0*$".toRegex(), "").length == 3 || areaCode.replace("0*$".toRegex(), "").length == 5
                || areaCode.replace("0*$".toRegex(), "").length == 8 || areaCode.replace(
                    "0*$".toRegex(),
                    ""
                ).length == 11
            )
                areaCode.replace("0*$".toRegex(), "") + "0"
            else
                areaCode.replace("0*$".toRegex(), "")
        if (areaCode.length == 7 || areaCode.length == 10) {
            areaCode += "00"
        }

        return areaCode
    }
}
