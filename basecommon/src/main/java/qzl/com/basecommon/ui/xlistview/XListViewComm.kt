package qzl.com.basecommon.ui.xlistview

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.widget.BaseAdapter
import android.widget.LinearLayout
import com.google.gson.Gson
import qzl.com.basecommon.R
import qzl.com.basecommon.base.BaseRequest
import qzl.com.basecommon.common.Constant
import qzl.com.basecommon.net.domain.CommonXListBean
import qzl.com.basecommon.net.net.MRequest
import qzl.com.basecommon.net.net.ResponseHandler

/**
 * @desc listView公共调用类
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2018-07-11 09:13
 */
class XListViewComm<ITEMBEAN,ITEMVIEW: View>(
    val mActivity: Activity, val xlistView: XListView, val url: String, val isPaging: Boolean,//是否分页
    val adapterIntf: AdapterParaIntf<ITEMBEAN,ITEMVIEW>,//加载成功回调
    var loadError:AdapterParaErrorIntf? = null,//加载失败时的回调
    val type: Int = 0,//传递的类型区分
    var isShowErrorPage:Boolean = true //加载出错了是否显示错误页面
    ) :
    ResponseHandler<String> {

    /**
     * 页面list封装工具
     */
    private var mPageListUtil: PageListUtil? = null
    /**
     * 存储查询条件
     */
    private var conditionMap: HashMap<String, String>? = null
    /**
     * 列表总记录数
     */
    private var totalNum = 0

    /**
     * 存储列表数据
     */
    var result:String? = null
    init {
        initErrorLayout(xlistView)
    }
    /**
     * 页面大小
     */
    private var pageSize: Int = Constant.pageSize
    private val initPageList = object : PageListUtil.InitPageList {
        override fun getList(pageNo: Int, pageSize: Int) {
            conditionMap?.set("pageNo", pageNo.toString())
            conditionMap?.set("pageSize",pageSize.toString())
            val mRequest = MRequest(mActivity,type,url,this@XListViewComm,conditionMap)
            BaseRequest<String>(type).getRequest(mRequest)
        }
        override fun initAdapter(): BaseAdapter {
            return adapterIntf.getParaAdapter(result,type)
        }
    }

    /**
     * @desc 根据条件查询
     * @author Qzl
     * @time 2018-06-06 14:52
     */
    fun getListByCondition(conditonMap: HashMap<String, String>,pageNo: String = "1") {
        this.conditionMap = conditonMap
        if (mPageListUtil != null) {
            mPageListUtil?.setConditionUpdate(true)
        }
        conditonMap["pageNo"] = pageNo
        conditonMap["pageSize"] = pageSize.toString()
        val mRequest = MRequest(mActivity,type,url,this,conditonMap)
        BaseRequest<String>(type).getRequest(mRequest)
    }
    /**
     * 显示错误的view
     */
    private var errorLayout: LinearLayout? = null
    /**
     * 添加异常界面的父view
     */
    private var errorParentView: LinearLayout? = null
    /**
     * 隐藏的view  为了显示异常view
     */
    private var hideView: View? = null
    //显示异常信息
    private fun showErrorLayout() {
        if (errorLayout != null) {
            hideView?.setVisibility(View.GONE)
            errorLayout?.setVisibility(View.VISIBLE)
        }
    }

    //隐藏异常信息界面
    private fun hideErrorLayout() {
        if (errorLayout != null) {
            hideView?.visibility = View.VISIBLE
            errorLayout?.visibility = View.GONE
        }
    }
    //初始化显示错误信息的布局
    private fun initErrorLayout(hideView: View?) {
        if (hideView != null) {
            this.hideView = hideView
            this.errorParentView = hideView.parent as LinearLayout
            errorLayout = LayoutInflater.from(mActivity).inflate(R.layout.common_no_data, errorParentView, false) as LinearLayout
            errorParentView?.addView(errorLayout)
            hideErrorLayout()
        }
    }
    /**
     * @desc 初始化分页 （list查询后执行）
     * @author 强周亮(Qzl)
     * @time 2018-09-06 19:16
     * @param map 请求参数
     */
    fun initPageListView(result: String) {
        var commonXListBean = Gson().fromJson(result, CommonXListBean::class.java)
        if (commonXListBean?.data?.totalRow?:0 == 0 || commonXListBean?.data?.result == null){
            //展示暂无数据页面
            showErrorLayout()
        }else {
            totalNum = if (isPaging) {
                commonXListBean.data.totalRow
            } else {
                1
            }

            if (mPageListUtil == null) {
                //列表
                mPageListUtil = PageListUtil()
                mPageListUtil?.setInitPageList(initPageList)
                mPageListUtil?.initPage(totalNum, xlistView)
            } else {
                mPageListUtil?.restPageSetting(totalNum)
            }
        }
    }

    /**
     * 回调接口-- 带有传递的参数
     */
    interface AdapterParaIntf<ITEMBEAN,ITEMVIEW: View> {
        /**
         * @desc 选择区划后，获取数据
         * @author Qzl
         * @time 2018-06-21 13:50
         * @param map 加载的数据
         */
        fun getParaAdapter(result:String?, type: Int): CommonAdapter<ITEMBEAN,ITEMVIEW>
    }
    /**
     * 回调接口-- 加载出错时的回调
     */
    interface AdapterParaErrorIntf {
        fun loadErrorInfo(type: Int, msg: String?)
    }
    override fun onError(type: Int, msg: String?) {
        loadError?.loadErrorInfo(type,msg)
        if (isShowErrorPage) {
            showErrorLayout()
        }
    }

    override fun OnSuccess(type: Int, result: String) {
        this.result = result
        initPageListView(result)
    }
}
