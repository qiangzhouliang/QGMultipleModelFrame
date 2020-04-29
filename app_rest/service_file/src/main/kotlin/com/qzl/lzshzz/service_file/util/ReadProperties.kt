package com.qzl.lzshzz.service_file.util

import java.util.*

/**
 *
 * Copyright: Copyright (c) 2011
 *
 * 公司名称 : 中国电信甘肃万维公司
 *
 * 项目名称 : lrs
 *
 * 创建时间 : 2012-4-18 下午05:36:20
 *
 * 类描述 :  读入配置文件
 *
 *
 * @version 1.0.0
 * @author [feiy]( )
 */
object ReadProperties {
    private var properties: Properties? = null
    /**
     * @param propertyName
     * :对象名称
     * @return 对象值 取得配置文件的对象值
     */
    @JvmStatic
    fun getPropertyByStr(propertyName: String?): String {
        return properties!![propertyName].toString()
    }

    /**
     * @param propertyName
     * :对象名称
     * @return 对象值 取得配置文件的对象值
     */
    fun getPropertyByInt(propertyName: String?): Int {
        return getPropertyByStr(propertyName).toInt()
    }

    /**
     * @param propertyName
     * :对象名称
     * @return 对象值 取得配置文件的对象值
     */
    fun getPropertyByBoolean(propertyName: String?): Boolean {
        return java.lang.Boolean.parseBoolean(getPropertyByStr(propertyName))
    }

    init {
        properties = Properties()
        val stream = ReadProperties::class.java.getResourceAsStream("/application.yml")
        try {
            properties!!.load(stream)
        } catch (e: Exception) {
        }
        properties!!.putAll(System.getProperties())
    }
}