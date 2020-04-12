package qzl.com.tools.img

import android.graphics.*
import android.media.ExifInterface
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * @desc 图片加载工具类
 * @anthor Qzl
 * @email 2538096489@qq.com
 * @time 2018-08-10 18:07
 * @class hzz
 * @package com.gsww.hzz.tools.common
 */
object BitmapUtil {
    /**
     * 获取图片的旋转角度
     * @param filePath 文件路径
     * @return 返回旋转角度
     */
    private fun getRotateAngle(filePath: String): Int {
        var rotateAngle = 0
        try {
            val exifInterface = ExifInterface(filePath)
            val orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> rotateAngle = 90
                ExifInterface.ORIENTATION_ROTATE_180 -> rotateAngle = 180
                ExifInterface.ORIENTATION_ROTATE_270 -> rotateAngle = 270
                else -> {
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return rotateAngle
    }

    /**
     * 旋转图片角度
     * @param angle 旋转角度
     * @param bitmap 要旋转的图片
     * @return 旋转完成后的图片
     */
    private fun setRotateAngle(angle: Int, bitmap: Bitmap?): Bitmap? {
        var bitmap = bitmap
        if (bitmap != null) {
            val m = Matrix()
            m.postRotate(angle.toFloat())
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, m, true)
            return bitmap
        }
        return null
    }

    /**
     * @desc 转换为圆形状的bitmap
     * @author 强周亮(Qzl)
     * @time 2018-09-18 13:38
     */
    fun createCircleImage(source: Bitmap): Bitmap {
        val length =
            if (source.width < source.height) source.width else source.height
        val paint = Paint()
        paint.isAntiAlias = true
        val target = Bitmap.createBitmap(length, length, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(target)
        canvas.drawCircle(length / 2.toFloat(), length / 2.toFloat(), length / 2.toFloat(), paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN) as Xfermode?
        canvas.drawBitmap(source, 0f, 0f, paint)
        return target
    }

    /**
     * 图片压缩-质量压缩
     * @param filePath 源图片路径
     * @param quality 压缩比例0-100
     * @return 压缩后的路径
     */
    fun compressImage(filePath: String,quality:Int = 30): String { //原文件
        val oldFile = File(filePath)
        //压缩文件路径 照片路径/
        val targetPath = oldFile.path
        //获取一定尺寸的图片
        var bm: Bitmap? = getSmallBitmap(filePath)
        //获取相片拍摄角度
        val degree = getRotateAngle(filePath)
        if (degree != 0) { //旋转照片角度，防止头像横着显示
            bm = setRotateAngle(degree, bm)
        }
        val outputFile = File(targetPath)
        try {
            if (!outputFile.exists()) {
                outputFile.parentFile.mkdirs()
            } else {
                outputFile.delete()
            }
            val out = FileOutputStream(outputFile)
            bm!!.compress(Bitmap.CompressFormat.JPEG, quality, out)
            out.close()
        } catch (e: Exception) {
            e.printStackTrace()
            return filePath
        }
        return outputFile.path
    }

    /**
     * 根据路径获得图片信息并按比例压缩，返回bitmap
     */
    private fun getSmallBitmap(filePath: String): Bitmap {
        val options = BitmapFactory.Options()
        //只解析图片边沿，获取宽高
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(filePath, options)
        // 计算缩放比
        options.inSampleSize =
            calculateInSampleSize(
                options,
                480,
                800
            )
        // 完整解析图片返回bitmap
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeFile(filePath, options)
    }

    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            val heightRatio =
                Math.round(height.toFloat() / reqHeight.toFloat())
            val widthRatio =
                Math.round(width.toFloat() / reqWidth.toFloat())
            inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
        }
        return inSampleSize
    }
}