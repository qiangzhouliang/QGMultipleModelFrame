package com.zdww.lzshzz.common.web

import org.springframework.web.bind.annotation.ModelAttribute

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

/**
 * @author 强周亮
 * @desc 公共controller
 * @email 2538096489@qq.com
 * @time 2019-07-25 17:22
 * @class BaseController
 * @package com.qzl.common.web
 */
open class BaseController {
    protected lateinit var httpRequest: HttpServletRequest

    protected lateinit var response: HttpServletResponse

    protected lateinit var session: HttpSession

    @ModelAttribute
    fun setReqAndRes(request: HttpServletRequest, response: HttpServletResponse) {

        this.httpRequest = request

        this.response = response

//        this.session = request.session

    }
}
