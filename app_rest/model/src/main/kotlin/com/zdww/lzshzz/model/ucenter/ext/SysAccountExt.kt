package com.zdww.lzshzz.model.ucenter.ext


import com.zdww.lzshzz.model.ucenter.BasRiverHead
import com.zdww.lzshzz.model.ucenter.SysAccount
import com.zdww.lzshzz.model.ucenter.SysDepartment

/**
 * @author 强周亮
 * @desc 用户信息扩展
 * @email 2538096489@qq.com
 * @time 2019/11/7 17:04
 */
class SysAccountExt : SysAccount() {
    //部门信息
    var department: SysDepartment? = null
    //河长信息
    var basRiverHead: BasRiverHead? = null
    //河长头像
    var headImage: List<String>? = null

    //角色信息
    var roleId: String? = ""
    //行政区划名称
    var areaName: String? = ""
    //行政区划级别
    var areaLevel: String? = ""
}
