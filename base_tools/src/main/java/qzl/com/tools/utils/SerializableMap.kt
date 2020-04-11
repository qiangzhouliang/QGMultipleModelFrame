package qzl.com.tools.utils

import java.io.Serializable

/**
 * @desc 自定义map序列化
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2018-07-27 17:24
 * @class hzz
 */
class SerializableMap : Serializable {
    private var map: Map<String, Any>? = null
    fun getMap(): Map<String, Any>? {
        return map
    }

    fun setMap(map: Map<String, Any>): SerializableMap {
        this.map = map
        return this
    }

}
