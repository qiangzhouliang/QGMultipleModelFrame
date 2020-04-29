package com.qzl.lzshzz.ucenter.dao

import com.qzl.lzshzz.model.ucenter.SysAccount
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by Administrator.
 */
interface SysAccountRepository : JpaRepository<SysAccount, String> {
    //根据账号和密码查询用户信息
    fun findByLoginAccountAndLoginPasswordAndUserState(username: String, passWord: String, userState: String): List<SysAccount>

    //根据账号和手机号码查询用户信息
    fun findByLoginAccountAndUserTeleAndUserState(username: String, phone: String, userState: String): List<SysAccount>
}
