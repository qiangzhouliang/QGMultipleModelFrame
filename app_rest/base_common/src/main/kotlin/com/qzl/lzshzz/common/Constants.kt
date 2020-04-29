package com.qzl.lzshzz.common

object Constants {
    /**
     * 发送短信-约束
     */
    val SMS_CONSTRAINT = "【甘肃省河湖长制信息管理平台】"

    //限制密码错误次数
    const val LOGIN_NUM = 5
    //限制多长时间后redis过期
    const val REDIS_DEAD_LINE = 60 * 10.toLong()

    /**
     * 文件类型图片
     */
    const val FILE_TYPE_IMG = "1"
    /**
     * 文件类型视屏
     */
    const val FILE_TYPE_VED = "2"
    /**
     * 文件类型(文档)
     */
    const val FILE_TYPE = "3"
    const val JASYPT_ENCRYPTOR_PASSWORD = "Ab0hU6KRzycTBiOPfa4Hgxmq1lSj8LJt-LZSHZZ"
}