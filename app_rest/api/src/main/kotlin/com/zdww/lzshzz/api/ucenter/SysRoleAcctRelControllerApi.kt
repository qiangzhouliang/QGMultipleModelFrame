package com.zdww.lzshzz.api.ucenter

import com.zdww.lzshzz.model.ucenter.SysRoleAcctRel
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation

/**
 * @author 强周亮
 * @desc 用户角色
 * @email 2538096489@qq.com
 * @time 2019/8/20 17:06
 */
@Api(value = "用户角色", description = "用户角色管理")
interface SysRoleAcctRelControllerApi {
    @ApiOperation(value = "根据登录账户信息查询用户角色信息")
    fun getSysRoleInfo(userAcctId: String): List<SysRoleAcctRel>?
}
