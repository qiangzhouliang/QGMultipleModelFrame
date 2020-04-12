package qzl.com.model.file_operate

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @desc 文件上传成功后的回调
 * @author qiangzhouliang
 * @email 2538096489@qq.com
 * @time 2019/12/17 20:21
 * @class lzshzz_android
 * @package com.zdww.fileuploadanddownload.model
 */
data class FileBean(
    val code: Int,
    val fileList: ArrayList<FileList>,
    val message: String,
    val success: Boolean
)

@Parcelize
data class FileList(
    val fileOldName: String? = null,
    val fileType: String? = null,
    val busId: String? = null,
    val table: String? = null,
    val fileName: String? = null,
    val fileNewName: String? = null,
    val fileUrl: String? = null,
    val fileSource: String? = null
): Parcelable