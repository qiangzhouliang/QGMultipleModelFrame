package com.qzl.lzshzz.service_file.service

import com.qzl.lzshzz.model.auth.Apk
import com.qzl.lzshzz.model.file.WechatAttentionEntity
import com.qzl.lzshzz.service_file.dao.ApkDao
import com.qzl.lzshzz.service_file.dao.WechatAttentionDao
import com.qzl.lzshzz.util.GuidCreateHelper
import com.qzl.lzshzz.util.StringHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @ClassName: FileService
 * @Description: 保存文件事件附件关系Service
 * @author hanjp
 * @date 2018年9月3日 下午3:33:36
 */
@Service("fileService")
@Transactional(rollbackFor = [Exception::class])
class FileService {
    private val bas_file_eventKey = "bas_file_event"
    @Autowired
    private val jdbcTemplate: JdbcTemplate? = null
    @Autowired
    lateinit var apkDao: ApkDao
    @Autowired
    lateinit var wechatAttentionDao: WechatAttentionDao

    /**
     * @Title: saveFile
     * @Description:  (保存文件事件附件关系)
     * @author hanjp
     * @param @param busId		业务主键
     * @param @param oldName	文件旧名称
     * @param @param newName	文件新名称
     * @param @param url		文件保存全路径
     * @param @param type		文件类型
     * @param @return    参数
     */
    fun saveFile(table: String, busId: String?, oldName: String?, newName: String?, url: String?, type: String?, fileSource: String?) {
        var table = table
        val pkId = GuidCreateHelper.getGuid()
        //生成主键bas_file_event
        if (StringHelper.isEmptyString(table) || bas_file_eventKey == table) {
            table = bas_file_eventKey
            val str = "INSERT INTO $table (pk_id,bus_id,file_old_name,file_new_name,file_url,file_type) VALUES (?,?,?,?,?,?)"
            jdbcTemplate?.update(str, pkId, busId, oldName, newName, url, type)
        } else {
            val str = "INSERT INTO $table (pk_id,bus_id,file_old_name,file_new_name,file_url,file_type) VALUES (?,?,?,?,?,?)"
            jdbcTemplate?.update(str, pkId, busId, oldName, newName, url, type)
        }
    }

    /**
     * @Title: deleteFile
     * @Description: 删除文件关系
     * @author hanjp
     * @param @param busId		业务主键
     * @param @param fileName	文件名称
     * @param @throws Exception    参数
     * @return void    返回类型
     * @date 2018年9月3日 下午3:34:46
     * @throws
     */
    fun deleteFile(busId: String, fileName: String, table: String) {
        var table = table
        if (StringHelper.isEmptyString(table)) {
            table = bas_file_eventKey
        }
        jdbcTemplate?.execute("delete from $table where bus_id = '$busId' and file_new_name = '$fileName'")
    }

    /**
     * @Title: queryFile
     * @Description: 查询文件关系
     * @author hanjp
     * @param @param busId 		业务主键
     * @param @param fileName	文件名称
     */
    fun queryFile(table: String, busId: String?, fileName: String?): Map<String, Any>? {
        var table = table
        if (StringHelper.isEmptyString(table)) {
            table = bas_file_eventKey
        }
        val list = jdbcTemplate?.queryForList("select CONCAT(file_url,'/',file_new_name) url from $table where bus_id = ? and file_new_name = ?", busId, fileName)
        return if (!StringHelper.isEmptyList(list)) {
            list?.get(0)
        } else {
            null
        }
    }

    //获取apk信息
    fun getApkInfo(appFlag:String): Apk {
        return apkDao.getApkInfo(appFlag)
    }

    //更新android下载次数
    fun updateNumber() {
        val wechatAttentionInfo = wechatAttentionDao.getWechatAttentionInfo()
        val wechat = WechatAttentionEntity(GuidCreateHelper.getGuid(),wechatAttentionInfo?.atNumber?:0,wechatAttentionInfo?.apkNumber?.plus(1)?:1)
        wechatAttentionDao.save(wechat)
    }
}