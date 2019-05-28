package qzl.com.basecommon.ui.kotlin.xlistview

import android.os.Handler
import android.view.View
import android.widget.BaseAdapter
import android.widget.LinearLayout

/**
 * Created by 肖龙 on 2015/6/8.
 */
class PageListUtil : XListView.IXListViewListener {
    private lateinit var page: Page
    private lateinit var handler: Handler
    private lateinit var baseAdapter: BaseAdapter
    private lateinit var listView: XListView
    private var initPageList: InitPageList? = null
    private var LAST_PAGE = 0
    private var NEXT_PAGE = 1
    private var STATE = LAST_PAGE
    //查询条件状态
    private var isConditionUpdate = false

    fun initPage(total: Int, listView: XListView) {
        this.listView = listView
        page = Page()
        page.totalCount = total.toLong()
        //初始化第一页
        initPageList?.let {
            this.baseAdapter = it.initAdapter()
        }

        listView.adapter = baseAdapter
        listView.setXListViewListener(this)
        listView.setPage(page)
        if (!page.isHasNext) {
            listView.setPullLoadEnable(false)
        } else {
            listView.setPullLoadEnable(true)
        }
        listView.setPullRefreshEnable(false)
        handler = Handler()
        val parentLinearLayout = listView.parent as LinearLayout
        parentLinearLayout.addView(listView.noDataLayout)
        isShowNoDataLayout()//是否展示暂无数据布局
    }

    override fun onRefresh() {}

    override fun onLoadMore() {}

    /**
     * 上一页
     */
    override fun onLast() {
        if (page.pageNo < 2) {
            return
        }
        handler.postDelayed({
            STATE = LAST_PAGE
            page.setPrePageNo()
            if (initPageList != null) {
                initPageList!!.getList(page.pageNo, page.pageSize)
            }
        }, 1000)
    }

    /**
     * 下一页
     */
    override fun onNext() {
        handler.postDelayed({
            STATE = NEXT_PAGE
            page.setNextPageNo()
            if (initPageList != null) {
                initPageList!!.getList(page.pageNo, page.pageSize)
            }
        }, 1000)
    }

    //重置分页配置  state:true 查询条件发生变化重置
    fun restPageSetting(total: Int) {
        page.totalCount = total.toLong()
        if (this.isConditionUpdate) resetInitPage()
        isShowNoDataLayout()
        baseAdapter = initPageList!!.initAdapter()
        listView.adapter = baseAdapter
        onLoad()
        if (STATE == NEXT_PAGE) {
            if (page.isHasPre) {
                listView.setPullRefreshEnable(true)
            }
            if (!page.isHasNext) {
                listView.setPullLoadEnable(false)
            }
        } else {
            if (page.isHasNext) {
                listView.setPullLoadEnable(true)
            }
            if (!page.isHasPre) {
                listView.setPullRefreshEnable(false)
            }
        }
    }

    fun resetInitPage() {
        page.pageNo = 1
        listView.setPage(page)
        if (!page.isHasNext) {
            listView.setPullLoadEnable(false)
        } else {
            listView.setPullLoadEnable(true)
        }
        if (!page.isHasPre) {
            listView.setPullRefreshEnable(false)
        } else {
            listView.setPullRefreshEnable(true)
        }
        this.isConditionUpdate = false
    }

    private fun onLoad() {
        listView.setPage(page)
        listView.stopRefresh()
        listView.stopLoadMore()
    }


    fun setInitPageList(initPageList: InitPageList) {
        this.initPageList = initPageList
    }

    interface InitPageList {
        fun getList(pageNo: Int, pageSize: Int)
        fun initAdapter(): BaseAdapter
    }


    fun setConditionUpdate(isConditionUpdate: Boolean) {
        this.isConditionUpdate = isConditionUpdate
    }

    //显示或隐藏暂无数据图片
    fun isShowNoDataLayout() {
        if (page.totalCount == 0L) {
            listView.visibility = View.GONE
            listView.noDataLayout?.visibility = View.VISIBLE
        } else {
            listView.visibility = View.VISIBLE
            listView.noDataLayout?.visibility = View.GONE
        }
    }
}
