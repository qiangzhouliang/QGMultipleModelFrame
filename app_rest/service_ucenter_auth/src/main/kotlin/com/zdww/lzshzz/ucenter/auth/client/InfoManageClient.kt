package com.zdww.lzshzz.ucenter.auth.client

import com.zdww.lzshzz.common.client.ServiceList
import com.zdww.lzshzz.model.sys.SysDistrict
import com.zdww.lzshzz.model.ucenter.request.GetDistrictInfoRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.cloud.openfeign.SpringQueryMap
import org.springframework.web.bind.annotation.GetMapping

/**
 * 信息管理客户端调运
 */
@FeignClient(value = ServiceList.SERVICE_INFO_MANAGER)
interface InfoManageClient {
    //根据账号查询用户信息
    @GetMapping("/infoManager/getDistrictInfo")
    fun getDistrictInfo(@SpringQueryMap request: GetDistrictInfoRequest): SysDistrict?
}
