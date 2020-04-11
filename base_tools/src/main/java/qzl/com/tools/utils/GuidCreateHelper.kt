package qzl.com.tools.utils

import java.util.*

/**
 * @author 强周亮(Qzl)
 * @desc 获取32位字符串
 * @email 2538096489@qq.com
 * @time 2019-05-24 15:39
 * @class GuidCreateHelper
 * @package qzl.com.tools.utils
 */
object GuidCreateHelper {

    val guid: String
        get() {
            val uuid = UUID.randomUUID()
            return uuid.toString().replace("-", "")
        }


    @JvmStatic
    fun main(args: Array<String>) {
        val uuid = UUID.randomUUID()
        val a = uuid.toString().replace("-", "")
    }
}
