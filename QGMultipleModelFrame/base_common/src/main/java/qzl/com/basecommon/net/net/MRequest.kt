package qzl.com.basecommon.net.net

import android.content.Context
import com.google.gson.Gson
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @desc 所有请求的基类
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-21 14:31
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.net
 */
open class MRequest<RESPONSE>(
    var mContext:Context?,                                  //上下文对象
    var type:Int = 0,                                       //请求类型区分
    var url:String?,                                        //请求地址
    var handler: ResponseHandler<RESPONSE>?=null,           //请求处理类
    var reqMap: HashMap<String,String?>? = null,             //请求参数
    var loadMessage:String = "正在加载数据...",           //加载进度提示信息
    var isShowProgress:Boolean = true,                      //是否显示加载进度条
    var isGetString:Boolean = false,                        //是否只是获取string数据
    var turnsType: Type? = null                             //需要json转换的对象泛型类型
) {
    /**
     * 解析网络请结果
     */
    fun parseResult(result: String?): RESPONSE {
        return try {
            Gson().fromJson(result, turnsType)
        }catch (e:Exception){
            e.printStackTrace()
            //获取泛型类型
            val type =  (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
            Gson().fromJson<RESPONSE>(result, type)
        }
    }

    /**
     * 发送网络请求的操作
     */
    fun execute(){
        NetManageVolley.manage.sendRequest(this)
    }
}