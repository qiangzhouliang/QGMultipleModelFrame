package qzl.com.main.view.kotlin

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout
import qzl.com.main.view.java.DragLayout

/**
 * @author 强周亮(Qzl)
 * @desc Activity内容外层布局，协助菜单动作反应
 * @email 2538096489@qq.com
 * @time 2019-05-28 14:53
 */
class MainRelativeLayout : LinearLayout {
    private var dl: DragLayout? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    fun setDragLayout(dl: DragLayout) {
        this.dl = dl
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return if (dl?.status != DragLayout.Status.Close) {
            true
        } else super.onInterceptTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (dl?.status != DragLayout.Status.Close) {
            if (event.action == MotionEvent.ACTION_UP) {
                dl?.close()
            }
            return true
        }
        return super.onTouchEvent(event)
    }

}
