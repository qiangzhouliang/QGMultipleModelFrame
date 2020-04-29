package com.qzl.lzshzz.model.file.request


import com.qzl.lzshzz.common.model.request.CommonRequestData
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author 强周亮
 * @desc 显示图片
 * @email 2538096489@qq.com
 * @time 2019/11/1 11:24
 */
@ApiModel(value = "获取通知公告请求参数")
class ShowImgRequest : CommonRequestData() {
    @ApiModelProperty(value = "文件全路径",required = true)
    var fileName: String = ""
    @ApiModelProperty(value = "1 为获取压缩图，否则为原图",required = true)
    var press: String? = ""
}
