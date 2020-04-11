package qzl.com.mpandroidchart.common

import android.graphics.Color
import androidx.annotation.ColorRes
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
/**
 * @desc 折线图
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-05-13 10:06
 */
class LineChart(val chart: LineChart,val formatY:String = "") {
    //初始化chart
    fun initLineChart(): qzl.com.mpandroidchart.common.LineChart {
        //隐藏x轴描述
        chart.description.isEnabled = false
        chart.setDrawGridBackground(false)
        //无数据时的显示文本
        chart.setNoDataText("暂无内容")
        //设置是否可以缩放 x和y，默认true
        chart.setScaleEnabled(false)
        //是否缩放y轴
        chart.isScaleYEnabled = false
        //是否缩放x轴
        chart.isScaleXEnabled = true

        //x轴
        val xAxis = chart.xAxis
        //设置x轴的显示位置
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.textColor = Color.parseColor("#333333");
        xAxis.textSize = 11f
        xAxis.axisMinimum = 0f
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        //设置坐标轴间隔大小
        xAxis.granularity = 1f;

        //左侧y轴
        val leftAxis = chart.axisLeft
        leftAxis.setLabelCount(5, false)
        leftAxis.valueFormatter = MyValueFormatter(formatY)
        //左侧Y轴网格线设置为虚线
        leftAxis.enableGridDashedLine(10f, 10f, 0f)
        //设置y轴从0开始
        leftAxis.axisMinimum = 0f

        //设置图表右边的y轴禁用
        chart.axisRight.isEnabled = false

        //折线图标志设置
        val legend = chart.legend
        legend.form = Legend.LegendForm.LINE
        legend.textSize = 11f
        //显示位置
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        //是否绘制在图表里面
        legend.setDrawInside(false)
        return this
    }
    fun setScanleX(scanlex:Float){
        chart.fitScreen()
        chart.viewPortHandler.matrixTouch.postScale(scanlex,1f)
        chart.resetZoom()
    }
    //显示线条
    fun showLinChart(xValues: List<String>, dataLists: HashMap<String, List<Float>>,@ColorRes colors: List<Int>) {
        if (xValues.isNotEmpty()) {
            var currentPosition = 0//用于柱状图颜色集合的index
            val sets = ArrayList<ILineDataSet>()
            dataLists.forEach { entry ->
                val name = entry.key
                val yValueList = entry.value
                val entries = ArrayList<Entry>()
                for (i in yValueList.indices) {
                    entries.add(Entry(i.toFloat(), yValueList[i]))
                }
                val d1 = LineDataSet(entries, name)
                //线条宽度
                d1.lineWidth = 2.5f
                d1.circleRadius = 4.5f
                d1.highLightColor = Color.rgb(244, 117, 117)
                //设置线条的颜色
                d1.color = colors[currentPosition % xValues.size]
                //设置圆圈的颜色
                d1.setCircleColor(colors[currentPosition % xValues.size])
                //显示数字
                d1.setDrawValues(true)
                //设置value字体大小
                d1.valueTextSize = 11f
                currentPosition++
                sets.add(d1)
            }
            val data = LineData(sets)
            data.setValueFormatter(MyValueFormatter(""))
            setDataX(chart, xValues)
            chart.data = data
        }else{
            chart.data = null
        }
        chart.animateX(750)
        //刷新数据
        chart.invalidate()
    }
    //格式化x轴坐标
    fun setDataX(chart: LineChart, list: List<String>) {
        val xAxis = chart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(list)
    }
}