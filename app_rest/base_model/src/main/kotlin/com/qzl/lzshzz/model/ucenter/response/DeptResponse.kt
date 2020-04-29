package com.qzl.lzshzz.model.ucenter.response

import com.qzl.lzshzz.common.model.response.ResponseResult
import com.qzl.lzshzz.common.model.response.ResultCode
import com.qzl.lzshzz.model.ucenter.SysDepartment

/**
 * @author 强周亮
 * @desc 部门信息返回值
 * @email 2538096489@qq.com
 * @time 2019/12/5 16:20
 */
class DeptResponse(private var resultCode: ResultCode, var deptInfo: List<SysDepartment>? = null): ResponseResult(resultCode)
