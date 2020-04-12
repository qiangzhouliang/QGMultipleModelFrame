package com.zdww.lzshzz.api.ucenter

import com.zdww.lzshzz.model.ucenter.SysDepartment
import com.zdww.lzshzz.model.ucenter.request.GetDeptByAreaCodeRequest
import com.zdww.lzshzz.model.ucenter.response.DeptResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation

/**
 * @author 强周亮
 * @desc 部门接口
 * @email 2538096489@qq.com
 * @time 2019/8/20 17:06
 */
@Api(value = "部门信息", description = "部门信息管理")
interface SysDeptControllerApi {
    @ApiOperation(value = "根据部门id查询部门信息")
    fun getDeptInfo(deptId: String): SysDepartment?

    @ApiOperation(value = "根据行政区划获取河长办信息")
    fun getDeptInfoByAreaCode(areaCode: String): SysDepartment?

    @ApiOperation(value = "根据行政区划获取成员单位信息")
    fun getDeptInfoByAreaCodeAndSetId(request: GetDeptByAreaCodeRequest): DeptResponse?
}
