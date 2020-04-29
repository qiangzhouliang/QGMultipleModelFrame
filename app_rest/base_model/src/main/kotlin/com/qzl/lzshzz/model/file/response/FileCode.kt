package com.qzl.lzshzz.model.file.response

import com.qzl.lzshzz.common.model.response.ResultCode
import io.swagger.annotations.ApiModelProperty
/**
 * @author 强周亮
 * @desc 文件管理返回结果代码
 * @email 2538096489@qq.com
 * @time 2019/11/20 13:57
 */
enum class FileCode(//操作代码
    @field:ApiModelProperty(value = "操作是否成功", example = "true", required = true)
    internal var success: Boolean, //操作代码
    @field:ApiModelProperty(value = "操作代码", example = "25000", required = true)
    internal var code: Int, //提示信息
    @field:ApiModelProperty(value = "操作提示", example = "操作过于频繁！", required = true)
    internal var message: String) : ResultCode {
    FILE_SUCCESS(true, 25001, "文件上传成功"),
    FILE_FAIL(true, 25002, "文件上传失败"),
    FILE_FTP_FAIL(true, 25003, "ftp出错,上传文件失败"),
    FILE_IMG_LARGE_FAIL(true, 25004, "图片太大，文件上传失败，文件大小限制为5M"),
    FILE_VIDEO_LIU_FAIL(true, 25005, "流服务出错，视频推送失败"),
    FILE_VIDEO_LARGE_FAIL(true, 25006, "视屏太大，文件上传失败，文件大小限制为30M"),
    FILE_FILE_LARGE_FAIL(true, 25007, "文件太大，文件上传失败，文件大小限制为30M");

    override fun success(): Boolean {
        return success
    }

    override fun code(): Int {
        return code
    }

    override fun message(): String {
        return message
    }
}
