package qzl.com.main.mvp.model

/**
 * @desc
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-05-26 13:52
 * @class QGMultipleModelFrame
 * @package qzl.com.main.mvp.model
 */
data class D(
    val `data`: Data,
    val msg: String,
    val state: String,
    val success: Boolean
)

data class Data(
    val result: List<Result>,
    val waUser: WaUser
)

data class WaUser(
    val APP_COUNT: Int,
    val WX_COUNT: Int
)

data class Result(
    val COUNT_NAME: String,
    val COUNT_TOTAL: Int
)