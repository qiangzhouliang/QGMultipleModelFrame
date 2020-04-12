package qzl.com.basecommon.net.net
import cz.msebera.android.httpclient.NameValuePair
import cz.msebera.android.httpclient.client.utils.URLEncodedUtils
import cz.msebera.android.httpclient.message.BasicNameValuePair
import qzl.com.basecommon.common.Constant
import qzl.com.basecommon.common.Constant.baseUrl
import qzl.com.basecommon.common.SysAccount
import utilclass.PrefUtils

/**
 * @desc 请求公共方法类
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-21 14:58
 */
class BaseRequest<RESPONSE>(val type:Int = 0) {
    /**
     * @desc get请求方法
     * @author 强周亮
     * @time 2019-05-26 12:37
     */
    fun getRequest(mRequest: MRequest<RESPONSE>){
        var requestUrl = mRequest.url
        //判断是不是网络地址
        if (!requestUrl!!.startsWith("http://") && !requestUrl.startsWith("https://")){
            requestUrl = baseUrl + requestUrl
        }
        val params = ArrayList<NameValuePair>()
        if (mRequest.reqMap != null && mRequest.reqMap!!.isNotEmpty()){
            mRequest.reqMap!!.forEach {
                params.add(BasicNameValuePair(it.key, it.value))
            }
        }
        //添加请求token
        params.add(BasicNameValuePair("uid", PrefUtils.getString(mRequest.mContext, Constant.TOKEN,"")))
        //用户账号ID
        params.add(BasicNameValuePair("userAcctId", SysAccount.getUserInfo(mRequest.mContext)?.userAcctId?:""))
        //登录用户账号
        params.add(BasicNameValuePair("loginAccount", SysAccount.getUserInfo(mRequest.mContext)?.loginAccount?:""))
        params.add(BasicNameValuePair("driveType", "android"))
        val param = URLEncodedUtils.format(params, "UTF-8")
        requestUrl = "$requestUrl?$param"
        mRequest.url = requestUrl

        NetManageVolley.manage.sendRequest(mRequest)
    }
    /**
     * @desc post请求方法
     * @author 强周亮
     * @time 2019-05-26 12:37
     */
    fun postRequest(mRequest: MRequest<RESPONSE>){
        var requestUrl = mRequest.url
        //判断是不是网络地址
        if (!requestUrl!!.startsWith("http://") && !requestUrl.startsWith("https://")){
            requestUrl = baseUrl + requestUrl
        }
        if (mRequest.reqMap == null){
            mRequest.reqMap = HashMap()
        }

        //添加请求token
        mRequest.reqMap!!["uid"] = PrefUtils.getString(mRequest.mContext, Constant.TOKEN,"")
        //用户账号ID
        mRequest.reqMap!!["userAcctId"] = SysAccount.getUserInfo(mRequest.mContext)?.userAcctId?:""
        //登录用户账号
        mRequest.reqMap!!["loginAccount"] = SysAccount.getUserInfo(mRequest.mContext)?.loginAccount?:""
        mRequest.reqMap!!["driveType"] = "android"
        mRequest.url = requestUrl
        NetManageVolley.manage.sendRequestPost(mRequest,mRequest.reqMap)
    }
}