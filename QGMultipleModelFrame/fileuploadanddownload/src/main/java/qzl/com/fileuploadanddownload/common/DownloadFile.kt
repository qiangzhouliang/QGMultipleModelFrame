package qzl.com.fileuploadanddownload.common

import android.app.Activity
import android.os.Environment
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.maning.mndialoglibrary.MProgressBarDialog
import com.qzl.prefutils.PrefUtils
import com.qzl.toast.MyToast
import org.jetbrains.anko.startActivity
import org.xutils.common.Callback
import org.xutils.http.RequestParams
import org.xutils.x
import qzl.com.basecommon.arouter.ARouterPath
import qzl.com.basecommon.base.BaseLargeImgActivity
import qzl.com.basecommon.common.Constant
import qzl.com.basecommon.common.Constant.getShowImgPress
import qzl.com.basecommon.common.ConstantUrl
import qzl.com.basecommon.popuwindow.ExpandablePopupWindow
import qzl.com.fileuploadanddownload.R
import qzl.com.tools.thread.ThreadPoolProxyFactory
import qzl.com.tools.utils.FileUtil.openFile
import qzl.com.tools.utils.MyLogUtils
import java.io.File
import java.util.*

/**
 * @author Qzl
 * @desc 文件下载
 * @email 2538096489@qq.com
 * @time 2019-03-23 09:25
 */
object DownloadFile {
    private var isOpenFile = false
    private var mProgressBarDialog: MProgressBarDialog? = null
    private var mFilePath: String? = null
    /**
     * @desc 文件下载
     * @author 强周亮
     * @time 2019-03-23 09:29
     */
    fun downloadFile(activity: Activity?, fileName: String, url: String, windowParentView: View?, fileDir: String) {
        val list: MutableList<String> = ArrayList<String>()
        list.add("打开")
        list.add("下载")
        list.add("取消")
        ExpandablePopupWindow(
            activity!!,
            list,
            windowParentView!!
        )
            .setOnSelectedItemListener(object : ExpandablePopupWindow.OnSelectedItemListener {
            override fun onSelectedValue(str: String,position:Int) {
                isOpenFile = false
                val filePath = Environment.getExternalStorageDirectory().path + File.separator + "lzsHzz/file/" + fileDir + "/"
                var uri = url
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    uri = Constant.baseUrl + ConstantUrl.File.FILE_DOWNLOAD
                }
                if ("打开" == str) {
                    isOpenFile = true
                    if (fileName.contains("jpg") || fileName.contains("JPG") || fileName.contains("jpeg") || fileName.contains(
                            "JPEG") || fileName.contains("PNG") || fileName.contains("png")
                    ) { //打开图片
                        if (url.startsWith("http://") || url.startsWith("https://")) {
                            openImage(activity, url)
                        } else {
                            openImage(activity, getShowImgPress(url, ""))
                        }

                    } else if (fileName.contains("mp4")) {
                        openVideos(activity, uri)
                    } else { //先下载，再打开
                        download(filePath, uri, url, activity, fileName)
                    }
                } else if ("下载" == str) { //下载
                    download(filePath, uri, url, activity, fileName)
                }
            }
        })
    }
    private fun download(filePath: String,uri: String,url: String, finalMActivity: Activity?, fileName: String) {
        val file = File(filePath)
        if (!file.exists()) {
            file.mkdir()
        } else {
            mFilePath = filePath + fileName
            val file1 = File(mFilePath)
            if (file1.exists() && !file1.isDirectory) {
                file1.delete()
                if (isOpenFile) {
                    openFile(finalMActivity!!, mFilePath!!)
                } else {
                    MyToast.showLong("文件已在" + mFilePath + "目录下存在")
                }
                return
            }
        }
        val requestParams = RequestParams(uri)
        // 为RequestParams设置文件下载后的保存路径
        requestParams.saveFilePath = filePath + fileName
        requestParams.addQueryStringParameter("fileUrl", url)
        requestParams.addQueryStringParameter("fileName", fileName)
        requestParams.addQueryStringParameter("uid", PrefUtils.getString(Constant.TOKEN, ""))
        requestParams.addHeader("Authorization", "Bearer " + PrefUtils.getString(Constant.jwtToken, ""))
        // 下载完成后自动为文件命名
        requestParams.isAutoRename = true
        MyLogUtils.e("download ${requestParams.uri}")
        x.http().get<File>(requestParams, object : Callback.ProgressCallback<File> {
            override fun onSuccess(result: File) {
                if (isOpenFile) {
                    openFile(finalMActivity!!, result.path)
                    mProgressBarDialog?.dismiss()
                } else {
                    mProgressBarDialog?.showProgress(100, "下载完成,文件存储位置为：" + result.path, true)
                    ThreadPoolProxyFactory.downLoadThreadPoolProxy?.execute(Runnable {
                        Thread.sleep(3000)
                        mProgressBarDialog?.dismiss()
                    })
                }
            }

            override fun onError(ex: Throwable, isOnCallback: Boolean) {
                MyToast.showLong("下载失败")
                mProgressBarDialog?.dismiss()
            }

            override fun onCancelled(cex: Callback.CancelledException) {
                MyLogUtils.e("取消下载")
                mProgressBarDialog?.dismiss()
            }
            override fun onFinished() {
                MyLogUtils.e("结束下载")
            }
            override fun onWaiting() { // 网络请求开始的时候调用
                MyLogUtils.e("等待下载")
                //新建一个Dialog
                mProgressBarDialog = MProgressBarDialog.Builder(finalMActivity)

                    .setStyle(MProgressBarDialog.MProgressBarDialogStyle_Horizontal)
                    //全屏背景窗体的颜色
                    .setBackgroundWindowColor(finalMActivity!!.resources.getColor(R.color.cell_ordinary_m_alpha))
                    //ProgressBar 颜色
                    .setProgressColor(finalMActivity.resources.getColor(R.color.cell_ordinary_m))
                    .build()
            }

            override fun onStarted() { // 下载的时候不断回调的方法
                MyLogUtils.e("开始下载")
                mProgressBarDialog?.showProgress(0, "当前进度为：0%", true)
            }

            override fun onLoading(total: Long, current: Long, isDownloading: Boolean) { // 当前的下载进度和文件总大小
//                    e("正在下载中")
                val progress = ((current*(1.0) / total) * 100).toInt()
                mProgressBarDialog?.showProgress(progress, "当前进度为：$progress%", true)
            }
        })
    }

    /**
     * @desc 打开视频
     * @author 强周亮
     * @time 2019-03-23 11:55
     */
    private fun openVideos(finalMActivity: Activity?,VideoUrl: String) {
        ARouter.getInstance().build(ARouterPath.VIDEO_PLAYER)
            .withString("videoUrl", VideoUrl)
            .navigation(finalMActivity)
    }

    /**
     * @desc 打开图片
     * @author 强周亮
     * @time 2019-03-23 10:01
     */
    private fun openImage(activity: Activity?, imageUrl: String) {
        if (activity != null) {
            activity.startActivity<BaseLargeImgActivity>("imgUrl" to  imageUrl)
        } else {
            MyLogUtils.e("DownloadFile，mActivity 为null")
        }
    }

}