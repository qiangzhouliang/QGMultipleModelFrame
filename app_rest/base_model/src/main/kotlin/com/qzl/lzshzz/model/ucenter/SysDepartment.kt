package com.qzl.lzshzz.model.ucenter

import org.hibernate.annotations.GenericGenerator
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * @author 强周亮
 * @desc 部门实体表
 * @email 2538096489@qq.com
 * @time 2019/11/7 16:22
 */
@Entity
@Table(name = "sys_department")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
open class SysDepartment : Serializable {
    //部门id
    @get:Id
    @get:Column(name = "DEPT_ID")
    var deptId: String = ""

    //部门名称
    @get:Column(name = "DEPT_NAME")
    var deptName: String? = ""

    //部门编码
    @get:Column(name = "DEPT_CODE")
    var deptCode: String? = ""

    //父集部门编码
    @get:Column(name = "PARENT_DEPT_CODE")
    var parentDeptCode: String? = ""

    //状态：0无效1有效
    @get:Column(name = "DEPT_STATE")
    var deptState: String? = ""

    //排序
    @get:Column(name = "DEPT_SEQ")
    var deptSeq: String? = ""

    //行政区划
    @get:Column(name = "XZQH")
    var xzqh: String? = ""

    //行政区划
    @get:Column(name = "SET_ID")
    var setId: String? = ""

    //部门区分
    @get:Column(name = "NODE_TYPE")
    var nodeType: String? = ""
}