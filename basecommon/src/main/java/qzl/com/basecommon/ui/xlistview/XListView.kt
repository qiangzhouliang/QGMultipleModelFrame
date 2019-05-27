package qzl.com.basecommon.ui.xlistview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.animation.DecelerateInterpolator
import android.widget.*
import android.widget.AbsListView.OnScrollListener
import qzl.com.basecommon.R


class XListView : ListView, OnScrollListener {

    private var mLastY = -1f // save event y
    private var mScroller: Scroller? = null // used for scroll back
    private var mScrollListener: OnScrollListener? = null // user's scroll listener

    // the interface to trigger refresh and load more.
    private var mListViewListener: IXListViewListener? = null

    // -- header view
    private var mHeaderView: XListViewHeader? = null
    // header view content, use it to calculate the Header's height. And hide it
    // when disable pull refresh.
    private var mHeaderViewContent: RelativeLayout? = null
    private var mHeaderViewHeight: Int = 0 // header view's height
    private var mEnablePullRefresh = true
    private var mPullRefreshing = false // is refreashing.

    // -- footer view
    private var mFooterView: XListViewFooter? = null
    private var mEnablePullLoad: Boolean = false
    private var mPullLoading: Boolean = false
    private var mIsFooterReady = false

    // total list items, used to detect is at the bottom of listview.
    private var mTotalItemCount: Int = 0

    // for mScroller, scroll back from header or footer.
    private var mScrollBack: Int = 0

    var pageType = PAGE_MODE_SINGLE//分页类型
    private var page: Page? = null//当前页
    var noDataLayout: LinearLayout? = null
        private set

    val preText: String
        get() {
            var text = ""
            if (page != null) {
                text = "松开加载第" + (page!!.pageNo - 1) + "页"
            }
            return text
        }

    /**
     * @param context
     */
    constructor(context: Context) : super(context) {
        initWithContext(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initWithContext(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        initWithContext(context)
    }

    private fun initWithContext(context: Context) {
        mScroller = Scroller(context, DecelerateInterpolator())
        // XListView need the scroll event, and it will dispatch the event to
        // user's listener (as a proxy).
        super.setOnScrollListener(this)

        // init header view
        mHeaderView = XListViewHeader(context)
        mHeaderViewContent = mHeaderView!!
            .findViewById<View>(R.id.xlistview_header_content) as RelativeLayout
        addHeaderView(mHeaderView)

        // init footer view
        mFooterView = XListViewFooter(context)

        // init header height
        mHeaderView!!.viewTreeObserver.addOnGlobalLayoutListener(
            object : OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    mHeaderViewHeight = mHeaderViewContent!!.height
                    viewTreeObserver
                        .removeGlobalOnLayoutListener(this)
                }
            })
        noDataLayout = LayoutInflater.from(context).inflate(R.layout.common_no_data, this, false) as LinearLayout
        noDataLayout?.visibility = View.GONE
    }

    override fun setAdapter(adapter: ListAdapter) {
        // make sure XListViewFooter is the last footer view, and only add once.
        if (!mIsFooterReady) {
            mIsFooterReady = true
            addFooterView(mFooterView)
        }
        super.setAdapter(adapter)
    }

    /**
     * enable or disable pull down refresh feature.
     *
     * @param enable
     */
    fun setPullRefreshEnable(enable: Boolean) {
        mEnablePullRefresh = enable
        if (!mEnablePullRefresh) { // disable, hide the content
            mHeaderView?.setState(XListViewHeader.STATE_FIRST, "已经是第一页了")
        } else {
            mHeaderViewContent?.visibility = View.VISIBLE
            mHeaderView?.setState(XListViewHeader.STATE_NORMAL, "")
        }
    }

    /**
     * enable or disable pull up load more feature.
     *
     * @param enable
     */
    fun setPullLoadEnable(enable: Boolean) {
        mEnablePullLoad = enable
        if (!mEnablePullLoad) {
            mFooterView?.hide()
            mFooterView?.setOnClickListener(null)
            //make sure "pull up" don't show a line in bottom when listview with one page
            setFooterDividersEnabled(false)
        } else {
            mPullLoading = false
            mFooterView?.show()
            mFooterView?.setState(XListViewFooter.STATE_NORMAL, getNextText(1))
            //make sure "pull up" don't show a line in bottom when listview with one page
            setFooterDividersEnabled(true)
            // both "pull up" and "click" will invoke load more.
            mFooterView?.setOnClickListener { startLoadMore() }
        }
    }

    /**
     * stop refresh, reset header view.
     */
    fun stopRefresh() {
        if (mPullRefreshing) {
            mPullRefreshing = false
            resetHeaderHeight()
        }
    }

    /**
     * stop load more, reset footer view.
     */
    fun stopLoadMore() {
        if (mPullLoading) {
            mPullLoading = false
            mFooterView?.setState(XListViewFooter.STATE_NORMAL, getNextText(1))
        }
    }

    /**
     * set last refresh time
     */
    fun setRefreshTime(totalText: String, currentText: String) {
        mHeaderView?.getmHintTextView()?.text = currentText
    }

    private fun invokeOnScrolling() {
        if (mScrollListener is OnXScrollListener) {
            val l = mScrollListener as OnXScrollListener?
            l?.onXScrolling(this)
        }
    }

    private fun updateHeaderHeight(delta: Float) {
        mHeaderView?.visiableHeight = delta.toInt() + mHeaderView?.visiableHeight!!
        if (mEnablePullRefresh && !mPullRefreshing) { // 未处于刷新状态，更新箭头
            if (mHeaderView?.visiableHeight!! > mHeaderViewHeight) {
                mHeaderView?.setState(XListViewHeader.STATE_READY, preText)
            } else {
                mHeaderView?.setState(XListViewHeader.STATE_NORMAL, preText)
            }
        }
        setSelection(0) // scroll to top each time
    }

    /**
     * reset header view's height.
     */
    private fun resetHeaderHeight() {
        val height = mHeaderView?.visiableHeight
        if (height == 0)
        // not visible.
            return
        // refreshing and header isn't shown fully. do nothing.
        if (mPullRefreshing && height!! <= mHeaderViewHeight) {
            return
        }
        var finalHeight = 0 // default: scroll back to dismiss header.
        // is refreshing, just scroll back to show all the header.
        if (mPullRefreshing && height!! > mHeaderViewHeight) {
            finalHeight = mHeaderViewHeight
        }
        mScrollBack = SCROLLBACK_HEADER
        mScroller?.startScroll(
            0, height?:0, 0, finalHeight - (height?:0),
            SCROLL_DURATION
        )
        // trigger computeScroll
        invalidate()
    }

    private fun updateFooterHeight(delta: Float) {
        val height = (mFooterView?.bottomMargin?:0) + delta.toInt()
        if (mEnablePullLoad && !mPullLoading) {
            if (height > PULL_LOAD_MORE_DELTA) { // height enough to invoke load
                // more.
                mFooterView?.setState(XListViewFooter.STATE_READY, getNextText(0))
            } else {
                mFooterView?.setState(XListViewFooter.STATE_NORMAL, getNextText(1))
            }
        }
        if (!mEnablePullLoad && height > 0) {
            mFooterView?.show()
            mFooterView?.setState(XListViewFooter.STATE_LAST, if (page?.totalCount?:0 > 0) "已经是最后一页了" else "")
        }
        mFooterView?.bottomMargin = height
        //		setSelection(mTotalItemCount - 1); // scroll to bottom
    }

    private fun resetFooterHeight() {
        val bottomMargin = mFooterView?.bottomMargin?:0
        if (bottomMargin > 0) {
            mScrollBack = SCROLLBACK_FOOTER
            mScroller?.startScroll(
                0, bottomMargin, 0, -bottomMargin,
                SCROLL_DURATION
            )
            invalidate()
        }
    }

    private fun startLoadMore() {
        mPullLoading = true
        mFooterView?.setState(XListViewFooter.STATE_LOADING, "")
        if (mListViewListener != null) {
            if (pageType == PAGE_MODE_All) {
                mListViewListener!!.onLoadMore()
            } else {
                mListViewListener!!.onNext()
            }
        }
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        if (mLastY == -1f) {
            mLastY = ev.rawY
        }

        when (ev.action) {
            MotionEvent.ACTION_DOWN -> mLastY = ev.rawY
            MotionEvent.ACTION_MOVE -> {
                val deltaY = ev.rawY - mLastY
                mLastY = ev.rawY
                if (firstVisiblePosition == 0 && (mHeaderView!!.visiableHeight > 0 || deltaY > 0)) {
                    // the first item is showing, header has shown or pull down.
                    updateHeaderHeight(deltaY / OFFSET_RADIO)
                    invokeOnScrolling()
                } else if (lastVisiblePosition == mTotalItemCount - 1 && (mFooterView!!.bottomMargin > 0 || deltaY < 0)) {
                    // last item, already pulled up or want to pull up.
                    updateFooterHeight(-deltaY / OFFSET_RADIO)
                }
            }
            else -> {
                mLastY = -1f // reset
                if (firstVisiblePosition == 0) {
                    // invoke refresh
                    val i = mHeaderView!!.visiableHeight
                    //if (mEnablePullRefresh && mHeaderView.getVisiableHeight() > mHeaderViewHeight) 改变上拉刷新条件，如果正在加载就不走加载数据的方法
                    if (mEnablePullRefresh && mHeaderView!!.visiableHeight > mHeaderViewHeight && !mPullRefreshing) {
                        mPullRefreshing = true
                        mHeaderView!!.setState(XListViewHeader.STATE_REFRESHING, "")
                        if (mListViewListener != null) {
                            if (pageType == PAGE_MODE_All) {
                                mListViewListener!!.onRefresh()
                            } else {
                                mListViewListener!!.onLast()
                            }
                        }
                    }
                    resetHeaderHeight()
                }
                if (lastVisiblePosition == mTotalItemCount - 1) {
                    // invoke load more.
                    val i = mFooterView!!.bottomMargin
                    if (mEnablePullLoad && mFooterView!!.bottomMargin > PULL_LOAD_MORE_DELTA && !mPullLoading) {
                        startLoadMore()
                    }
                    //如果没有下一页了
                    if (!mEnablePullLoad) {
                        setPullLoadEnable(false)
                    }
                    resetFooterHeight()
                }
            }
        }
        return super.onTouchEvent(ev)
    }

    override fun computeScroll() {
        if (mScroller!!.computeScrollOffset()) {
            if (mScrollBack == SCROLLBACK_HEADER) {
                mHeaderView!!.visiableHeight = mScroller!!.currY
            } else {
                mFooterView!!.bottomMargin = mScroller!!.currY
            }
            postInvalidate()
            invokeOnScrolling()
        }
        super.computeScroll()
    }

    override fun setOnScrollListener(l: OnScrollListener) {
        mScrollListener = l
    }

    override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {
        if (mScrollListener != null) {
            mScrollListener!!.onScrollStateChanged(view, scrollState)
        }
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
            if (!mEnablePullLoad) {
                mFooterView!!.hide()
            }
        }
    }

    override fun onScroll(
        view: AbsListView, firstVisibleItem: Int,
        visibleItemCount: Int, totalItemCount: Int
    ) {
        // send to user's listener
        mTotalItemCount = totalItemCount
        if (mScrollListener != null) {
            mScrollListener!!.onScroll(
                view, firstVisibleItem, visibleItemCount,
                totalItemCount
            )
        }
    }

    fun setXListViewListener(l: IXListViewListener) {
        mListViewListener = l
    }

    fun getmFooterView(): XListViewFooter? {
        return mFooterView
    }

    fun setmFooterView(mFooterView: XListViewFooter) {
        this.mFooterView = mFooterView
    }

    fun getmHeaderView(): XListViewHeader? {
        return mHeaderView
    }

    fun setmHeaderView(mHeaderView: XListViewHeader) {
        this.mHeaderView = mHeaderView
    }

    fun setPage(page: Page) {
        this.page = page
    }

    fun getNextText(state: Int): String {
        var text = ""
        if (state == 0) {
            text = "松开加载第" + (page!!.pageNo + 1) + "页"
        } else {
            text = "当前第" + page!!.pageNo + "页(共" + page!!.totalPages + "页),上拉加载下一页"
        }
        return text
    }

    /**
     * you can listen ListView.OnScrollListener or this one. it will invoke
     * onXScrolling when header/footer scroll back.
     */
    interface OnXScrollListener : OnScrollListener {
        fun onXScrolling(view: View)
    }

    /**
     * implements this interface to get refresh/load more event.
     */
    interface IXListViewListener {
        fun onRefresh()

        fun onLoadMore()

        fun onLast()

        fun onNext()
    }

    companion object {
        private val SCROLLBACK_HEADER = 0
        private val SCROLLBACK_FOOTER = 1

        private val SCROLL_DURATION = 400 // scroll back duration
        private val PULL_LOAD_MORE_DELTA = 50 // when pull up >= 50px
        // at bottom, trigger
        // load more.
        private val OFFSET_RADIO = 1.8f // support iOS like pull
        // feature.
        private val PAGE_MODE_SINGLE = "0" // 展示单页
        private val PAGE_MODE_All = "1"//展示所有的
    }
}
