package com.zdww.lzshzz.api.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.EnvironmentAware
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


/**
 * @ClassName SwaggerConfig
 * @Description 接口文档配置
 * @Author qzl
 * @Date 2019-03-06 12:08
 */
@Configuration
@EnableSwagger2
class SwaggerConfig: WebMvcConfigurationSupport(),EnvironmentAware {
    //是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
    @Value(value = "\${swagger.enabled}")
    internal var swaggerEnabled: Boolean = true
    private var environment: Environment? = null

    @Bean
    fun createRestApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                // 是否开启
                .enable(swaggerEnabled).select()
                // 扫描的路径包
                .apis(RequestHandlerSelectors.basePackage("com.zdww"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any()).build().pathMapping("/")
    }

    //设置静态资源
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/")
        //springboot 集成swagger2.2后静态资源404，添加如下两行配置
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
        super.addResourceHandlers(registry)
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("兰州市河长制接口使用规范")
                .description("kotlin+springboot+gradle编写接口")
                // 作者信息
                .contact(Contact("强周亮", "", "2538096489@qq.com"))
                .version("1.0.0")
                .build()
    }

    override fun setEnvironment(environment: Environment) {
        this.environment = environment
    }
}
