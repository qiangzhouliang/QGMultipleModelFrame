package qzl.com.basecommon.base

import android.content.Context
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import qzl.com.basecommon.R

/**
 * @author Qzl
 * @desc popuwindow基础类
 * @email 2538096489@qq.com
 * @time 2019-02-26 17:21
 * @class hzz
 * @package com.gsww.hzz.main.popuwindow
 */
abstract class BasCommonPopuWindow(private val mContext: Context) : BasePopuWindow(mContext) {
    private var pupView: View? = null
    /**
     * 设置popuwindow内容
     */
    fun setPopWindowContent(layout: Int) {
        pupView = LayoutInflater.from(mContext).inflate(layout, null)
        contentView = pupView
        setPopuWidth()
        setPopHeight()
        isTouchable = true
        isFocusable = true
        isOutsideTouchable = true
        pupView?.isFocusableInTouchMode = true
        setBackgroundDrawable(mContext.resources.getDrawable(android.R.color.transparent))
        animationStyle = R.style.popupWindowAnimation
        pupView?.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                dismiss()
                return@OnKeyListener true
            }
            false
        })
        //显示popuwindow
        showPopuwindow(this)
        initComplate(this, pupView)
    }

    /**
     * @desc 显示popuwindow
     * @author 强周亮
     * @time 2019-02-26 18:07
     * @param popuWindow
     */
    protected abstract fun showPopuwindow(popuWindow: BasePopuWindow)

    /**
     * @desc 设置高度 默认 LinearLayout.LayoutParams.WRAP_CONTENT
     * @author 强周亮
     * @time 2019-02-26 17:51
     */
    fun setPopHeight() {
        height = LinearLayout.LayoutParams.WRAP_CONTENT
    }

    /**
     * @desc 设置宽度 默认 LinearLayout.LayoutParams.MATCH_PARENT
     * @author 强周亮
     * @time 2019-02-26 17:51
     */
    fun setPopuWidth() {
        width = LinearLayout.LayoutParams.MATCH_PARENT
    }

    /**
     *
     * @param popupWindow
     * @param pupView 内容view
     */
    protected abstract fun initComplate(popupWindow: PopupWindow, pupView: View?)

}
