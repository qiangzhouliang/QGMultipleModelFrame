package com.qzl.lzshzz.model.ucenter

import org.hibernate.annotations.GenericGenerator
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * @author 强周亮
 * @desc 河长头像表实体
 * @email 2538096489@qq.com
 * @time 2019/11/7 16:22
 */
@Entity
@Table(name = "bas_file_head")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
open class BasFileHead : Serializable {
    //主键
    @get:Id
    @get:Column(name = "pk_id")
    var pkId: String = ""

    //业务主键
    @get:Column(name = "bus_id")
    var busId: String? = ""

    //原文件名
    @get:Column(name = "file_old_name")
    var fileOldName: String? = ""

    //重命名后的文件名
    @get:Column(name = "file_new_name")
    var fileNewName: String? = ""

    //文件路径
    @get:Column(name = "file_url")
    var fileUrl: String? = ""

    //图片类型
    @get:Column(name = "file_type")
    var fileType: String? = ""
}