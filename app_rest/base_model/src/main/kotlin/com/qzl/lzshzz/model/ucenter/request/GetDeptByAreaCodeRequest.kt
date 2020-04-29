package com.qzl.lzshzz.model.ucenter.request


import com.qzl.lzshzz.common.model.request.CommonRequestData
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author 强周亮
 * @desc 根据行政区划获取成员单位信息
 * @email 2538096489@qq.com
 * @time 2019/11/1 11:24
 */
@ApiModel(value = "根据行政区划获取成员单位信息")
class GetDeptByAreaCodeRequest : CommonRequestData() {
    @ApiModelProperty(value = "区划编码")
    var areaCode: String = ""
}
