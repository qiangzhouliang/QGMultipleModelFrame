package com.zdww.lzshzz.ucenter.exception

import com.zdww.lzshzz.common.exception.ExceptionCatch
import com.zdww.lzshzz.common.model.response.CommonCode
import org.springframework.security.access.AccessDeniedException
import org.springframework.web.bind.annotation.ControllerAdvice

/**
 * @author 强周亮
 * @desc 除了统一异常处理以外的异常在这儿处理
 * @email 2538096489@qq.com
 * @time 2019/11/13 10:52
 */
@ControllerAdvice//控制器增强
class CustomExceptionCatch : ExceptionCatch() {
    companion object {
        init {
            //除了CustomException以外的异常类型及对应的错误代码在这里定义,，如果不定义则统一返回固定的错误信息
            builder.put(AccessDeniedException::class.java, CommonCode.UNAUTHORISE)
        }
    }
}