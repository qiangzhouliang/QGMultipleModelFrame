package com.zdww.lzshzz.model.ucenter

import org.hibernate.annotations.GenericGenerator
import java.io.Serializable
import javax.persistence.*

/**
 *
 * 类描述 : 用户角色实体
 * @version 1.0.0
 */
@Entity
@Table(name = "SYS_ROLE_ACCT_REL")
class SysRoleAcctRel : Serializable {
    @get:Id
    @get:GeneratedValue(generator = "system-uuid")
    @get:GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    @get:Column(name = "ROLE_ACCT_ID")
    var roleAcctId: String? = null

    @get:Column(name = "ROLE_ID")
    var roleId: String? = null

    @get:Column(name = "USER_ACCT_ID")
    var userAcctId: String? = null
}
