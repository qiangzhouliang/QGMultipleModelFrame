package com.zdww.login.presenter

import android.content.Context
import com.zdww.login.view.JiaoYanCheckNumView
import qzl.com.basecommon.common.ConstantUrl
import qzl.com.basecommon.net.base.BasPresenter
import qzl.com.basecommon.net.net.BaseRequest
import qzl.com.basecommon.net.net.MRequest
import qzl.com.basecommon.net.net.ResponseHandler
import qzl.com.model.login.CheckNumModel
import qzl.com.tools.utils.MyLogUtils
import utilclass.Tt

/**
 * @author 强周亮(qiangzhouliang)
 * @desc 校验验证码
 * @email 2538096489@qq.com
 * @time 2019/10/31 16:31
 */
class CheckNumPresenterImpl(var mContext: Context?, var checkView: JiaoYanCheckNumView<CheckNumModel>?) : BasPresenter,
    ResponseHandler<CheckNumModel> {
    override fun OnSuccess(type: Int?, result: CheckNumModel) {
        checkView?.onJiaoYanmSuccess(result)
    }

    /**
     * 解绑view和presenter
     */
    override fun destroyView(){
        checkView?.let {
            checkView = null
        }
    }
    /**
     * 初始化数据或刷新数据
     */
    override fun loadDatas(map:HashMap<String,String?>?) {
        //1定义一个request+执行
        val mRequest = MRequest(mContext,
            url = ConstantUrl.Auth.CHECK_NUM,handler = this,
            reqMap = map,turnsType = CheckNumModel::class.java)
        BaseRequest<CheckNumModel>(1).getRequest(mRequest)
    }

    /**
     * 加载数据失败
     */
    override fun onError(type:Int?,msg: String?) {
        MyLogUtils.e(msg?:"验证码发送失败")
        Tt.showShort("验证码发送失败")
    }

}