package com.zdww.lzshzz.model.file.request


import io.swagger.annotations.ApiModel

/**
 * @author 强周亮
 * @desc 文件上传成功后的返回结果集参数
 * @email 2538096489@qq.com
 * @time 2019/11/1 11:24
 */
@ApiModel(value = "文件上传成功后的返回结果集参数")
data class FileSuccessParam(var busId: String, var table: String,var fileName: String = "",var fileNewName: String = "", var fileUrl: String = "")
