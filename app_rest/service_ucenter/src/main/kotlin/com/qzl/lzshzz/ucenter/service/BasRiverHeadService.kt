package com.qzl.lzshzz.ucenter.service

import com.qzl.lzshzz.common.model.response.CommonCode
import com.qzl.lzshzz.common.model.response.GetPageInfoResponse
import com.qzl.lzshzz.common.model.response.ResultCode
import com.qzl.lzshzz.common.web.PageUtil
import com.qzl.lzshzz.common.web.SetDataListener
import com.qzl.lzshzz.common.web.SetDataListener3
import com.qzl.lzshzz.model.ucenter.BasRiverHead
import com.qzl.lzshzz.model.ucenter.request.*
import com.qzl.lzshzz.model.ucenter.response.RiverHeadCountResponse
import com.qzl.lzshzz.model.ucenter.response.RiverHeadInfoResponse
import com.qzl.lzshzz.ucenter.dao.BasRiverHeadDao
import com.qzl.lzshzz.ucenter.mapper.BasRiverHeaderMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

/**
 * @author 强周亮
 * @desc 河长服务类
 * @email 2538096489@qq.com
 * @time 2019/11/7 17:18
 */
@Service
class BasRiverHeadService {
    @Autowired
    private lateinit var basRiverHeadDao: BasRiverHeadDao
    @Autowired
    lateinit var basRiverHeaderMapper: BasRiverHeaderMapper

    //根据登录信息id查询河长信息
    fun getBasRiverHead(userAcctId: String): BasRiverHead? {
        return basRiverHeadDao.findByAccountId(userAcctId)
    }

    /**
     * @Author 强周亮
     * @Description 根据河长id获取河长信息
     * @Date 18:43 2019/12/27
     **/
    fun getBaseRiverHeadByHeadId(request: GetRiverHeadByHeadIdRequest): RiverHeadInfoResponse? {
        val basRiverHead = basRiverHeadDao.findByIdOrNull(request.headId)
        return if (basRiverHead != null) {
            RiverHeadInfoResponse(CommonCode.SUCCESS,basRiverHead)
        } else {
            RiverHeadInfoResponse(CommonCode.FAIL)
        }
    }

    /**
     * @Author 强周亮
     * @Description 根据河段id获取河长列表
     * @Date 18:43 2019/12/27
     **/
    fun getRiverHeadList(request: GetRiverHeadListByReachIdRequest): GetPageInfoResponse<BasRiverHead>? {
        return PageUtil<BasRiverHead>().getPageDataMoreToable(request.pageNum, request.pageSize, object : SetDataListener<BasRiverHead> {
            override fun getData(): Pair<List<BasRiverHead>?, ResultCode> {
                val page = basRiverHeaderMapper.getRiverHeadListS(request)
                return Pair(page, CommonCode.SUCCESS)
            }
        })
    }

    /**
     * @Author 强周亮
     * @Description 查询区划下的河长列表
     * @Date 15:09 2020/1/2
     **/
    fun getRiverHeadListByAreaCode(request: GetRiverHeadListByAreaCodeRequest): GetPageInfoResponse<BasRiverHead>? {
        return PageUtil<BasRiverHead>().getPageDataMoreToable3(request.pageNum, request.pageSize, object : SetDataListener3<BasRiverHead> {
            override fun getData(): Triple<List<BasRiverHead>?, ResultCode,List<Any>?> {
                val countByAreaCode = basRiverHeaderMapper.getRiverHeadCountByAreaCode(request)
                val page = basRiverHeaderMapper.getRiverHeadListByAreaCode(request)
                return Triple(page, CommonCode.SUCCESS,countByAreaCode)
            }
        })
    }

    /**
     * @Author 强周亮
     * @Description 查询区划下的河长湖长统计信息
     * @Date 15:09 2020/1/2
     **/
    fun getRiverHeadListByAreaCodeCount(request: GetRiverHeadListByAreaCodeCountRequest): RiverHeadCountResponse? {
        when (request.totalOrSelf){
            "01" -> { request.totalOrSelf = "02"}
            else -> { request.totalOrSelf = "01" }
        }
        val request1 = GetRiverHeadListByAreaCodeRequest(request.areaCode, totalOrSelf = request.totalOrSelf)
        //河长
        request1.riverLakeHead = "1"
        val list = basRiverHeaderMapper.getRiverHeadListByAreaCode(request1)
        //湖长
        request1.riverLakeHead = "2"
        val countLake = basRiverHeaderMapper.getRiverHeadListByAreaCode(request1)
        return RiverHeadCountResponse(CommonCode.SUCCESS, list?.size,countLake?.size)
    }

    fun getRiverHeadListByAreaCodeAndHeadLevelLike(areaCode: String,headLevel: String): List<BasRiverHead>? {
        return basRiverHeadDao.findByAreaCodeAndHeadLevelLike(areaCode, areaCode)
    }
}