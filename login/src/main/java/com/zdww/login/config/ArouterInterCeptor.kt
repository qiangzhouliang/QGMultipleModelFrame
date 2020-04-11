package com.zdww.login.config

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.zdww.login.R
import qzl.com.tools.utils.LogUtils

/**
 * @desc 路由跳转拦截器
 * @author qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2019/12/5 18:25
 */
@Interceptor(priority = 0)
class ArouterInterCeptor: IInterceptor {
    private var mContext: Context? = null
    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        //添加进入退出动画 右进左出
        postcard?.withTransition(R.anim.push_right_in, R.anim.default_anim)
        callback?.onContinue(postcard)
    }

    override fun init(context: Context?) {
        mContext = context
        LogUtils.d("路由跳转拦截器初始化")
    }
}