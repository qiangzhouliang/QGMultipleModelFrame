package qzl.com.main.activity

import android.view.View
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_net2.*
import qzl.com.basecommon.base.BaseActivity
import qzl.com.basecommon.ui.kotlin.xlistview.CommonAdapter
import qzl.com.basecommon.ui.kotlin.xlistview.XListViewComm
import qzl.com.main.R
import qzl.com.main.adapter.Net2ListAdapter
import qzl.com.main.domain.Net2Bean
import qzl.com.main.domain.Result
import qzl.com.main.mvp.widget.Net2ItemView
import qzl.com.tools.utils.TimeHelper

class Net2Activity: BaseActivity(), XListViewComm.AdapterParaIntf<Result,Net2ItemView> {

    private var mXListViewComm: XListViewComm<Result,Net2ItemView>? = null
    private var conditionMap = HashMap<String, String>()
    override fun getLayoutId(): Int {
        return R.layout.activity_net2
    }

    override fun initView() {
        initHead(R.id.head_layout,"网络请求封装二", View.OnClickListener { finishWithAnimation() })
        mXListViewComm = XListViewComm(this, list, "getQslList", true, this)
    }

    override fun initData() {
        conditionMap["state"] = "1"
        conditionMap["areaCode"] = "620000000000"
        conditionMap["startTime"] = TimeHelper.currentYear + "-01-01"
        conditionMap["endTime"] = TimeHelper.addDay(TimeHelper.currentDate, 1)?:""
        mXListViewComm?.getListByCondition(conditionMap)
    }
    override fun getParaAdapter(result: String?, type: Int): CommonAdapter<Result, Net2ItemView> {
        val net2Bean = Gson().fromJson(result, Net2Bean::class.java)
        return Net2ListAdapter(net2Bean.data.result)
    }
}
