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
@ApiModel(value = "文件上传接口")
class DeleteFileRequest : CommonRequestData() {
    @ApiModelProperty(value = "关联id",required = true)
    var busId: String = ""
    @ApiModelProperty(value = "存储文件的表名，默认值（bas_file_event），清四乱（bas_file_clear_chaos）")
    var table: String = "bas_file_event"
    @ApiModelProperty(value = "文件新名称",required = true)
    var fileNewName: String = ""
}
