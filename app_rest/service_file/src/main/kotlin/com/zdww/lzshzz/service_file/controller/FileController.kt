package com.zdww.lzshzz.service_file.controller

import com.zdww.lzshzz.api.file_manager.FileControllerApi
import com.zdww.lzshzz.common.Constants
import com.zdww.lzshzz.common.model.response.CommonCode
import com.zdww.lzshzz.common.web.BaseController
import com.zdww.lzshzz.model.file.request.*
import com.zdww.lzshzz.model.file.response.FileCode
import com.zdww.lzshzz.model.ucenter.response.DeleteFileResult
import com.zdww.lzshzz.model.ucenter.response.FileUploadResult
import com.zdww.lzshzz.service_file.service.FileService
import com.zdww.lzshzz.service_file.util.ChangeImageSize
import com.zdww.lzshzz.service_file.util.FtpUtil
import com.zdww.lzshzz.util.StringHelper
import com.zdww.tools.utils.TimeHelper
import org.apache.commons.net.ftp.FTPClientConfig
import org.apache.commons.net.ftp.FTPReply
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import java.io.OutputStream
import java.net.URLConnection
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.*

/**
 * @author 强周亮
 * @desc 文件上传接口
 * @email 2538096489@qq.com
 * @time 2019/11/25 18:47
 */
@RestController
class FileController:BaseController(), FileControllerApi {
    @Autowired
    lateinit var fileService: FileService
    /**
     * 本地字符编码
     */
    private var LOCAL_CHARSET = "GBK"
    /**
     * @Author 强周亮
     * @Description 文件下载
     * @Date 18:48 2019/11/25
     **/
    @GetMapping("/download")
    override fun downloadFile(request: DownloadFileRequest): ResponseEntity<ByteArray?>? {
        return download(request)
    }

    //文件下载公共方法
    private fun download(request: DownloadFileRequest): ResponseEntity<ByteArray?>? {
        try {
            // 解析文件的 mime 类型
            val contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(request.fileName)
                    ?: MediaType.APPLICATION_OCTET_STREAM_VALUE
            // 实例化MIME
            val mediaType = MediaType.parseMediaType(contentTypeFor)
            /*
             * 构造响应的头
             */
            val httpHeaders = HttpHeaders()
            // 下载之后需要在请求头中放置文件名，该文件名按照ISO_8859_1编码。
            val filenames = String(request.fileName.toByteArray(), StandardCharsets.ISO_8859_1)
            httpHeaders.setContentDispositionFormData("attachment", filenames)
            httpHeaders.contentType = mediaType

            val ftpFile: ByteArray? = FtpUtil.downloadFtpFileByte(request.fileUrl)
            httpHeaders.contentLength = ftpFile?.size?.toLong() ?: 0
            /*
             * 返还资源
             */
            return ResponseEntity.ok().headers(httpHeaders).contentLength(ftpFile?.size?.toLong() ?: 0).body(ftpFile)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }

    /**
     * @Author 强周亮
     * @Description 图片展示
     * @Date 21:20 2019/11/27
     **/
    @GetMapping("/show",produces = [MediaType.IMAGE_JPEG_VALUE])
    override fun showImg(request: ShowImgRequest): ByteArray? {
        val img = FtpUtil.downloadFtpFileByte(request.fileName)
        return if (img != null) {
            //压缩图片
            if ("1" == request.press) { //1表示压缩
                ChangeImageSize.compressPic(img, 200, 200, true)
            } else {
                img
            }
        } else {
            null
        }
    }

    /**
     * @Author 强周亮
     * @Description 删除文件
     * @Date 21:33 2019/11/27
     **/
    @GetMapping("deleteFile")
    override fun deleteFile(request: DeleteFileRequest): DeleteFileResult {
        try {
            val allUrl: String
            val map = fileService.queryFile(request.table, request.busId, request.fileNewName)
            return if (map != null){
                allUrl = StringHelper.toString(map["url"])
                if (!StringHelper.isEmptyString(allUrl) && FtpUtil.deleteFile(allUrl)) {
                    fileService.deleteFile(request.busId, request.fileNewName, request.table)
                    DeleteFileResult(CommonCode.SUCCESS)
                } else {
                    DeleteFileResult(CommonCode.FAIL)
                }
            } else {
                DeleteFileResult(CommonCode.FAIL)
            }
        } catch (e: java.lang.Exception) {
            return DeleteFileResult(CommonCode.FAIL)
        }
    }


    /**
     * @Author 强周亮
     * @Description 文件上传
     * @Date 18:48 2019/11/25
     **/
    @PostMapping("/upload")
    override fun uploadFile(request: UploadFileRequest): FileUploadResult {
        var myFileName = ""
        val list = ArrayList<FileSuccessParam>()
        val fileUrls = TimeHelper.currentYear+TimeHelper.currentMonth+
                "/"+TimeHelper.currentDay+"/"+request.areaCode+
                "/"+request.fileType+
                "/"+request.loginAccount
        request.file.forEach {
            if (!it.isEmpty) {
                try {
                    val imgType = ".jpg.jpeg.gif.png.JPG.JPEG.GIF.PNG"
                    val fileName = it.originalFilename // 文件全名
                    var extName = "" // 文件后缀名
                    if (!StringHelper.isEmptyString(fileName) && fileName.lastIndexOf(".") > 0) {
                        extName = fileName.substring(fileName.lastIndexOf(".") + 1)
                    }
                    val fileUrl = "$fileUrls/$extName"
                    //重命名唯一
                    myFileName = TimeHelper.currentCompactTimeSS.toString() + "${request.fileAppFlag}${(0..100).random()}.${extName}"
                    if (!StringHelper.isEmptyString(extName) && imgType.contains(extName)) { // 5兆
                        val imgSize = 5 * 1024 * 1024.toLong()  //限制图片只能上传5M以内的
                        if (it.size <= imgSize) {
                            val result = FtpUtil.uploadFile(fileUrl, myFileName, FtpUtil.byte2Input(it.bytes))
                            if (result) {
                                //如果上传成功，则保存文件信息
                                fileService.saveFile(request.table, request.busId, fileName, myFileName, fileUrl, Constants.FILE_TYPE_IMG, request.fileSource)
                                list.add(FileSuccessParam(request.busId,request.table,fileName,myFileName, "$fileUrl/$myFileName"))
                            } else {
                                return FileUploadResult(FileCode.FILE_FTP_FAIL)
                            }
                        } else {
                            return FileUploadResult(FileCode.FILE_IMG_LARGE_FAIL)
                        }
                    } else if ("mp4" == extName) { // 15兆
                        val vedSize = 30 * 1024 * 1024.toLong()
                        if (it.size <= vedSize) {
                            val result = FtpUtil.uploadFile(fileUrl, myFileName, FtpUtil.byte2Input(it.bytes))
                            if (result) { // SendVideoPostUrl,把视频推送到流服务器
//                                val resultB: Boolean = SendVideoPostUtils.getJsonData("fileName=$fileUrl/$myFileName", SendVideoPostUrl)
//                                if (resultB) {
                                fileService.saveFile(request.table, request.busId, fileName, myFileName, fileUrl, Constants.FILE_TYPE_VED, request.fileSource)
                                list.add(FileSuccessParam(request.busId,request.table,fileName, myFileName,"$fileUrl/$myFileName"))
//                                } else {
//                                    return FileUploadResult(FileCode.FILE_VIDEO_LIU_FAIL)
//                                }
                            } else {
                                return FileUploadResult(FileCode.FILE_FTP_FAIL)
                            }
                        } else {
                            return FileUploadResult(FileCode.FILE_VIDEO_LARGE_FAIL)
                        }
                    } else { // 15兆
                        val vedSize = 30 * 1024 * 1024.toLong()
                        if (it.size <= vedSize) {
                            val result = FtpUtil.uploadFile(fileUrl, myFileName, FtpUtil.byte2Input(it.bytes))
                            if (result) {
                                fileService.saveFile(request.table, request.busId, fileName, myFileName, fileUrl, Constants.FILE_TYPE, request.fileSource)
                                list.add(FileSuccessParam(request.busId,request.table,fileName, myFileName,"$fileUrl/$myFileName"))
                            } else {
                                return FileUploadResult(FileCode.FILE_FTP_FAIL)
                            }
                        } else {
                            return FileUploadResult(FileCode.FILE_FILE_LARGE_FAIL)
                        }
                    }
                } catch (e: Exception) {
                    return FileUploadResult(FileCode.FILE_FAIL)
                }
            } else {
                return FileUploadResult(FileCode.FILE_FAIL)
            }
        }
        return return FileUploadResult(FileCode.FILE_SUCCESS,list)
    }
    /**
     * @Author 强周亮
     * @Description 视频播放
     * @Date 19:16 2019/12/18
     **/
    @GetMapping("playVideo")
    override fun playVideo(request: PlayRadioRequest) {
        val ftp = FtpUtil.fTPClient
        ftp?.let {
            try {
                if (FTPReply.isPositiveCompletion(ftp.sendCommand("OPTS UTF8", "ON"))) {
                    LOCAL_CHARSET = "UTF-8"
                }
                ftp.controlEncoding = LOCAL_CHARSET
                val conf = FTPClientConfig("WINDOWS")
                conf.serverLanguageCode = "zh"
                ftp.setFileType(2)
                ftp.enterLocalPassiveMode()
                val reply = ftp.replyCode
                if (!FTPReply.isPositiveCompletion(reply)) {
                    ftp.disconnect()
                    return
                }
                val paths: String = request.fileUrl
                var pathss = ""
                val path: Array<String> = request.fileUrl.split("/".toRegex()).toTypedArray()
                val sum: Int = request.fileUrl.length
                if (path.size > 1) {
                    request.fileUrl = path[path.size - 1]
                    val length: Int = request.fileUrl.length
                    pathss = paths.subSequence(0, sum - length - 1).toString() + "/"
                }
                ftp.changeWorkingDirectory(pathss)
                val fs = ftp.listFiles()
                for (i in fs.indices) {
                    val ff = fs[i]
                    val fileNameTemp = String(ff.name.toByteArray(charset(LOCAL_CHARSET)), Charsets.ISO_8859_1)
                    if (ff.name != request.fileUrl) continue
                    response.resetBuffer()
                    response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileNameTemp, "utf-8"))
                    val `is` = ftp.retrieveFileStream(fileNameTemp)
                    response.setHeader("Accept-Ranges", "bytes")
                    response.contentType = "video/mpeg4; charset=utf-8"
                    var p = 0L
                    var l = 0L
                    l = ff.size
                    if (httpRequest.getHeader("Range") != null) {
                        response.status = 206
                        p = httpRequest.getHeader("Range").replace("bytes=".toRegex(), "").replace("-".toRegex(), "").toLong()
                    }
                    response.setHeader("Content-Length", java.lang.Long.toString(l - p))
                    if (p != 0L) {
                        response.setHeader("Content-Range", "bytes " + p + "-" + (l - 1L) + "/" + l)
                    }
                    response.contentType = "application/octet-stream"
                    response.setHeader("content-disposition", "attachment;filename=${request.fileUrl}")
                    val skip = `is`.skip(p)
                    val buffer = ByteArray(1024)
                    val os: OutputStream = response.outputStream
                    var len: Int
                    while (`is`.read(buffer).also { len = it } != -1) {
                        os.write(buffer, 0, len)
                    }
                    `is`.close()
                    os.flush()
                    os.close()
                }
                ftp.logout()
            } catch (e: IOException) {
                ftp.disconnect()
            } finally {
                ftp.disconnect()
            }
        }
    }

    //下载更新apk
    @GetMapping("downloadApk")
    override fun downloadApk(request: DownloadApkRequest): ResponseEntity<ByteArray?>? {
        val response = if (!StringHelper.isEmptyString(request.fileUrl)){
            download(DownloadFileRequest(request.fileUrl,"lzshzz.apk"))
        } else {
            //获取版本信息
            val apkInfo = fileService.getApkInfo(request.appFlag)
            download(DownloadFileRequest(apkInfo.fileUrl?:"","lzshzz.apk"))
        }

        if (response != null){
            if (request.downloadOrUpdate == "01") {
                //表示下载，更新下载次数
                fileService.updateNumber()
            }
        }

        return response
    }
}