package qzl.com.basecommon.ui.kotlin

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.head_control_panel.view.*
import qzl.com.basecommon.R

/**
 * @author 强周亮(Qzl)
 * @desc 头部导航
 * @email 2538096489@qq.com
 * @time 2019-05-28 19:22
 * @class HeadControlPanel
 * @package qzl.com.basecommon.ui
 */
class HeadControlPanel(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {
    var leftText: TextView? = null
        private set
    var leftImg: ImageView? = null
        private set
    var headLayoutBack: LinearLayout? = null
        private set

    override fun onFinishInflate() {
        super.onFinishInflate()
        leftText = findViewById<View>(R.id.left_btn_text) as TextView
        leftImg = findViewById<View>(R.id.left_btn_img) as ImageView
        headLayoutBack = findViewById<View>(R.id.head_layout_back) as LinearLayout
        if (!isInEditMode && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            head_view.visibility = View.VISIBLE
        }
    }

    fun initHeadPanel() {
        setMiddleTitle("首页")
    }

    fun setMiddleTitle(s: String) {
        var s = s
        midle_title.textSize = title_size
        if (s.length == 2) {
            s = s[0] + " " + s[1]
        }
        midle_title.text = s
    }

    fun getmLeftImage(): ImageView? {
        return menu_btn_img
    }

    fun getmRightText(): TextView? {
        return right_btn_text
    }

    fun getmRightImg(): ImageView? {
        return right_btn_img
    }

    companion object {
        private val middle_title_size = 20f
        private val title_size = 18f
        private val default_background_color = Color.rgb(99, 220, 215)
    }
}
