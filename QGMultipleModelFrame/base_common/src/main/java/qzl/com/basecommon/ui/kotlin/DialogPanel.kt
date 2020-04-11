package qzl.com.basecommon.ui.kotlin

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.common_dialog_main_layout.view.*
import qzl.com.basecommon.R
import utilclass.StringHelper

/**
 * @author 强周亮(Qzl)
 * @desc 展示弹框
 * @email 2538096489@qq.com
 * @time 2019-05-28 19:27
 */
class DialogPanel(context: Context, theme: Int) : Dialog(context, theme) {
    /**
     * 构造内容
     */
    class Builder(private val context: Context) {
        private var title: String? = null//标题
        private var message: String? = null//信息
        private var positiveButtonText: String? = null//确定按钮
        private var negativeButtonText: String? = null//取消按钮
        /**
         * 取消元素2
         */
        private var negativeButtonText2: String? = null
        private var customView: View? = null//自定义布局view元素
        private var dialog: DialogPanel? = null
        private var positiveButtonClickListener: DialogInterface.OnClickListener? = null//确定按钮事件
        private var negativeButtonClickListener: DialogInterface.OnClickListener? = null//取消按钮事件
        private var negativeButtonClickListener2: DialogInterface.OnClickListener? = null//取消按钮事件2

        fun setMessage(message: String): Builder {
            this.message = message
            return this
        }
        /**
         * 设置提示信息
         * @return
         */
        fun setMessage(message: Int): Builder {
            this.message = context.getText(message) as String
            return this
        }

        fun setTitle(title: String): Builder {
            this.title = title
            return this
        }

        /**
         * 设置内容，view类型
         * @param v
         * @return
         */
        fun setCustomView(v: View): Builder {
            this.customView = v
            return this
        }

        /**
         * 设置确定按钮
         *
         * @param positiveButtonText
         * @return
         */
        fun setPositiveButton(positiveButtonText: String, listener: DialogInterface.OnClickListener): Builder {
            this.positiveButtonText = positiveButtonText
            this.positiveButtonClickListener = listener
            return this
        }

        /**
         * 设置取消按钮
         *
         * @param negativeButtonText
         * @param listener
         * @return
         */
        fun setNegativeButton(negativeButtonText: String, listener: DialogInterface.OnClickListener): Builder {
            this.negativeButtonText = negativeButtonText
            this.negativeButtonClickListener = listener
            return this
        }

        fun setNegativeButton2(negativeButtonText: String, listener: DialogInterface.OnClickListener?): Builder {
            this.negativeButtonText2 = negativeButtonText
            this.negativeButtonClickListener2 = listener
            return this
        }
        fun createDialog(): DialogPanel {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            dialog = DialogPanel(context, R.style.Dialog)
            val layout = inflater.inflate(R.layout.common_dialog_main_layout, null)
            dialog?.addContentView(layout, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
            layout.title.text = title
            if (positiveButtonText != null) {
                layout.positiveButton.text = positiveButtonText
                layout.positiveButton.setTextColor(context.resources.getColor(R.color.white))
                if (positiveButtonClickListener != null) {
                    layout.positiveButton.setOnClickListener {
                        positiveButtonClickListener?.onClick(dialog, DialogInterface.BUTTON_POSITIVE)
                    }
                }
            } else {
                layout.positiveButton.visibility = View.GONE
            }
            if (negativeButtonText != null) {
                layout.negativeButton.text = negativeButtonText
                layout.negativeButton.setTextColor(context.resources.getColor(R.color.black))
                if (negativeButtonClickListener != null) {
                    (layout.findViewById<View>(R.id.negativeButton) as Button).setOnClickListener {
                        negativeButtonClickListener?.onClick(dialog, DialogInterface.BUTTON_NEGATIVE)
                    }
                }
            } else {
                layout.negativeButton.visibility = View.GONE
            }

            if (negativeButtonText2 != null) {
                layout.negativeButton2.text = negativeButtonText2
                layout.negativeButton2.setTextColor(context.resources.getColor(R.color.white))
                if (negativeButtonClickListener2 != null) {
                    layout.negativeButton2.setOnClickListener {
                        negativeButtonClickListener2?.onClick(dialog, DialogInterface.BUTTON_NEGATIVE)
                    }
                }
            } else {
                layout.negativeButton2.visibility = View.GONE
            }
            if (message != null) {
                layout.message.text = message
            } else if (customView != null) {
                layout.content.removeAllViews()
                layout.content.addView(
                    customView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                )
            }
            dialog?.setContentView(layout)
            return dialog as DialogPanel
        }
        //关闭当天dialog
        fun colseDialog() {
            dialog?.dismiss()
        }
    }

    companion object {
        /**
         * @desc 修改统一的调用方法
         * @author 强周亮
         * @time 2019-03-14 16:53
         * @param context
         * @param message     要显示的内容
         * @param tip          标题提示信息
         * @param postvStr     积极操作内容
         * @param pListener   积极操作监听
         * @param navStr1
         * @param nav1Listener
         * @param navStr2
         * @param nav2Listener
         */
        @JvmStatic
        fun dialogOperate(
            context: Context,
            message: String,
            tip: String,
            postvStr: String,
            pListener: DialogInterface.OnClickListener,
            navStr1: String,
            nav1Listener: DialogInterface.OnClickListener,
            navStr2: String? = null,
            nav2Listener: DialogInterface.OnClickListener? = null
        ) {
            val builder = Builder(context)
            builder.setMessage(message)
            builder.setTitle(tip)
            if (!StringHelper.isEmptyString(postvStr)) {
                builder.setPositiveButton(postvStr, pListener)
            }

            if (!StringHelper.isEmptyString(navStr1)) {
                builder.setNegativeButton(navStr1, nav1Listener)
            }
            navStr2?.let {
                builder.setNegativeButton2(navStr2, nav2Listener)
            }
            builder.createDialog().show()
        }
    }
}
