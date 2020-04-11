package qzl.com.model.test

/**
 * @desc
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-06-04 18:14
 * @class QGMultipleModelFrame
 * @package qzl.com.main.mvp.model
 */
data class HomeBean(
    val error_code: Int,
    val reason: String,
    val result: Result
)

data class Result(
    val `data`: List<Data>,
    val stat: String
)

data class Data(
    val author_name: String,
    val category: String,
    val date: String,
    val thumbnail_pic_s: String,
    val thumbnail_pic_s02: String,
    val thumbnail_pic_s03: String,
    val title: String,
    val uniquekey: String,
    val url: String
)