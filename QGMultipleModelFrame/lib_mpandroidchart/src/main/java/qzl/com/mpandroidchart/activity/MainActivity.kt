package qzl.com.mpandroidchart.activity

import android.view.View
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.p_chart_main.*
import org.jetbrains.anko.startActivity
import qzl.com.basecommon.base.BaseActivity
import qzl.com.basecommon.arouter.ARouterPath
import qzl.com.mpandroidchart.R

/**
 * @desc 图表统计首页
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-06-03 18:25
 * @class QGMultipleModelFrame
 * @package qzl.com.mpandroidchart
 */
@Route(path = ARouterPath.CHART)
class MainActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.p_chart_main
    }
    override fun initView() {
        initHead(R.id.head_layout,"图表", View.OnClickListener {
            finishWithAnimation()
        })
    }

    override fun initListener() {
        findViewById<TextView>(R.id.tv_pie).setOnClickListener {
            startActivity<PieActivity>()
        }
        tv_line.setOnClickListener {
            startActivity<LineActivity>()
        }
        tv_bar.setOnClickListener {
            startActivity<BarManyActivity>()
        }
    }
}