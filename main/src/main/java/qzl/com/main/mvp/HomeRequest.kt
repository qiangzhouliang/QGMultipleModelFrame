package qzl.com.main.mvp

import android.content.Context
import com.itheima.player.model.bean.HomeItemBean
import qzl.com.basecommon.common.URLProviderUtils
import qzl.com.basecommon.net.net.MRequest
import qzl.com.basecommon.net.net.ResponseHandler

/**
 * @desc
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-21 14:58
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.net
 */
class HomeRequest(mContext:Context,type:Int,offset:Int,handler: ResponseHandler<List<HomeItemBean>>): MRequest<List<HomeItemBean>>(mContext,type,
    URLProviderUtils.getHomeUrl(offset,20),handler,isShowProgress = false) {
}