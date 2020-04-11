package qzl.com.tools.video

import android.content.Context
import com.netcompss.ffmpeg4android.CommandValidationException
import com.netcompss.ffmpeg4android.GeneralUtils
import com.netcompss.ffmpeg4android.ProgressCalculator
import com.netcompss.loader.LoadJNI
import qzl.com.tools.thread.ThreadPoolProxyFactory
import qzl.com.tools.utils.AppUtil
import qzl.com.tools.utils.LogUtils
import java.io.File
import java.lang.Thread.sleep

/**
 * @author 强周亮(Qzl)
 * @desc 视频压缩器
 * @email 2538096489@qq.com
 * @time 2019-06-04 16:08
 * @class VideoCompressor
 * @package qzl.com.tools.utils
 */
class VideoCompressor private constructor(private val mContext: Context) {
    init {
        init(mContext)
    }
    companion object {
        /**
         * -y: 当已存在out.mp4是，不提示是否覆盖。
         * -i in.mp4: 输入文件名。
         * -s 176x144: 输出分辨率。
         * -vcodec -libx264: 输出文件使用的编解码器。
         * -vpre fast: 使用libx264做为编解码器时，需要带上这个参数。
         * -b 800000: 码率，单位是字节，不是k字节。
         * out.mp4: 输出文件名。
         */
        private val mStrCmd =
            " -strict -2 -vcodec libx264 -preset ultrafast -acodec aac -ar 44100 -ac 1 -b:a 72k -s 240x180 -aspect 1:1 -r 24 "
        private val mStrCmdPre = "ffmpeg -y -i "
        private var mOutputFile = AppUtil.appDir + "/video_compress.mp4"

        private var workFolder: String? = null
        private var demoVideoFolder: String? = null
        private var vkLogPath: String? = null
        private var vk: LoadJNI? = null
        private var commandValidationFailedFlag = false
        private fun init(context: Context) {
            demoVideoFolder = "${AppUtil.appDir}/videokit/"
            workFolder = context.applicationContext.filesDir.toString() + "/"
            vkLogPath = "${workFolder}vk.log"
            GeneralUtils.copyLicenseFromAssetsToSDIfNeeded(context, workFolder)
            GeneralUtils.copyDemoVideoFromAssetsToSDIfNeeded(context, demoVideoFolder)
            val rc = GeneralUtils.isLicenseValid(context.applicationContext, workFolder)
        }

        /**
         * 视频压缩工具方法
         * @param context 上下文
         * @param inputFile 文件
         * @param listener 监听
         */
        fun compress(context: Context, inputFile: String, listener: VideoCompressListener) {
            init(context)
            val runnable = Runnable {
                try {
                    compressByFFmpeg(context, inputFile, listener)
                } catch (e: Exception) {
                    LogUtils.e("compress exception:" + e.message)
                }
            }
            ThreadPoolProxyFactory.normalThreadPoolProxy!!.execute(runnable)
            getVideoDuration(inputFile)
            val proUpdateRunnable = Runnable {
                val pc = ProgressCalculator(vkLogPath)
                LogUtils.e("Progress update started")
                var progress = -1
                try {
                    while (true) {
                        sleep(50)
                        progress = pc.calcProgress()
                        if (progress != 0 && progress < 100) {
                            LogUtils.e("progress=$progress")
                            listener.onProgress(progress)
                        } else if (progress >= 100) {
                        }
                    }
                } catch (e: Exception) {
                    LogUtils.e("threadmessage:" + e.message)
                }
            }
            ThreadPoolProxyFactory.normalThreadPoolProxy?.execute(proUpdateRunnable)
        }

        private fun compressByFFmpeg(context: Context, inputFile: String, listener: VideoCompressListener) {
            LogUtils.e("runTranscodingUsingLoader started...")
            vk = LoadJNI()
            var newFilename: String? = null
            try {
                newFilename =  "${VideoUtil.getFileMD5(File(inputFile))}.mp4"
                mOutputFile = AppUtil.appDir + "/" + newFilename
                val cmdStr = mStrCmdPre + inputFile + mStrCmd + mOutputFile
                vk?.run(GeneralUtils.utilConvertToComplex(cmdStr), workFolder, context.applicationContext)
                GeneralUtils.copyFileToFolder(vkLogPath, demoVideoFolder)
            } catch (e: CommandValidationException) {
                commandValidationFailedFlag = true
            } catch (e: Throwable) {
                LogUtils.e("vk run exeption.$e")
            }

            var rc = if (commandValidationFailedFlag) {
                "Command Vaidation Failed"
            } else {
                GeneralUtils.getReturnCodeFromLog(vkLogPath)
            }
            val status = rc
            LogUtils.e("compress rc=$rc")
            if ("Transcoding Status: Failed" == status) {
                val strFailInfo = "Check: $vkLogPath for more information."
                listener.onFail(strFailInfo)
            } else {
                listener.onSuccess(mOutputFile, newFilename, getVideoDuration(inputFile))
            }
        }
        private fun getVideoDuration(path: String): Int {
            if (mVideoDuration <= 0) {
                mVideoDuration = VideoUtil.getVideoDuration(path)
            }
            return mVideoDuration
        }
        private var mVideoDuration: Int = 0
    }
}
