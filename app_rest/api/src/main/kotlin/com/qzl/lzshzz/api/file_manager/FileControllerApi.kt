package com.qzl.lzshzz.api.file_manager

import com.qzl.lzshzz.model.file.request.*
import com.qzl.lzshzz.model.ucenter.response.DeleteFileResult
import com.qzl.lzshzz.model.ucenter.response.FileUploadResult
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity

@Api(value = "文件管理接口", description = "文件管理")
interface FileControllerApi {
    //文件下载
    @ApiOperation(value = "文件下载")
    fun downloadFile(downloadFileRequest: DownloadFileRequest): ResponseEntity<ByteArray?>?
    @ApiOperation(value = "文件上传")
    fun uploadFile(request: UploadFileRequest): FileUploadResult
    @ApiOperation(value = "显示图片")
    fun showImg(request: ShowImgRequest): ByteArray?
    @ApiOperation(value = "删除文件")
    fun deleteFile(request: DeleteFileRequest): DeleteFileResult
    @ApiOperation(value = "视频播放")
    fun playVideo(request: PlayRadioRequest)

    @ApiOperation(value = "下载apk和更新apk")
    fun downloadApk(request: DownloadApkRequest): ResponseEntity<ByteArray?>?
}