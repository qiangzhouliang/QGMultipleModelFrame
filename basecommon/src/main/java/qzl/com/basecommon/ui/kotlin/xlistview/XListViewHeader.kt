package qzl.com.basecommon.ui.kotlin.xlistview

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import qzl.com.basecommon.R


class XListViewHeader : LinearLayout {
    private var mContainer: LinearLayout? = null
    private var mArrowImageView: ImageView? = null
    private var mProgressBar: ProgressBar? = null
    private var mHintTextView: TextView? = null
    private var mState = STATE_NORMAL

    private var mRotateUpAnim: Animation? = null
    private var mRotateDownAnim: Animation? = null

    private val ROTATE_ANIM_DURATION = 180

    var visiableHeight: Int
        get() = mContainer?.layoutParams?.height?:0
        set(height) {
            var height = height
            if (height < 0)
                height = 0
            val lp = mContainer?.layoutParams as LinearLayout.LayoutParams
            lp.height = height
            mContainer?.layoutParams = lp
        }

    constructor(context: Context) : super(context) {
        initView(context)
    }

    /**
     * @param context
     * @param attrs
     */
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context)
    }

    private fun initView(context: Context) {
        // 初始情况，设置下拉刷新view高度为0
        val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0)
        mContainer = LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header, null) as LinearLayout
        addView(mContainer, lp)
        gravity = Gravity.BOTTOM

        mArrowImageView = findViewById<View>(R.id.xlistview_header_arrow) as ImageView
        mHintTextView = findViewById<View>(R.id.xlistview_header_hint_textview) as TextView
        mProgressBar = findViewById<View>(R.id.xlistview_header_progressbar) as ProgressBar

        mRotateUpAnim = RotateAnimation(
            0.0f, -180.0f,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
            0.5f
        )
        mRotateUpAnim?.duration = ROTATE_ANIM_DURATION.toLong()
        mRotateUpAnim?.fillAfter = true
        mRotateDownAnim = RotateAnimation(
            -180.0f, 0.0f,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
            0.5f
        )
        mRotateDownAnim?.duration = ROTATE_ANIM_DURATION.toLong()
        mRotateDownAnim?.fillAfter = true
    }

    fun setState(state: Int, text: String) {
        if (state == mState) return

        if (state != STATE_REFRESHING) {    //显示箭头
            mArrowImageView?.visibility = View.VISIBLE
            mProgressBar?.visibility = View.INVISIBLE
        }
        when (state) {
            //第一页显示的头
            STATE_FIRST -> {
                mArrowImageView?.visibility = View.INVISIBLE
                mProgressBar?.visibility = View.INVISIBLE
                mHintTextView?.text = text
            }
            STATE_NORMAL -> {
                if (mState == STATE_READY) {
                    mArrowImageView?.startAnimation(mRotateDownAnim)
                }
                if (mState == STATE_REFRESHING) {
                    mArrowImageView?.clearAnimation()
                }
                mHintTextView?.setText(R.string.xlistview_header_hint_normal)
            }
            STATE_READY -> if (mState != STATE_READY) {
                mArrowImageView?.clearAnimation()
                mArrowImageView?.startAnimation(mRotateUpAnim)
                mHintTextView?.text = text
            }
            STATE_REFRESHING -> {
                mArrowImageView?.clearAnimation()
                mArrowImageView?.visibility = View.INVISIBLE
                mProgressBar?.visibility = View.VISIBLE
                mHintTextView?.setText(R.string.xlistview_header_hint_loading)
            }
        }

        mState = state
    }

    fun getmHintTextView(): TextView? {
        return mHintTextView
    }

    companion object {

        val STATE_FIRST = -1//第一页
        val STATE_NORMAL = 0//正常
        val STATE_READY = 1//下拉状态
        val STATE_REFRESHING = 2//松开
    }

}
