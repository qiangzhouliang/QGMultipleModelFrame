package qzl.com.tools.utils

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


/**
 * @desc Gson解析使用的公共类
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-05-26 14:41
 * @class QGMultipleModelFrame
 * @package qzl.com.tools.utils
 */
class GsonUtil<RESPONSE> {
    //获取泛型类型的方法
    fun getType():Type{
        return object : TypeToken<RESPONSE>() {}.type
//        return  (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
    }
}
