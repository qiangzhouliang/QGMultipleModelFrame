package qzl.com.basecommon.net.net

import android.app.Dialog
import okhttp3.*
import qzl.com.basecommon.ui.LoadingDialog
import qzl.com.tools.utils.ThreadUtil
import java.io.IOException

/**
 * @desc 发送网络请求的类
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-21 14:36
 */
class NetManage private constructor(){
    val client by lazy { OkHttpClient() }
    private var progressDialog: Dialog? = null
    companion object {
        val manage by lazy { NetManage() }
    }

    /**
     * 发送网络请求
     */
    fun <RESPONSE>sendRequest(req:MRequest<RESPONSE>){
        //显示加载进度条
        progressDialog = LoadingDialog.createLoadingDialog(req.mContext, req.loadMessage)
        if (req.isShowProgress){
            progressDialog?.show()
        }
        val request = Request.Builder().url(req.url).get().build()
        client.newCall(request).enqueue(object : Callback {
            /**
             * 在子线程中调运的
             */
            override fun onFailure(call: Call, e: IOException) {
                ThreadUtil.runOnMainThread(Runnable {
                    progressDialog?.dismiss()
                    //回调到view层进行处理
                    req.handler.onError(req.type,e.message)
                })
            }

            override fun onResponse(call: Call, response: Response) {
                progressDialog?.dismiss()
                val result = response.body()?.string()
                try {
                    val parseResult = req.parseResult(result)
                    //刷新列表
                    ThreadUtil.runOnMainThread(Runnable {
                        //将结果回调到view层
                        req.handler.OnSuccess(req.type,parseResult)
                    })
                }catch (e:Exception){
                    e.printStackTrace()
                    //刷新列表
                    ThreadUtil.runOnMainThread(Runnable {
                        //将结果回调到view层
                        req.handler.OnSuccess(req.type,result as RESPONSE)
                    })
                }
            }
        })
    }
}