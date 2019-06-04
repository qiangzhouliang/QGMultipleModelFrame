package qzl.com.mpandroidchart.activity

import android.view.View
import com.github.mikephil.charting.data.PieEntry
import kotlinx.android.synthetic.main.p_pie.*
import qzl.com.basecommon.base.BaseActivity
import qzl.com.mpandroidchart.R
import qzl.com.mpandroidchart.common.PieChart
import java.util.*

/**
 * @desc 用户行为分析
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-05-10 14:14
 * @class hzz
 */
class PieActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.p_pie

    private lateinit var colors:MutableList<Int>

    override fun initView() {
        initHead(R.id.head_layout, "饼图", View.OnClickListener {
            finishWithAnimation()
        })
        //设置用户行为分析
        PieChart(chart1).initPieChart()
    }

    override fun initData() {
        colors = Arrays.asList(resources.getColor(R.color.cell_ordinary_m),
            resources.getColor(R.color.darkorange),
            resources.getColor(R.color.detail_name),
            resources.getColor(R.color.event_tag_text_red),
            resources.getColor(R.color.fl_slgc))
        PieChart(chart1).setCenterContent("1200", "总数")
        val values = ArrayList<PieEntry>()
        values.add(PieEntry(200f,"第一"))
        values.add(PieEntry(300f,"第二"))
        values.add(PieEntry(400f,"第三"))
        values.add(PieEntry(100f,"第四"))
        values.add(PieEntry(200f,"第五"))
        PieChart(chart1).setData(values, colors)
    }
}