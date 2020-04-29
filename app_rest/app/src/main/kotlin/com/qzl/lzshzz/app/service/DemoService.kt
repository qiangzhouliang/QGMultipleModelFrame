package com.qzl.lzshzz.app.service

import com.qzl.lzshzz.model.ucenter.SysAccount
import org.springframework.stereotype.Service
import com.qzl.lzshzz.app.mapper.DemoMapper
import org.springframework.beans.factory.annotation.Autowired
import com.github.pagehelper.PageInfo
import com.github.pagehelper.PageHelper
import com.qzl.lzshzz.common.model.response.CommonCode
import com.qzl.lzshzz.common.model.response.GetPageInfoResponse
import com.qzl.lzshzz.common.model.response.ResultCode
import com.qzl.lzshzz.common.web.PageUtil
import com.qzl.lzshzz.common.web.SetDataListener

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