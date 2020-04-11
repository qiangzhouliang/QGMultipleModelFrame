package qzl.com.model.user_info

/**
 * @desc
 * @author qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2019/11/4 14:45
 * @class lzshzz_android
 * @package com.zdww.loginmodel.model
 */
data class UserInfo(
    val accountNonExpired: Boolean,
    val accountNonLocked: Boolean,
    val adminPostSort: Any,
    val areaCode: String,
    val areaName: String,
    val areaLevel: String,
    val authorities: List<Any>,
    val basRiverHead: BasRiverHead?,
    val createTime: String,
    val credentialsNonExpired: Boolean,
    val department: Department?,
    val deviceId: String,
    val enabled: Boolean,
    val headImage: List<String>?,
    val loginAccount: String,
    val loginPassword: String,
    val opeAcctId: String?,
    val password: String?,
    val postSort: Any,
    val roleId: String,
    val setId: String,
    val updateTime: String?,
    val userAcctId: String,
    val userEmail: String?,
    val userHomeTele: String?,
    val userName: String,
    val userPosition: String?,
    val userSex: String,
    val userState: String,
    val userTele: String,
    val userType: String?,
    val username: String
)

data class BasRiverHead(
    val accountId: String,
    val adminLevel: String,
    val adminPost: String,
    val adminPostSort: Int,
    val areaCode: String,
    val areaName: String,
    val createTime: String,
    val dateOfBirth: String?,
    val deptCode: String,
    val education: String?,
    val email: String,
    val entryIntoForceTime: String,
    val headIdNumber: String?,
    val headLevel: String,
    val headState: String,
    val lat: String,
    val linkTel: String,
    val loginName: String,
    val lon: String,
    val mainLeader: String,
    val name: String,
    val postSort: Int,
    val prevRiverHeadId: String?,
    val prevRiverHeadName: String?,
    val remarks: String,
    val riverHeadId: String,
    val riverLakeHead: String,
    val riverPost: String,
    val slbHeadCode: String?,
    val state: String,
    val unitCategory: String,
    val updateTime: String,
    val workTel: String
)

data class Department(
    val deptCode: String,
    val deptId: String,
    val deptName: String,
    val deptSeq: String,
    val deptState: String,
    val parentDeptCode: String,
    val setId: String?,
    val xzqh: String
)