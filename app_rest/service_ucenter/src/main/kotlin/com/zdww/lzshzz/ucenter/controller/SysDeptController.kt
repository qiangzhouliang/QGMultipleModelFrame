package com.zdww.lzshzz.ucenter.controller

import com.zdww.lzshzz.api.ucenter.SysDeptControllerApi
import com.zdww.lzshzz.model.ucenter.SysDepartment
import com.zdww.lzshzz.model.ucenter.request.GetDeptByAreaCodeRequest
import com.zdww.lzshzz.model.ucenter.response.DeptResponse
import com.zdww.lzshzz.ucenter.service.SysDeptService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SysDeptController : SysDeptControllerApi {
    @Autowired
    lateinit var sysDeptService: SysDeptService

    //获取用户信息
    @PreAuthorize("hasAnyAuthority('app')")
    @GetMapping("/getDeptInfo")
    override fun getDeptInfo(@RequestParam("deptId") deptId: String): SysDepartment? {

        return sysDeptService.getDeptInfo(deptId)
    }

    //根据行政区划获取河长办信息
    @GetMapping("/getDeptInfoByAreaCode")
    override fun getDeptInfoByAreaCode(areaCode: String): SysDepartment? {
        return sysDeptService.getDeptInfoByAreaCode(areaCode)
    }

    /**
     * @Author 强周亮
     * @Description 根据行政区划获取成员单位信息
     * @Date 22:39 2020/2/27
     **/
    @GetMapping("/getDeptInfoByAreaCodeAndSetId")
    override fun getDeptInfoByAreaCodeAndSetId(request: GetDeptByAreaCodeRequest): DeptResponse? {
        return sysDeptService.getDeptInfoByAreaCodeAndSetId(request)
    }
}