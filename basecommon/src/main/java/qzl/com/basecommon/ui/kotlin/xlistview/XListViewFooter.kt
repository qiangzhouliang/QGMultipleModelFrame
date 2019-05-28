package qzl.com.basecommon.ui.kotlin.xlistview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import qzl.com.basecommon.R


class XListViewFooter : LinearLayout {

    private var mContext: Context? = null

    private var mContentView: View? = null
    private var mProgressBar: View? = null
    private var mHintView: TextView? = null
    private val ROTATE_ANIM_DURATION = 180
    private var mRotateUpAnim: Animation? = null
    private var mRotateDownAnim: Animation? = null
    private var mArrowImageView: ImageView? = null
    private var mState = STATE_NORMAL

    var bottomMargin: Int
        get() {
            val lp = mContentView?.layoutParams as LinearLayout.LayoutParams
            return lp.bottomMargin
        }
        set(height) {
            if (height < 0) return
            val lp = mContentView?.layoutParams as LinearLayout.LayoutParams
            lp.bottomMargin = height
            mContentView?.layoutParams = lp
        }

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context)
    }


    fun setState(state: Int, text: String) {

        mProgressBar?.visibility = View.INVISIBLE
        //如果是最后一页
        if (state == STATE_LAST) {
            mArrowImageView?.visibility = View.INVISIBLE
            mProgressBar?.visibility = View.INVISIBLE
            mHintView?.text = text
        } else if (state == STATE_READY) {
            if (mState != STATE_READY) {
                mArrowImageView?.clearAnimation()
                mArrowImageView?.startAnimation(mRotateUpAnim)
            }
            mHintView?.text = text
        } else if (state == STATE_LOADING) {
            mArrowImageView?.clearAnimation()
            mArrowImageView?.visibility = View.INVISIBLE
            mProgressBar?.visibility = View.VISIBLE
            mHintView?.text = "正在加载中"
        } else {
            if (mState == STATE_READY) {
                mArrowImageView?.startAnimation(mRotateDownAnim)
            } else {
                mArrowImageView?.clearAnimation()
            }
            mArrowImageView?.visibility = View.VISIBLE
            mHintView?.text = text
        }
        mState = state
    }


    /**
     * normal status
     */
    fun normal() {
        mHintView?.visibility = View.VISIBLE
        mProgressBar?.visibility = View.GONE
    }


    /**
     * loading status
     */
    fun loading() {
        mHintView?.visibility = View.GONE
        mProgressBar?.visibility = View.VISIBLE
    }

    /**
     * hide footer when disable pull load more
     */
    fun hide() {
        val lp = mContentView?.layoutParams as LinearLayout.LayoutParams
        lp.height = 0
        mContentView?.layoutParams = lp
    }

    /**
     * show footer
     */
    fun show() {
        val lp = mContentView?.layoutParams as LinearLayout.LayoutParams
        lp.height = LinearLayout.LayoutParams.WRAP_CONTENT
        mContentView?.layoutParams = lp
    }

    private fun initView(context: Context) {
        mContext = context
        val moreView = LayoutInflater.from(mContext).inflate(R.layout.pull_to_refresh_footer, null) as LinearLayout
        addView(moreView)
        moreView.layoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        mArrowImageView = findViewById<View>(R.id.xlistview_foot_arrow) as ImageView
        mContentView = moreView.findViewById(R.id.xlistview_footer_content)
        mProgressBar = moreView.findViewById(R.id.xlistview_footer_progressbar)
        mHintView = moreView.findViewById<View>(R.id.xlistview_footer_hint_textview) as TextView

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

    fun getmHintView(): TextView? {
        return mHintView
    }

    companion object {
        val STATE_LAST = -1
        val STATE_NORMAL = 0
        val STATE_READY = 1
        val STATE_LOADING = 2
    }
}
