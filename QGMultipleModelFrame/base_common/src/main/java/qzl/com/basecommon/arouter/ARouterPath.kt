package qzl.com.basecommon.arouter

/**
 * @author 强周亮(Qzl)
 * @desc 路由地址
 * @email 2538096489@qq.com
 * @time 2019-03-30 03:23
 * @class ARouterPath
 */
object ARouterPath {
    //视频播放
    const val VIDEO_PLAYER = "/video/videoPlay"
    //文件上传和下载
    const val FILE = "/file/uploadAndDownload"
    //图表
    const val CHART = "/chart/main"
    class Login {
        companion object {
            //登录
            const val LOGIN = "/login/loginActivity"
            //修改密码
            const val CHANGE_PASSWORD = "/login/changePassword"
            //隐私政策
            const val PRIVATE = "/login/private"
        }
    }
    class Home {
        companion object {
            //首页
            const val HOME_ACTIVITY = "/home/homeActivity"
        }
    }
}
