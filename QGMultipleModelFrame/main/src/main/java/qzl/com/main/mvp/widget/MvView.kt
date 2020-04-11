package qzl.com.main.mvp.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.itheima.player.model.bean.HomeItemBean
import kotlinx.android.synthetic.main.item_home.view.*
import qzl.com.basecommon.utils.GlideUtils
import qzl.com.main.R

/**
 * @desc 首页条目view
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-01 17:03
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.widget
 */
class MvView:RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    /**
     * 初始化方法
     */
    init {
        //最后一个参数写为this后，不需要在使用addview添加view了
        View.inflate(context, R.layout.item_mv,this)
    }

    /**
     * 刷新条目view数据
     */
    fun setData(data: HomeItemBean) {
        //歌曲名称
        title.text = data.title
        //简介
        desc.text = data.description
        //背景图片
        GlideUtils.loadImgAnim(context,bg,data.posterPic?:"")
    }
}