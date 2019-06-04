package qzl.com.main.mvp

import android.content.Context
import qzl.com.basecommon.net.net.MRequest
import qzl.com.basecommon.net.net.ResponseHandler
import qzl.com.main.mvp.model.HomeBean

/**
 * @desc
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-21 14:58
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.net
 */
/*class HomeRequest(mContext:Context?,type:Int,offset:Int,handler: ResponseHandler<List<HomeItemBean>>): MRequest<List<HomeItemBean>>(mContext,type,
    URLProviderUtils.getHomeUrl(offset,20),handler,isShowProgress = false) {
}*/
class HomeRequest(mContext:Context?,type:Int,offset:Int,handler: ResponseHandler<HomeBean>): MRequest<HomeBean>(mContext,type,
    "http://v.juhe.cn/toutiao/index?type=top&key=e8159dfaee4760ddbcc13f6bb648d9e2",handler,isShowProgress = false) {
}