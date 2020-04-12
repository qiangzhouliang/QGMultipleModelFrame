package qzl.com.mpandroidchart.common

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.ValueFormatter
import qzl.com.tools.utils.NumUtil

/**
 * @desc 参数格式化
 * @author 强周亮
 * @time 2019-05-16 16:53
 */
class MyValueFormatter(private val suffix: String) : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        return NumUtil.doubleToIntStr(value) + suffix
    }

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return when {
            axis is XAxis -> NumUtil.doubleToIntStr(value)
            value > 0 -> NumUtil.doubleToIntStr(value) + suffix
            else -> NumUtil.doubleToIntStr(value)
        }
    }
}
