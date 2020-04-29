package com.qzl.lzshzz.model.ucenter.request


import com.qzl.lzshzz.common.model.request.CommonRequestData
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author 强周亮
 * @desc 根据河段id获取河长列表请求参数
 * @email 2538096489@qq.com
 * @time 2019/11/1 11:24
 */
@ApiModel(value = "根据河段id获取河长列表请求参数")
class GetRiverHeadListByAreaCodeCountRequest : CommonRequestData() {
    @ApiModelProperty(value = "区划编码")
    var areaCode: String = ""
    @ApiModelProperty(value = "搜索字段")
    var searchName: String = ""
    @ApiModelProperty(value = "所有，还是本级 01 所有，02 本级，默认01")
    var totalOrSelf: String = "01"
}
