package com.zdww.lzshzz.model.file.request


import com.zdww.lzshzz.common.model.request.CommonRequestData
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author 强周亮
 * @desc 视频播放请求参数
 * @email 2538096489@qq.com
 * @time 2019/11/1 11:24
 */
@ApiModel(value = "视频播放请求参数")
class PlayRadioRequest : CommonRequestData() {
    @ApiModelProperty(value = "视频全路径")
    var fileUrl: String = ""
}
