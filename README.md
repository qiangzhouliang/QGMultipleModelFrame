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
