package com.zdww.lzshzz.model.auth

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "SYS_LOG")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
class SysLog(
        @get:Column(name = "LOG_MODEL")
        var logModel: String,
        @get:Column(name = "LOG_TYPE")
        var logType: String,
        @get:Column(name = "LOG_CONTENT")
        var logContent: String? = null,
        @get:Column(name = "USER_ACCT_ID")
        var userAcctId: String? = null,

        @get:Column(name = "LOGIN_ACCOUNT")
        var loginAccount: String? = null,

        @get:Column(name = "OPERATOR_TIME")
        var operatorTime: String? = null,
        @get:Column(name = "LOG_IP")
        var logIp: String? = null
) {
    //    @ApiModelProperty(value = "系统日志ID")
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @get:Column(name = "LOG_ID")
    var logId: String? = null
}