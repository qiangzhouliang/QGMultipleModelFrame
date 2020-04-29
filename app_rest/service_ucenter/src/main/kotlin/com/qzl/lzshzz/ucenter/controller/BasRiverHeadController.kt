package com.qzl.lzshzz.ucenter.controller

import com.qzl.lzshzz.api.ucenter.BasRiverHeadControllerApi
import com.qzl.lzshzz.common.model.response.GetPageInfoResponse
import com.qzl.lzshzz.model.ucenter.BasRiverHead
import com.qzl.lzshzz.model.ucenter.request.GetRiverHeadByHeadIdRequest
import com.qzl.lzshzz.model.ucenter.request.GetRiverHeadListByAreaCodeCountRequest
import com.qzl.lzshzz.model.ucenter.request.GetRiverHeadListByAreaCodeRequest
import com.qzl.lzshzz.model.ucenter.request.GetRiverHeadListByReachIdRequest
import com.qzl.lzshzz.model.ucenter.response.RiverHeadCountResponse
import com.qzl.lzshzz.model.ucenter.response.RiverHeadInfoResponse
import com.qzl.lzshzz.ucenter.service.BasRiverHeadService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

/**
 * @author 强周亮
 * @desc 河长信息controller
 * @email 2538096489@qq.com
 * @time 2019/11/7 17:21
 */
@RestController
class BasRiverHeadController : BasRiverHeadControllerApi {
    @Autowired
    lateinit var basRiverHeadService: BasRiverHeadService

    //根据登录账户信息 查询河长信息
    @PreAuthorize("hasAnyAuthority('app')")
    @GetMapping("/getBaseRiverHead")
    override fun getBaseRiverHead(@RequestParam("userAcctId") userAcctId: String): BasRiverHead? {
        return basRiverHeadService.getBasRiverHead(userAcctId)
    }

    /**
     * @Author 强周亮
     * @Description 根据河长id获取河长信息
     * @Date 18:39 2019/12/27
     **/
    @GetMapping("getBaseRiverHeadByHeadId")
    override fun getBaseRiverHeadByHeadId(request: GetRiverHeadByHeadIdRequest): RiverHeadInfoResponse? {
        return basRiverHeadService.getBaseRiverHeadByHeadId(request)
    }
    /**
     * @Author 强周亮
     * @Description 根据河段id获取河长列表
     * @Date 18:40 2019/12/27
     **/
    @GetMapping("getRiverHeadListByReachId")
    override fun getRiverHeadList(request: GetRiverHeadListByReachIdRequest): GetPageInfoResponse<BasRiverHead>? {
        return basRiverHeadService.getRiverHeadList(request)
    }
    /**
     * @Author 强周亮
     * @Description 根据行政区划查询河长
     * @Date 16:15 2020/1/2
     **/
    @GetMapping("getRiverHeadListByAreaCode")
    override fun getRiverHeadListByAreaCode(request: GetRiverHeadListByAreaCodeRequest): GetPageInfoResponse<BasRiverHead>? {
        return basRiverHeadService.getRiverHeadListByAreaCode(request)
    }
    /**
     * @Author 强周亮
     * @Description 获取行政区划下面河长湖长统计数据
     * @Date 14:25 2020/1/6
     **/
    @PostMapping("getRiverHeadListByAreaCodeCount")
    override fun getRiverHeadListByAreaCodeCount(@RequestBody request: GetRiverHeadListByAreaCodeCountRequest): RiverHeadCountResponse? {
        return basRiverHeadService.getRiverHeadListByAreaCodeCount(request)
    }

    /**
     * @Author 强周亮
     * @Description 根据行政区划和河长级别查询河长数据
     * @Date 16:18 2020/3/2
     **/
    @GetMapping("getRiverHeadListByAreaCodeAndHeadLevelLike")
    override fun getRiverHeadListByAreaCodeAndHeadLevelLike(@RequestParam("areaCode") areaCode: String,@RequestParam("headLevel") headLevel: String): List<BasRiverHead>? {
        return basRiverHeadService.getRiverHeadListByAreaCodeAndHeadLevelLike(areaCode,headLevel)
    }

}