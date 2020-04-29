package com.qzl.lzshzz.ucenter.dao

import com.qzl.lzshzz.model.ucenter.BasFileHead
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author 强周亮
 * @desc 河长头像操作类
 * @email 2538096489@qq.com
 * @time 2019/11/7 16:36
 */
interface BasFileHeadDao : JpaRepository<BasFileHead, String> {
    //根据河长id查询河长头像
    fun findByBusId(riverHeadId: String): List<BasFileHead>
}
