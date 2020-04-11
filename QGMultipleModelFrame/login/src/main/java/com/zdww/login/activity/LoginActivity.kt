package com.zdww.login.activity

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.wifi.WifiManager
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.zdww.login.R
import com.zdww.login.ext.activity.LoginActivityExt.analysisResult
import com.zdww.login.ext.activity.LoginActivityExt.initIcon
import com.zdww.login.ext.activity.LoginActivityExt.login
import com.zdww.login.ext.activity.LoginActivityExt.phoneLogin
import com.zdww.login.ext.activity.LoginActivityExt.userNamePasswordLogin
import com.zdww.login.presenter.GetCheckedNumPresenterImpl
import com.zdww.login.presenter.GetUserInfoPresenterImpl
import com.zdww.login.presenter.LoginPresenterImpl
import com.zdww.login.view.CheckNumView
import kotlinx.android.synthetic.main.p_login.*
import org.jetbrains.anko.startActivityForResult
import qzl.com.basecommon.arouter.ARouterPath
import qzl.com.basecommon.base.BaseActivity
import qzl.com.basecommon.common.CheckVersion
import qzl.com.basecommon.common.Constant
import qzl.com.basecommon.common.SysAccount
import qzl.com.basecommon.net.base.BaseView
import qzl.com.basecommon.ui.kotlin.DialogPanel
import qzl.com.model.login.CheckNumModel
import qzl.com.model.login.LoginModel
import qzl.com.tools.network.NetworkUtil
import qzl.com.tools.operate.CompleteQuit
import qzl.com.tools.thread.ThreadPoolProxyFactory
import qzl.com.tools.utils.*
import utilclass.PrefUtils
import utilclass.Tt

@Route(path = ARouterPath.Login.LOGIN)
class LoginActivity : BaseActivity(), View.OnClickListener, Timer.TimeIntf, BaseView<LoginModel>,CheckNumView<CheckNumModel> {

    private var builder: DialogPanel.Builder? = null
    private var checkVersion: CheckVersion? = null
    /**
     * 是否是短信验证登录  true 账号密码   false 短信
     */
    var isPhoneLogin: Boolean = false
    private var countDown: Double = 0.toDouble()
    val presenter by lazy { LoginPresenterImpl(this, this) }
    val getCheckNumPresenter by lazy { GetCheckedNumPresenterImpl(this, this) }
    val getUserInfoPres by lazy { GetUserInfoPresenterImpl(this) }
    //是否使用缓存密码，true 使用缓存密码，false 不使用缓存密码
    var isUserCachePass:Boolean = true
    override fun getLayoutId(): Int { return R.layout.p_login }

    override fun initView() {
        //初始化输入框图标
        initIcon()
        isPhoneLogin = true
        checkVersion = CheckVersion(this)
        //下线通知
        when (intent.getStringExtra("msg")){
            "offline" -> {
                builder = DialogPanel.Builder(this)
                builder?.setMessage("你的账号在另一台设备登录，请重新登录！")
                builder?.setTitle("提示")
                builder?.setPositiveButton("确定", DialogInterface.OnClickListener { dialog, which -> dialog?.dismiss() })
                builder?.createDialog()?.show()
            }
        }
        checkVersion?.setCheckVersionCustomHandle(object:CheckVersion.CheckVersionCustomHandle {
            override fun customHandle(state: Int) {
                when (state) {
                    0 -> when {
                        ll_get_check_parent?.visibility == View.VISIBLE -> if (StringHelper.isEmptyString(login_check?.text.toString())) {
                            Tt.showShort("验证码不能为空")
                        } else {
                            when {
                                countDown <= 0 -> Tt.showShort("验证码已过期，请重新获取！")
                                else -> {
                                    //登录
                                    login()
                                }
                            }
                        }
                        else -> {
                            //登录
                            login()
                        }
                    }
                    else -> checkVersion?.handleByCheckState(state)
                }
            }
        })
        login_remember_pass?.setOnCheckedChangeListener { buttonView, isChecked -> PrefUtils.setBoolean(this@LoginActivity, Constant.isAutoLogin, isChecked) }
        login_user_name?.setText(SysAccount.getUserInfo(this)?.loginAccount)
        when {
            PrefUtils.getBoolean(this, Constant.isAutoLogin, true) -> {
                login_remember_pass?.isChecked = true
                var loginPassword = SysAccount.getUserInfo(this)?.loginPassword
                if (loginPassword?.length?:0 >= 32){
                    loginPassword = loginPassword?.substring(0,12)
                }
                login_user_password?.setText(loginPassword)
            }
            else -> login_remember_pass?.isChecked = false
        }
    }
    override fun initListener() {
        Timer.TimeIntfUtil.setTimeIntf(this)
        login_forget_pass?.setOnClickListener(this)
        btn_login?.setOnClickListener(this)
        login_phone_num?.setOnClickListener(this)
        tv_get_check_num?.setOnClickListener(this)
        iv_user_tip.setOnClickListener(this)
        login_user_password.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isUserCachePass = false
            }
        })
    }
    override fun onClick(v: View) {
        when (v.id){
            R.id.btn_login -> //判断网络弹出框
                when {
                    NetworkUtil.getNetworkState(this) == 0 -> {

                        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                        val customLayout = inflater.inflate(R.layout.app_network_ability_dialog, null)
                        builder = DialogPanel.Builder(this)
                        builder?.setTitle(getString(R.string.tip))
                        builder?.setCustomView(customLayout)
                        builder?.createDialog()?.show()
                    }
                    else -> when {
                        StringHelper.isEmptyString(login_user_name.text?.toString()) -> {
                            login_user_name.requestFocus()
                            login_user_name.error = "请输入用户名！"
                        }
                        StringHelper.isEmptyString(login_user_password.text.toString()) -> {
                            login_user_password.requestFocus();
                            login_user_password.error = "密码或电话号码不能为空！"
                        }
                        else -> checkVersion?.let {
                            ThreadPoolProxyFactory.normalThreadPoolProxy?.execute(it)
                        }
                    }
                }
            R.id.login_forget_pass -> {
                //忘记密码
                Timer().destory()
                startActivityForResult<UserRecoverPwdActivity>(0)
            }
            R.id.login_phone_num -> when {
                //电话号码登录
                isPhoneLogin -> phoneLogin()
                else -> userNamePasswordLogin()
            }
            R.id.tv_get_check_num -> //获取验证码
                when {
                    !StringHelper.isEmptyString(login_user_password?.text.toString()) -> {
                        val map = HashMap<String, String?>()
                        map["tel"] = StringHelper.toString(login_user_password.text.toString()).trim { it <= ' ' }
                        getCheckNumPresenter.loadDatas(map)
                    }
                    else -> Tt.showShort("请填写电话号码！")
                }
            //登录用户名提示信息显示影藏
            R.id.iv_user_tip -> {
                when(tv_user_tip.visibility){
                    View.VISIBLE -> tv_user_tip.visibility = View.GONE
                    View.GONE -> tv_user_tip.visibility = View.VISIBLE
                }
            }
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return when {
            keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN -> {
                CompleteQuit.getInstance()!!.exitAll(true)
                true
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }
    override fun setIntervalTime(time: Double) {
        countDown = time
        when {
            countDown > 0 -> {
                tv_get_check_num?.text = NumUtil.doubleToIntStr(countDown) + " (s)"
                tv_get_check_num?.setOnClickListener(null)
                tv_get_check_num?.setTextColor(resources.getColor(R.color.light_grey))
            }
            else -> {
                tv_get_check_num?.text = "重新发送"
                tv_get_check_num?.setOnClickListener(this)
                tv_get_check_num?.setTextColor(resources.getColor(R.color.gray))
            }
        }
    }

    //登录成功之后请求到的数据
    override fun loadSuccess(result: LoginModel?) {
        Timer().destory()
        analysisResult(result)
    }

    override fun onError(message: String?) {
        ThreadUtil.runOnMainThread(Runnable {
            MyLogUtils.e(message?:"登录失败")
            Tt.showShort("登录失败")
        })
    }

    //获取验证码成功后请求到的数据
    override fun onSuccess(list: CheckNumModel?) {
        if (list?.success == true){
            Tt.showShort("验证码发送成功,请注意查收！")
            Timer().checkNumTimer(list.yxq)
        } else {
            Tt.showShort(list?.message)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Timer().destory()
    }

    //打开wifi
    fun wifiOpen(view: View) {
        //WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        wifiManager.isWifiEnabled = !wifiManager.isWifiEnabled
        builder?.colseDialog()
    }

    /**
     * @desc 打开系统设置
     * @author 强周亮(Qzl)
     * @time 2018-09-19 14:32
     */
    fun onSetting(view: View) {
        val intent = Intent(Settings.ACTION_SETTINGS)
        builder?.colseDialog()
        startActivityForResult(intent, 0)
    }

    /**
     * @desc 取消
     * @author 强周亮(Qzl)
     * @time 2018-09-19 14:32
     */
    fun onCancel(view: View) {
        builder?.colseDialog()
    }
}
