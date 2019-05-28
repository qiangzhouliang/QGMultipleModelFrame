package qzl.com.basecommon.ui.kotlin.xlistview

import android.content.Context
import android.graphics.Bitmap
import android.text.Html
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * @author 强周亮(Qzl)
 * @desc 适配器公用的holder
 * @email 2538096489@qq.com
 * @time 2019-05-27 08:57
 */
class ViewHolder private constructor(context: Context, parent: ViewGroup?, layoutId: Int) {
    private val mViews: SparseArray<View> = SparseArray()
    var position: Int = 0
    val convertView: View = LayoutInflater.from(context).inflate(layoutId, parent, false)

    init {
        // setTag
        convertView.tag = this
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    fun <T : View> getView(viewId: Int): T {
        var view: View? = mViews.get(viewId)
        if (view == null) {
            view = convertView.findViewById(viewId)
            mViews.put(viewId, view)
        }
        return view as T
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    fun setText(viewId: Int, text: String): ViewHolder {
        val view = getView<TextView>(viewId)
        view.text = Html.fromHtml(text)
        return this
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    fun setImageResource(viewId: Int, drawableId: Int): ViewHolder {
        val view = getView<ImageView>(viewId)
        view.setImageResource(drawableId)

        return this
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param bm
     * @return
     */
    fun setImageBitmap(viewId: Int, bm: Bitmap): ViewHolder {
        val view = getView<ImageView>(viewId)
        view.setImageBitmap(bm)
        return this
    }

    companion object {

        /**
         * 拿到一个ViewHolder对象
         * @param context
         * @param convertView
         * @param parent
         * @param layoutId
         * @param position
         * @return
         */
        operator fun get(context: Context, convertView: View?, parent: ViewGroup?, layoutId: Int, position: Int): ViewHolder {
            val viewHolder: ViewHolder
            if (convertView == null) {
                viewHolder = ViewHolder(context, parent, layoutId)
            } else {
                viewHolder = convertView.tag as ViewHolder
            }
            viewHolder.position = position
            return viewHolder
        }
    }
}