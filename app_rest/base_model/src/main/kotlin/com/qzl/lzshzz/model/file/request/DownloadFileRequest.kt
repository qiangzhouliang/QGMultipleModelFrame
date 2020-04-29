package com.qzl.lzshzz.model.file.request


import com.qzl.lzshzz.common.model.request.CommonRequestData
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author 强周亮
 * @desc 文件下载请求参数
 * @email 2538096489@qq.com
 * @time 2019/11/1 11:24
 */
@ApiModel(value = "获取通知公告请求参数")
class DownloadFileRequest(
   @ApiModelProperty(value = "文件地址")
   var fileUrl: String = "",
   @ApiModelProperty(value = "文件名称")
   var fileName: String = "") : CommonRequestData() {}
