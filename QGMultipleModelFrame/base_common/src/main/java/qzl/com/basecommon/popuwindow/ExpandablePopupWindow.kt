package qzl.com.basecommon.popuwindow

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.PopupWindow
import android.widget.TextView
import qzl.com.basecommon.R
import qzl.com.basecommon.base.BasCommonPopuWindow
import qzl.com.basecommon.base.BasePopuWindow
import qzl.com.tools.utils.ScreenUtil.Companion.getVirtualBarHeight

class ExpandablePopupWindow(val activity: Context, val list: List<String>,val mView:View) : BasCommonPopuWindow(activity) {
    private var onSelectedItemListener: OnSelectedItemListener? = null

    init {
        setPopWindowContent(R.layout.pop_event_material)
    }
    override fun showPopuwindow(popuWindow: BasePopuWindow) {
        popuWindow.showAtLocation(mView, Gravity.BOTTOM, 0, getVirtualBarHeight(activity))
    }

    override fun initComplate(popupWindow: PopupWindow, pupView: View?) {
        val listView = pupView?.findViewById<ListView>(R.id.pop_item_list)
        listView?.adapter = PopupWindowAdapter(activity, list)
        listView?.setOnItemClickListener { parent, view, position, id ->
            onSelectedItemListener?.onSelectedValue(parent.adapter.getItem(position).toString(),position)
            popupWindow.dismiss()
        }
    }

    interface OnSelectedItemListener {
        fun onSelectedValue(str: String,position: Int)
    }

    fun setOnSelectedItemListener(onSelectedItemListener: OnSelectedItemListener?) {
        this.onSelectedItemListener = onSelectedItemListener
    }

    private inner class PopupWindowAdapter(private val context: Context, private val list: List<String>) : BaseAdapter() {
        override fun getCount(): Int {
            return if (list.isNotEmpty()) {
                list.size
            } else 0
        }

        override fun getItem(position: Int): Any {
            return list[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
            var convertView = convertView
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.i_pop_material_type, parent, false)
            }
            val popMaterialType = convertView?.findViewById<TextView>(R.id.pop_material_type)
            popMaterialType?.text = list[position]
            return convertView
        }
    }
}