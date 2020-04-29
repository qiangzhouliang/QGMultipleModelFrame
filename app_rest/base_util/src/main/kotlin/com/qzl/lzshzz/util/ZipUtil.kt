package com.qzl.lzshzz.util


import net.lingala.zip4j.core.ZipFile
import net.lingala.zip4j.exception.ZipException

object ZipUtil {

    /**
     * 解压zip文件
     * @param zipFilePath
     * @param targetPath
     * @throws ZipException
     */
    @Throws(Exception::class)
    fun unzip(zipFilePath: String, targetPath: String) {
        val zipFile = ZipFile(zipFilePath)
        zipFile.extractAll(targetPath)
    }

    /**
     * 解压zip文件（带密码）
     * @param zipFilePath
     * @param targetPath
     * @param password
     * @throws ZipException
     */
    @Throws(Exception::class)
    fun unzip(zipFilePath: String, password: String, targetPath: String) {
        val zipFile = ZipFile(zipFilePath)
        if (zipFile.isEncrypted) {
            zipFile.setPassword(password)
        }
        zipFile.extractAll(targetPath)
    }

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        unzip("F:\\develop\\upload\\upload.zip", "F:\\develop\\upload\\zip\\")
    }
}
