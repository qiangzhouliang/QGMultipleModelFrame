package qzl.com.main.adapter

import android.content.Context
import qzl.com.basecommon.net.base.BasListAdapter
import qzl.com.model.test.Data
import qzl.com.main.mvp.widget.HomeItemView

/**
 * @desc
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-01 17:19
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.adapter
 */
class HomeAdapter : BasListAdapter<qzl.com.model.test.Data, HomeItemView>() {
    override fun getItemView(context: Context?): HomeItemView {
        return HomeItemView(context)
    }

    override fun refreshView(itemView: HomeItemView, data: qzl.com.model.test.Data) {
        itemView.setData(data)
    }

}