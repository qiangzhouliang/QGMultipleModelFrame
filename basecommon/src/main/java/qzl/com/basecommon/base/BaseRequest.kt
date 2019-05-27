package qzl.com.basecommon.base
import org.apache.http.NameValuePair
import org.apache.http.client.utils.URLEncodedUtils
import org.apache.http.message.BasicNameValuePair
import org.apache.http.protocol.HTTP
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
            val param = URLEncodedUtils.format(params, HTTP.UTF_8)
            requestUrl = "$requestUrl?$param"
        }
        mRequest.url = requestUrl

        NetManage.manage.sendRequest(mRequest)
    }
}