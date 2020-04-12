package qzl.com.mpandroidchart.common

import android.graphics.Color
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

/**
 * @desc
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-05-13 12:04
 */
class PieChart(val chart: PieChart,val maxAngle:Float = 360f) {

    fun initPieChart() {
        chart.setBackgroundColor(Color.WHITE)
        chart.setUsePercentValues(true)
        chart.description.isEnabled = false
        chart.isDrawHoleEnabled = true
        chart.setHoleColor(Color.WHITE)
        chart.setTransparentCircleColor(Color.WHITE)
        chart.setTransparentCircleAlpha(110)
        //设置半径
        chart.holeRadius = 58f
        chart.transparentCircleRadius = 61f
        chart.setDrawCenterText(true)
        //设置是否可以旋转
        chart.isRotationEnabled = true
        //false 禁止点击放大的方法
        chart.isHighlightPerTapEnabled = true
        //显示成百分比
        chart.setUsePercentValues(false)
        chart.maxAngle = maxAngle // HALF CHART
        chart.rotationAngle = maxAngle
        chart.isHighlightPerTapEnabled = true
        //设置中间内容偏移
//        chart.setCenterTextOffset(0f, -20f)
        chart.animateY(1400, Easing.EaseInOutQuad)
        //设置指示标签的一些操作
        val l = chart.getLegend()
        l.isEnabled = false
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        l.xEntrySpace = 7f
        l.yEntrySpace = 0f
        l.yOffset = 0f
        // entry label styling
        chart.setEntryLabelColor(Color.WHITE)
        chart.setEntryLabelTextSize(12f)
        chart.setPadding(4,4,4,4)
    }
    fun setCenterContent(countTotal:String,countName:String){
        chart.centerText = generateCenterSpannableText(countTotal,countName)
    }
    fun generateCenterSpannableText(countTotal:String,countName:String): SpannableString {

        val s = SpannableString("${countTotal}\n${countName}")
        s.setSpan(RelativeSizeSpan(1.8f), 0, countTotal.length, 0)
        s.setSpan(RelativeSizeSpan(1.8f), 0, s.length-countName.length, 0)
        s.setSpan(ForegroundColorSpan(ColorTemplate.getHoloBlue()), 0, s.length-countName.length, 0)
//        s.setSpan(StyleSpan(Typeface.NORMAL), 5, s.length - 6, 0)
//        s.setSpan(ForegroundColorSpan(Color.GRAY), 5, s.length - 6, 0)
//        s.setSpan(RelativeSizeSpan(.8f), 5, s.length - 6, 0)
//        s.setSpan(StyleSpan(Typeface.ITALIC), s.length - 5, s.length, 0)
//        s.setSpan(ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length - 5, s.length, 0)
        return s
    }

    fun setData(values: MutableList<PieEntry>, colors:MutableList<Int>,label: String = "") {
        val dataSet = PieDataSet(values, label)
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f
        //设置显示颜色样式
        dataSet.setColors(*ColorTemplate.MATERIAL_COLORS)
        dataSet.colors = colors
        dataSet.valueLinePart1OffsetPercentage = 80f
        dataSet.valueLinePart1Length = 0.2f
        dataSet.valueLinePart2Length = 0.4f
        dataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE

        val data = PieData(dataSet)
        data.setValueFormatter(MyValueFormatter(""))
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.BLACK)
        chart.data = data
        chart.invalidate()
    }
}