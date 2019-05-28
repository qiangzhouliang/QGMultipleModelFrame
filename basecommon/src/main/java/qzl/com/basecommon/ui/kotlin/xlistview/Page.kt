package qzl.com.basecommon.ui.kotlin.xlistview

import qzl.com.tools.operate.java.ReadProperties

/**
 * 与具体ORM实现无关的分页参数及查询结果封装. 注意所有序号从1开始.
 * Page中记录的类型.
 * @author calvin
 */
class Page {
    // -- 分页参数 --//
    var pageNo = 1
    var pageSize = ReadProperties.getPropertyByInt("list.pagesize")
    var totalCount: Long = 0

    /**
     * 根据pageSize与totalCount计算总页数, 默认值为-1.
     */
    val totalPages: Long
        get() {
            if (totalCount < 0) {
                return -1
            }

            var count = totalCount / pageSize
            if (totalCount % pageSize > 0) {
                count++
            }
            return count
        }
    /**
     * 是否还有下一页.
     */
    val isHasNext: Boolean
        get() = pageNo + 1 <= totalPages

    /**
     * 是否还有上一页.
     */
    val isHasPre: Boolean
        get() = pageNo - 1 >= 1

    // -- 构造函数 --//
    constructor() {}
    constructor(pageSize: Int) {
        this.pageSize = pageSize
    }
    /**
     * 行号加一
     */
    fun setNextPageNo() {
        pageNo++
    }
    /**
     * 行号减一
     */
    fun setPrePageNo() {
        pageNo--
    }
}
