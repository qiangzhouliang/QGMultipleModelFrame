package qzl.com.fileuploadanddownload.presenter

import android.content.Context
import qzl.com.basecommon.common.ConstantUrl
import qzl.com.basecommon.net.base.BasPresenter
import qzl.com.basecommon.net.base.BaseView
import qzl.com.basecommon.net.net.BaseRequest
import qzl.com.basecommon.net.net.MRequest
import qzl.com.basecommon.net.net.ResponseHandler
import qzl.com.model.common.CommonModel


/**
 * @desc 删除文件
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-21 13:51
 */
class DeleteFilePresenterImpl(var mContext:Context?, var homeView: BaseView<CommonModel>?) : BasPresenter,
    ResponseHandler<CommonModel> {
    /**
     * 解绑view和presenter
     */
    override fun destroyView(){
        homeView.let {
            homeView = null
        }
    }
    /**
     * 初始化数据或刷新数据
     */
    override fun loadDatas(map: HashMap<String, String?>?) {
        //1定义一个request+执行
        val mRequest = MRequest(mContext,
            url = ConstantUrl.File.FILE_DELETE,
            handler = this,
            reqMap = map,
            turnsType = CommonModel::class.java)
        BaseRequest<CommonModel>().getRequest(mRequest)
    }

    /**
     * 加载数据失败
     */
    override fun onError(type:Int?,msg: String?) {
        homeView?.onError(msg)
    }
    /**
     * 加载数据成功
     */
    override fun OnSuccess(type:Int?,result: CommonModel) {
        homeView?.loadSuccess(result)
    }
}