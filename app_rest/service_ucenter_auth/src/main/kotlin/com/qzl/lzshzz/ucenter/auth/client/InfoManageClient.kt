package com.qzl.lzshzz.ucenter.auth.client

import com.qzl.lzshzz.common.client.ServiceList
import com.qzl.lzshzz.model.sys.SysDistrict
import com.qzl.lzshzz.model.ucenter.request.GetDistrictInfoRequest
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
