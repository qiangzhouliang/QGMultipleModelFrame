package com.qzl.lzshzz.ucenter.controller

import com.qzl.lzshzz.api.ucenter.SysRoleAcctRelControllerApi
import com.qzl.lzshzz.model.ucenter.SysRoleAcctRel
import com.qzl.lzshzz.ucenter.service.SysRoleAcctRelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * @author 强周亮
 * @desc 河长信息controller
 * @email 2538096489@qq.com
 * @time 2019/11/7 17:21
 */
@RestController
class SysRoleAcctRelController : SysRoleAcctRelControllerApi {
    @Autowired
    lateinit var sysRoleAcctRelService: SysRoleAcctRelService

    //根据登录账户信息 查询用户角色信息
    @PreAuthorize("hasAnyAuthority('app')")
    @GetMapping("/getSysRoleInfo")
    override fun getSysRoleInfo(@RequestParam("userAcctId") userAcctId: String): List<SysRoleAcctRel>? {
        return sysRoleAcctRelService.getRoleInfo(userAcctId)
    }
}