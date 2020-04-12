package com.zdww.lzshzz.app.service

import com.zdww.lzshzz.model.ucenter.SysAccount
import org.springframework.stereotype.Service
import com.zdww.lzshzz.app.mapper.DemoMapper
import org.springframework.beans.factory.annotation.Autowired
import com.github.pagehelper.PageInfo
import com.github.pagehelper.PageHelper
import com.zdww.lzshzz.common.model.response.CommonCode
import com.zdww.lzshzz.common.model.response.GetPageInfoResponse
import com.zdww.lzshzz.common.model.response.ResultCode
import com.zdww.lzshzz.common.web.PageUtil
import com.zdww.lzshzz.common.web.SetDataListener

@Service
class DemoService {
    @Autowired
    lateinit var demoMapper: DemoMapper

    fun findSysAccount(id: String): SysAccount {
        val findSysAccount = demoMapper.findSysAccount(id)
        return findSysAccount
    }

    fun findAllSysAccount(): GetPageInfoResponse<SysAccount>? {

        return PageUtil<SysAccount>().getPageDataMoreToable(1, 10, object : SetDataListener<SysAccount> {
            override fun getData(): Pair<List<SysAccount>?, ResultCode> {
                val findAllSysAccount = demoMapper.findAllSysAccount()
                return Pair(findAllSysAccount, CommonCode.SUCCESS)
            }
        })
    }
}