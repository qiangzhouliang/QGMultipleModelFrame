package com.qzl.lzshzz.ucenter.mapper

import com.qzl.lzshzz.model.sys.SysDistrict
import com.qzl.lzshzz.model.ucenter.BasRiverHead
import com.qzl.lzshzz.model.ucenter.request.GetRiverHeadListByAreaCodeRequest
import com.qzl.lzshzz.model.ucenter.request.GetRiverHeadListByReachIdRequest
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
/**
 * @author 强周亮
 * @desc 河长查询mapper
 * @email 2538096489@qq.com
 * @time 2019/12/3 14:52
 */
@Mapper
interface BasRiverHeaderMapper {
    //查询河流统计信息
    fun getRiverHeadListS(@Param("request") request: GetRiverHeadListByReachIdRequest): List<BasRiverHead>?

    //查询河长按区划统计信息
    fun getRiverHeadCountByAreaCode(@Param("request") request: GetRiverHeadListByAreaCodeRequest): List<SysDistrict>?
    //查询区划下河长信息
    fun getRiverHeadListByAreaCode(@Param("request") request: GetRiverHeadListByAreaCodeRequest): List<BasRiverHead>?
}