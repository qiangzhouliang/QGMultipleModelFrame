package com.qzl.lzshzz.api.ucenter

import com.qzl.lzshzz.model.auth.request.ChangePasswordRequest
import com.qzl.lzshzz.model.sys.SysDistrict
import com.qzl.lzshzz.model.ucenter.SysAccount
import com.qzl.lzshzz.model.ucenter.ext.SysAccountExt
import com.qzl.lzshzz.model.ucenter.request.GetDistrictInfoRequest
import com.qzl.lzshzz.model.ucenter.response.ChangePassWordNumResult
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation

/**
 * @author 强周亮
 * @desc 用户中心接口
 * @email 2538096489@qq.com
 * @time 2019/8/20 17:06
 */
@Api(value = "用户中心", description = "用户中心管理")
interface UcenterControllerApi {
    @ApiOperation(value = "根据用户账号查询用户信息")
    fun getUserext(username: String, password: String): List<SysAccountExt>?

    @ApiOperation(value = "根据用户账号和手机号码查询用户信息")
    fun getUserextByPhone(username: String, phone: String): List<SysAccountExt>?

    @ApiOperation("修改密码")
    fun changePwd(changePasswordRequest: ChangePasswordRequest): ChangePassWordNumResult?

    @ApiOperation("保存用户信息")
    fun saveSysAccount(sysAccount: SysAccount): SysAccount?

    @ApiOperation("查询用户信息")
    fun getAccountInfo(userAcctId: String): SysAccount?

    @ApiOperation(value = "获取行政区划信息")
    fun getDistrictInfo(request : GetDistrictInfoRequest): SysDistrict?
}
