package qzl.com.main.adapter

import android.content.Context
import com.itheima.player.model.bean.HomeItemBean
import qzl.com.basecommon.net.base.BasListAdapter
import qzl.com.main.mvp.widget.MvpItemView

/**
 * @desc
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-01 17:19
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.adapter
 */
class MvpAdapter : BasListAdapter<HomeItemBean, MvpItemView>() {
    override fun getItemView(context: Context?): MvpItemView {
        return MvpItemView(context)
    }

    override fun refreshView(itemView: MvpItemView, data: HomeItemBean) {
        itemView.setData(data)
    }

}