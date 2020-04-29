package com.qzl.lzshzz.common.model.response

/**
 * @author 强周亮
 * @desc 分页返回值数据
 * @email 2538096489@qq.com
 * @time 2019/11/14 14:36
 */
data class GetPageInfoResponse<RESPONSE>(
    private var resultCode: ResultCode,   //返回结果状态码
    var data: List<RESPONSE>? = null,    //结果集
    var pagePara:PagePara? = null,
    var data2: List<Any>? = null,   //结果集
    var flag:String? = null
) : ResponseResult(resultCode)
