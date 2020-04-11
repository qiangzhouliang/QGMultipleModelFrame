package com.zdww.login.presenter

import android.content.Context
import qzl.com.basecommon.net.base.BasPresenter
import qzl.com.basecommon.net.base.BaseView
import qzl.com.basecommon.net.net.BaseRequest
import qzl.com.basecommon.net.net.MRequest
import qzl.com.basecommon.net.net.ResponseHandler
import qzl.com.model.login.LoginModel

/**
 * @author 强周亮(qiangzhouliang)
 * @desc 登录处理类
 * @email 2538096489@qq.com
 * @time 2019/10/31 16:31
 */
class TestPresenterImpl(var mContext: Context?, var loginView: BaseView<LoginModel>?) : BasPresenter,
    ResponseHandler<LoginModel> {
    override fun OnSuccess(type: Int?, result: LoginModel) {
        loginView?.loadSuccess(result)
    }

    /**
     * 解绑view和presenter
     */
    override fun destroyView(){
        loginView?.let {
            loginView = null
        }
    }
    /**
     * 初始化数据或刷新数据
     */
    override fun loadDatas(map:HashMap<String,String?>?) {
        //1定义一个request+执行
        val mRequest = MRequest(mContext, url = "app/testK",handler = this,
            reqMap = map,turnsType = LoginModel::class.java)
        BaseRequest<LoginModel>(0).getRequest(mRequest)
    }

    /**
     * 加载数据失败
     */
    override fun onError(type:Int?,msg: String?) {
        loginView?.onError(msg)
    }

}