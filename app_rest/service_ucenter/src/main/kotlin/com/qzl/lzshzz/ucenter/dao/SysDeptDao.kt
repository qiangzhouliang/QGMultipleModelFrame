package com.qzl.lzshzz.ucenter.dao

import com.qzl.lzshzz.model.ucenter.SysDepartment
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author 强周亮
 * @desc 部门实体操作类
 * @email 2538096489@qq.com
 * @time 2019/11/7 16:36
 */
interface SysDeptDao : JpaRepository<SysDepartment, String> {
    fun findByXzqhAndNodeType(areaCode:String,nodeType:String):SysDepartment?
    //根据行政区划获取成员单位
    fun findByXzqhAndSetId(areaCode:String,detId:String):List<SysDepartment>?
}
