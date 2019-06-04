package qzl.com.mpandroidchart.activity

import android.content.pm.ActivityInfo
import android.view.View
import kotlinx.android.synthetic.main.p_line.*
import qzl.com.basecommon.base.BaseActivity
import qzl.com.mpandroidchart.R
import qzl.com.mpandroidchart.common.LineChart
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * @desc 折线图
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-05-10 14:14
 * @class hzz
 */
class LineActivity : BaseActivity(){
    override fun getLayoutId(): Int = R.layout.p_line

    val paraMap by lazy { HashMap<String,String>() }

    //处理数据是 记得判断每条柱状图对应的数据集合 长度是否一致
    private val chartDataMap by lazy { HashMap<String, List<Float>>() }
    //柱状图x轴的值
    private val xValues by lazy { ArrayList<String>()}
    //柱状图y轴的值
    private val yValue1 by lazy { ArrayList<Float>()}
    private val yValue2 by lazy { ArrayList<Float>()}
    //线条的颜色
    private lateinit var colors:MutableList<Int>
    private var lineChart: LineChart? = null

    override fun initData() {
        colors = Arrays.asList(resources.getColor(R.color.cell_ordinary_m),
                resources.getColor(R.color.darkorange),resources.getColor(R.color.fl_slgc))
        xValues.clear()
        yValue1.clear()
        yValue2.clear()
        chartDataMap.clear()
        for (i in 1..30){
            xValues.add("1月${i}号")
            yValue1.add((Math.random()*100).toFloat())
            yValue2.add((Math.random()*100).toFloat())
        }
        chartDataMap["信息管理平台登录 XXX（次）"] = yValue1
        chartDataMap["移动平台登录 XXX（次）"] = yValue2
        lineChart?.showLinChart(xValues, chartDataMap, colors)
    }

    override fun initView() {
        //设置横屏显示
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        initHead(R.id.head_layout, "折线图", View.OnClickListener {
            finishWithAnimation()
        })
        //初始化折线图
        lineChart = LineChart(chart).initLineChart()
    }
}