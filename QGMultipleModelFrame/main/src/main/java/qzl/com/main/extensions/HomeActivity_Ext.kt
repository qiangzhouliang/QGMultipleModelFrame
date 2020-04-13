package qzl.com.main.extensions

import qzl.com.basecommon.common.CheckVersion
import qzl.com.main.R
import qzl.com.main.activity.HomeActivity
import qzl.com.tools.thread.ThreadPoolProxyFactory
import java.util.*

/**
 * @desc 生成测试菜单选项
 * @author Qzl
 * @time 2018-08-31 11:05
 */
val HomeActivity.menuItems: List<Map<String, Any>>
    get() {
        val data = ArrayList<Map<String, Any>>()
        var item: MutableMap<String, Any> = HashMap()
        item["menu_text"] = "我的信息"
        item["menu_ico"] = R.drawable.menu_info
        data.add(item)

        item = HashMap()
        item["menu_text"] = "修改密码"
        item["menu_ico"] = R.drawable.menu_pwd
        data.add(item)

        item = HashMap()
        item["menu_text"] = "检查更新"
        item["menu_ico"] = R.drawable.menu_update
        data.add(item)

        item = HashMap()
        item["menu_text"] = "应用推荐"
        item["menu_ico"] = R.drawable.menu_recom
        data.add(item)

        item = HashMap()
        item["menu_text"] = "技术支持"
        item["menu_ico"] = R.drawable.menu_help
        data.add(item)
        item = HashMap()
        item["menu_text"] = "隐私声明"
        item["menu_ico"] = R.drawable.menu_help
        data.add(item)
        return data
    }

/**
 * 检查更新
 */
fun HomeActivity.checkVesion() {
    val cv = CheckVersion(this, true)
    ThreadPoolProxyFactory.downLoadThreadPoolProxy?.execute(cv)
}