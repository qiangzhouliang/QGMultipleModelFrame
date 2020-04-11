package qzl.com.basecommon.base

import android.text.Html
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import qzl.com.basecommon.net.base.BasListAdapter
import qzl.com.basecommon.net.base.BasListPresenter
import kotlinx.android.synthetic.main.common_search_screen.*
import kotlinx.android.synthetic.main.fragment_list.*
import org.jetbrains.anko.support.v4.runOnUiThread
import qzl.com.basecommon.R
import qzl.com.basecommon.common.Constant
import qzl.com.basecommon.net.base.BaseListView
import qzl.com.basecommon.ui.kotlin.HeadControlPanel
import qzl.com.model.common.PagePara

/**
 * @desc 具有所有下拉刷新和上拉加载更多的基类
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-23 10:08
 */
abstract class BasListFragment<RESPONSE,ITEMBEAN,ITEMVIEW:View> : BaseFragment(), BaseListView<RESPONSE> {
    //适配器懒加载初始化
    val adapter by lazy { getSpecAdapter() }
    var isAddItemDecoration = true

    /**
     * 数据操作层懒加载初始化
     */
    val presenter by lazy { getSpecPresenter() }
    //请求参数
    private val map = HashMap<String, String?>()
    override fun onError(message: String?) {
        runOnUiThread {
            recycleView.visibility = View.VISIBLE
            //隐藏刷新控件
            refreshLayout.isRefreshing = false
        }
    }

    override fun loadSuccess(response: RESPONSE?) {
        runOnUiThread {
            recycleView.visibility = View.VISIBLE
            //隐藏刷新控件
            refreshLayout.isRefreshing = false
            if (activity?.isDestroyed != true) {
                //刷新列表
                adapter.updateList(getList(response))
            }
        }
    }

    override fun loadMoreSuccess(response: RESPONSE?) {
        if (activity?.isDestroyed != true) {
            //刷新列表
            adapter.loadMore(getList(response))
        }
    }

    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_list,null)
    }

    override fun initListener() {
        view?.findViewById<HeadControlPanel>(R.id.head_layout)?.visibility = View.GONE
        //初始化Recycleview
        recycleView.visibility = View.GONE
        recycleView.layoutManager = LinearLayoutManager(context)
        if (isAddItemDecoration){
            recycleView.addItemDecoration(RecycleViewDivider(context,LinearLayoutManager.VERTICAL))
        }

        recycleView.adapter = adapter

        //初始化刷新控件
        //1 设置控件颜色
        refreshLayout.setColorSchemeColors(resources.getColor(R.color.cell_ordinary_m))
        //2设置刷新监听
        refreshLayout.setOnRefreshListener {
            //刷新监听
            getInitData(map,false)
        }

        //监听列表的滑动
        recycleView.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                //滑动状态的改变
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    //空闲状态，是否是最后一条已经显示
                    val layoutManager = recyclerView.layoutManager
                    if(layoutManager !is LinearLayoutManager) return
                    val lastPosition = layoutManager.findLastVisibleItemPosition()
                    if (lastPosition == adapter.itemCount - 1 && adapter.getItemViewType(lastPosition) == adapter.TYPE_AUTO_MORE){
                        //最后一条已经显示了
                        map["pageNum"] = adapter.paraPara?.pageNo?.plus(1).toString()
                        presenter.loadMore(map)
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            }
        })
    }

    /**
     * @desc 初始化数据
     * @author 强周亮
     * @time 2019/11/24 0:32
     */
    fun getInitData(maps:HashMap<String,String?>,isShowProgres:Boolean = true) {
        maps["pageNum"] = "0"
        maps["pageSize"] = Constant.pageSize.toString()
        presenter.loadDatas(maps,isShowProgres)
    }

    override fun initData() {
        getInitData(map)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroyView()
    }

    fun getSearch(): Pair<LinearLayout, HashMap<String, String?>> {
        return Pair(search_linear,map)
    }
    //设置搜索框下边左边提示内容
    fun setTipLeft(str:String){
        tv_tip_left.visibility = View.VISIBLE
        tv_tip_left.text = Html.fromHtml(str)
    }
    //设置搜索框下边右边提示内容
    fun setTipRight(str:String){
        tv_tip_right.visibility = View.VISIBLE
        tv_tip_right.text = Html.fromHtml(str)
    }
    //获取搜索选择控件
    fun showSearchSelect(text:String? = "筛选"): Pair<LinearLayout, HashMap<String, String?>> {
        f_select_ll.findViewById<TextView>(R.id.area_name).text = text
        f_select_ll.visibility = View.VISIBLE
        return Pair(f_select_ll,map)
    }
    /**
     * 获取适配器adapter
     */
    abstract fun getSpecAdapter(): BasListAdapter<ITEMBEAN, ITEMVIEW>
    //获取present
    abstract fun getSpecPresenter(): BasListPresenter
    //从返回结果中获取列表数据集合
    abstract fun getList(response: RESPONSE?): Pair<List<ITEMBEAN>?, PagePara?>
}