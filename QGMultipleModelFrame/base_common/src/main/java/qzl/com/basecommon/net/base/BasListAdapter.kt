package qzl.com.basecommon.net.base

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zdww.basecommon.ui.kotlin.FooterView
import com.zdww.basecommon.ui.kotlin.FooterViewNoNextPage
import qzl.com.basecommon.ui.page.LoadMoreView
import qzl.com.basecommon.ui.page.NoDataView
import qzl.com.model.common.PagePara

/**
 * @desc 所有下拉刷新和上拉加载更多列表界面基类
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-23 10:28
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.base
 */
abstract class BasListAdapter <ITEMBEAN,ITEMVIEW:View>: RecyclerView.Adapter<BasListAdapter.BasListHolder>() {
    private val TYPE_NORMAL = 0   //正常条目
    val TYPE_AUTO_MORE = 1        // 自动加载下一页
    private val TYPE_FOOTER = 2   //已经到达最后
    private val TYPE_NO_DATA = 3   //没有数据了
    private val TYPE_LOD_TIP = 4   //提示架子啊数据条目

    var list = ArrayList<ITEMBEAN>()
    var paraPara: PagePara? = null
    /**
     * 更新数据
     */
    fun updateList(list: Pair<List<ITEMBEAN>?, PagePara?>) {
        val (list, pagePara) = list
        paraPara = pagePara
        list?.let {
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }

    /**
     * 加载更多方法
     */
    fun loadMore(list: Pair<List<ITEMBEAN>?, PagePara?>){
        val (list, pagePara) = list
        paraPara = pagePara
        list?.let {
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }
    var footerView: FooterView? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasListHolder {
        return when(viewType){
            TYPE_NO_DATA -> BasListHolder(NoDataView(parent.context))
            //最后一个条目，没有下一页
            TYPE_FOOTER -> {
                val footerViewNoData = FooterViewNoNextPage(parent.context)
                footerViewNoData.setData(paraPara)
                BasListHolder(footerViewNoData)
            }
            //最后一个条目,还有下一页
            TYPE_LOD_TIP -> {
                footerView = FooterView(parent.context)
                footerView?.setData(paraPara)
                BasListHolder(footerView!!)
            }
            //还有下一页
            TYPE_AUTO_MORE -> BasListHolder(
                LoadMoreView(
                    parent.context
                )
            )
            //其他条目
            else -> BasListHolder(getItemView(parent.context))
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when {
            (list.isEmpty() || paraPara == null) -> {
                //表示没有查询到数据
                TYPE_NO_DATA
            }
            position > paraPara?.total!! -> {
                TYPE_FOOTER
            }
            position == list.size -> {
                if (list.size >= paraPara?.total?:0){
                    TYPE_FOOTER
                } else {
                    //说明是最后一条，自动加载
                    footerView?.setData(paraPara)
                    TYPE_LOD_TIP
                }
            }
            position == list.size + 1 -> {
                //说明是最后一条，自动加载
                TYPE_AUTO_MORE
            }
            else -> {
                TYPE_NORMAL
            }
        }
    }
    override fun getItemCount(): Int {
        return when {
            (list.isEmpty() || paraPara == null) -> {
                1
            }
            list.size >= paraPara?.total!! -> {
                if (paraPara?.totalPages!! > 1){
                    list.size + 1
                } else {
                    list.size
                }
            }
            else -> {
                list.size + 2
            }
        }
    }

    override fun onBindViewHolder(holder: BasListHolder, position: Int) {
        //如果是暂无数据页面，直接返回
        if (holder.itemView is NoDataView || holder.itemView is FooterViewNoNextPage) return

        //如果是最后一条，就不需要刷新了
        if (list.size >= paraPara?.total?:0 || list.isEmpty()) {
            if (position == list.size) return
        } else {
            if (position >= list.size - 1) return
        }
        //条目数据
        val data = list[position]
        //条目view
        val itemView = holder.itemView as ITEMVIEW
        //条目刷新
        refreshView(itemView,data)
        //设置点击事件
        itemView.setOnClickListener {
            //条目点击事件
            listener?.let {
                it(data)
            }
        }
        //设置长按事件
        itemView.setOnLongClickListener {
            longListener?.let {
                it(data)
            }
            false
        }
    }
    //定义了一个函数类型的变量
    var listener:((itemBean:ITEMBEAN)->Unit)? = null
    fun setMyListener(listener:(itemBean:ITEMBEAN)->Unit){
        this.listener = listener
    }
    //定义了一个函数类型的变量
    var longListener:((itemBean:ITEMBEAN)->Unit)? = null
    fun setMyLongListener(longListener:(itemBean:ITEMBEAN)->Unit){
        this.longListener = longListener
    }

    //删除条目数据
    fun deleteItem(data: ITEMBEAN){
        list.remove(data)
        paraPara?.total = paraPara?.total?.minus(1)!!
        notifyDataSetChanged()
    }

    /**
     * 获取条目的view
     */
    abstract fun getItemView(context: Context?): ITEMVIEW
    /**
     * 刷新条目view
     */
    abstract fun refreshView(itemView: ITEMVIEW, data: ITEMBEAN)

    class BasListHolder(itemView: View): RecyclerView.ViewHolder(itemView){}
}