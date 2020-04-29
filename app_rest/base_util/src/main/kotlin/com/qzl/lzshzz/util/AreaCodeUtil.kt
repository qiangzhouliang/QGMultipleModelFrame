package com.qzl.lzshzz.util

import java.util.*

/**
 * @author 强周亮
 * @desc 行政区划工具类
 * @email 2538096489@qq.com
 * @time 2019/12/3 15:18
 */
object AreaCodeUtil {
    //截取areaCode
    @JvmStatic
    fun getAreaCode(areaCode: String?): String? {
        var areaCode = areaCode
        areaCode = if (areaCode?.replace("0*$".toRegex(), "")?.length == 3
                || areaCode?.replace("0*$".toRegex(), "")?.length == 5
                || areaCode?.replace("0*$".toRegex(), "")?.length == 8
                || areaCode?.replace("0*$".toRegex(), "")?.length == 11)
            areaCode.replace("0*$".toRegex(), "") + "0" else areaCode?.replace("0*$".toRegex(), "")
        if (areaCode?.length == 7 || areaCode?.length == 10) {
            areaCode += "00"
        }
        return areaCode
    }

    /**
     * @Title: parentAreaCode
     * @Description: 截取父级code
     * @author 强周亮
     * @param @param areaCode
     * @param @return    参数
     * @return String    返回类型
     * @date 2018年12月22日 下午3:27:49
     * @throws
     */
    @JvmStatic
    fun parentAreaCode(areaCode: String): String {
        val areaLength: Int = getAreaCode(areaCode)?.length?:0
        val cutCode: String
        cutCode = when (areaLength) {
            2 ->  // 例：620 甘肃省
                areaCode
            4 ->  // 例：6201 兰州市
                areaCode.substring(0, 2) + "0000000000"
            6 ->  // 例：620122 皋兰县 620105 安宁区
                areaCode.substring(0, 4) + "00000000"
            9, 12 -> areaCode.substring(0, 6) + "000000"
            else -> "620000000000"
        }
        return cutCode
    }

    /**
     * @Title: getParentAreaCode
     * @Description: 获取他所有的父级code和他自己的code
     * @author 强周亮
     * @param @param areaCode
     * @param @return    参数
     * @return List<String>    返回类型
     * @date 2018年12月13日 下午3:53:25
    </String> */
    fun getParentAreaCodeAll(areaCode: String): List<String> {
        val list: MutableList<String> = ArrayList()
        //获取将code截取之后的长度
        val codeLength: Int = getAreaCode(areaCode)?.length?:0
        when (codeLength) {
            4 -> {
                list.add(areaCode.substring(0, 2) + "0000000000")
            }
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
     * @Author 强周亮
     * @Description 获取需要截取的区划的长度
     * @Date 14:46 2019-05-15
     */
    @JvmStatic
    fun getIntAreaCodeCut(area: String): Int {
        var end = 12
        when (area.length) {
            2 -> {
                end = 4
            }
            4 -> {
                end = 6
            }
            6 -> {
                end = 9
            }
            9 -> {
                end = 12
            }
        }
        return end
    }
}