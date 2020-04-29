package com.qzl.lzshzz.model.ucenter.response

import com.qzl.lzshzz.common.model.response.ResponseResult
import com.qzl.lzshzz.common.model.response.ResultCode
import com.qzl.lzshzz.model.ucenter.BasRiverHead

/**
 * @author 强周亮
 * @desc 获取河长信息返回值
 * @email 2538096489@qq.com
 * @time 2019/12/5 16:20
 */
class RiverHeadInfoResponse(
        private var resultCode: ResultCode,
        var riverHeadInfo:BasRiverHead? = null): ResponseResult(resultCode)
