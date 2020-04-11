package com.zdww.basecommon.ui.kotlin

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.view_footer.view.*
import qzl.com.basecommon.R
import qzl.com.model.common.PagePara


/**
 * @desc 最后一页，在没有下一页
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-21 13:17
 */
class FooterView : RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    init {
        View.inflate(context, R.layout.view_footer, this)
    }
    fun setData(page: PagePara?){
        foot_content.text = "当前第${page?.pageNo?.plus(1)}页,(共${page?.totalPages}页),上拉加载更多！"
    }
}