package com.zdww.lzshzz.model.ucenter.response


import com.zdww.lzshzz.common.model.response.ResponseResult
import com.zdww.lzshzz.common.model.response.ResultCode
import com.zdww.lzshzz.model.file.request.FileSuccessParam

/**
 * @author 强周亮
 * @desc 登录成功后返回的信息
 * @email 2538096489@qq.com
 * @time 2019/11/18 17:58
 */
data class FileUploadResult(private var resultCode: ResultCode,var fileList:List<FileSuccessParam>? = null) : ResponseResult(resultCode)
