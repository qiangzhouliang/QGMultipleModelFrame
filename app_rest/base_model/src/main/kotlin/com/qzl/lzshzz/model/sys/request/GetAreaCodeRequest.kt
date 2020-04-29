package com.qzl.lzshzz.model.sys.request


import com.qzl.lzshzz.common.model.request.CommonRequestData
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "获取行政区划参数接收")
class GetAreaCodeRequest : CommonRequestData() {
    @ApiModelProperty(value = "行政区划编码")
    var areaCode: String = "620100000000"
}
