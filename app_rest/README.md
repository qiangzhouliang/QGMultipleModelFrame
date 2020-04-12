[toc]

# 1 只用java代码搭建
参考博客地址：https://www.jianshu.com/p/439a8b6a7617
# 2 用kotlin搭建
## 项目地址源代码地址
https://github.com/qiangzhouliang/framework
## 2.1 创建主项目
进入新建项目窗口，选择 Spring Initializr ，这是spring官方提供的构建springboot demo的网站 https://start.spring.io，也可直接在此网站上初始化项目后，下载后在导入idea
- 修改主项目build.gradle配置文件为：
```
plugins {
    id 'org.springframework.boot' version '2.1.5.RELEASE'
    // 使用kotlin
    id 'org.jetbrains.kotlin.jvm' version '1.3.41'
    id 'org.jetbrains.kotlin.plugin.spring' version '1.3.41'
    id 'java'
}

repositories {
    mavenCentral()
    maven { url 'https://repo.spring.io/milestone' }
}
subprojects{
    //使用kotlin插件
    apply plugin: 'kotlin' // Required for Kotlin integration
    apply plugin: "kotlin-spring" // https://kotlinlang.org/docs/reference/compiler-plugins.html#spring-support
    apply plugin: 'java'
    apply plugin: 'idea'
    apply plugin: 'maven'
    apply plugin: 'eclipse'
    apply plugin: 'jacoco'
    apply plugin: 'org.springframework.boot'
    apply plugin: 'io.spring.dependency-management'

    group = 'com.example'
    sourceCompatibility = 1.8
    targetCompatibility = 1.8

    // java编译的时候缺省状态下会因为中文字符而失败
    [compileJava,compileTestJava,javadoc]*.options*.encoding = 'UTF-8'

    bootJar {
        enabled = false  // 默认不需要打可执行jar包
    }
    repositories {
        mavenCentral()
        maven { url 'https://repo.spring.io/milestone' }
    }

    dependencies {
        compile ('org.jetbrains.kotlin:kotlin-stdlib') // Required for Kotlin integration
        testCompile('org.springframework.boot:spring-boot-starter-test')
    }

    uploadArchives {
        repositories {
            mavenDeployer {
                repository(url: "http://XXX:8081/repository/mxq/") {
                    authentication(userName: "XXX", password: "XXX.")
                }
            }
        }
    }
}

```
删除主项目的src目录
- setting.gradle配置
在主项目的setting中将模块包含进来
```
include('core','service-test')
```
## 2.2 创建core模块
同上
- core模块配置文件
```
jar{
    enabled = true
}

bootJar {
    enabled = true
}

dependencies {
    // https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
    compile group: 'org.apache.commons', name: 'commons-lang3', version: '3.9'

    implementation 'org.springframework.boot:spring-boot-starter'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```
删除setting.gradle 、.gitignore 、HELP.md文件
## 2.3 创建应用项目APP
```
bootJar {
    enabled = true
}
jar{
    enabled = true
}

dependencies {
    //引入core依赖
    implementation project(':core')
    implementation 'org.jetbrains.kotlin:kotlin-stdlib'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

```
同样删除删除setting.gradle 、.gitignore 、HELP.md文件
## 2.4 启动类
```
package com.example.servicetest

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ServiceTestApplication

fun main(args: Array<String>) {
    SpringApplication.run(ServiceTestApplication::class.java, *args)
}

```
## 2.5 测试
```
package com.example.servicetest

import com.example.core.Test
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
    @GetMapping("/testK")
    fun getString():String{
        // test 为 core里面的一个类
        val test = Test()
        return "姓名：${test.getName()}"
    }
}
```

# 3 kotlin使用配置网址
https://kotlinlang.org/docs/reference/using-gradle.html
## 3.1 kotlin Spring支持
https://kotlinlang.org/docs/reference/compiler-plugins.html#spring-support

