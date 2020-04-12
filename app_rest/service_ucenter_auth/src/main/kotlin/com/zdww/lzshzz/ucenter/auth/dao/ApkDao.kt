package com.zdww.lzshzz.ucenter.auth.dao

import com.zdww.lzshzz.model.auth.Apk
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

/**
 * @author 强周亮
 * @desc 日志操作类
 * @email 2538096489@qq.com
 * @time 2019/11/7 11:19
 */
interface ApkDao : JpaRepository<Apk, String> {
    @Query(" from Apk where updateTime = (select max(updateTime) from Apk where pubLish = '1' and appFlag = ?1)")
    fun getApkInfo(appFlag:String):Apk?
}
