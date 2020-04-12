package com.zdww.lzshzz.ucenter.auth.service

import com.zdww.lzshzz.model.auth.SysLog
import com.zdww.lzshzz.model.auth.request.LogRequest
import com.zdww.lzshzz.ucenter.auth.dao.SysLogDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author 强周亮
 * @desc 公共service
 * @email 2538096489@qq.com
 * @time 2019/11/6 17:30
 */
@Service
class SysLogService {
    @Autowired
    private lateinit var sysLogDao: SysLogDao

    /**
     * @Author 强周亮
     * @Description 保存系统日志
     * @Date 18:12 2019/11/6
     **/
    fun saveSysLog(req: LogRequest): SysLog {
        val sysLog = SysLog(req.logModel, req.logType, req.logContent, req.userAcctId,
                req.loginAccount, req.operatorTime, req.logIp)

        return sysLogDao.save(sysLog)
    }
}