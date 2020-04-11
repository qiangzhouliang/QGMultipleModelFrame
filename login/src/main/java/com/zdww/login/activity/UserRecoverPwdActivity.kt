package com.zdww.login.activity

import android.view.View
import com.zdww.login.R
import com.zdww.login.presenter.CheckNumPresenterImpl
import com.zdww.login.presenter.GetCheckedNumPresenterImpl
import com.zdww.login.view.CheckNumView
import com.zdww.login.view.JiaoYanCheckNumView
import kotlinx.android.synthetic.main.p_user_recover_pwd.*
import org.jetbrains.anko.startActivity
import qzl.com.basecommon.base.BaseActivity
import qzl.com.model.login.CheckNumModel
import qzl.com.tools.utils.NumUtil
import qzl.com.tools.utils.StringHelper
import qzl.com.tools.utils.ThreadUtil
import qzl.com.tools.utils.Timer
import utilclass.Tt

/**
 * @author 强周亮(Qzl)
 * @desc 找回密码
 * @email 2538096489@qq.com
 * @time 2019-01-18 17:19
 * @class UserRecoverPwdActivity
 * @package com.gsww.hzz.loginmodule.activity
 */
class UserRecoverPwdActivity : BaseActivity(), View.OnClickListener, Timer.TimeIntf,
    CheckNumView<CheckNumModel>, JiaoYanCheckNumView<CheckNumModel> {

    private var countDown: Double = 0.toDouble()
    //姓名
    private var userName: String? = null
    //手机
    private var userTelephone: String? = null

    val getCheckNumPresenter by lazy { GetCheckedNumPresenterImpl(this, this) }
    val checkNumPresenter by lazy { CheckNumPresenterImpl(this,this) }
    override fun getLayoutId(): Int {
        return R.layout.p_user_recover_pwd
    }


    override fun initView() {
        //初始化头
        initHead(R.id.head_layout, "重置密码", View.OnClickListener {
            Timer().stop()
            finishWithAnimation()
        })
    }

    override fun initListener() {
        Timer.TimeIntfUtil.setTimeIntf(this)
        //获取短信验证码
        user_recover_get_veri.setOnClickListener(this)
        next_btn.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        userName = user_recover_name.text.toString().trim { it <= ' ' }
        userTelephone = user_recover_telephone.text.toString().trim { it <= ' ' }
        when (v.id) {
            R.id.user_recover_get_veri -> when {
                !StringHelper.isEmptyString(userName) && !StringHelper.isEmptyString(userTelephone) -> {
                    val map = HashMap<String, String?>()
                    map["user"] = userName?.replace("'", "")?:""
                    map["tel"] =  user_recover_telephone.text.toString().trim { it <= ' ' }
                    getCheckNumPresenter.loadDatas(map)
                }
                else -> Tt.showShort("请输入用户名或电话号码")
            }
            R.id.next_btn -> when {
                StringHelper.isEmptyString(userName) && StringHelper.isEmptyString(userTelephone) -> {
                    Tt.showShort("请输入用户名或电话号码")
                    return
                }
                StringHelper.isEmptyString(user_recover_safe_code.text.toString()) -> {
                    Tt.showShort("验证码不能为空")
                    return
                }
                countDown <= 0 -> {
                    Tt.showShort("验证码已过期，请重新获取!")
                    return
                }
                else -> {
                    //校验验证码
                    val map = HashMap<String, String?>()
                    map["checkNum"] = user_recover_safe_code.text.toString()
                    map["tel"] =  user_recover_telephone.text.toString().trim { it <= ' ' }
                    checkNumPresenter.loadDatas(map)
                }
            }
        }
    }

    override fun setIntervalTime(time: Double) {
        ThreadUtil.runOnMainThread(Runnable {
            user_recover_get_veri?.let {
                countDown = time
                when {
                    time > 0 -> user_recover_get_veri.text = NumUtil.doubleToIntStr(countDown) + "(s)"
                    else -> {
                        user_recover_get_veri.isEnabled = true
                        user_recover_get_veri.text = "重新获取验证码"
                    }
                }
            }
        })
    }

    /**
     * @desc 获取验证码成功后的回调
     * @author 强周亮
     * @time 2019/11/5 11:53
     */
    override fun onSuccess(list: CheckNumModel?) {
        if (list?.success == true){
            user_recover_get_veri.isEnabled = false
            Timer().checkNumTimer(list.yxq)
            Tt.showShort(list.message)
        } else {
            Tt.showShort(list?.message)
        }
    }
    //校验验证吗后的结果
    override fun onJiaoYanmSuccess(list: CheckNumModel?) {
        if (list?.success == true){
            startActivity<MenuModifyPwdActivity>(
                "userName" to userName,
                "userTelephone" to userTelephone,
                "isForgetPassword" to true   ////是否是忘记密码  true 是 false 否
            )
            finishWithAnimation()
        } else {
            Tt.showShort(list?.message)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        Timer().destory()
    }
}
