package com.qzl.lzshzz.model.ucenter.request


import com.qzl.lzshzz.common.model.request.CommonRequestData
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author 强周亮
 * @desc 获取行政区划信息
 * @email 2538096489@qq.com
 * @time 2019/11/1 11:24
 */
@ApiModel(value = "获取行政区划信息请求参数")
class GetDistrictInfoRequest : CommonRequestData() {

    @ApiModelProperty(value = "行政区划编码",required = true)
    var areaCode: String = ""
}
