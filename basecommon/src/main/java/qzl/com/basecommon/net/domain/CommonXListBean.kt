package qzl.com.basecommon.net.domain

/**
 * @desc 请求结果返回值公共解析bean
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-05-27 14:00
 * @class QGMultipleModelFrame
 * @package qzl.com.basecommon.net2.domain
 */
data class CommonXListBean(
    val `data`: Data
)

data class Data(
    val result: Any,
    val sql: String,
    val totalPage: Int,
    val totalRow: Int
)