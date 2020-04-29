package com.qzl.lzshzz.model.ucenter.response


import com.qzl.lzshzz.common.model.response.ResponseResult
import com.qzl.lzshzz.common.model.response.ResultCode

/**
 * @author 强周亮
 * @desc 删除文件返回值
 * @email 2538096489@qq.com
 * @time 2019/11/18 17:58
 */
data class DeleteFileResult(private var resultCode: ResultCode) : ResponseResult(resultCode)
