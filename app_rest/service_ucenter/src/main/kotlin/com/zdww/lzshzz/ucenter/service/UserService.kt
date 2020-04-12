package com.zdww.lzshzz.ucenter.service

import com.zdww.lzshzz.model.sys.SysDistrict
import com.zdww.lzshzz.model.ucenter.SysAccount
import com.zdww.lzshzz.model.ucenter.ext.SysAccountExt
import com.zdww.lzshzz.model.ucenter.request.GetDistrictInfoRequest
import com.zdww.lzshzz.ucenter.dao.SysAccountRepository
import com.zdww.lzshzz.ucenter.dao.SysDistrictDao
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

/**
 * @author Administrator
 * @version 1.0
 */
@Service
class UserService {

    @Autowired
    lateinit var sysAccountRepository: SysAccountRepository
    @Autowired
    lateinit var sysDistrictDao: SysDistrictDao


    //根据账号查询用户信息
    fun getUserExt(username: String, password: String): List<SysAccountExt>? {
        val sysAccount = sysAccountRepository.findByLoginAccountAndLoginPasswordAndUserState(username, password, "1")
                ?: return null
        //根据账号查询xcUser信息
        return userExtHandler(sysAccount)
    }

    private fun userExtHandler(sysAccount: List<SysAccount>): ArrayList<SysAccountExt> {
        var list = ArrayList<SysAccountExt>()
        for (sa in sysAccount) {
            val sysAccountExt = SysAccountExt()
            //将sysAccount的内容拷贝给sysAccountExt
            BeanUtils.copyProperties(sa, sysAccountExt)
            list.add(sysAccountExt)
        }
        return list
    }

    //根据账号查询用户信息
    fun getUserExtByPhone(username: String, phone: String): List<SysAccountExt>? {
        val sysAccount = sysAccountRepository.findByLoginAccountAndUserTeleAndUserState(username, phone, "1")
                ?: return null
        //根据账号查询xcUser信息
        return userExtHandler(sysAccount)
    }

    //根据账号id查询用户信息
    fun getUserInfoByAcctId(acctId: String): SysAccount? {
        val sysAccount = sysAccountRepository.findByIdOrNull(acctId)
        return sysAccount
    }

    //保存用户信息
    fun saveUserInfo(sysAccount: SysAccount): SysAccount {
        return sysAccountRepository.save(sysAccount)
    }

    /**
     * @Author 强周亮
     * @Description 获取行政区划信息接口
     * @Date 11:50 2019/12/10
     **/
    fun getDistrictInfo(request : GetDistrictInfoRequest): SysDistrict? {
        return sysDistrictDao.findByIdOrNull(request.areaCode)
    }
}
