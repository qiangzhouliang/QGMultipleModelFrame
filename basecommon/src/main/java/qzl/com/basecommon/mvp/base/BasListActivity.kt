package qzl.com.basecommon.mvp.base

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.p_list.*
import qzl.com.basecommon.R
import qzl.com.basecommon.base.BaseActivity
import qzl.com.tools.utils.LogUtils
import utilclass.Tt

/**
 * @desc 具有所有下拉刷新和上拉加载更多的activity基类
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-23 10:08
 */
abstract class BasListActivity<RESPONSE,ITEMBEAN,ITEMVIEW:View> : BaseActivity(), BaseView<RESPONSE> {
    /**
     * 数据操作层懒加载初始化
     */
    val presenter by lazy { getSpecPresenter() }
    //适配器懒加载初始化
    val adapter by lazy { getSpecAdapter() }
    override fun getLayoutId(): Int {
        return R.layout.p_list
    }
    override fun onError(message: String?) {
        //隐藏刷新控件
        refreshLayout.isRefreshing = false
        Tt.showShort("加载数据失败")
    }

    override fun loadSuccess(response: RESPONSE?) {
        //隐藏刷新控件
        refreshLayout.isRefreshing = false
        //刷新列表
        adapter.updateList(getList(response))
    }
    override fun loadMoreSuccess(response: RESPONSE?) {
        //刷新列表
        adapter.loadMore(getList(response))
    }
    override fun initListener() {
        //初始化Recycleview
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = adapter

        //初始化刷新控件
        //1 设置控件颜色
        refreshLayout.setColorSchemeColors(Color.RED, Color.YELLOW, Color.GREEN)
        //2设置刷新监听
        refreshLayout.setOnRefreshListener {
            //刷新监听
            presenter.loadDatas()
        }

        //监听列表的滑动
        recycleView.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                //滑动状态的改变
                /*when(newState){
                    RecyclerView.SCROLL_STATE_IDLE ->{
                        println("空闲状态")
                    }
                    RecyclerView.SCROLL_STATE_DRAGGING ->{
                        println("拖动状态")
                    }
                    RecyclerView.SCROLL_STATE_SETTLING ->{
                        println(" SETTLING 状态")
                    }
                }*/
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    //空闲状态，是否是最后一条已经显示
                    val layoutManager = recyclerView.layoutManager
                    if(!(layoutManager is LinearLayoutManager)) return
                    val lastPosition = layoutManager.findLastVisibleItemPosition()
                    if (lastPosition == adapter.itemCount - 1){
                        //最后一条已经显示了
                        presenter.loadMore(adapter.itemCount - 1)
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                LogUtils.i("onScrolled dx = $dx dy = $dy")
            }
        })
    }

    override fun initData() {
        //初始化数据
        presenter.loadDatas()
    }
    /**
     * 获取适配器adapter
     */
    abstract fun getSpecAdapter():BasListAdapter<ITEMBEAN,ITEMVIEW>
    //获取present
    abstract fun getSpecPresenter():BasListPresenter
    //从返回结果中获取列表数据集合
    abstract fun getList(response: RESPONSE?): List<ITEMBEAN>?
}