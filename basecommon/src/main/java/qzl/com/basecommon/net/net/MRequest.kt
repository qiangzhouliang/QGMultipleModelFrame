package qzl.com.basecommon.net.net

import android.content.Context
import com.google.gson.Gson
import java.lang.reflect.ParameterizedType

/**
 * @desc 所有请求的基类
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-21 14:31
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.net
 */
open class MRequest<RESPONSE>(
    var mContext:Context, val type:Int, var url:String, val handler: ResponseHandler<RESPONSE>,
    val reqMap: HashMap<String,String>? = null,var loadMessage:String = "正在加载数据。。。",var isShowProgress:Boolean = true) {
    /**
     * 解析网络请结果
     */
    fun parseResult(result: String?): RESPONSE {
        //获取泛型类型
        val type =  (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        return Gson().fromJson<RESPONSE>(result, type)
    }

    /**
     * 发送网络请求的操作
     */
    fun execute(){
        NetManage.manage.sendRequest(this)
    }
}