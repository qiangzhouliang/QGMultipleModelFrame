package com.qzl.lzshzz.model.sys.request


import com.qzl.lzshzz.common.model.request.CommonRequestData
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author 强周亮
 * @desc 获取所需数据类型请求参数
 * @email 2538096489@qq.com
 * @time 2019/11/1 11:24
 */
@ApiModel(value = "获取所需数据类型请求参数")
class GetTypeRequest : CommonRequestData() {

    @ApiModelProperty(value = "搜索字段")
    var searchName: String = ""
    @ApiModelProperty(value = "类型id ")
    var paraTypeId: String = ""

}
