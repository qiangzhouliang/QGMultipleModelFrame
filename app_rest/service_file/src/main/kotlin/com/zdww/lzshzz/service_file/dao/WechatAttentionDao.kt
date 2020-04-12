package com.zdww.lzshzz.service_file.dao

import com.zdww.lzshzz.model.file.WechatAttentionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

/**
 * @author 强周亮
 * @desc app下载次数操作层
 * @email 2538096489@qq.com
 * @time 2019/11/7 11:19
 */
interface WechatAttentionDao : JpaRepository<WechatAttentionEntity, String> {
    @Query(" from WechatAttentionEntity where updateTime = (select max(updateTime) from WechatAttentionEntity)")
    fun getWechatAttentionInfo():WechatAttentionEntity?
}
