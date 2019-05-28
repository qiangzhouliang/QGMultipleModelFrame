package qzl.com.basecommon.base
import cz.msebera.android.httpclient.NameValuePair
import cz.msebera.android.httpclient.client.utils.URLEncodedUtils
import cz.msebera.android.httpclient.message.BasicNameValuePair
import okhttp3.FormBody
import okhttp3.RequestBody
import qzl.com.basecommon.common.Constant.baseUrl
import qzl.com.basecommon.net.net.MRequest
import qzl.com.basecommon.net.net.NetManage
import qzl.com.basecommon.net.util.NameValuePairs
import java.util.*

/**
 * @desc 请求公共方法类
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-21 14:58
 */
class BaseRequest<RESPONSE>(val type:Int) {
    /**
     * @desc get请求方法
     * @author 强周亮
     * @time 2019-05-26 12:37
     */
    fun getRequest(mRequest: MRequest<RESPONSE>){
        var requestUrl = mRequest.url
        //判断是不是网络地址
        if (!requestUrl.startsWith("http://") && !requestUrl.startsWith("https://")){
            requestUrl = baseUrl + requestUrl
        }

        if (mRequest.reqMap != null && mRequest.reqMap.isNotEmpty()){
            val params = ArrayList<NameValuePair>()
            //添加请求验证
            NameValuePairs.buidNameValuePairList(params)
            mRequest.reqMap.forEach {
                params.add(BasicNameValuePair(it.key, it.value))
            }
            //对参数编码
            val param = URLEncodedUtils.format(params, "UTF-8")
            requestUrl = "$requestUrl?$param"
        }
        mRequest.url = requestUrl

        NetManage.manage.sendRequest(mRequest)
    }
    /**
     * @desc get请求方法
     * @author 强周亮
     * @time 2019-05-26 12:37
     */
    fun postRequest(mRequest: MRequest<RESPONSE>){
        var requestBody:RequestBody? = null
        var requestUrl = mRequest.url
        //判断是不是网络地址
        if (!requestUrl.startsWith("http://") && !requestUrl.startsWith("https://")){
            requestUrl = baseUrl + requestUrl
        }

        if (mRequest.reqMap != null && mRequest.reqMap.isNotEmpty()){
            val builder = FormBody.Builder()
            //添加请求验证
//            NameValuePairs.buidNameValuePairList(params)
            mRequest.reqMap.forEach {
                builder.add(it.key,it.value)
            }
            requestBody = builder.build()
        }
        mRequest.url = requestUrl

        NetManage.manage.sendRequestPost(mRequest,requestBody)
    }
}