package com.qzl.lzshzz.ucenter.service

import com.qzl.lzshzz.model.ucenter.SysRoleAcctRel
import com.qzl.lzshzz.ucenter.dao.SysRoleAcctRelDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * @author 强周亮
 * @desc 用户角色
 * @email 2538096489@qq.com
 * @time 2019/11/7 17:18
 */
@Service
class SysRoleAcctRelService {
    @Autowired
    private lateinit var sysRoleAcctRelDao: SysRoleAcctRelDao

    //根据登录账户id查询用户角色信息
    @GetMapping("/getRoleInfo")
    fun getRoleInfo(@RequestParam("userAcctId") userAcctId: String): List<SysRoleAcctRel>? {
        return sysRoleAcctRelDao.findByUserAcctId(userAcctId)
    }
}