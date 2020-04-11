package qzl.com.basecommon.arouter

import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter

/**
 * @desc
 * @author qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2019/12/5 19:36
 * @class lzshzz_android
 * @package com.zdww.basecommon.arouter
 */
object ARouterUtil {
    /**
     * @desc 别的地方可以用，不需要关闭activity
     * @author 强周亮
     * @time 2019/12/6 10:30
     */
    fun arouterToAct(context:Context?,routPath:String){
        ARouter.getInstance().build(routPath).navigation(context)
    }

}