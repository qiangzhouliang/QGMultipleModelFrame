package com.qzl.lzshzz.model.auth

import com.qzl.tools.utils.TimeHelper
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "app_exception_log")
@ApiModel(value = "app异常实体类")
open class AppExceptionLogEntity {
    @ApiModelProperty(value = "主键",required = true)
    @get:Id
    @get:Column(name = "ID")
    @get:GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解/生成32位UUID
    @get:GeneratedValue(generator="idGenerator")
    var id: String? = null
    @ApiModelProperty(value = "产品品牌")
    @get:Basic
    @get:Column(name = "BRAND")
    var brand: String? = null
    @ApiModelProperty(value = "产品型号")
    @get:Basic
    @get:Column(name = "PRODUCT")
    var product: String? = null
    @ApiModelProperty(value = "发布版本")
    @get:Basic
    @get:Column(name = "RELEASES")
    var releases: String? = null
    @ApiModelProperty(value = "Android API版本（String类型）")
    @get:Basic
    @get:Column(name = "SDK")
    var sdk: String? = null
    @ApiModelProperty(value = "异常信息")
    @get:Basic
    @get:Column(name = "EX_MSG")
    var exMsg: String? = null

    @get:Basic
    @get:Column(name = "LAT")
    var lat: String? = null

    @get:Basic
    @get:Column(name = "LNG")
    var lng: String? = null
    @ApiModelProperty(value = "创建时间")
    @get:Basic
    @get:Column(name = "CREATE_TIME")
    var createTime: String? = TimeHelper.currentTime
    @ApiModelProperty(value = "APP异常-反馈信息")
    @get:Basic
    @get:Column(name = "FEED_BACK_MSG")
    var feedbackmsg: String? = null
}

