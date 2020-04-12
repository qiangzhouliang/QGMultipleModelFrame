package com.zdww.lzshzz.api.ucenter

import com.zdww.lzshzz.model.ucenter.BasFileHead
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation

/**
 * @author 强周亮
 * @desc 河长头像信息接口
 * @email 2538096489@qq.com
 * @time 2019/8/20 17:06
 */
@Api(value = "河长头像信息", description = "河长头像信息管理")
interface BasFileHeadControllerApi {
    @ApiOperation(value = "根据河长id河长头像信息")
    fun getHeadImage(riverHeadId: String): List<BasFileHead>?
}
