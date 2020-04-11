package qzl.com.main.fragment

import android.view.View
import qzl.com.basecommon.base.BaseFragment
import qzl.com.main.mvp.widget.MvView

/**
 * ClassName:HomeFragment
 * Description:
 */
class MvFragment : BaseFragment(){
    override fun initView(): View? {
        return MvView(context)
    }
}