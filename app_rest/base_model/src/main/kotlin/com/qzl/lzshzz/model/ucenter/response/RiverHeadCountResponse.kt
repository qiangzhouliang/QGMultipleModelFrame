package com.qzl.lzshzz.model.ucenter.response

import com.qzl.lzshzz.common.model.response.ResponseResult
import com.qzl.lzshzz.common.model.response.ResultCode

/**
 * @author 强周亮
 * @desc 查询行政区划河长统计
 * @email 2538096489@qq.com
 * @time 2019/12/5 16:20
 */
class RiverHeadCountResponse(private var resultCode: ResultCode, var riverCount:Int? = 0,var lakeCount:Int? = 0): ResponseResult(resultCode)
