package qzl.com.model.common

/**
 * @author 强周亮(qiangzhouliang)
 * @desc 分页请求返回参数
 * @email 2538096489@qq.com
 * @time 2019/11/22 11:14
 */
data class PagePara(
    val pageNo: Int,
    val pageSize: Int,
    var total: Int,
    val totalPages: Int
)