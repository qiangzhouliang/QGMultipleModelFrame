package qzl.com.tools.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.webkit.MimeTypeMap
import android.widget.Toast
import cz.msebera.android.httpclient.util.EncodingUtils
import java.io.File
import java.io.InputStream
import java.text.DecimalFormat
import java.util.*
import java.util.regex.Pattern

/**
 * @desc 处理文件工具类
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2018-08-16 12:05
 */
object FileUtil {
    /**
     * @desc 调运android应用打开一个文件
     * @author Qzl
     * @time 2018-08-16 12:05
     * @param filePath  文件绝对路径
     */
    @JvmStatic
    fun openFile(activity: Activity, filePath: String) {
        val ext = filePath.substring(filePath.lastIndexOf('.')).toLowerCase(Locale.US)
        try {
            val mimeTypeMap = MimeTypeMap.getSingleton()
            val temp = ext.substring(1)
            val mime = mimeTypeMap.getMimeTypeFromExtension(temp)

            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            val file = File(filePath)
            intent.setDataAndType(Uri.fromFile(file), mime)
            //activity.startActivity(intent);
            activity.startActivityForResult(intent, 10000)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(
                activity.applicationContext, "无法打开后缀名为." + ext + "的文件！",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    /**
     * 转换文件大小
     * @param fileS
     * @return
     */
    fun FormetFileSize(fileS: Long): String {
        val df = DecimalFormat("#.00")
        var fileSizeString = ""
        val wrongSize = "0B"
        if (fileS == 0L) {
            return wrongSize
        }
        if (fileS < 1024) {
            fileSizeString = df.format(fileS.toDouble()) + "B"
        } else if (fileS < 1048576) {
            fileSizeString = df.format(fileS.toDouble() / 1024) + "KB"
        } else if (fileS < 1073741824) {
            fileSizeString = df.format(fileS.toDouble() / 1048576) + "MB"
        } else {
            fileSizeString = df.format(fileS.toDouble() / 1073741824) + "GB"
        }
        return fileSizeString
    }

    fun readFileContent(`in`: InputStream): String {
        var comtent = ""

        try {


            val length = `in`.available()

            val buffer = ByteArray(length)

            `in`.read(buffer)

            //res = EncodingUtils.getString(buffer, "UTF-8");

            //res = EncodingUtils.getString(buffer, "UNICODE");

            comtent = EncodingUtils.getString(buffer, "UTF-8")
            val p = Pattern.compile("\\s*|\t|\r|\n")
            val m = p.matcher(comtent)
            comtent = m.replaceAll("")
            `in`.close()

        } catch (e: Exception) {

            e.printStackTrace()

        }

        return comtent
    }
}
