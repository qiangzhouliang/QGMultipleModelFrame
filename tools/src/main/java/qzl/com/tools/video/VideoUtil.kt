package qzl.com.tools.video

import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.media.ThumbnailUtils
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import qzl.com.tools.utils.AppUtil
import java.io.*
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

/**
 * Created by caizhiming on 2016/11/10.
 */

class VideoUtil {
    private fun createVideoThumbnail(url: String, width: Int, height: Int): Bitmap? {
        var bitmap: Bitmap? = null
        val retriever = MediaMetadataRetriever()
        val kind = MediaStore.Video.Thumbnails.MINI_KIND
        try {
            if (Build.VERSION.SDK_INT >= 14) {
                retriever.setDataSource(url, HashMap())
            } else {
                retriever.setDataSource(url)
            }
            bitmap = retriever.frameAtTime
        } catch (ex: IllegalArgumentException) {
            // Assume this is a corrupt video file
        } catch (ex: RuntimeException) {
            // Assume this is a corrupt video file.
        } finally {
            try {
                retriever.release()
            } catch (ex: RuntimeException) {
                // Ignore failures while cleaning up.
            }

        }
        if (kind == MediaStore.Images.Thumbnails.MICRO_KIND && bitmap != null) {
            bitmap = ThumbnailUtils.extractThumbnail(
                bitmap, width, height,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT
            )
        }
        return bitmap
    }

    companion object {

        /**
         * 获取视频缩略图
         * @param videoPath 视频路径
         * @return 返回视频缩略图路径
         */
        fun getVideoCover(videoPath: String): String? {
            val bmpCover = ThumbnailUtils.createVideoThumbnail(videoPath, MediaStore.Images.Thumbnails.MINI_KIND)
            val strCover = AppUtil.appDir + "/video_cover.jpg"
            if (bmp2File(bmpCover, strCover)) {
                val file = File(strCover)
                return if (file.exists()) {
                    strCover
                } else {
                    null
                }
            } else {
                return null
            }
        }

        /**
         * convert Bitmap to File
         *
         * @param bmp
         * @param file
         * @return
         */
        fun bmp2File(bmp: Bitmap, file: String): Boolean {
            val format = Bitmap.CompressFormat.JPEG
            val quality = 100
            var stream: OutputStream? = null
            try {
                stream = FileOutputStream(file)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

            return bmp.compress(format, quality, stream)
        }

        /**
         * 获取视频时长
         * @param mUri
         */
        fun getVideoDuration(mUri: String): Int {
            val mmr = MediaMetadataRetriever()
            try {
                mmr.setDataSource(mUri)

                val duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
                //时长(毫秒)
                return Integer.parseInt(duration) / 1000

            } catch (ex: Exception) {
                Log.e("TAG", "MediaMetadataRetriever exception $ex")
            } finally {
                mmr.release()
            }
            return 0

        }

        /**
         * 获取单个文件MD5
         * @param file
         * @return
         */
        fun getFileMD5(file: File): String? {
            // TODO Auto-generated method stub
            if (!file.isFile) {
                return null
            }
            var digest: MessageDigest? = null
            var `in`: FileInputStream? = null
            val buffer = ByteArray(1024)
            var len: Int
            try {
                digest = MessageDigest.getInstance("MD5")
                `in` = FileInputStream(file)
                do {
                    len = `in`.read(buffer, 0, 1024)
                    if (len == -1){
                        break
                    }else{
                        digest?.update(buffer, 0, len)
                    }
                } while (true)
                `in`.close()
            } catch (e: Exception) {
                e.printStackTrace()
                return null
            }

            val bigint = BigInteger(1, digest!!.digest())
            return bigint.toString(16)
        }
    }
}
