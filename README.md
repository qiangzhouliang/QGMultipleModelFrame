# 一 整体框架结构
# 如何使用
直接可以把整个工程直接作为一个开发框架，直接在里面开发，如果想用里面的lib库，可以先将项目跑起来，
然后根据效果使用,建议下载源代码使用
Step 1. Add the JitPack repository to your build file
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency
```
dependencies {
	        implementation 'com.github.qiangzhouliang:MyApplication:V1.0.1'
	}
```
## 1.1 APP 模块
负责整个APP的功能模块的调度，加载，装配的功能，也是主功能
## 1.2 basecommon 模块
&ensp;&ensp;&ensp;&ensp;主要是一些公共的内容，可以提供给各大功能模块都可以使用基础功能实现类，如图片加载，文件下载，动态权限获取，网络请求等基础功能模块  
&ensp;&ensp;&ensp;&ensp;包含了公共UI
## 1.3 tools 模块
&ensp;&ensp;&ensp;&ensp;该模块当中主要放的一些工具类，一些第三方的引用都放在此模块下
## 1.4 功能模块
主要是引入的一些可以作为单独模块开发的功能模块
# 二 功能模块
## 2.1 视频播放模块videoplayer
该模块主要负责视频播放的公共module，可以单独开发，可以继承作为所有视频播放的功能模块来开发，调用方法是通过aRouter路由的方式调用  
- [ ] 例如
```
ARouter.getInstance().build(ARouterPath.VIDEO_PLAYER)
                .withString("videoTitle", "首页跳转")
                .navigation()

```
再被调用的地方activity上写上注解@Route(path = XXX)
- [ ] 例如
```
@Route(path = ARouterPath.VIDEO_PLAYER)
class JiaoZiVideoPlayerActivity: BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_video_player_jiecao
    }
}
```
## 2.2 运行时权限和获取
用到的是 https://github.com/lovedise/PermissionGen
## 2.3 MVP请求框架封装
这里面封装了两种调运方法，分别是“MVP使用”和“网络请求封装二”，这里我将网络请求封装二加以调运说明，这里面还分装了具有分页功能的xlistView
### 2.3.1 布局文件
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:gravity="center_horizontal">
    <include
        android:id="@+id/head_layout"
        layout="@layout/head_control_panel"/>
    <qzl.com.basecommon.ui.kotlin.xlistview.XListView
            android:id="@+id/list"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:divider="@color/transparent"
            android:dividerHeight="@dimen/dp_4"
            android:listSelector="@android:color/transparent"
            android:scrollbars="none"/>
</LinearLayout>
```
### 2.3.2 activity中调运
```
package qzl.com.main.exampleactivity

import android.view.View
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_net2.*
import qzl.com.basecommon.base.BaseActivity
import qzl.com.basecommon.ui.kotlin.xlistview.CommonAdapter
import qzl.com.basecommon.ui.kotlin.xlistview.XListViewComm
import qzl.com.main.R
import qzl.com.main.adapter.MainListAdapter
import qzl.com.main.domain.Net2Bean
import qzl.com.main.domain.Result
import qzl.com.main.mvp.widget.NetsItemView
import qzl.com.tools.utils.TimeHelper

class Net2Activity: BaseActivity(), XListViewComm.AdapterParaIntf<Result,NetsItemView> {

    private var mXListViewComm: XListViewComm<Result,NetsItemView>? = null
    private var conditionMap = HashMap<String, String>()
    override fun getLayoutId(): Int {
        return R.layout.activity_net2
    }

    override fun initView() {
        initHead(R.id.head_layout,"网络请求封装二", View.OnClickListener { finishWithAnimation() })
        mXListViewComm = XListViewComm(this, list, "getQslList", true, this)
    }

    override fun initData() {
        conditionMap["state"] = "1"
        conditionMap["areaCode"] = "620000000000"
        conditionMap["startTime"] = TimeHelper.currentYear + "-01-01"
        conditionMap["endTime"] = TimeHelper.addDay(TimeHelper.currentDate, 1)?:""
        mXListViewComm?.getListByCondition(conditionMap)
    }
    override fun getParaAdapter(result: String?, type: Int): CommonAdapter<Result, NetsItemView> {
        val net2Bean = Gson().fromJson(result, Net2Bean::class.java)
        return MainListAdapter(net2Bean.data.result)
    }
}

```
### 2.3.3 XListViewComm 封装代码
```
package qzl.com.basecommon.ui.kotlin.xlistview

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

```
### 2.3.4 CommonXListBean代码
```
package qzl.com.basecommon.net.domain

/**
 * @desc 请求结果返回值公共解析bean
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-05-27 14:00
 * @class QGMultipleModelFrame
 * @package qzl.com.basecommon.net2.domain
 */
data class CommonXListBean(
    val `data`: Data
)

data class Data(
    val result: Any,
    val sql: String,
    val totalPage: Int,
    val totalRow: Int
)
```
### 2.3.5 view 代码 Net2ItemView
```
package qzl.com.main.mvp.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.item_home.view.*
import qzl.com.main.R
import qzl.com.main.domain.Result

/**
 * @desc 首页条目view
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-01 17:03
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.widget
 */
class Net2ItemView:RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    /**
     * 初始化方法
     */
    init {
        //最后一个参数写为this后，不需要在使用addview添加view了
        View.inflate(context, R.layout.item_home,this)
    }

    /**
     * 刷新条目view数据
     */
    fun setData(data: Result) {
        //歌曲名称
        title.text = data.RIVER_NAME
        //简介
        desc.text = data.ADDRESS
        //背景图片
//        GlideUtils.loadImgAnim(context,bg,data.posterPic?:"")
    }
}
```
### 2.3.6 实体类Net2Bean代码形式
```
package qzl.com.main.domain

/**
 * @desc
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-05-27 15:25
 * @class QGMultipleModelFrame
 * @package qzl.com.main.domain
 */
data class Net2Bean(
    val `data`: Data
)

data class Data(
    val result: List<Result>,
    val sql: String,
    val totalPage: Int,
    val totalRow: Int
)

data class Result(
    val PK_ID: String,
    ...
)
```
### 2.3.7 返回结果限制
由于在xlistcommon当中封装了分页，所以需要获取总请求总数，因此，对返回结果有一定的限制，后面使用中可按需设置
```
{"data":{"totalRow":2510,"sql":"SELECT * FROM bas_clear_chaos WHERE STATE = ?  AND CREATE_TIME BETWEEN '2019-01-01' and '2019-05-28' AND AREA_CODE LIKE '62%' ORDER BY CREATE_TIME DESC ","result":[{"PK_ID":"0d375b7e711a4802bfe7ed7aa3de3610",...}],"totalPage":168}}
```
