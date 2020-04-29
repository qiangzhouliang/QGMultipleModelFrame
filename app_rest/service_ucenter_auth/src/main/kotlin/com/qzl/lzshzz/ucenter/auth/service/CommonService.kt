package com.qzl.lzshzz.ucenter.auth.service

import com.qzl.lzshzz.model.auth.Apk
import com.qzl.lzshzz.model.auth.request.GetApkVersionRequest
import com.qzl.lzshzz.ucenter.auth.dao.ApkDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

/**
 * @author 强周亮
 * @desc 公共service
 * @email 2538096489@qq.com
 * @time 2019/11/29 15:56
 */
@Service
@Transactional(rollbackOn = [Exception::class])
class CommonService {
    @Autowired
    lateinit var apkDao: ApkDao

    fun getApkInfo(request: GetApkVersionRequest): Apk? {
        return apkDao.getApkInfo(request.androidOrIosFlag)
    }
}