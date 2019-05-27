package qzl.com.basecommon.ui.xlistview

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

/**
 * @desc  公共适配器
 * @author 强周亮（Qzl）
 * @email 2538096489@qq.com
 * @time 2018-09-05 19:51
 */
abstract class CommonAdapter<ITEMBEAN,ITEMVIEW:View>(var data: List<ITEMBEAN>) : BaseAdapter() {
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): ITEMBEAN {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView = getItemView(parent?.context)
        refreshView(itemView, data[position])
        return itemView
    }
    /**
     * 获取条目的view
     */
    abstract fun getItemView(context: Context?): ITEMVIEW
    /**
     * 刷新条目view
     */
    abstract fun refreshView(itemView: ITEMVIEW, data: ITEMBEAN)
}
