package com.zdww.lzshzz.model.ucenter

import java.io.Serializable
import javax.persistence.*

/**
 * @author 强周亮
 * @desc BasRiverHead 实体
 * @email 2538096489@qq.com
 * @time 2019/11/7 17:32
 */
@Table(name = "bas_river_head")
@Entity
class BasRiverHead : Serializable {
    /**
     * 河长id
     */
    @get:Id
    @get:Column(name = "RIVER_HEAD_ID")
    var riverHeadId: String? = ""
    /**
     * 登录账号
     */
    @get:Column(name = "LOGIN_NAME")
    var loginName: String? = ""
    /**
     * 河长姓名
     */
    @get:Column(name = "NAME")
    var name: String? = ""
    /**
     * 联系电话
     */
    @get:Column(name = "LINK_TEL")
    var linkTel: String? = ""
    /**
     * 河流职务
     */
    @get:Column(name = "RIVER_POST")
    var riverPost: String? = ""
    /**
     * 行政职务
     */
    @get:Column(name = "ADMIN_POST")
    var adminPost: String? = ""
    /**
     * 河长级别
     */
    @get:Column(name = "HEAD_LEVEL")
    var headLevel: String? = ""
    /**
     * 上级河长姓名
     */
    @get:Column(name = "PREV_RIVER_HEAD_NAME")
    var prevRiverHeadName: String? = ""
    /**
     * 上级河长id
     */
    @get:Column(name = "PREV_RIVER_HEAD_ID")
    var prevRiverHeadId: String? = ""
    /**
     * 用户表的id
     */
    @get:Column(name = "ACCOUNT_ID")
    var accountId: String? = ""
    /**
     * 地区编号
     */
    @get:Column(name = "AREA_CODE")
    var areaCode: String? = ""
    /**
     * 0：河长；1：治理人员
     */
    @get:Column(name = "STATE")
    var state: String? = ""
    /**
     * 创建时间
     */
    @get:Column(name = "CREATE_TIME")
    var createTime: String? = ""
    /**
     * 工作电话
     */
    @get:Column(name = "WORK_TEL")
    var workTel: String? = ""
    /**
     * 邮箱
     */
    @get:Column(name = "EMAIL")
    var email: String? = ""
    /**
     * 经度
     */
    @get:Column(name = "LON")
    var lon: String? = ""
    /**
     * 纬度
     */
    @get:Column(name = "LAT")
    var lat: String? = ""
    /**
     * 部门code
     */
    @get:Column(name = "DEPT_CODE")
    var deptCode: String? = ""
    /**
     * 行政级别编号
     */
    @get:Column(name = "ADMIN_POST_SORT")
    var adminPostSort: Int? = null
    /**
     * 地区名称
     */
    @get:Column(name = "AREA_NAME")
    var areaName: String? = ""
    /**
     * 身份证号
     */
    @get:Column(name = "HEAD_ID_NUMBER")
    var headIdNumber: String? = ""
    /**
     * 主要领导
     */
    @get:Column(name = "MAIN_LEADER")
    var mainLeader: String? = ""
    /**
     * 行政级别
     */
    @get:Column(name = "ADMIN_LEVEL")
    var adminLevel: String? = ""
    /**
     * 是否已公告
     */
    @get:Column(name = "IS_NOTICE")
    var isNotice: String? = ""
    /**
     * 备注
     */
    @get:Column(name = "REMARKS")
    var remarks: String? = ""
    /**
     * 是否专职
     */
    @get:Column(name = "IS_FULL_TIME")
    var isFullTime: String? = ""
    /**
     * 状态：0已删除数据 1正常数据
     */
    @get:Column(name = "HEAD_STATE")
    var headState: String? = ""
    /**
     * 河长位置排序字段
     */
    @get:Column(name = "POST_SORT")
    var postSort: Int? = null
    /**
     * 更新时间
     */
    @get:Column(name = "UPDATE_TIME")
    var updateTime: String? = ""
    /**
     * 单位类别（1：党委、政府，2：人大、政协，9：其他）
     */
    @get:Column(name = "UNIT_CATEGORY")
    var unitCategory: String? = ""
    /**
     * 生效时间
     */
    @get:Column(name = "ENTRY_INTO_FORCE_TIME")
    var entryIntoForceTime: String? = ""
    /**
     * 生效时间
     */
    @get:Column(name = "RIVER_LAKE_HEAD")
    var riverLakeHead: String? = ""

    /**
     * 水利部河长编码
     */
    @get:Column(name = "SLB_HEAD_CODE")
    var slbHeadCode: String? = ""
    /**
     * 性别（1男0女）
     */
    @get:Column(name = "IS_GENDER")
    var isGender: String? = ""
    /**
     * 出生日期
     */
    @get:Column(name = "DATE_OF_BIRTH")
    var dateOfBirth: String? = ""
    /**
     * 学历（0：小学、1：初中、2：高中、3：专科教育、4：本科教育、5：研究生、6：博士生）
     */
    @get:Column(name = "EDUCATION")
    var education: String? = ""

    @get:Transient
    var fileUrl:String? = ""

    @get:Transient
    var fileNewName:String? = ""

    //河长头像
    @get:OneToMany(mappedBy = "busId")
    var basFileHead: List<BasFileHead>? = null
}
