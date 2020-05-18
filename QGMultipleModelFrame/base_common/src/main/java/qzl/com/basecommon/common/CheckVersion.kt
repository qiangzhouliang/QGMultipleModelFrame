package qzl.com.basecommon.common

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.Message
import android.widget.Toast
import androidx.core.content.FileProvider
import com.google.gson.Gson
import com.qzl.toast.MyToast
import qzl.com.basecommon.R
import qzl.com.basecommon.permissions.ConstantPermission
import qzl.com.basecommon.permissions.RequestPermissionUtil
import qzl.com.basecommon.ui.java.LoadingDialog
import qzl.com.basecommon.utils.VersionXmlParser
import qzl.com.model.app_info.VersionInfo
import qzl.com.tools.network.NetworkUtil
import qzl.com.tools.thread.ThreadPoolProxyFactory
import qzl.com.tools.utils.AppUtil
import qzl.com.tools.utils.MyLogUtils
import qzl.com.tools.utils.ScreenUtil
import qzl.com.tools.utils.StringHelper
import java.io.*
import java.lang.Thread.sleep
import java.net.HttpURLConnection
import java.net.URL

/**
 * @author 强周亮(Qzl)
 * @desc 检查更新
 * @email 2538096489@qq.com
 * @time 2019-05-28 17:54
 */
class CheckVersion(var activity: Activity, var isSyncHandle: Boolean = false //是否同步处理检查后的结果信息
    ) : Runnable, MyHandler.MyHandlerIntf {

    private val mResponseCode200 = 200
    private var info: VersionInfo? = null
    /**
     * 不需要更新
     */
    private val UPDATA_NONEED = 0
    /**
     * 升级更新
     */
    private val UPDATA_CLIENT = 1
    /**
     * 获取服务器失败
     */
    private val GET_UNDATAINFO_ERROR = 2
    private val DOWN_ERROR = 4
    private var localVersion: String? = null
    private var `is`: InputStream? = null
    private var checkVersionHandle: CheckVersionHandle? = null
    private var checkVersionCustomHandle: CheckVersionCustomHandle? = null
    private var dialog: Dialog? = null
    private var mHandler: MyHandler? = null

    init {
        this.localVersion = AppUtil.getVersionName(activity)
        mHandler = MyHandler(activity)
    }

    fun handleByCheckState(state: Int) {
        when (state) {
            UPDATA_NONEED -> if (isSyncHandle) {
                Toast.makeText(activity.applicationContext, "当前版本为$localVersion，已经是最新版本！", Toast.LENGTH_SHORT).show()
            }
            UPDATA_CLIENT ->
                //对话框通知用户升级程序
                showUpdataDialog()
            GET_UNDATAINFO_ERROR ->
                //服务器超时
                Toast.makeText(activity.applicationContext, "获取失败！", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * @desc 异步检查版本
     * @author 强周亮
     * @time 2019-05-28 15:42
     */
    override fun run() {
        MyHandler.MyHandlerIntfUtil.setMyHandlerIntf(this)
        try {
            val path = if ("0" == Constant.versionInfoFlag) {
                //从配置文件查看版本信息
                Constant.baseUrl + "version/version.xml"
            } else {
                //从数据库查看版本信息
                Constant.baseUrl + ConstantUrl.Auth.GET_APK_VERSION
            }
            val url = URL(path)
            val conn = url.openConnection() as HttpURLConnection
            conn.connectTimeout = 5000
            conn.requestMethod = "GET"
            val responseCode = conn.responseCode
            if (responseCode == mResponseCode200) {
                // 从服务器获得一个输入流
                `is` = conn.inputStream
            }
            info = if ("0" == Constant.versionInfoFlag) {
                VersionXmlParser.getUpdataInfo(`is`)
            } else {
                getVersionInfo(`is`)
            }
            if (StringHelper.isEmptyString(info?.version) || info?.version == localVersion) {
                MyLogUtils.i("检查版本结果：版本号相同")
                val msg = Message()
                msg.what = UPDATA_NONEED
                mHandler?.sendMessage(msg)
            } else {
                MyLogUtils.i("检查版本结果：版本号不相同")
                val msg = Message()
                msg.what = UPDATA_CLIENT
                mHandler?.sendMessage(msg)
            }
        } catch (e: Exception) {
            MyLogUtils.i("检查版本结果：获取版本信息失败")
            val msg = Message()
            msg.what = GET_UNDATAINFO_ERROR
            mHandler?.sendMessage(msg)
            e.printStackTrace()
        }

    }

    /**
     * @desc 解析从服务器端拿到的版本信息
     * @author 强周亮
     * @time 2018-10-18 10:57
     */
    @Throws(IOException::class)
    private fun getVersionInfo(`is`: InputStream?): VersionInfo {
        val reader = BufferedReader(InputStreamReader(`is`))
        var str: String?
        val sb = StringBuffer()
        do {
            str = reader.readLine()
            if (str == null) {
                break
            }else{
                sb.append(str)
            }
        } while (true)
        reader.close()
        return Gson().fromJson(sb.toString(), VersionInfo::class.java)
    }

    /**
     * 显示更新弹出框
     */
    private fun showUpdataDialog() {
        dialog = LoadingDialog.initVersionDialog(activity, info,
            {
                dialog?.dismiss()
                RequestPermissionUtil.requestPermission(activity, ConstantPermission.writeSdPermiss,
                    String.format(ConstantPermission.writeSdContent, "更新下载", "更新下载")
                    , object : RequestPermissionUtil.PermissionListener {
                        override fun cancel(code: Int, perms: MutableList<String>) {}

                        override fun success(code: Int, perms: MutableList<String>?) {
                            downLoadApk()
                        }
                    })
            }, {
                dialog?.dismiss()
                if (checkVersionCustomHandle != null && NetworkUtil.getNetworkState(activity) != 0) {
                    checkVersionCustomHandle?.customHandle(UPDATA_NONEED)
                }
            })
        dialog?.show()
        val dialogWindow = dialog?.window
        // 获取屏幕宽、高用
        val screenWidth = ScreenUtil.getScreenWidth(activity)
        // 获取对话框当前的参数值
        val p = dialogWindow?.attributes
        // 宽度设置为屏幕的0.65
        p?.width = (screenWidth * 0.8).toInt()
        dialogWindow?.attributes = p
    }

    /**
     * @desc
     * @author 强周亮
     * @time 2018-12-10 20:05
     */
    private fun downLoadApk() {
        val pd: ProgressDialog
        //进度条对话框
        pd = ProgressDialog(activity)
        pd.setCancelable(false)
        pd.setProgressDrawable(activity.resources.getDrawable(R.drawable.progressbar))
        //返回键不可关闭
        pd.setCanceledOnTouchOutside(false)
        //边界外不可点击关闭
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        pd.setMessage("正在下载最新版本:")
        pd.show()
        val runnable = Runnable {
            try {
                val path: String
                if ("0" == Constant.versiondownloadFlag) {
                    //接口中从ftp上下载
                    path = Constant.baseUrl + ConstantUrl.File.FILE_DOWNLOAD_APK+"?fileUrl=${info?.fileUrl}&downloadOrUpdate=02"
                    val patchFile = getFileFromServer(path, pd)
                    sleep(2000)
                    installApk(patchFile)
                    pd.dismiss() //结束掉进度条对话框
                } else {
                    //接口配置文件中下载
                    path = Constant.baseUrl + "version/lzshzz.apk"
                    val patchFile = getFileFromServer(path, pd)
                    sleep(2000)
                    installApk(patchFile)
                    pd.dismiss() //结束掉进度条对话框
                }
            } catch (e: Exception) {
                val msg = Message()
                msg.what = DOWN_ERROR
                mHandler?.sendMessage(msg)
                pd.dismiss() //结束掉进度条对话框
                e.printStackTrace()
            }
        }
        ThreadPoolProxyFactory.downLoadThreadPoolProxy?.execute(runnable)
    }
    /**
     * @desc 下载文件并获取文件内容
     * @author 强周亮(Qzl)
     * @time 2018-09-18 14:02
     */
    @Throws(Exception::class)
    private fun getFileFromServer(path: String, pd: ProgressDialog): File {
        val url = URL(path)
        val conn = url.openConnection() as HttpURLConnection
        conn.connectTimeout = 5000
        pd.max = conn.contentLength
        val all = 1.0f * conn.contentLength / 1024f / 1024f
        val `is` = conn.inputStream
        val dir: File
        //如果有sd卡就存放在sd卡，否则放在手机内存
        val i = Environment.getExternalStorageState()
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            dir = File(Environment.getExternalStorageDirectory().toString() + "/" + Constant.CACHE_DIR)
            if (!dir.exists()) {
                dir.mkdir()
            }
        } else {
            dir = activity.cacheDir
        }
        val file = File(dir.absolutePath, "lzshzz.apk")
        val fos = FileOutputStream(file)
        val bis = BufferedInputStream(`is`)
        try {
            val buffer = ByteArray(1024)
            var len: Int
            var total = 0
            do {
                len = bis.read(buffer)
                if (len != -1){
                    fos.write(buffer, 0, len)
                    total += len
                    //获取当前下载量
                    pd.progress = total
                    val percent = 1.0f * total / 1024f / 1024f
                    pd.setProgressNumberFormat(String.format("%.1fM/%.1fM", percent, all))
                }else{
                    break
                }
            }while (true)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            fos.close()
            bis.close()
            `is`.close()
        }
        return file
    }

    //安装apk
    private fun installApk(file: File) {
        val intent = Intent()
        //执行动作
        intent.action = Intent.ACTION_VIEW
        //执行的数据类型
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val contentUri =
                activity.applicationContext?.let { FileProvider.getUriForFile(it, "com.zdww.lzshzz.fileProvider", file) }
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive")
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        activity.startActivity(intent)
        android.os.Process.killProcess(android.os.Process.myPid())
    }

    fun setCheckVersionHandle(checkVersionHandle: CheckVersionHandle) {
        this.checkVersionHandle = checkVersionHandle
    }

    fun setCheckVersionCustomHandle(checkVersionCustomHandle: CheckVersionCustomHandle) {
        this.checkVersionCustomHandle = checkVersionCustomHandle
    }

    override fun setHandleMessage(msg: Message) {
        if (msg.what == DOWN_ERROR) {
            MyToast.showShort("下载新版本失败!")
            return
        }

        Constant.VERSION_MAP[Constant.VERSION_STATE] = msg.what
        Constant.VERSION_MAP[Constant.VERSION_INFO] = info
        checkVersionHandle?.resultHandle()
        if (isSyncHandle) {
            handleByCheckState(msg.what)
        }

        checkVersionCustomHandle?.customHandle(msg.what)
    }

    interface CheckVersionHandle {
        /**
         * 没有新版本后的结果处理
         */
        fun resultHandle()
    }

    interface CheckVersionCustomHandle {
        /**
         * 自定义处理结果
         */
        fun customHandle(state: Int)
    }
}
