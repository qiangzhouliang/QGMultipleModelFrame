package com.zdww.lzshzz.service_file.util

import com.zdww.lzshzz.service_file.util.ReadProperties.getPropertyByStr
import org.apache.commons.logging.LogFactory
import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPClient
import org.apache.commons.net.ftp.FTPReply
import java.io.*
import java.net.SocketException

/**
 * ftp工具类
 * @author lijj
 */
object FtpUtil {
    private val logger = LogFactory.getLog(FtpUtil::class.java)
    private const val UTF = "OPTS UTF8"
    private const val ON = "ON"
    /**
     * 本地字符编码
     */
    private var LOCAL_CHARSET = "GBK"
    /**
     * FTP协议里面，规定文件名编码为iso-8859-1
     */
    private val SERVER_CHARSET = "ISO-8859-1"//				logger.info("FTP连接成功。");// 连接FTP服务器
    //FTP IP地址
    val ftpHost by lazy { getPropertyByStr("ftpHost") }
    //FTP登录用户名
    val ftpUserName by lazy { getPropertyByStr("ftpUserName")}
    //FTP 登录密码
    val ftpPassword by lazy { getPropertyByStr("ftpPassword")}
    //FTP端口 默认为21
    val ftpPort by lazy { getPropertyByStr("ftpPort").toInt()}
    val ftpPath by lazy { getPropertyByStr("ftpPath")}

    /**
     * @Title: getFTPClient
     * @Description:  (获取FTPClient对象，连接FTP服务)
     * @author hanjp
     * @return FTPClient    返回类型
     */
    val fTPClient: FTPClient?
        get() {
            var ftpClient: FTPClient? = null
            try {
                ftpClient = FTPClient()
                // 连接FTP服务器
                ftpClient.connect(ftpHost, ftpPort)
                // 登录FTP服务器
                ftpClient.login(ftpUserName, ftpPassword)
                if (!FTPReply.isPositiveCompletion(ftpClient.replyCode)) {
                    logger.info("未连接到FTP，用户名或密码错误。")
                    ftpClient.disconnect()
                }
            } catch (e: SocketException) {
                logger.info("FTP的IP地址可能错误，请正确配置。")
            } catch (e: IOException) {
                logger.info("FTP的端口错误,请正确配置。")
            }
            return ftpClient
        }

    /**
     * @Title: downloadFtpFileByte
     * @Description: 下载文件byte
     * @author hanjp
     * @param @param fileName  文件在ftp服务下的全路径
     * @date 2018年7月10日 下午7:48:13
     * @throws
     */
    fun downloadFtpFileByte(fileName: String): ByteArray? {
        var fileName = fileName
        var ftpClient: FTPClient? = null
        val paths = fileName
        var pathss = ""
        val path = fileName.split("/").toTypedArray()
        val sum = fileName.length
        if (path.size > 1) {
            fileName = path[path.size - 1]
            val length = fileName.length
            pathss = paths.subSequence(0, sum - length - 1).toString() + "/"
        }
        val ftpPath = pathss
        try {
            ftpClient = fTPClient
            // 设置上传文件的类型为二进制类型
            // 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
            if (FTPReply.isPositiveCompletion(ftpClient!!.sendCommand(UTF, ON))) {
                LOCAL_CHARSET = "UTF-8"
            }
            ftpClient.controlEncoding = LOCAL_CHARSET
            // 设置被动模式
            ftpClient.enterLocalPassiveMode()
            // 设置传输的模式
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE)
            // 上传文件
            //对中文文件名进行转码，否则中文名称的文件下载失败
            val fileNameTemp = String(fileName.toByteArray(charset(LOCAL_CHARSET)), charset(SERVER_CHARSET))
            ftpClient.changeWorkingDirectory(ftpPath)
            val retrieveFileStream = ftpClient.retrieveFileStream(fileNameTemp)
            // 第一种方式下载文件(推荐)
            /* File localFile = new File(localPath + File.separatorChar + fileName);
				  OutputStream os = new FileOutputStream(localFile);
				  ftpClient.retrieveFile(fileName, os); os.close();*/
            // 第二种方式下载：将输入流转成字节，再生成文件，这
            var input2byte: ByteArray? = null
            if (retrieveFileStream != null) {
                input2byte = input2byte(retrieveFileStream)
            }
            ftpClient.logout()
            return input2byte
        } catch (e: FileNotFoundException) {
            logger.error("没有找到" + ftpPath + "文件")
            e.printStackTrace()
        } catch (e: SocketException) {
            logger.error("连接FTP失败.")
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
            logger.error("文件读取错误。")
            e.printStackTrace()
        }
        return ByteArray(100)
    }

    /**
     * @Title: downloadFtpFile
     * @Description:  (下载文件file)
     * @author hanjp
     * @param @param fileName    文件在ftp服务下的全路径
     * @return void    返回类型
     * @date 2018年7月10日 下午7:49:59
     * @throws
     */
    fun downloadFtpFile(fileName: String) {
        var ftpClient: FTPClient? = null
        try {
            ftpClient = fTPClient
            // 设置上传文件的类型为二进制类型
// 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
            if (FTPReply.isPositiveCompletion(ftpClient!!.sendCommand(UTF, ON))) {
                LOCAL_CHARSET = "UTF-8"
            }
            ftpClient.controlEncoding = LOCAL_CHARSET
            ftpClient.enterLocalPassiveMode()
            // 设置传输的模式
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE)
            //对中文文件名进行转码，否则中文名称的文件下载失败
            val fileNameTemp = String(fileName.toByteArray(charset(LOCAL_CHARSET)), charset(SERVER_CHARSET))
            ftpClient.changeWorkingDirectory(ftpPath)
            val retrieveFileStream = ftpClient.retrieveFileStream(fileNameTemp)
            // 第一种方式下载文件(推荐)
/*
			 * File localFile = new File(localPath + File.separatorChar +
			 * fileName); OutputStream os = new FileOutputStream(localFile);
			 * ftpClient.retrieveFile(fileName, os); os.close();
			 */
// 第二种方式下载：将输入流转成字节，再生成文件，这种方式方便将字节数组直接返回给前台jsp页面
//			byte[] input2byte = input2byte(retrieveFileStream);
//byte2File(input2byte, localPath, fileName);
            retrieveFileStream?.close()
        } catch (e: FileNotFoundException) {
            logger.error("没有找到" + ftpPath + "文件")
            e.printStackTrace()
        } catch (e: SocketException) {
            logger.error("连接FTP失败.")
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
            logger.error("文件读取错误。")
            e.printStackTrace()
        } finally {
            if (ftpClient != null && ftpClient.isConnected) {
                try { //退出登录
                    ftpClient.logout()
                    //关闭连接
                    ftpClient.disconnect()
                } catch (e: IOException) {
                }
            }
        }
    }

    /**
     *
     * @Title: uploadFile
     * @Description: TODO(向FTP服务器上传文件)
     * @author hanjp
     * @param @param filePath	保存文件路径
     * @param @param filename	文件名称
     * @param @param input		二进制流文件
     * @param @return    参数
     * @return boolean    返回类型
     * @date 2018年7月10日 下午7:51:04
     * @throws
     */
    fun uploadFile(filePath: String, filename: String, input: InputStream?): Boolean {
        var filename = filename
        var result = false
        var ftpClient: FTPClient? = null
        try {
            val reply: Int
            ftpClient = fTPClient
            reply = ftpClient!!.replyCode
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect()
                return result
            }
            // 切换到上传目录
            if (!ftpClient.changeWorkingDirectory(filePath)) { // 如果目录不存在创建目录
                val dirs = filePath.split("/").toTypedArray()
                val tempPath = StringBuffer()
                for (dir in dirs) {
                    if (null == dir || "" == dir) {
                        continue
                    }
                    tempPath.append("/$dir")
                    if (!ftpClient.changeWorkingDirectory(tempPath.toString())) {
                        if (!ftpClient.makeDirectory(tempPath.toString())) {
                            return result
                        } else {
                            ftpClient.changeWorkingDirectory(tempPath.toString())
                        }
                    }
                }
            }
            // 设置上传文件的类型为二进制类型
            // 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
            if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(UTF, ON))) {
                LOCAL_CHARSET = "UTF-8"
            }
            ftpClient.controlEncoding = LOCAL_CHARSET
            // 设置被动模式
            ftpClient.enterLocalPassiveMode()
            // 设置传输的模式
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE)
            // 上传文件
            filename = String(filename.toByteArray(charset(LOCAL_CHARSET)), charset(SERVER_CHARSET))
            if (!ftpClient.storeFile(filename, input)) {
                return result
            }
            input?.close()
            result = true
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (ftpClient != null && ftpClient.isConnected) {
                ftpClient.logout()
                //关闭连接
                ftpClient.disconnect()
            }
        }
        return result
    }

    /**
     * @Author 强周亮
     * @Description 删除文件
     * @Date 21:35 2019/11/27
     **/
    fun deleteFile(filename: String): Boolean {
        var filename = filename
        val paths = filename
        var pathss = ""
        val path = filename.split("/").toTypedArray()
        val sum = filename.length
        if (path.size > 1) {
            filename = path[path.size - 1]
            val length = filename.length
            pathss = paths.subSequence(0, sum - length - 1).toString() + "/"
        }
        val pathname = pathss
        var flag = false
        var ftpClient: FTPClient? = FTPClient()
        try {
            ftpClient = fTPClient
            // 验证FTP服务器是否登录成功
            val replyCode = ftpClient?.replyCode
            if (!replyCode?.let { FTPReply.isPositiveCompletion(it) }!!) {
                return flag
            }
            // 切换FTP目录
            // 设置上传文件的类型为二进制类型
            // 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
            if (ftpClient?.sendCommand(UTF, ON)?.let { FTPReply.isPositiveCompletion(it) }!!) {
                LOCAL_CHARSET = "UTF-8"
            }
            ftpClient.controlEncoding = LOCAL_CHARSET
            ftpClient.enterLocalPassiveMode() // 设置被动模式
            // 设置传输的模式
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE)
            //对中文名称进行转码
            filename = String(filename.toByteArray(charset(LOCAL_CHARSET)), charset(SERVER_CHARSET))
            ftpClient.changeWorkingDirectory(pathname)
            ftpClient.dele(filename)
            flag = true
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (ftpClient?.isConnected == true) {
                //退出登录
                ftpClient.logout()
                //关闭连接
                ftpClient.disconnect()
            }
        }
        return flag
    }

    /**
     *
     * @Title: byte2Input
     * @Description: 将字节数组转换为输入流
     * @author hanjp
     * @param @param buf
     * @param @return    参数
     * @return InputStream    返回类型
     * @date 2018年9月4日 下午4:04:11
     * @throws
     */
    fun byte2Input(buf: ByteArray?): InputStream {
        return ByteArrayInputStream(buf)
    }

    /**
     *
     * @Title: input2byte
     * @Description: 将输入流转为byte[]
     * @author hanjp
     * @param @param inStream
     * @param @return
     * @param @throws IOException    参数
     * @return byte[]    返回类型
     * @date 2018年9月4日 下午4:04:01
     * @throws
     */
    @Throws(IOException::class)
    fun input2byte(inStream: InputStream): ByteArray {
        val swapStream = ByteArrayOutputStream()
        val i = 100
        val buff = ByteArray(100)
        var rc = 0
        while (inStream.read(buff, 0, i).also { rc = it } > 0) {
            swapStream.write(buff, 0, rc)
        }
        return swapStream.toByteArray()
    }

    /**
     *
     * @Title: byte2File
     * @Description: 将byte[]转为文件
     * @author hanjp
     * @param @param buf		二进制流
     * @param @param filePath
     * @param @param fileName    参数
     * @return void    返回类型
     * @date 2018年9月4日 上午11:01:28
     * @throws
     */
    fun byte2File(buf: ByteArray?, filePath: String, fileName: String) {
        var bos: BufferedOutputStream? = null
        var fos: FileOutputStream? = null
        val file: File
        try {
            val dir = File(filePath)
            if (!dir.exists() && dir.isDirectory) {
                val mkdirs = dir.mkdirs()
                if (!mkdirs) {
                    println("创建文件夹失败！！！")
                }
            }
            file = File(filePath + File.separator + fileName)
            fos = FileOutputStream(file)
            bos = BufferedOutputStream(fos)
            bos.write(buf)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (bos != null) {
                try {
                    bos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            if (fos != null) {
                try {
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}