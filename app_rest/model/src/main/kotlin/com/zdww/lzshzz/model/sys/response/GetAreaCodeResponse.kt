package com.zdww.lzshzz.model.ucenter.response


import com.zdww.lzshzz.common.model.response.ResponseResult
import com.zdww.lzshzz.common.model.response.ResultCode
import com.zdww.lzshzz.model.sys.SysDistrict

/**
 * @author 强周亮
 * @desc 获取行政区划成功后的返回值
 * @email 2538096489@qq.com
 * @time 2019/11/18 17:58
 */
data class GetAreaCodeResponse(private var resultCode: ResultCode, var areaCodeList:List<SysDistrict>? = null) : ResponseResult(resultCode)
