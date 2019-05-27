package qzl.com.basecommon.net.net

/**
 * @desc 把请求结果转交给需要的地方的回调
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-21 14:32
 */
interface ResponseHandler<RESPONSE> {
    fun onError(type:Int,msg:String?)
    fun OnSuccess(type:Int,result:RESPONSE)
}