package com.qzl.lzshzz.util

import com.qzl.lzshzz.util.AreaCodeUtil.getAreaCode
import com.qzl.lzshzz.util.AreaCodeUtil.parentAreaCode

object RiverReachUtil {
    /**
     * @Title: getReachCodeCutOut
     * @Description: 获取截取后的河段编码
     * @author 强周亮
     * @param @param reachCode
     * @date 2018年12月13日 下午5:19:51
     */
    @JvmStatic
    fun getReachCodeCutOut(reachCode: String): String? {
        return if (reachCode.length >= 12) {
            reachCode.substring(0, 12) + getAreaCode(reachCode.substring(12))
        } else {
            reachCode
        }
    }

    /**
     * @Author 强周亮
     * @Description 获取附件河段编码
     * @Date 15:22 2020/3/3
     **/
    @JvmStatic
    fun getReachParent(reachCode: String): String {
        return if (reachCode.length >= 12) {
            reachCode.substring(0, 12) + parentAreaCode(reachCode.substring(12))
        } else {
            reachCode
        }
    }
}