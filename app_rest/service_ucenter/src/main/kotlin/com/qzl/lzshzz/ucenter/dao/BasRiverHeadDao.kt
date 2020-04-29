package com.qzl.lzshzz.ucenter.dao

import com.qzl.lzshzz.model.ucenter.BasRiverHead
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

/**
 * @author 强周亮
 * @desc 河长操作类
 * @email 2538096489@qq.com
 * @time 2019/11/7 16:36
 */
interface BasRiverHeadDao : JpaRepository<BasRiverHead, String>, JpaSpecificationExecutor<BasRiverHead> {
    //根据登录账户的主键查询河长信息
    fun findByAccountId(userAcctId: String): BasRiverHead?

    //根据河长级别和区划查询河长数据
    fun findByAreaCodeAndHeadLevelLike(areaCode:String,headLevel:String):List<BasRiverHead>?
}
