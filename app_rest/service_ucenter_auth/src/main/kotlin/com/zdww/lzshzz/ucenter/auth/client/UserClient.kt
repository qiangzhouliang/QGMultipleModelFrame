package com.zdww.lzshzz.ucenter.auth.client

import com.zdww.lzshzz.common.client.ServiceList
import com.zdww.lzshzz.model.sys.SysDistrict
import com.zdww.lzshzz.model.ucenter.*
import com.zdww.lzshzz.model.ucenter.ext.SysAccountExt
import com.zdww.lzshzz.model.ucenter.request.GetDistrictInfoRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

/**
 * Created by Administrator.
 */
@FeignClient(value = ServiceList.SERVICE_UCENTER)
interface UserClient {
    //根据账号查询用户信息
    @GetMapping("/ucenter/getuserext")
    fun getUserext(@RequestParam("username") username: String, @RequestParam("password") password: String): List<SysAccountExt>

    //根据账号查询用户信息
    @GetMapping("/ucenter/getUserextByPhone")
    fun getUserextByPhone(@RequestParam("username") username: String, @RequestParam("password") phone: String): List<SysAccountExt>

    //保存用户信息
    @PostMapping("/ucenter/saveSysAccount")
    fun saveSysAccount(@RequestBody sysAccount: SysAccount): SysAccount?

    //通过部门id获取部门信息
    @GetMapping("/ucenter/getDeptInfo")
    fun getDeptInfo(@RequestParam("deptId") deptId: String): SysDepartment?

    //根据登录账户信息 查询河长信息
    @GetMapping("/ucenter/getBaseRiverHead")
    fun getBaseRiverHead(@RequestParam("userAcctId") userAcctId: String): BasRiverHead?

    //根据河长id获取河长头像
    @GetMapping("/ucenter/getHeadImage")
    fun getHeadImage(@RequestParam("riverHeadId") riverHeadId: String): List<BasFileHead>?

    //根据登录账户信息 查询用户角色信息
    @GetMapping("/ucenter/getSysRoleInfo")
    fun getSysRoleInfo(@RequestParam("userAcctId") userAcctId: String): List<SysRoleAcctRel>?

    //根据用户id查询用户信息
    @GetMapping("/ucenter/getuserAcctId")
    fun getAccountInfo(@RequestParam("userAcctId") userAcctId: String): SysAccount?

    @PostMapping("/ucenter/getDistrictInfo")
    fun getDistrictInfo(@RequestBody quest: GetDistrictInfoRequest): SysDistrict?
}
