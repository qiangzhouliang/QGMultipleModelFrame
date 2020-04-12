package com.zdww.lzshzz.ucenter.service

import com.zdww.lzshzz.model.ucenter.BasFileHead
import com.zdww.lzshzz.ucenter.dao.BasFileHeadDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author 强周亮
 * @desc 河长头像服务类
 * @email 2538096489@qq.com
 * @time 2019/11/7 17:18
 */
@Service
class BasFileHeadService {
    @Autowired
    private lateinit var basFileHeadDao: BasFileHeadDao

    //根据河长id查询河长头像信息
    fun getHeadImage(riverHeadId: String): List<BasFileHead>? {
        return basFileHeadDao.findByBusId(riverHeadId)
    }
}