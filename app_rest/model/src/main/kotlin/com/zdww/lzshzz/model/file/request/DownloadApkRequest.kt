package com.zdww.lzshzz.model.file.request


import com.zdww.lzshzz.common.model.request.CommonRequestData
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author 强周亮
 * @desc 文件下载请求参数
 * @email 2538096489@qq.com
 * @time 2019/11/1 11:24
 */
@ApiModel(value = "下载更新APP请求参数")
class DownloadApkRequest : CommonRequestData() {
    @ApiModelProperty(value = "文件下载或者更新 01 下载 02 更新 默认 01")
    var downloadOrUpdate: String = "01"
    @ApiModelProperty(value = "下载apk的文件地址")
    var fileUrl: String = ""
    @ApiModelProperty(value = "安卓和iOS区分  0:android   1:ios,默认 0")
    var appFlag: String = "0"
}
