package com.qzl.lzshzz.ucenter.dao

import com.qzl.lzshzz.model.sys.SysDistrict
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

/**
 * @author 强周亮
 * @desc 行政区划dao
 * @email 2538096489@qq.com
 * @time 2019/11/7 16:36
 */
interface SysDistrictDao : JpaRepository<SysDistrict, String>, JpaSpecificationExecutor<SysDistrict> {
    fun findAreaCodeByResPartIdOrResIdOrderByResIdAsc(resPartId:String,resId:String):List<SysDistrict>
    //获取父级区划的下级区划
    fun findByResPartId(partId:String): List<SysDistrict>
}
