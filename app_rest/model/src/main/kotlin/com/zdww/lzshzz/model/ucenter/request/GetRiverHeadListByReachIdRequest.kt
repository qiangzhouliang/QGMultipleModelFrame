package com.zdww.lzshzz.model.ucenter.request


import com.zdww.lzshzz.common.model.request.CommonRequestData
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author 强周亮
 * @desc 根据河段id获取河长列表请求参数
 * @email 2538096489@qq.com
 * @time 2019/11/1 11:24
 */
@ApiModel(value = "根据河段id获取河长列表请求参数")
class GetRiverHeadListByReachIdRequest : CommonRequestData() {
    @ApiModelProperty(value = "河段或湖库id")
    var riverReachId: String = ""
    @ApiModelProperty(value = "搜索字段")
    var searchName: String = ""
    @ApiModelProperty(value = "当前页")
    var pageNum: Int = 0
    @ApiModelProperty(value = "页面大小")
    var pageSize: Int = 20
}
