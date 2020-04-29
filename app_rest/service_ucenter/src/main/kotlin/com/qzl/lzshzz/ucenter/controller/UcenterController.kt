package com.qzl.lzshzz.ucenter.controller

import com.qzl.lzshzz.api.ucenter.UcenterControllerApi
import com.qzl.lzshzz.common.exception.ExceptionCast
import com.qzl.lzshzz.model.auth.request.ChangePasswordRequest
import com.qzl.lzshzz.model.sys.SysDistrict
import com.qzl.lzshzz.model.ucenter.SysAccount
import com.qzl.lzshzz.model.ucenter.ext.SysAccountExt
import com.qzl.lzshzz.model.ucenter.request.GetDistrictInfoRequest
import com.qzl.lzshzz.model.ucenter.response.ChangePassWordNumResult
import com.qzl.lzshzz.model.ucenter.response.UcenterCode
import com.qzl.lzshzz.model.ucenter.response.auth.AuthCode
import com.qzl.lzshzz.ucenter.service.UserService
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

/**
 * @author
 * @version 1.0
 */
@RestController
class UcenterController : UcenterControllerApi {

    @Autowired
    lateinit var userService: UserService

    //获取用户信息
    @GetMapping("/getuserext")
    override fun getUserext(@RequestParam("username") username: String, @RequestParam("password") password: String): List<SysAccountExt>? {
        return userService.getUserExt(username, password)
    }

    //通过用户名和手机号获取用户信息
    @GetMapping("/getUserextByPhone")
    override fun getUserextByPhone(@RequestParam("username") username: String, @RequestParam("password") phone: String): List<SysAccountExt>? {
        return userService.getUserExtByPhone(username, phone)
    }

    //通过用户id查询用户信息
    @GetMapping("/getuserAcctId")
    override fun getAccountInfo(@RequestParam("userAcctId") userAcctId: String): SysAccount? {
        return userService.getUserInfoByAcctId(userAcctId)
    }

    //获取行政区划信息
    @PostMapping("getDistrictInfo")
    override fun getDistrictInfo(@RequestBody request: GetDistrictInfoRequest): SysDistrict? {
        return userService.getDistrictInfo(request)
    }

    /**
     * 修改密码
     */
    @PostMapping("/changePwd")
    override fun changePwd(@RequestBody changePasswordRequest: ChangePasswordRequest): ChangePassWordNumResult? {
        val userExtByPhone = if ("1" == changePasswordRequest.phoneLoginFlag) {
            //账号 手机号登录修改
            userService.getUserExtByPhone(changePasswordRequest.userName, changePasswordRequest.oldPwd)
        } else {
            // 账号密码登录修改
            userService.getUserExt(changePasswordRequest.userName, changePasswordRequest.oldPwd)
        }

        if (userExtByPhone?.isEmpty() == true) {
            //没有查到账号 - 提示账号或密码错误，修改不成功
            ExceptionCast.cast(UcenterCode.UCENTER_CREDENTIAL_ERROR)
        }

        if (userExtByPhone!!.size > 1) {
            ExceptionCast.cast(AuthCode.AUTH_ACCOUNT_EXISTS_MORE)
        }

        val sysAccount = SysAccount()
        //将sysAccountExt的内容拷贝给sysAccount
        BeanUtils.copyProperties(userExtByPhone[0], sysAccount)
        sysAccount.loginPassword = changePasswordRequest.newPwd
        val saveUserInfo = userService.saveUserInfo(sysAccount)
        return if (saveUserInfo != null) {
            //更新成功
            ChangePassWordNumResult(UcenterCode.UCENTER_CHANGE_PWD_SUCCESS)
        } else {
            // 更新失败
            ChangePassWordNumResult(UcenterCode.UCENTER_CHANGE_PWD_FAIL)
        }
    }

    /**
     * @Author 强周亮
     * @Description 保存用户信息
     * @Date 16:08 2019/11/6
     **/
    @PostMapping("/saveSysAccount")
    override fun saveSysAccount(@RequestBody sysAccount: SysAccount): SysAccount? {
        return userService.saveUserInfo(sysAccount)
    }
}
