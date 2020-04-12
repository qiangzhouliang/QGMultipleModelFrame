package com.zdww.lzshzz.model.file.request


import com.zdww.lzshzz.common.model.request.CommonRequestData
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.web.multipart.MultipartFile

/**
 * @author 强周亮
 * @desc 文件下载请求参数
 * @email 2538096489@qq.com
 * @time 2019/11/1 11:24
 */
@ApiModel(value = "文件上传接口")
class UploadFileRequest : CommonRequestData() {
    @ApiModelProperty(value = "要接受的文件",required = true)
    lateinit var file: List<MultipartFile>
    @ApiModelProperty(value = "关联id",required = true)
    var busId: String = ""
    @ApiModelProperty(value = "存储文件的表名，默认值（bas_file_event）")
    var table: String = "bas_file_event"
    @ApiModelProperty(value = "清四乱上传文件区分-0：填报附件，1：销号附件，",required = true)
    var fileSource: String = ""
    @ApiModelProperty(value = "行政区划编码，",required = true)
    var areaCode: String = ""
    @ApiModelProperty(value = "文件类别，如事件（event）,默认值event")
    var fileType: String = "event"
    @ApiModelProperty(value = "上传应用标识，默认为 -app-",required = true)
    var fileAppFlag: String = "-app-"
}
