package qzl.com.videoplayer

import com.alibaba.android.arouter.facade.annotation.Route
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard
import kotlinx.android.synthetic.main.activity_video_player_jiecao.*
import qzl.com.basecommon.base.BaseActivity
import qzl.com.basecommon.arouter.ARouterPath
import qzl.com.basecommon.utils.GlideUtils
import qzl.com.tools.utils.StringHelper

/**
 * @desc 视频播放器
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-23 14:10
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.ui.activity
 */
@Route(path = ARouterPath.VIDEO_PLAYER)
class JiaoZiVideoPlayerActivity: BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_video_player_jiecao
    }

    override fun initData() {
        val data = intent.data;
        if (data == null){
            //从应用内响应http://hc.yinyuetai.com/uploads/videos/common/0FBB016ADECDDFF86FF7D6E8CE792DCC.mp4?sc=c19481d47147e188&br=785&rd=Android
            //获取传递的数据
            var videoUrl = intent.getStringExtra("videoUrl")
            var videoTitle = intent.getStringExtra("videoTitle")
            var thumbUrl = intent.getStringExtra("thumbUrl")
            if(StringHelper.isEmptyString(videoUrl)){
                videoUrl = "http://hc.yinyuetai.com/uploads/videos/common/0FBB016ADECDDFF86FF7D6E8CE792DCC.mp4?sc=c19481d47147e188&br=785&rd=Android"
            }

            videoplayer.setUp(videoUrl, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,videoTitle?:"")
            thumbUrl?.let {
                GlideUtils.loadImgAnim(this,videoplayer.thumbImageView,thumbUrl,isShowAnim = false)
            }

        }else{
            if (data.toString().startsWith("http:")){
                //外部网络视频
                videoplayer.setUp(data.toString(),
                    JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,data.toString())
            }else{
                //从应用外响应
                videoplayer.setUp(data.path,
                    JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,data.toString())
            }
        }
    }
    override fun onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        JCVideoPlayer.releaseAllVideos()
    }
}