package com.zdww.lzshzz.ucenter.auth.controller

import com.zdww.lzshzz.api.auth.CommonControllerApi
import com.zdww.lzshzz.api.auth.YszcApi
import com.zdww.lzshzz.common.Constants
import com.zdww.lzshzz.common.exception.ExceptionCast
import com.zdww.lzshzz.common.model.response.CommonCode
import com.zdww.lzshzz.common.model.response.ResponseResult
import com.zdww.lzshzz.common.web.BaseController
import com.zdww.lzshzz.model.auth.Apk
import com.zdww.lzshzz.model.auth.request.*
import com.zdww.lzshzz.model.ucenter.response.CheckNumResult
import com.zdww.lzshzz.model.ucenter.response.auth.AuthCode
import com.zdww.lzshzz.ucenter.auth.client.UserClient
import com.zdww.lzshzz.ucenter.auth.service.AuthService
import com.zdww.lzshzz.ucenter.auth.service.CommonService
import com.zdww.lzshzz.ucenter.auth.service.RedisService
import com.zdww.lzshzz.util.CheckNumUtil
import com.zdww.lzshzz.util.JPushClientUtil
import com.zdww.lzshzz.util.StringHelper
import org.apache.commons.lang.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.UnsupportedEncodingException
import java.util.*

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