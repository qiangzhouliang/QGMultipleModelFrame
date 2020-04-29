package com.qzl.lzshzz.api.ucenter

import com.qzl.lzshzz.common.model.response.GetPageInfoResponse
import com.qzl.lzshzz.model.ucenter.BasRiverHead
import com.qzl.lzshzz.model.ucenter.request.GetRiverHeadByHeadIdRequest
import com.qzl.lzshzz.model.ucenter.request.GetRiverHeadListByAreaCodeCountRequest
import com.qzl.lzshzz.model.ucenter.request.GetRiverHeadListByAreaCodeRequest
import com.qzl.lzshzz.model.ucenter.request.GetRiverHeadListByReachIdRequest
import com.qzl.lzshzz.model.ucenter.response.RiverHeadCountResponse
import com.qzl.lzshzz.model.ucenter.response.RiverHeadInfoResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation

/**
 * @author 强周亮
 * @desc 河长信息接口
 * @email 2538096489@qq.com
 * @time 2019/8/20 17:06
 */
@Api(value = "河长信息", description = "河长信息管理")
interface BasRiverHeadControllerApi {
    @ApiOperation(value = "根据账号id获取河长头像信息")
    fun getBaseRiverHead(userAcctId: String): BasRiverHead?
    @ApiOperation(value = "根据河长id获取河长信息")
    fun getBaseRiverHeadByHeadId(request: GetRiverHeadByHeadIdRequest): RiverHeadInfoResponse?
    @ApiOperation(value = "根据河段id查询河长列表")
    fun getRiverHeadList(request: GetRiverHeadListByReachIdRequest): GetPageInfoResponse<BasRiverHead>?

    @ApiOperation(value = "查询区划下的河长列表")
    fun getRiverHeadListByAreaCode(request: GetRiverHeadListByAreaCodeRequest): GetPageInfoResponse<BasRiverHead>?
    @ApiOperation(value = "查询区划下的河长湖长统计")
    fun getRiverHeadListByAreaCodeCount(request: GetRiverHeadListByAreaCodeCountRequest): RiverHeadCountResponse?

    @ApiOperation(value = "根据区划和河长级别查询河长列表")
    fun getRiverHeadListByAreaCodeAndHeadLevelLike(areaCode: String,headLevel:String): List<BasRiverHead>?
}
