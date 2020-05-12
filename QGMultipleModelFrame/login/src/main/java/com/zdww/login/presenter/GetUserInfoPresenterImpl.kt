package com.zdww.login.presenter

import com.google.gson.Gson
import com.qzl.prefutils.PrefUtils
import com.qzl.toast.MyToast
import com.zdww.login.activity.LoginActivity
import qzl.com.basecommon.arouter.ARouterPath
import qzl.com.basecommon.common.Constant
import qzl.com.basecommon.common.ConstantUrl
import qzl.com.basecommon.common.SysAccount
import qzl.com.basecommon.net.base.BasPresenter
import qzl.com.basecommon.net.net.BaseRequest
import qzl.com.basecommon.net.net.MRequest
import qzl.com.basecommon.net.net.ResponseHandler
import qzl.com.model.login.GetUserInfoModel
import qzl.com.tools.utils.DeviceUtils

/**
 * @author 强周亮(qiangzhouliang)
 * @desc 获取用户信息处理类
 * @email 2538096489@qq.com
 * @time 2019/10/31 16:31
 */
class GetUserInfoPresenterImpl(var mContext: LoginActivity?) : BasPresenter,
    ResponseHandler<GetUserInfoModel> {
    override fun OnSuccess(type: Int?, result: GetUserInfoModel) {
        if (result.success){
            //保存用户信息
            SysAccount.setUserInfo(mContext,Gson().toJson(result.data))
            //记录登陆次数
            var count = PrefUtils.getInt(Constant.LOGIN_COUNT, 0)
            PrefUtils.setInt(Constant.LOGIN_COUNT, ++count)

            //极光推送  为每台移动设备设置别名，实现点对点推送
            val deviceId = DeviceUtils.getUniqueId(mContext)
            //设置riverHeadId为设备的别名
            //跳转到首页
            mContext?.startActivityArouter(ARouterPath.Home.HOME_ACTIVITY,true)
        } else {
            //表示是设备id更新不成功导致的问题，这边目前不做处理
            if (result.code != 23012) {
                MyToast.showShort(result.message)
            } else {
                mContext?.startActivityArouter(ARouterPath.Home.HOME_ACTIVITY,true)
            }
        }
    }

    /**
     * 解绑view和presenter
     */
    override fun destroyView(){
    }
    /**
     * 初始化数据或刷新数据
     */
    override fun loadDatas(map:HashMap<String,String?>?) {
        //1定义一个request+执行
        val mRequest = MRequest(mContext, url = ConstantUrl.Auth.GET_USER_INFO,handler = this,
            reqMap = map,loadMessage = "正在获取登录信息，请稍后......",turnsType = GetUserInfoModel::class.java)
        BaseRequest<GetUserInfoModel>().postRequest(mRequest)
    }

    /**
     * 加载数据失败
     */
    override fun onError(type:Int?,msg: String?) {
        MyToast.showShort("获取数据失败，请重新登录")
    }
}