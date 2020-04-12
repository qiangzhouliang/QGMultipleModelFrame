package com.zdww.lzshzz.common.web

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.zdww.lzshzz.common.model.response.CommonCode
import com.zdww.lzshzz.common.model.response.GetPageInfoResponse
import com.zdww.lzshzz.common.model.response.PagePara
import com.zdww.lzshzz.common.model.response.ResultCode
import org.springframework.data.domain.Page

/**
 * @author 强周亮
 * @desc 分页公共工具类
 * @email 2538096489@qq.com
 * @time 2019/11/14 14:08
 */
class PageUtil<POSITION> {
    fun getPageDataMoreToable3(pageNo: Int, pageSize: Int, setDataListener: SetDataListener3<POSITION>): GetPageInfoResponse<POSITION> {
        return try {
            PageHelper.startPage<Any>(pageNo+1, pageSize)
            val (list, resultCode,data2) = setDataListener.getData()
            val pageInfo = PageInfo<POSITION>(list)
            GetPageInfoResponse(resultCode, pageInfo.list, PagePara(pageInfo.pageNum-1, pageInfo.pageSize, pageInfo.total, pageInfo.pages),data2)
        } catch (e: Exception) {
            e.printStackTrace()
            GetPageInfoResponse(CommonCode.FAIL)
        }
    }
    /**
     * @Author 强周亮
     * @Description 获取多表查询的分页数据
     * @Date 14:15 2019/11/14
     **/
    fun getPageDataMoreToable(pageNo: Int, pageSize: Int, setDataListener: SetDataListener<POSITION>): GetPageInfoResponse<POSITION> {
        return try {
            PageHelper.startPage<Any>(pageNo+1, pageSize)
            val (list, resultCode) = setDataListener.getData()
            val pageInfo = PageInfo<POSITION>(list)
            GetPageInfoResponse(resultCode, pageInfo.list, PagePara(pageInfo.pageNum-1, pageInfo.pageSize, pageInfo.total, pageInfo.pages))
        } catch (e: Exception) {
            e.printStackTrace()
            GetPageInfoResponse(CommonCode.FAIL)
        }
    }

    // 获取单表查询请求返回值
    fun getPageInfoResponse(code: CommonCode, pageNotice: Page<POSITION>,data2:List<Any>? = null,flag:String? = null) =
            GetPageInfoResponse(code, pageNotice.content, PagePara(pageNotice.pageable.pageNumber, pageNotice.pageable.pageSize, pageNotice.totalElements, pageNotice.totalPages),data2,flag)
}

//获取请求数据接口
interface SetDataListener<POSITION> {
    fun getData(): Pair<List<POSITION>?, ResultCode>
}

//获取请求数据接口
interface SetDataListener3<POSITION> {
    fun getData(): Triple<List<POSITION>?, ResultCode,List<Any>?>
}