package com.qzl.lzshzz.ucenter.controller

import com.qzl.lzshzz.api.ucenter.BasFileHeadControllerApi
import com.qzl.lzshzz.model.ucenter.BasFileHead
import com.qzl.lzshzz.ucenter.service.BasFileHeadService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * @author 强周亮
 * @desc 河长头像信息controller
 * @email 2538096489@qq.com
 * @time 2019/11/7 17:21
 */
@RestController
class BasFileHeadController : BasFileHeadControllerApi {
    @Autowired
    lateinit var basFileHeadService: BasFileHeadService

    //根据河长id获取河长头像
    @PreAuthorize("hasAnyAuthority('app')")
    @GetMapping("/getHeadImage")
    override fun getHeadImage(@RequestParam("riverHeadId") riverHeadId: String): List<BasFileHead>? {
        return basFileHeadService.getHeadImage(riverHeadId)
    }
}