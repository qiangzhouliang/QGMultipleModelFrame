package qzl.com.main.util

import qzl.com.basecommon.base.BaseFragment
import qzl.com.main.fragment.HomeFragment
import qzl.com.main.fragment.MvFragment
import qzl.com.main.fragment.VBangFragment
import qzl.com.main.fragment.YueDanFragment


/**
 * ClassName:FragmentUtil
 * Description:管理fragment的util类
 */
class FragmentUtil private constructor(){//私有化构造方法
    val homeFragment by lazy { HomeFragment() }
    val mvFragment by lazy { MvFragment() }
    val vbangFragment by lazy { VBangFragment() }
    val yuedanFragment by lazy { YueDanFragment() }
    //伴生对象
    companion object {
        val fragmentUtil by lazy { FragmentUtil() }
    }

    /**
     * 根据tabid获取对应的fragment
     */
    fun getFragment(tabId:Int): BaseFragment?{
        when(tabId){
            0 -> return homeFragment
            1 -> return mvFragment
            2 -> return vbangFragment
            3 -> return yuedanFragment
        }
        return null
    }
}