package com.zdww.lzshzz.common.config

import com.zdww.lzshzz.common.Constants
import org.springframework.beans.BeansException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment


/**
 * @author 强周亮
 * @desc 项目初始化时配置的一些参数
 * @email 2538096489@qq.com
 * @time 2019/11/5 20:38
 */
@Configuration
class initProject: BeanPostProcessor {

    @Throws(BeansException::class)
    override fun postProcessBeforeInitialization(bean: Any?, beanName: String?): Any? {
        System.setProperty("jasypt.encryptor.password", Constants.JASYPT_ENCRYPTOR_PASSWORD)
        return bean
    }

    @Throws(BeansException::class)
    override fun postProcessAfterInitialization(bean: Any?, beanName: String?): Any? {
        return bean
    }
}