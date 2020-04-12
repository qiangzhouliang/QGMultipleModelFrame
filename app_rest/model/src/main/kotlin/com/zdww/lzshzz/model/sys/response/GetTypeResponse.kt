package com.zdww.lzshzz.model.sys.response

import com.zdww.lzshzz.common.model.response.ResponseResult
import com.zdww.lzshzz.common.model.response.ResultCode
import com.zdww.lzshzz.model.sys.SysPara

/**
 * @author 强周亮
 * @desc 获取数据类型返回值
 * @email 2538096489@qq.com
 * @time 2019/12/5 16:20
 */
class GetTypeResponse(private var resultCode: ResultCode, var sysPara: List<SysPara>? = null): ResponseResult(resultCode)
