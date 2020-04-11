package com.zdww.login.ext.activity

import android.text.InputType
import android.view.View
import android.view.inputmethod.EditorInfo
import com.zdww.login.R
import com.zdww.login.activity.LoginActivity
import kotlinx.android.synthetic.main.p_login.*
import qzl.com.basecommon.arouter.ARouterPath
import qzl.com.basecommon.common.Constant
import qzl.com.basecommon.common.SysAccount
import qzl.com.model.login.LoginModel
import qzl.com.tools.utils.DeviceUtils
import qzl.com.tools.utils.MD5
import qzl.com.tools.utils.StringHelper
import utilclass.PrefUtils
import utilclass.Tt

/**
 * @desc 登录activity扩展
 * @author qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2019/10/31 16:16
 * @class lzshzz_android
 * @package com.zdww.loginmodel.ext
 */
var map = HashMap<String, String?>()
object LoginActivityExt {
    //登录方法
    fun LoginActivity.login() {
        map["user"] = StringHelper.toString(login_user_name.text.toString()).trim { it <= ' ' }
        map["deviceId"] = DeviceUtils.getUniqueId(applicationContext)
        map["loginType"] = getString(R.string.android_login)
        if (ll_get_check_parent.visibility == View.VISIBLE) {
            //1 表示用用户名手机号登陆
            map["phoneLoginFlag"] = "1"
            map["userMd5"] = login_user_password.text.toString().trim { it <= ' ' }
            map["checkNum"] = login_check.text.toString().trim { it <= ' ' }
            map["checkNumFlag"] = "1"
        } else {
            //0 表示用账号密码登录
            map["phoneLoginFlag"] = "0"
            if (isUserCachePass){
                map["userMd5"] = SysAccount.getUserInfo(this)?.loginPassword?:""
            } else {
                map["userMd5"] =
                    MD5().getMD5ofStr(login_user_password.text.toString().trim { it <= ' ' })
            }
        }
        presenter.loadDatas(map)
    }
    /**
     * @desc 电话号码登录
     * @author 强周亮
     * @time 2019-01-18 11:32
     */
    fun LoginActivity.phoneLogin() {
        isPhoneLogin = false
        val drawable = resources.getDrawable(R.drawable.icon_login_phone)
        drawable.setBounds(0, 0, 50, 50)
        login_user_password?.inputType = EditorInfo.TYPE_CLASS_NUMBER or EditorInfo.TYPE_NUMBER_FLAG_DECIMAL
        login_user_password?.hint = "请输入电话号码"
        login_user_password?.setCompoundDrawables(drawable, null, null, null)
        login_user_password?.setText("")
        login_phone_num?.text = "用户名密码登录"
        ll_get_check_parent?.visibility = View.VISIBLE
    }
    //用户名密码登录
    fun LoginActivity.userNamePasswordLogin() {
        isPhoneLogin = true
        val drawable = resources.getDrawable(R.drawable.icon_pass)
        drawable.setBounds(0, 0, 50, 50)
        login_user_password?.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        login_user_password?.hint = "请输入密码"
        login_user_password?.setCompoundDrawables(drawable, null, null, null)
        login_user_password?.setText("")
        login_phone_num?.text = "动态密码登录"
        ll_get_check_parent?.visibility = View.GONE
    }
    //初始化输入框图标
    fun LoginActivity.initIcon(){
        val iconPass = resources.getDrawable(R.drawable.icon_pass)
        iconPass.setBounds(0, 0, 50, 50)
        login_user_password.setCompoundDrawables(iconPass, null, null, null)
        val iconAccount = resources.getDrawable(R.drawable.icon_account)
        iconAccount.setBounds(0, 0, 50, 50)
        login_user_name.setCompoundDrawables(iconAccount, null, null, null)
        val iconCheckBox = resources.getDrawable(R.drawable.check_box_selector)
        iconCheckBox.setBounds(0, 0, 50, 50)
        login_remember_pass.setCompoundDrawables(iconCheckBox, null, null, null)
    }

    //请求到登录结果后的处理
    fun LoginActivity.analysisResult(list: LoginModel?) = if (list?.success == true){
        // 存储token信息
        PrefUtils.setString(this, Constant.TOKEN,list.data.token)
        //存储jwtToken
        val jwtToken1 = list.data.jwtToken
        PrefUtils.setString(this,Constant.jwtToken,jwtToken1)
        //存储刷新token
        PrefUtils.setString(this,Constant.refreshToken,list.data.refreshToken)
        //缓存是否记住密码
        PrefUtils.setBoolean(this,Constant.isAutoLogin,login_remember_pass.isChecked)
        //缓存账号密码登录 还是账号电话号码登录
        when (ll_get_check_parent?.visibility) {
            View.VISIBLE -> //1 表示用用户名手机号登陆
                PrefUtils.setString(this,Constant.isPhoneLogin,"1")
            else -> //0 表示用账号密码登录
                PrefUtils.setString(this,Constant.isPhoneLogin,"0")
        }
        //表示登录成功
        getUserInfoPres.loadDatas(map)
    } else {
        //表示是设备id更新不成功导致的问题，这边目前不做处理
        when (list?.code) {
            23012 -> startActivityArouter(ARouterPath.Home.HOME_ACTIVITY,true)
            10004 -> Tt.showShort(list.checkTip)
            else -> Tt.showShort(list?.message)
        }
    }
}