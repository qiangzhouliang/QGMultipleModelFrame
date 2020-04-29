package com.qzl.lzshzz.app.mapper

import com.qzl.lzshzz.model.ucenter.SysAccount
import org.apache.ibatis.annotations.Mapper

@Mapper
interface DemoMapper {
    fun findSysAccount(userAcctId: String): SysAccount
    fun findAllSysAccount(): List<SysAccount>
}