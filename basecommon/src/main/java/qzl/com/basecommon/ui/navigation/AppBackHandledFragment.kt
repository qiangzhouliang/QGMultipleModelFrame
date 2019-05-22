package qzl.com.basecommon.ui.navigation

import android.os.Bundle
import android.support.v4.app.Fragment


class AppBackHandledFragment : Fragment() {

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
