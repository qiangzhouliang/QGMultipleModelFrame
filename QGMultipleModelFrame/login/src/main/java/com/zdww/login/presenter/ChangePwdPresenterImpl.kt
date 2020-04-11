package com.zdww.login.presenter

import android.content.Context
import qzl.com.basecommon.common.ConstantUrl
import qzl.com.basecommon.net.base.BasPresenter
import qzl.com.basecommon.net.base.BaseView
import qzl.com.basecommon.net.net.BaseRequest
import qzl.com.basecommon.net.net.MRequest
import qzl.com.basecommon.net.net.ResponseHandler
import qzl.com.model.common.CommonModel

/**
 * @author 强周亮(qiangzhouliang)
 * @desc 修改密码 - 助手类
 * @email 2538096489@qq.com
 * @time 2019/10/31 16:31
 */
class ChangePwdPresenterImpl(var mContext: Context?, var view: BaseView<CommonModel>?) : BasPresenter,
    ResponseHandler<CommonModel> {
    override fun OnSuccess(type: Int?, result: CommonModel) {
        view?.loadSuccess(result)
    }

    /**
     * 解绑view和presenter
     */
    override fun destroyView(){
        view?.let {
            view = null
        }
    }
    /**
     * 初始化数据或刷新数据
     */
    override fun loadDatas(map:HashMap<String,String?>?) {
        //1定义一个request+执行
        val mRequest = MRequest(mContext, url = ConstantUrl.Ucenter.CHENGER_PWD,handler = this,
            reqMap = map,turnsType = CommonModel::class.java)
        BaseRequest<CommonModel>().postRequest(mRequest)
    }

    /**
     * 加载数据失败
     */
    override fun onError(type:Int?,msg: String?) {
        view?.onError(msg)
    }

}