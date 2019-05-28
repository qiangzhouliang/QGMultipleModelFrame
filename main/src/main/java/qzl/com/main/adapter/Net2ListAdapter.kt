package qzl.com.main.adapter

import android.content.Context
import qzl.com.basecommon.ui.kotlin.xlistview.CommonAdapter
import qzl.com.main.domain.Result
import qzl.com.main.mvp.widget.Net2ItemView

/**
 * @desc 清四乱列表适配器
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2018-06-06 15:35
 * @class hzz
 */
class Net2ListAdapter(var datas:List<Result>) : CommonAdapter<Result, Net2ItemView>(datas) {
    override fun getItemView(context: Context?): Net2ItemView {
        return Net2ItemView(context)
    }
    override fun refreshView(itemView: Net2ItemView, data: Result) {
        itemView.setData(data)
    }
}