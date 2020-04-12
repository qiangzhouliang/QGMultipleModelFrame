package com.zdww.lzshzz.util

import org.apache.http.Consts
import org.apache.http.NameValuePair
import org.apache.http.client.ClientProtocolException
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.*
import org.apache.http.conn.ssl.SSLConnectionSocketFactory
import org.apache.http.conn.ssl.SSLContextBuilder
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils

import java.io.IOException
import java.util.HashMap
import java.util.LinkedList

/**
 * http请求客户端
 *
 * @author Administrator
 */
class HttpClient {
    private var url: String? = null
    private var param: MutableMap<String, String>? = null
    var statusCode: Int = 0
        private set
    var content: String? = null
        private set
    var xmlParam: String? = null
    var isHttps: Boolean = false

    constructor(url: String, param: MutableMap<String, String>) {
        this.url = url
        this.param = param
    }

    constructor(url: String) {
        this.url = url
    }

    fun setParameter(map: MutableMap<String, String>) {
        param = map
    }

    fun addParameter(key: String, value: String) {
        if (param == null)
            param = HashMap()
        param!![key] = value
    }

    @Throws(ClientProtocolException::class, IOException::class)
    fun post() {
        val http = HttpPost(url)
        setEntity(http)
        execute(http)
    }

    @Throws(ClientProtocolException::class, IOException::class)
    fun put() {
        val http = HttpPut(url)
        setEntity(http)
        execute(http)
    }

    @Throws(ClientProtocolException::class, IOException::class)
    fun get() {
        if (param != null) {
            val url = StringBuilder(this.url!!)
            val isFirst = true
            for (key in param!!.keys) {
                if (isFirst)
                    url.append("?")
                else
                    url.append("&")
                url.append(key).append("=").append(param!![key])
            }
            this.url = url.toString()
        }
        val http = HttpGet(url)
        execute(http)
    }

    /**
     * set http post,put param
     */
    private fun setEntity(http: HttpEntityEnclosingRequestBase) {
        if (param != null) {
            val nvps = LinkedList<NameValuePair>()
            for (key in param!!.keys)
                nvps.add(BasicNameValuePair(key, param!![key])) // 参数
            http.entity = UrlEncodedFormEntity(nvps, Consts.UTF_8) // 设置参数
        }
        if (xmlParam != null) {
            http.entity = StringEntity(xmlParam!!, Consts.UTF_8)
        }
    }

    @Throws(ClientProtocolException::class, IOException::class)
    private fun execute(http: HttpUriRequest) {
        var httpClient: CloseableHttpClient? = null
        try {
            if (isHttps) {
                val sslContext = SSLContextBuilder()
                        .loadTrustMaterial(null) { chain, authType ->
                            // 信任所有
                            true
                        }.build()
                val sslsf = SSLConnectionSocketFactory(
                        sslContext)
                httpClient = HttpClients.custom().setSSLSocketFactory(sslsf)
                        .build()
            } else {
                httpClient = HttpClients.createDefault()
            }
            val response = httpClient!!.execute(http)
            try {
                if (response != null) {
                    if (response.statusLine != null)
                        statusCode = response.statusLine.statusCode
                    val entity = response.entity
                    // 响应内容
                    content = EntityUtils.toString(entity, Consts.UTF_8)
                }
            } finally {
                response!!.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            httpClient!!.close()
        }
    }

}
