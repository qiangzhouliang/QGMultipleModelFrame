package com.zdww.lzshzz.api.auth

import com.zdww.lzshzz.common.model.response.ResponseResult
import com.zdww.lzshzz.model.auth.Apk
import com.zdww.lzshzz.model.auth.request.*
import com.zdww.lzshzz.model.ucenter.response.CheckNumResult
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.ui.Model

/**
 * 公共调运方法的接口
 */
@Api(value = "隐私政策公共接口", description = "隐私政策公共接口")
interface YszcApi {
    @ApiOperation("获取隐私政策")
    fun getYszc(request: GetApkVersionRequest, model: Model): String
}
