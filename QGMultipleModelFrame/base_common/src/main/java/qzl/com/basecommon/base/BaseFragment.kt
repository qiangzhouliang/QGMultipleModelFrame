package qzl.com.basecommon.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoLogger
import qzl.com.basecommon.common.Constant
import qzl.com.basecommon.common.SysAccount
import qzl.com.model.user_info.UserInfo
import utilclass.PrefUtils

/**
 * @desc 所有fragment的基类
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-01 10:19
 */
abstract class BaseFragment: androidx.fragment.app.Fragment(),AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    /**
     * fragment初始化
     */
    open protected fun init() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return initView()
    }

    /**
     * 获取布局view
     */
    abstract fun initView():View?

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
        initData()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        //判断是否需要重新加载数据
        if (isVisibleToUser && context != null && PrefUtils.getBoolean(context,
                Constant.IS_TAB_CONTENT,false)){
            initData()
            PrefUtils.setBoolean(activity,Constant.IS_TAB_CONTENT,false)
        }
    }
    /**
     * 数据的初始化
     */
    open protected fun initData() {}

    /**
     * adapter 和 listener 的操作
     */
    open protected fun initListener() {}
}