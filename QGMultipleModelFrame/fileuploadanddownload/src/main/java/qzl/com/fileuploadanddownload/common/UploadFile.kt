package qzl.com.fileuploadanddownload.common

import android.app.Dialog
import android.content.Context
import com.google.gson.Gson
import com.qzl.prefutils.PrefUtils
import com.qzl.toast.MyToast
import org.xutils.common.Callback
import org.xutils.common.util.KeyValue
import org.xutils.http.RequestParams
import org.xutils.http.body.MultipartBody
import org.xutils.x
import qzl.com.basecommon.common.Constant
import qzl.com.basecommon.common.ConstantUrl
import qzl.com.basecommon.common.SysAccount
import qzl.com.basecommon.ui.java.LoadingDialog
import qzl.com.model.file_operate.FileBean
import qzl.com.tools.img.BitmapUtil
import qzl.com.tools.utils.MyLogUtils
import qzl.com.tools.video.VideoCompressListener
import qzl.com.tools.video.VideoCompressor
import java.io.File

/**
 * @desc 文件上传
 * @author qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2019/12/17 17:52
 */
object UploadFile {
    private var progressDialog: Dialog? = null
    private var type: String? = null
    private var sUpLoadFileIntf: UpLoadFileIntf? = null
    /**
     * @desc 文件上传
     * @author 强周亮
     * @time 2019/12/18 9:57
     * busId 关联id
     * type 上传文件类型
     * table 保存文件信息表名
     * fileType 文件存储位置文件夹
     * fileSource 请四乱上传附件区分
     */
    fun uploadFile(
        context: Context,filePath:List<String>,busId:String, type:String,table: String = "bas_file_event",
                   fileType: String = "event", fileSource: String = "0" //清四乱上传文件区分-0：填报附件，1：销号附件
    ): UploadFile {
        UploadFile.type = type
        if (progressDialog != null) {
            progressDialog?.show()
        } else {
            progressDialog = LoadingDialog.createLoadingDialog(context, "正在上传文件...")
            progressDialog?.show()
        }
        val params = RequestParams(Constant.baseUrl + ConstantUrl.File.FILE_UPLOAD)
        val list = ArrayList<KeyValue>()
        filePath.forEach {
            when(type) {
                "video" -> {
                    MyLogUtils.e("视频压缩前  " + File(it).length())
                    //视频压缩
                    VideoCompressor.compress(context, it, object : VideoCompressListener {
                        override fun onSuccess(outputFile: String?, filename: String?, duration: Int) {
                            list.add(KeyValue("file",File(outputFile)))
                            params.requestBody = MultipartBody(list, "UTF-8")
                            upload(params, context, busId, table, fileType, fileSource)
                            MyLogUtils.e("视频压缩后  " + File(outputFile).length())
                        }

                        override fun onFail(reason: String?) {
                            MyLogUtils.e("视频压缩  失败")
                        }

                        override fun onProgress(progress: Int) {
                            MyLogUtils.e("视频压缩中  $progress")
                        }
                    })
                }
                "img" -> {
                    list.add(KeyValue("file",File(BitmapUtil.compressImage(it))))
                }
                "file" -> {
                    list.add(KeyValue("file",File(it)))
                }
            }
        }
        if ("video" != type) {
            params.requestBody = MultipartBody(list, "UTF-8")
            upload(params, context, busId, table, fileType, fileSource)
        }
        return this
    }

    /**
     * @desc 上传操作
     * @author 强周亮
     * @time 2019/12/18 9:52
     */
    private fun upload(params: RequestParams, context: Context, busId: String, table: String, fileType: String, fileSource: String) {
        params.addQueryStringParameter("uid", PrefUtils.getString(Constant.TOKEN, ""))
        params.addQueryStringParameter("busId", busId)
        params.addQueryStringParameter("table", table)
        params.addQueryStringParameter("areaCode", SysAccount.userInfo?.areaCode)
        params.addQueryStringParameter("fileType", fileType)
        params.addQueryStringParameter("fileAppFlag", "-android-")
        params.addQueryStringParameter("fileSource", fileSource)
        params.addQueryStringParameter("userAcctId", SysAccount.userInfo?.userAcctId)
        params.addQueryStringParameter("loginAccount", SysAccount.userInfo?.loginAccount)
        params.addQueryStringParameter("driveType", "android")
        params.isMultipart = true
        params.addHeader("Authorization", "Bearer " + PrefUtils.getString(Constant.jwtToken, ""))
        x.http().post(params, object : Callback.CommonCallback<String> {
            override fun onFinished() {
                MyLogUtils.e("文件上传完成")
                progressDialog?.dismiss()
            }

            override fun onSuccess(result: String?) {
                val fileBean = Gson().fromJson(result, FileBean::class.java)
                doSetData(type,fileBean)
                MyLogUtils.e("文件上传成功")
                progressDialog?.dismiss()
            }

            override fun onCancelled(cex: Callback.CancelledException?) {
                MyLogUtils.e("取消文件上传")
                progressDialog?.dismiss()
            }

            override fun onError(ex: Throwable?, isOnCallback: Boolean) {
                MyToast.showShort("文件上传出错")
                progressDialog?.dismiss()
            }
        })
    }

    interface UpLoadFileIntf {
        fun setData(type: String?,fileBean: FileBean)
    }
    //设置监听
    fun setUpLoadFileIntf(upLoadFileIntf: UpLoadFileIntf?) {
        sUpLoadFileIntf = upLoadFileIntf
    }

    fun doSetData(type: String?,fileBean: FileBean) {
        sUpLoadFileIntf?.setData(type, fileBean)
    }
}