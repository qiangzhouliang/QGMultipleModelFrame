package qzl.com.mpandroidchart.common

import android.graphics.Color
import android.support.annotation.ColorRes
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import qzl.com.mpandroidchart.bean.CommonCountBean


/**
 * @desc 柱状图公共方法
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-05-14 09:59
 */
class BarChart(val chart: BarChart,val formatY: String = "") {
    fun initBarChart(): qzl.com.mpandroidchart.common.BarChart {
        //背景颜色
        chart.setBackgroundColor(Color.WHITE)
        //不显示右下角的描述
        chart.description.isEnabled = false
        //无数据时的显示文本
        chart.setNoDataText("暂无内容")
        // drawn
        chart.setMaxVisibleValueCount(60)
        // 设置true支持两个指头向X、Y轴的缩放，如果为false，只能支持X或者Y轴的当方向缩放
        chart.setPinchZoom(false)
        //背景阴影
        chart.setDrawBarShadow(false)
        chart.isHighlightFullBarEnabled = false
        //不显示图表网格
        chart.setDrawGridBackground(false)
        //不显示边框
        chart.setDrawBorders(false)

        //设置是否可以缩放 x和y，默认true
        chart.setScaleEnabled(false)
        //是否缩放y轴
        chart.isScaleYEnabled = false
        //是否缩放x轴
        chart.isScaleXEnabled = true

        /***X轴的设置***/
        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        //设置X轴上每个竖线是否显示
        xAxis.setDrawGridLines(true)
        //设置是否绘制X轴上的对应值(标签)
        xAxis.setDrawLabels(true)
        //设置x轴间距
        xAxis.granularity = 1f
        //不显示X轴网格线
        xAxis.setDrawGridLines(false)
        //不显示右侧y轴
        chart.axisRight.isEnabled = false


        //左侧Y轴
        val leftAxis = chart.axisLeft
        //左侧Y轴网格线设置为虚线
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        //设置y轴从0开始
        leftAxis.axisMinimum = 0f
        leftAxis.valueFormatter = MyValueFormatter(formatY)

        val legend = chart.legend
        legend.form = Legend.LegendForm.SQUARE
        legend.textSize = 11f
        //显示位置
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP;
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT;
        legend.orientation = Legend.LegendOrientation.HORIZONTAL;
        //是否绘制在图表里面
        legend.setDrawInside(false)
        return this
    }
    //格式化x轴坐标
    fun setDataX(list: List<String>,chart1: BarChart = chart) {
        val xAxis = chart1.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(list)
    }

    fun setScanleX(scanlex:Float){
        chart.fitScreen()
        chart.viewPortHandler.matrixTouch.postScale(scanlex,1f)
        chart.resetZoom()
    }
    //显示单条
    fun showBarChart(dateValueList: ArrayList<CommonCountBean>?, name: String, color:Int = 0, isUserXDefine: Boolean = true, barWidth:Float = 0.3f) {
        if (dateValueList != null && dateValueList.size > 0){
            if (isUserXDefine) {
                chart.xAxis.valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        return if (value >= 0 && value < dateValueList.size) {
                            dateValueList[value.toInt() % dateValueList.size].name
                        } else {
                            ""
                        }
                    }
                }
            }
            val entries = ArrayList<BarEntry>()
            for (i in dateValueList.indices) {
                /**
                 * 此处还可传入Drawable对象 BarEntry(float x, float y, Drawable icon)
                 * 即可设置柱状图顶部的 icon展示
                 */
                val barEntry = BarEntry(i.toFloat(), dateValueList[i].fValue)
                entries.add(barEntry)
            }
            // 每一个BarDataSet代表一类柱状图
            val barDataSet = BarDataSet(entries, name)
            initBarDataSet(barDataSet,color)
            val data = BarData(barDataSet)
            data.barWidth = barWidth
            //设置柱状图宽度
            chart.data = data
        }else{
            chart.data = null
        }
        //设置动画效果
        chart.animateY(1500, Easing.Linear)
        //刷新
        chart.invalidate()
    }
    /**
     * 显示多条
     * @param xValues   X轴的值
     * @param dataLists LinkedHashMap<String></String>, List<Float>>
     * key对应柱状图名字  List<Float> 对应每类柱状图的Y值
     * @param colors
     */
    fun showBarChart(xValues: List<String>, dataLists: HashMap<String, List<Float>>,@ColorRes colors: List<Int>) {

        //右侧还有部分图表未展示出来，此时还需要对X轴进行相应的设置
        chart.xAxis.axisMinimum = 0f
        chart.xAxis.axisMaximum = xValues.size.toFloat()
        //将X轴的值显示在中央
        chart.xAxis.setCenterAxisLabels(true)
        val dataSets = ArrayList<IBarDataSet>()
        var currentPosition = 0//用于柱状图颜色集合的index
        dataLists.forEach {entry->
            val name = entry.key
            val yValueList = entry.value
            val entries = ArrayList<BarEntry>()
            for (i in yValueList.indices) {
                entries.add(BarEntry(i.toFloat(), yValueList.get(i)))
            }
            // 每一个BarDataSet代表一类柱状图
            val barDataSet = BarDataSet(entries, name)
            initBarDataSet(barDataSet, colors[currentPosition%colors.size])
            dataSets.add(barDataSet)
            currentPosition++
        }
        setDataX(xValues)

        val data = BarData(dataSets)
        /**
         * float groupSpace = 0.3f;   //柱状图组之间的间距
         * float barSpace =  0.05f;  //每条柱状图之间的间距  一组两个柱状图
         * float barWidth = 0.3f;    //每条柱状图的宽度     一组两个柱状图
         * (barWidth + barSpace) * barAmount + groupSpace = (0.3 + 0.05) * 2 + 0.3 = 1.00
         * 3个数值 加起来 必须等于 1 即100% 按照百分比来计算 组间距 柱状图间距 柱状图宽度
         */
        val barAmount = dataLists.size //需要显示柱状图的类别 数量
        //设置组间距占比30% 每条柱状图宽度占比 70% /barAmount  柱状图间距占比 0%
        //计算公式 (barWidth + barSpace) * barAmount + groupSpace = (0.3 + 0.05) * 2 + 0.3 = 1.00
        val groupSpace = 0.3f //柱状图组之间的间距
        val barWidth = (1f - groupSpace) / barAmount
        val barSpace = 0f
        //设置柱状图宽度
        data.barWidth = barWidth
        //(起始点、柱状图组间距、柱状图之间间距)
        data.groupBars(0f, groupSpace, barSpace)

        if (xValues.isNotEmpty()) {
            chart.data = data
        }else{
            chart.data = null
        }
        //设置动画效果
        chart.animateY(1500, Easing.Linear)
        //刷新
        chart.invalidate()
    }

    private fun initBarDataSet(barDataSet: BarDataSet, color: Int) {
        if (color != 0) {
            barDataSet.color = color
        }
        barDataSet.formLineWidth = 1f
        barDataSet.formSize = 15f
        //显示柱状图顶部值
        barDataSet.setDrawValues(true)
        barDataSet.valueTextSize = 9f;
        barDataSet.valueFormatter = MyValueFormatter("")
        //barDataSet.setValueTextColor(color);
    }
}