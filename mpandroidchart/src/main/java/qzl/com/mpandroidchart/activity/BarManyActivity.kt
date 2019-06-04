package qzl.com.mpandroidchart.activity

import android.content.pm.ActivityInfo
import android.view.View
import kotlinx.android.synthetic.main.p_bar.*
import qzl.com.basecommon.base.BaseActivity
import qzl.com.mpandroidchart.R
import qzl.com.mpandroidchart.common.BarChart
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/**
 * @desc 柱状图
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-05-10 14:14
 * @class hzz
 */
class BarManyActivity : BaseActivity(){
    override fun getLayoutId(): Int = R.layout.p_bar

    //处理数据是 记得判断每条柱状图对应的数据集合 长度是否一致
    private val chartDataMap by lazy { HashMap<String, List<Float>>() }
    //柱状图x轴的值
    private val xValues by lazy { ArrayList<String>()}
    //柱状图y轴的值
    private val yValue1 by lazy { ArrayList<Float>()}
    private val yValue2 by lazy { ArrayList<Float>()}
    private val yValue3 by lazy { ArrayList<Float>()}
    private var barChart: BarChart? = null
    //柱状图的颜色
    private lateinit var colors:MutableList<Int>

    override fun initData() {
        colors = Arrays.asList(resources.getColor(R.color.cell_ordinary_m),
                resources.getColor(R.color.darkorange),resources.getColor(R.color.fl_slgc))
        setData()
    }


    override fun initView() {
        //设置横屏显示
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        initHead(R.id.head_layout, "柱状图", View.OnClickListener { finishWithAnimation() })
        barChart = BarChart(chart).initBarChart()
    }
    private fun setData(){
        for (i in 1..30){
            xValues.add("第$i")
            yValue1.add((Math.random()*100).toFloat())
            yValue2.add((Math.random()*100).toFloat())
            yValue3.add((Math.random()*100).toFloat())
        }
        chartDataMap["类型1 XXX"] = yValue1
        chartDataMap["类型2 XXX"] = yValue2
        chartDataMap["类型3 XXX"] = yValue3
        if (xValues.size > 9){
            barChart?.setScanleX(1.7f)
        }else{
            barChart?.setScanleX(1.0f)
        }
        //显示值
        barChart?.showBarChart(xValues, chartDataMap, colors = colors)
    }
}