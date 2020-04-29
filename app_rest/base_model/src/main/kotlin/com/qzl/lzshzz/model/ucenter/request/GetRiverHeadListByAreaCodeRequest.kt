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
class GetRiverHeadListByAreaCodeRequest(
    @ApiModelProperty(value = "区划编码")
    var areaCode: String = "",
    @ApiModelProperty(value = "搜索字段")
    var searchName: String = "",
    @ApiModelProperty(value = "当前页")
    var pageNum: Int = 0,
    @ApiModelProperty(value = "页面大小")
    var pageSize: Int = 200,
    @ApiModelProperty(value = "本级或者含下级 01 本级，02 含下级，默认 01")
    var totalOrSelf: String = "01",
    @ApiModelProperty(value = "1：河长；2：湖长；1，2：河长湖长，默认1")
    var riverLakeHead: String = "1"
) : CommonRequestData() {}
