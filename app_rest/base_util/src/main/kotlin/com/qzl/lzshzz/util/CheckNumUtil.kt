/**
 * @Title: CheckNumUtil.java
 * @Package com.gsww.util
 * @author 强周亮
 * @date 2019年1月21日 上午9:55:59
 * @version V1.0
 */
package com.qzl.lzshzz.util

import java.util.*

/**
 * @ClassName: CheckNumUtil
 * @Description: 生成短信验证码工具
 * @author 强周亮
 * @date 2019年1月21日 上午9:55:59
 */
object CheckNumUtil {
    /**
     * 生成4位随机码
     */
    val random: String
        get() {
            val random = Random()
            var str = ""
            for (i in 0..5) {
                str += random.nextInt(10).toString()
            }
            return str
        }
}
