package qzl.com.basecommon.ui

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * @desc 下拉刷新框架代码重写,下拉刷新时禁止点击事件
 * @author qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2020/5/13 9:52
 */
class MySwipeRefreshLayout: SwipeRefreshLayout {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context,attrs) {}

    //如果是正在刷新时，消费点击事件
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return if (isRefreshing){
            true
        } else {
            super.dispatchTouchEvent(ev)
        }
    }

}