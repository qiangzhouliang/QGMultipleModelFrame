package com.zdww.lzshzz.model.auth

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "app_version")
class Apk : Serializable {
    /**
     * 主键
     */
    @get:Column(name = "PK_ID")
    @get:Id
    var pkId: String? = null
    /**
     * 版本号
     */
    @get:Column(name = "APP_VERSION")
    var version: String? = null
    /**
     * 是否强制更新
     */
    @get:Column(name = "FORCE_UPDATE")
    var forceUpdate: String? = null
    /**
     * 大小
     */
    @get:Column(name = "APP_SIZE")
    var apkSize: String? = null
    /**
     * 内容
     */
    @get:Column(name = "DESCRIPTION")
    var description: String? = null
    /**
     * 更新时间
     */
    @get:Column(name = "UPDATE_TIME")
    var updateTime: String? = null
    /**
     * 文件地址
     */
    @get:Column(name = "FILE_URL")
    var fileUrl: String? = null
    /**
     * 是否发布
     */
    @get:Column(name = "IS_PUBLISH")
    var pubLish: String? = null
    /**
     * android和iOS区分
     */
    @get:Column(name = "APP_FLAG")
    var appFlag: String? = null
}