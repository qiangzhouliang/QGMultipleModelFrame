package qzl.com.qgmultiplemodelframe
import android.content.Intent
import android.view.KeyEvent
import android.view.View
import com.qzl.toast.MyToast
import kotlinx.android.synthetic.main.activity_error.*
import qzl.com.basecommon.base.BaseActivity

/**
 * @author 强周亮
 * @desc 公共错误处理页面
 * @email 2538096489@qq.com
 * @time 2018-08-31 09:23
 * @class HzzErrorReportActivity
 */
class HzzErrorReportActivity : BaseActivity() {
    private var exMsg: String? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_error
    }

    override fun initView() {
        MyToast.init(this)
        init()
    }

    private fun init() {
        this.initHead(R.id.head_layout,"错误信息提示页面", View.OnClickListener {finishWithAnimation() })
        exMsg = intent.getStringExtra("msg")
        error_report_restart_btn.setOnClickListener {
            restart()
        }
        error_report_send_btn.setOnClickListener {
            MyToast.showShort("上报")
        }
    }

    /**
     * @desc 重启应用
     * @author Qzl
     * @time 2018-08-31 09:27
     */
    private fun restart() {
        val i = baseContext.packageManager.getLaunchIntentForPackage(baseContext.packageName)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(i)
        finishWithAnimation()
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        finishWithAnimation()
        return super.onKeyDown(keyCode, event)
    }
}
