package qzl.com.main.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import org.jetbrains.anko.textColor
import qzl.com.basecommon.base.BaseFragment

/**
 * ClassName:HomeFragment
 * Description:
 */
class MvFragment : BaseFragment(){
    override fun initView(): View? {
        var tv = TextView(context)
        tv.gravity = Gravity.CENTER
        tv.textColor = Color.RED
        tv.text = javaClass.simpleName
        return tv
    }
}