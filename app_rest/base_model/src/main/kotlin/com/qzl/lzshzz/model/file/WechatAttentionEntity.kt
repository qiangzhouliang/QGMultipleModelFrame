package com.qzl.lzshzz.model.file

import com.qzl.tools.utils.TimeHelper
import javax.persistence.*

@Entity
@Table(name = "wechat_attention")
data class WechatAttentionEntity(
    @get:Id
    @get:Column(name = "PK_ID")
    var pkId: String? = null,
    @get:Basic
    @get:Column(name = "AT_NUMBER")
    var atNumber: Int? = 0,
    @get:Basic
    @get:Column(name = "APK_NUMBER")
    var apkNumber: Int? = 0,
    @get:Basic
    @get:Column(name = "UPDATE_TIME")
    var updateTime: String? = TimeHelper.currentTime)

