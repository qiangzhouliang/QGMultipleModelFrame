package com.qzl.lzshzz.ucenter.auth.dao

import com.qzl.lzshzz.model.auth.SysLog
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author 强周亮
 * @desc 日志操作类
 * @email 2538096489@qq.com
 * @time 2019/11/7 11:19
 */
interface SysLogDao : JpaRepository<SysLog, String> {

}
