package qzl.com.tools.img

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.util.Base64
import android.util.Log
import android.view.WindowManager
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream

/**
 * @author 强周亮(Qzl)
 * @desc 图片操作类
 * @email 2538096489@qq.com
 * @time 2019-06-04 16:06
 * @class ImageUtils
 * @package qzl.com.tools.utils
 */
object ImageUtils {

    /**
     * 图片压缩处理，size参数为压缩比，比如size为2，则压缩为1/4
     */
    fun compressBitmap(path: String, data: ByteArray, context: Context, uri: Uri, size: Int, width: Boolean): Bitmap? {
        var options: BitmapFactory.Options? = null
        if (size > 0) {
            val info = BitmapFactory.Options()
            /**如果设置true的时候，decode时候Bitmap返回的为数据将空 */
            info.inJustDecodeBounds = false
            decodeBitmap(
                path,
                data,
                context,
                uri,
                info
            )
            var dim = info.outWidth
            if (!width) dim = Math.max(dim, info.outHeight)
            options = BitmapFactory.Options()
            /**把图片宽高读取放在Options里 */
            options.inSampleSize = size
        }
        var bm: Bitmap? = null
        try {
            bm = decodeBitmap(
                path,
                data,
                context,
                uri,
                options
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return bm
    }


    /**
     * 把byte数据解析成图片
     */
    private fun decodeBitmap(path: String?, data: ByteArray?, context: Context, uri: Uri?, options: BitmapFactory.Options?): Bitmap? {
        var result: Bitmap? = null
        if (path != null) {
            result = BitmapFactory.decodeFile(path, options)
        } else if (data != null) {
            result = BitmapFactory.decodeByteArray(data, 0, data.size, options)
        } else if (uri != null) {
            val cr = context.contentResolver
            var inputStream: InputStream? = null
            try {
                inputStream = cr.openInputStream(uri)
                result = BitmapFactory.decodeStream(inputStream, null, options)
                inputStream!!.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        return result
    }


    /**
     * 把bitmap转换成String
     *
     * @param filePath
     * @return
     */
    fun bitmapToString(filePath: String): String {
        val bm = getSmallBitmap(filePath, 480, 800)
        val baos = ByteArrayOutputStream()
        bm!!.compress(Bitmap.CompressFormat.JPEG, 40, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    /**
     * 计算图片的缩放值
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    fun calculateInSampleSize(
        options: BitmapFactory.Options,
        reqWidth: Int, reqHeight: Int
    ): Int {
        // Raw height and width of image
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and
            // width
            val heightRatio = Math.round(height.toFloat() / reqHeight.toFloat())
            val widthRatio = Math.round(width.toFloat() / reqWidth.toFloat())

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
        }

        return inSampleSize
    }

    /**
     * 根据路径获得突破并压缩返回bitmap用于显示
     *
     * @return
     */
    fun getSmallBitmap(filePath: String, newWidth: Int, newHeight: Int): Bitmap? {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(filePath, options)

        // Calculate inSampleSize
        options.inSampleSize =
            calculateInSampleSize(
                options,
                newWidth,
                newHeight
            )

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false

        val bitmap = BitmapFactory.decodeFile(filePath, options)
        val newBitmap =
            compressImage(bitmap!!, 500)
        bitmap?.recycle()
        return newBitmap
    }

    /**
     * 根据路径删除图片
     *
     * @param path
     */
    fun deleteTempFile(path: String) {
        val file = File(path)
        if (file.exists()) {
            file.delete()
        }
    }

    /**
     * 添加到图库
     */
    fun galleryAddPic(context: Context, path: String) {
        val mediaScanIntent = Intent(
            Intent.ACTION_MEDIA_SCANNER_SCAN_FILE
        )
        val f = File(path)
        val contentUri = Uri.fromFile(f)
        mediaScanIntent.data = contentUri
        context.sendBroadcast(mediaScanIntent)
    }

    //使用Bitmap加Matrix来缩放
    fun resizeImage(bitmapOrg: Bitmap, newWidth: Int, newHeight: Int): Bitmap? {
        var newWidth = newWidth
        var newHeight = newHeight
        //        Bitmap bitmapOrg = BitmapFactory.decodeFile(imagePath);
        // 获取这个图片的宽和高
        val width = bitmapOrg.width
        val height = bitmapOrg.height
        //如果宽度为0 保持原图
        if (newWidth == 0) {
            newWidth = width
            newHeight = height
        }
        // 创建操作图片用的matrix对象
        val matrix = Matrix()
        // 计算宽高缩放率
        val scaleWidth = (newWidth / width).toFloat()
        val scaleHeight = (newHeight / height).toFloat()
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight)
        var resizedBitmap: Bitmap? = Bitmap.createBitmap(
            bitmapOrg, 0, 0, newWidth,
            newHeight, matrix, true
        )
        //Log.e("###newWidth=", resizedBitmap.getWidth()+"");
        //Log.e("###newHeight=", resizedBitmap.getHeight()+"");
        resizedBitmap = compressImage(
            resizedBitmap!!,
            100
        )//质量压缩
        return resizedBitmap
    }

    //使用BitmapFactory.Options的inSampleSize参数来缩放
    fun resizeImage2(path: String, width: Int, height: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true//不加载bitmap到内存中
        BitmapFactory.decodeFile(path, options)
        val outWidth = options.outWidth
        val outHeight = options.outHeight
        options.inDither = false
        options.inPreferredConfig = Bitmap.Config.ARGB_8888
        options.inSampleSize = 1

        if (outWidth != 0 && outHeight != 0 && width != 0 && height != 0) {
            val sampleSize = (outWidth / width + outHeight / height) / 2
            Log.d("###", "sampleSize = $sampleSize")
            options.inSampleSize = sampleSize
        }

        options.inJustDecodeBounds = false
        return BitmapFactory.decodeFile(path, options)
    }

    /**
     * 通过像素压缩图片，将修改图片宽高，适合获得缩略图，Used to get thumbnail
     * @param srcPath
     * @return
     */
    fun compressBitmapByPath(srcPath: String, pixelW: Float, pixelH: Float): Bitmap {
        val newOpts = BitmapFactory.Options()
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565
        var bitmap = BitmapFactory.decodeFile(srcPath, newOpts)//此时返回bm为空

        newOpts.inJustDecodeBounds = false
        val w = newOpts.outWidth
        val h = newOpts.outHeight
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        var be = 1//be=1表示不缩放
        if (w > h && w > pixelW) {//如果宽度大的话根据宽度固定大小缩放
            be = (newOpts.outWidth / pixelW).toInt()
        } else if (w < h && h > pixelH) {//如果高度高的话根据宽度固定大小缩放
            be = (newOpts.outHeight / pixelH).toInt()
        }
        if (be <= 0)
            be = 1
        newOpts.inSampleSize = be//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts)
        //        return compress(bitmap, maxSize); // 这里再进行质量压缩的意义不大，反而耗资源，删除
        return bitmap
    }

    /**
     * 通过大小压缩，将修改图片宽高，适合获得缩略图，Used to get thumbnail
     * @param image
     * @param pixelW
     * @param pixelH
     * @return
     */
    fun compressBitmapByBmp(image: Bitmap, pixelW: Float, pixelH: Float): Bitmap? {
        val os = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, os)
        if (os.toByteArray().size / 1024 > 1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            os.reset()//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 50, os)//这里压缩50%，把压缩后的数据存放到baos中
        }
        var `is` = ByteArrayInputStream(os.toByteArray())
        val newOpts = BitmapFactory.Options()
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565
        var bitmap = BitmapFactory.decodeStream(`is`, null, newOpts)
        newOpts.inJustDecodeBounds = false
        val w = newOpts.outWidth
        val h = newOpts.outHeight
//缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        var be = 1//be=1表示不缩放
        if (w > h && w > pixelW) {//如果宽度大的话根据宽度固定大小缩放
            be = (newOpts.outWidth / pixelW).toInt()
        } else if (w < h && h > pixelH) {//如果高度高的话根据宽度固定大小缩放
            be = (newOpts.outHeight / pixelH).toInt()
        }
        if (be <= 0) be = 1
        newOpts.inSampleSize = be//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        `is` = ByteArrayInputStream(os.toByteArray())
        bitmap = BitmapFactory.decodeStream(`is`, null, newOpts)
        val desWidth = w / be
        val desHeight = h / be
        bitmap = Bitmap.createScaledBitmap(bitmap!!, desWidth, desHeight, true)
        //压缩好比例大小后再进行质量压缩
        //      return compress(bitmap, maxSize); // 这里再进行质量压缩的意义不大，反而耗资源，删除
        return bitmap
    }

    /**
     * 质量压缩
     * @param image
     * @param maxSize
     */
    fun compressImage(image: Bitmap, maxSize: Int): Bitmap? {
        val os = ByteArrayOutputStream()
        // scale
        var options = 80
        // Store the bitmap into output stream(no compress)
        image.compress(Bitmap.CompressFormat.JPEG, options, os)
        // Compress by loop
        while (os.toByteArray().size / 1024 > maxSize) {
            // Clean up os
            os.reset()
            // interval 10
            options -= 10
            image.compress(Bitmap.CompressFormat.JPEG, options, os)
        }

        var bitmap: Bitmap? = null
        val b = os.toByteArray()
        if (b.size != 0) {
            bitmap = BitmapFactory.decodeByteArray(b, 0, b.size)
        }
        return bitmap
    }


    /**
     * 对图片进行缩放
     * @param bgimage
     * @param newWidth
     * @param newHeight
     * @return
     */
    fun zoomImage(bgimage: Bitmap, newWidth: Double, newHeight: Double): Bitmap? {
        var newWidth = newWidth
        var newHeight = newHeight
        //        //使用方式
        //        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.default_img);
        //        int paddingLeft = getPaddingLeft();
        //        int paddingRight = getPaddingRight();
        //        int bmWidth = bitmap.getWidth();//图片高度
        //        int bmHeight = bitmap.getHeight();//图片宽度
        //        int zoomWidth = getWidth() - (paddingLeft + paddingRight);
        //        int zoomHeight = (int) (((float)zoomWidth / (float)bmWidth) * bmHeight);
        //        Bitmap newBitmap = zoomImage(bitmap, zoomWidth,zoomHeight);
        // 获取这个图片的宽和高
        val width = bgimage.width.toFloat()
        val height = bgimage.height.toFloat()
        //如果宽度为0 保持原图
        if (newWidth == 0.0) {
            newWidth = width.toDouble()
            newHeight = height.toDouble()
        }
        // 创建操作图片用的matrix对象
        val matrix = Matrix()
        // 计算宽高缩放率
        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight)
        var bitmap: Bitmap? = Bitmap.createBitmap(
            bgimage, 0, 0, width.toInt(),
            height.toInt(), matrix, true
        )
        bitmap = compressImage(bitmap!!, 100)//质量压缩
        return bitmap
    }

    /**
     * @desc drawable 和 bitmap之间的转换
     * @author 强周亮
     * @time 2019/10/30 15:14
     */
    fun decodeDrawableBitmap(res: Resources, windowManager: WindowManager, id: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(res, id, options)
        var srcHeight = options.outHeight
        var srcWidth = options.outWidth
        val display = windowManager.defaultDisplay
        val targetWidth = display.width
        val targetHeight = display.height
        var scale = 1
        while (srcWidth / 2 >= targetWidth && srcHeight / 2 >= targetHeight) { // &&
            srcWidth /= 2
            srcHeight /= 2
            scale *= 2
        }
        while (srcWidth * srcHeight > targetWidth * targetHeight) {
            srcWidth /= 2
            srcHeight /= 2
            scale *= 2
        }
        Log.e("scale", "" + scale)
        options.inSampleSize = scale
        //设置采样率
        options.inPreferredConfig = Bitmap.Config.ALPHA_8
        //该模式是默认的,可不设
        options.inPurgeable = true
        // 同时设置才会有效
        options.inInputShareable = true
        //。当系统内存不够时候图片自动被回收
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeResource(res, id, options)
    }
}
