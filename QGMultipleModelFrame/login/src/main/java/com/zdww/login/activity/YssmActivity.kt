package com.zdww.login.activity

import android.view.View
import android.webkit.WebSettings
import com.alibaba.android.arouter.facade.annotation.Route
import com.zdww.login.R
import kotlinx.android.synthetic.main.p_yszc.*
import qzl.com.basecommon.arouter.ARouterPath
import qzl.com.basecommon.base.BaseActivity
import qzl.com.basecommon.common.Constant
import qzl.com.basecommon.common.ConstantUrl

/**
 * @author 强周亮(qiangzhouliang)
 * @desc 隐私声明
 * @email 2538096489@qq.com
 * @time 2020/4/13 10:00
 */
@Route(path = ARouterPath.Login.PRIVATE)
class YssmActivity : BaseActivity() {
    override fun initView() {
        super.initView()
        initHead(R.id.head_layout,"隐私声明", View.OnClickListener { finishWithAnimation() })
    }
    override fun initData() {
        web_view.loadUrl(Constant.baseUrl+ ConstantUrl.Auth.GET_YSZC)
        web_view.settings.useWideViewPort = true
        web_view.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        web_view.settings.loadWithOverviewMode = true
        web_view.settings.textZoom = 80
    }

    override fun getLayoutId(): Int {
        return R.layout.p_yszc
    }
}
