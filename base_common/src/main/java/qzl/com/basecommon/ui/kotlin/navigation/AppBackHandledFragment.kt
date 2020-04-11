package qzl.com.basecommon.ui.kotlin.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment


class AppBackHandledFragment : androidx.fragment.app.Fragment() {

    protected lateinit var mBackHandledInterface: AppBackHandledInterface

    /**
     * 所有继承BackHandledFragment的子类都将在这个方法中实现物理Back键按下后的逻辑
     */
    fun onBackPressed(): Boolean {
        return false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity is AppBackHandledInterface) {
            this.mBackHandledInterface = activity as AppBackHandledInterface
            throw ClassCastException("Hosting Activity must implement BackHandledInterface")
        } else {
            throw ClassCastException("Hosting Activity must implement BackHandledInterface")
        }
    }

    override fun onStart() {
        super.onStart()
        mBackHandledInterface.setSelectedFragment(this)
    }

}
