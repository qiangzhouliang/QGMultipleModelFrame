package com.zdww.lzshzz.ucenter.dao

import com.zdww.lzshzz.model.ucenter.SysRoleAcctRel
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author 强周亮
 * @desc 用户角色类
 * @email 2538096489@qq.com
 * @time 2019/11/7 16:36
 */
interface SysRoleAcctRelDao : JpaRepository<SysRoleAcctRel, String> {
    //根据登录账户的主键查询角色信息
    fun findByUserAcctId(userAcctId: String): List<SysRoleAcctRel>?
}
