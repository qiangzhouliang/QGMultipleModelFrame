package qzl.com.basecommon.net.net

import android.app.Dialog
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import qzl.com.basecommon.common.Constant
import qzl.com.basecommon.ui.java.LoadingDialog
import qzl.com.tools.utils.ThreadUtil
import utilclass.PrefUtils
import utilclass.Tt

/**
 * @desc 发送网络请求的类 volley
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-21 14:36
 */
class NetManageVolley private constructor(){
    private var progressDialog: Dialog? = null
    private var mQueue: RequestQueue? = null
    companion object {val manage by lazy { NetManageVolley() } }

    /**
     * @desc 发送get请求
     * @author Qzl
     */
    fun <RESPONSE>sendRequest(req:MRequest<RESPONSE>){
        showPopu(req)
        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(req.mContext)
        }
        mQueue?.add(object : JsonObjectRequest(req.url,null,
            Response.Listener<JSONObject?> { response ->
                resultResponse(response, req)
            },
            Response.ErrorListener {
                ThreadUtil.runOnMainThread(Runnable {
                    closePopu()
                    //回调到view层进行处理
                    req.handler?.onError(req.type,it.message)
                })
            }){
            override fun getHeaders(): MutableMap<String, String> {
                return setHeaders(req)
            }
        })
    }

    /**
     * 发送网络请求-post方式
     */
    fun <RESPONSE>sendRequestPost(req:MRequest<RESPONSE>,requestBody: Map<String,String?>? = null){
        showPopu(req)
        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(req.mContext)
        }
        mQueue?.add(object : JsonObjectRequest(Method.POST,req.url, JSONObject(requestBody),
            Response.Listener<JSONObject?> { response ->
                resultResponse(response, req)
            },
            Response.ErrorListener {
                ThreadUtil.runOnMainThread(Runnable {
                    closePopu(true)
                    //回调到view层进行处理
                    req.handler?.onError(req.type,it.message)
                })
            }){
            override fun getHeaders(): MutableMap<String, String> {
                return setHeaders(req)
            }
        })

    }

    /**
     * @desc 显示加载进度条
     * @author 强周亮
     * @time 2020/4/10 14:16
     */
    private fun <RESPONSE> showPopu(req: MRequest<RESPONSE>) {
        if (req.isShowProgress) {
            //显示加载进度条
            if (req.mContext != null ) {
                if (progressDialog != null) {
                    progressDialog?.dismiss()
                    progressDialog == null
                }

                progressDialog = LoadingDialog.createLoadingDialog(req.mContext, req.loadMessage)
                progressDialog?.show()
            }
        }
    }

    /**
     * @desc 请求接口返回结果统一处理
     * @author 强周亮
     * @time 2019/11/1 13:21
     */
    private fun <RESPONSE> resultResponse(response: JSONObject?, req: MRequest<RESPONSE>) {
        ThreadUtil.runOnMainThread(Runnable {
            closePopu()
        })
        val result = response?.toString()
        //表示不用转换，直接将string输出
        if (req.isGetString) {
            ThreadUtil.runOnMainThread(Runnable {
                //将结果回调到view层
                req.handler?.OnSuccess(req.type, result as RESPONSE)
            })
        } else {
            //刷新列表
            ThreadUtil.runOnMainThread(Runnable {
                try {
                    //将结果回调到view层
                    req.handler?.OnSuccess(req.type, req.parseResult(result))
                } catch (e: Exception) {
                    e.printStackTrace()
                    try {
                        //将结果回调到view层
                        req.handler?.OnSuccess(req.type, result as RESPONSE)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        ThreadUtil.runOnMainThread(Runnable {
                            //回调到view层进行处理
                            req.handler?.onError(req.type, e.message)
                        })
                    }
                }
            })
        }
    }

    /**
     * @desc 关闭加载进度条
     * @author 强周亮
     * @time 2020/4/10 11:58
     */
    private fun closePopu(isShowTt:Boolean = false,title:String = "加载出错") {
        if (isShowTt){
            Tt.showShort(title)
        }
        progressDialog?.dismiss()
        progressDialog = null
    }

    //设置请求头
    private fun <RESPONSE> setHeaders(req: MRequest<RESPONSE>): HashMap<String, String> {
        return hashMapOf(
            "Authorization" to "Bearer ${PrefUtils.getString(req.mContext, Constant.jwtToken, "")}",
            "Content-Type" to "application/json; charset=utf-8"
        )
    }
}