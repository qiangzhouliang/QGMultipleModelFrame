package com.qzl.lzshzz.ucenter.auth.controller

import com.qzl.lzshzz.api.auth.YszcApi
import com.qzl.lzshzz.common.exception.ExceptionCast
import com.qzl.lzshzz.common.model.response.CommonCode
import com.qzl.lzshzz.common.model.response.ResponseResult
import com.qzl.lzshzz.common.web.BaseController
import com.qzl.lzshzz.model.auth.request.*
import com.qzl.lzshzz.ucenter.auth.service.CommonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

/**
 * @author 强周亮
 * @desc 隐私政策
 * @email 2538096489@qq.com
 * @time 2019/11/28 11:17
 */
@Controller
class YszcController : BaseController(), YszcApi {
    @Autowired
    lateinit var commonService: CommonService

    //获取隐私政策
    @GetMapping("getYszc")
    override fun getYszc(request: GetApkVersionRequest, model: Model): String {
        model.addAttribute("data",commonService.getApkInfo(request))
        return "yszc"
    }
}