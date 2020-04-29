package com.qzl.lzshzz.model.auth.request


import com.qzl.lzshzz.common.model.request.CommonRequestData
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author 强周亮
 * @desc 获取apk版本信息
 * @email 2538096489@qq.com
 * @time 2019/11/1 11:24
 */
@ApiModel(value = "获取apk版本信息")
class GetApkVersionRequest : CommonRequestData() {
    @ApiModelProperty(value = "Android和ios的区分，1表示ios，0表示Android，默认为0")
    var androidOrIosFlag: String = "0"
}
