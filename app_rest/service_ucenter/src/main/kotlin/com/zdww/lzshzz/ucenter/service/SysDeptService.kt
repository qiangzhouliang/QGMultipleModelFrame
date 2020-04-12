package com.zdww.lzshzz.ucenter.service

import com.zdww.lzshzz.common.model.response.CommonCode
import com.zdww.lzshzz.model.ucenter.SysDepartment
import com.zdww.lzshzz.model.ucenter.request.GetDeptByAreaCodeRequest
import com.zdww.lzshzz.model.ucenter.response.DeptResponse
import com.zdww.lzshzz.ucenter.dao.SysDeptDao
import com.zdww.lzshzz.util.AreaCodeUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SysDeptService {
    @Autowired
    lateinit var sysDeptDao: SysDeptDao

    //根据部门id查询部门信息
    fun getDeptInfo(deptId: String): SysDepartment? {
        return sysDeptDao.findByIdOrNull(deptId)
    }

    //根据行政区划查询部门信息
    fun getDeptInfoByAreaCode(areaCode: String): SysDepartment? {
        var dept: SysDepartment?
        when (areaCode.length) {
            9 -> {
                dept = sysDeptDao.findByXzqhAndNodeType(areaCode,"5")
                if (dept == null) {
                    dept = sysDeptDao.findByXzqhAndNodeType(AreaCodeUtil.parentAreaCode(areaCode), "5")
                }
            }

            12 -> {
                dept = sysDeptDao.findByXzqhAndNodeType(AreaCodeUtil.parentAreaCode(areaCode), "5")
                if (dept == null) {
                    dept = sysDeptDao.findByXzqhAndNodeType(AreaCodeUtil.parentAreaCode(AreaCodeUtil.parentAreaCode(areaCode)), "5")
                }
            }
            else ->
                dept = sysDeptDao.findByXzqhAndNodeType(areaCode, "5")
        }
        return dept
    }

    fun getDeptInfoByAreaCodeAndSetId(request: GetDeptByAreaCodeRequest): DeptResponse? {
        val department = sysDeptDao.findByXzqhAndSetId(request.areaCode, "1")
        return if (department != null && department.isNotEmpty()) {
            DeptResponse(CommonCode.SUCCESS,department)
        } else {
            DeptResponse(CommonCode.SUCCESS)
        }
    }
}