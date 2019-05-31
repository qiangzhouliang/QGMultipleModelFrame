package qzl.com.basecommon.base

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.PopupWindow
import android.widget.PopupWindow.OnDismissListener
import qzl.com.basecommon.common.Constant
import qzl.com.tools.utils.ScreenUtil
import utilclass.LogUtils

/**
 * @author Qzl
 * @desc popuwindow 基类
 * @email 2538096489@qq.com
 * @time 2018-12-11 16:36
 * @class hzz
 * @package com.gsww.hzz.uikit.base
 */
open class BasePopuWindow : PopupWindow {
    private lateinit var mActivity: Activity

    internal var mOnDismissListener: PopupWindow.OnDismissListener = OnDismissListener {
        ScreenUtil.backgroundAlpha(mActivity, 1f)
        PopuWindowCloseAtferUtil.doPopuWindowCloseAfter()
    }

    constructor(context: Context) : super(context) {
        this.mActivity = context as Activity
        this.setOnDismissListener(mOnDismissListener)
    }

    constructor(context: Context, attrs: AttributeSet, activity: Activity) : super(context, attrs) {
        mActivity = activity
        this.setOnDismissListener(mOnDismissListener)
    }

    constructor(activity: Activity) {
        mActivity = activity
        this.setOnDismissListener(mOnDismissListener)
    }

    constructor(contentView: View, width: Int, height: Int, activity: Activity) : super(contentView, width, height) {
        mActivity = activity
        this.setOnDismissListener(mOnDismissListener)
    }

    override fun showAtLocation(parent: View, gravity: Int, x: Int, y: Int) {
        ScreenUtil.backgroundAlpha(mActivity, Constant.SCREEM_BACKGROUND_ALPHA)
        super.showAtLocation(parent, gravity, x, y)
    }

    override fun showAsDropDown(anchor: View) {
        ScreenUtil.backgroundAlpha(mActivity, Constant.SCREEM_BACKGROUND_ALPHA)
        super.showAsDropDown(anchor)
    }

    override fun showAsDropDown(anchor: View, xoff: Int, yoff: Int) {
        ScreenUtil.backgroundAlpha(mActivity, Constant.SCREEM_BACKGROUND_ALPHA)
        super.showAsDropDown(anchor, xoff, yoff)
    }

    override fun showAsDropDown(anchor: View, xoff: Int, yoff: Int, gravity: Int) {
        ScreenUtil.backgroundAlpha(mActivity, Constant.SCREEM_BACKGROUND_ALPHA)
        super.showAsDropDown(anchor, xoff, yoff, gravity)
    }

    fun showPopuWindowAsDrawView(popupWindow: PopupWindow, view: View) {
        val mLocation = IntArray(2)
        view.getLocationInWindow(mLocation)
        popupWindow.showAtLocation(view, Gravity.TOP, 0, mLocation[1] + view.height)
    }

    /**
     * @author 强周亮
     * @desc 监听popuwindow关闭后做的一些事
     * @time 2018-12-11 17:54
     */
    interface PopuWindowCloseAtfer {
        fun popuWindowCloseAfter()
    }

    object PopuWindowCloseAtferUtil {
        private var mPopuWindowCloseAtfer: PopuWindowCloseAtfer? = null

        fun setPopuWindowCloseAtfer(popuWindowCloseAtfer: PopuWindowCloseAtfer) {
            mPopuWindowCloseAtfer = popuWindowCloseAtfer
        }

        fun doPopuWindowCloseAfter() {
            if (mPopuWindowCloseAtfer != null) {
                mPopuWindowCloseAtfer?.popuWindowCloseAfter()
            } else {
                LogUtils.i("类BasePopuWindow：popuWindow监听类mPopuWindowCloseAtfer没有初始化")
            }
        }
    }
}
