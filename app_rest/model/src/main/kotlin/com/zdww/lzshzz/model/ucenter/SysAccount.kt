package com.zdww.lzshzz.model.ucenter

import org.hibernate.annotations.GenericGenerator
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * Created by admin on 2018/3/19.
 */
@Entity
@Table(name = "sys_account")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
open class SysAccount : Serializable {
    //账号id
    @get:Id
    @get:Column(name = "USER_ACCT_ID")
    var userAcctId: String = ""

    @get:Column(name = "LOGIN_ACCOUNT")
    var loginAccount: String? = ""
    @get:Column(name = "LOGIN_PASSWORD")
    var loginPassword: String? = ""
    //    人员姓名
    @get:Column(name = "USER_NAME")
    var userName: String? = ""
    //    性别:0:男  1:女  2:未知
    @get:Column(name = "USER_SEX")
    var userSex: String? = ""
    //职位
    @get:Column(name = "USER_POSITION")
    var userPosition: String? = ""
    //联系电话
    @get:Column(name = "USER_TELE")
    var userTele: String? = ""
    //住宅电话
    @get:Column(name = "USER_HOME_TELE")
    var userHomeTele: String? = ""
    //邮箱
    @get:Column(name = "USER_EMAIL")
    var userEmail: String? = ""
    //状态：0无效1有效
    @get:Column(name = "USER_STATE")
    var userState: String? = ""
    //创建时间
    @get:Column(name = "CREATE_TIME")
    var createTime: String? = ""
    //操作账号ID
    @get:Column(name = "OPE_ACCT_ID")
    var opeAcctId: String? = ""
    @get:Column(name = "SET_ID")
    var setId: String? = ""

    @get:Column(name = "USER_TYPE")
    var userType: String? = ""
    @get:Column(name = "DEVICE_ID")
    var deviceId: String? = ""
    @get:Column(name = "UPDATE_TIME")
    var updateTime: String? = ""
    @get:Column(name = "ADMIN_POST_SORT")
    var adminPostSort: String? = ""
    @get:Column(name = "POST_SORT")
    var postSort: String? = ""
    @get:Column(name = "AREA_CODE")
    var areaCode: String? = ""
    //所属部门ID
    @get:Column(name = "DEPT_ID")
    var deptId: String? = ""
}
