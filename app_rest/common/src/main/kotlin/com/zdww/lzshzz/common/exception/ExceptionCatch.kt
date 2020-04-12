package com.zdww.lzshzz.common.exception

import com.google.common.collect.ImmutableMap
import com.zdww.lzshzz.common.model.response.CommonCode
import com.zdww.lzshzz.common.model.response.ResponseResult
import com.zdww.lzshzz.common.model.response.ResultCode
import org.slf4j.LoggerFactory
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

/**
 * 统一异常捕获类
 * @author Administrator
 * @version 1.0
 * @create 2018-09-14 17:32
 */
@ControllerAdvice//控制器增强
class ExceptionCatch {

    //捕获CustomException此类异常
    @ExceptionHandler(CustomException::class)
    @ResponseBody
    fun customException(customException: CustomException): ResponseResult {
        //记录日志
        LOGGER.error("catch exception:{}", customException.message)
        val resultCode = customException.resultCode
        return ResponseResult(resultCode)
    }

    //捕获Exception此类异常
    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun exception(exception: Exception): ResponseResult {
        //记录日志
        LOGGER.error("catch exception:{}", exception.message)
        if (EXCEPTIONS == null) {
            EXCEPTIONS = builder.build()//EXCEPTIONS构建成功
        }
        //从EXCEPTIONS中找异常类型所对应的错误代码，如果找到了将错误代码响应给用户，如果找不到给用户响应99999异常
        val resultCode = EXCEPTIONS!![exception.javaClass]
        return resultCode?.let { ResponseResult(it) } ?: //返回99999异常
        ResponseResult(CommonCode.SERVER_ERROR)
    }

    companion object {

        private val LOGGER = LoggerFactory.getLogger(ExceptionCatch::class.java)

        //定义map，配置异常类型所对应的错误代码
        private var EXCEPTIONS: ImmutableMap<Class<out Throwable>, ResultCode>? = null
        //定义map的builder对象，去构建ImmutableMap
        public var builder: ImmutableMap.Builder<Class<out Throwable>, ResultCode> = ImmutableMap.builder()

        init {
            //定义异常类型所对应的错误代码
            builder.put(HttpMessageNotReadableException::class.java, CommonCode.INVALID_PARAM)
        }
    }
}
