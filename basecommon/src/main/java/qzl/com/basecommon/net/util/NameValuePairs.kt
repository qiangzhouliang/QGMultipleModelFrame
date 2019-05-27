package qzl.com.basecommon.net.util

import org.apache.http.NameValuePair
import org.apache.http.message.BasicNameValuePair
import qzl.com.basecommon.common.Constant
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @ClassName: EleNameValuePairs
 * @Description: 请求参数封装公共类
 * @author liushouning
 * @date 2018/2/28 9:09
 */
object NameValuePairs {

    /**
     * @Title: buidNameValuePairList
     * @Description: 请求参数加密
     * @author liushouning
     * @date  2018/2/28 9:09
     * @param params
     * @return void
     */
    fun buidNameValuePairList(params: MutableList<NameValuePair>) {
        val sdf = SimpleDateFormat("yyyyMMddhhmmssSSS")
        val pTime = sdf.format(Date())
        val md5Str = getvKey(pTime)
        params.add(BasicNameValuePair("authCode", md5Str))
        params.add(BasicNameValuePair("ptime", pTime))
    }

    /**
     * @Title: getvKey
     * @Description: 获取加密关键字
     * @author liushouning
     * @date  2018/2/28 9:10
     * @param ptime
     * @return java.lang.String
     */
    private fun getvKey(ptime: String): String {
        var vKey = ""
        try {
            val md5 = MessageDigest.getInstance("MD5")
            // 明文
            val key = String.format("%s_%s_%s", Constant.PKEY, ptime, Constant.PKEY)
            // 加密后的字符串
            vKey = convertToHexString(md5.digest(key.toByteArray(charset("utf-8"))))
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        return vKey
    }

    /**
     * @Title: convertToHexString
     * @Description:
     * @author liushouning
     * @date  2018/2/28 9:10
     * @param data
     * @return java.lang.String
     */
    private fun convertToHexString(data: ByteArray): String {
        val strBuffer = StringBuilder()
        for (aData in data) {
            strBuffer.append(Integer.toHexString(0xff and aData.toInt()))
        }
        return strBuffer.toString()
    }

}
