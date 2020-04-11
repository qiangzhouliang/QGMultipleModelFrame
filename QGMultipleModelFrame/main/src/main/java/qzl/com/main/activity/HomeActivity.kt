package qzl.com.main.activity

import android.content.DialogInterface
import android.content.Intent
import android.view.KeyEvent
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.SimpleAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.index.*
import kotlinx.android.synthetic.main.left.*
import kotlinx.android.synthetic.main.main.*
import qzl.com.basecommon.arouter.ARouterPath
import qzl.com.basecommon.base.BaseActivity
import qzl.com.basecommon.ui.kotlin.DialogPanel
import qzl.com.basecommon.ui.kotlin.HeadControlPanel
import qzl.com.main.R
import qzl.com.main.extensions.checkVesion
import qzl.com.main.extensions.menuItems
import qzl.com.main.util.FragmentUtil
import qzl.com.main.view.java.DragLayout
import qzl.com.tools.operate.CompleteQuit
import qzl.com.tools.utils.AppUtil
import utilclass.Tt
/**
 * @author 强周亮(Qzl)
 * @desc 首页
 * @email 2538096489@qq.com
 * @time 2019-05-28 14:00
 * @class HomeActivity
 * @package qzl.com.main.activity
 */
@Route(path = ARouterPath.Home.HOME_ACTIVITY)
class HomeActivity : BaseActivity() {
    private var exitTime: Long = 0
    override fun getLayoutId(): Int { return R.layout.index }
    override fun initView() {
        findViewById<HeadControlPanel>(R.id.head_layout).setMiddleTitle("首页")
        //软件版本信息
        version.text = "v${AppUtil.getVersionName(this)}"
    }
    override fun initListener() {
        dl.setDragListener(object : DragLayout.DragListener {
            // 动作监听
            override fun onOpen() {}

            override fun onClose() {}

            override fun onDrag(percent: Float) {}
        })
        menu_listview.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            when (position) {
                0 -> { Tt.showShort("我的信息") }
                1 -> {
                    //修改密码
                    Tt.showShort("修改密码")
                }
                2 -> {
                    //检查更新
                    checkVesion();
                }
                3 ->{
                    //应用推荐
                    val smsBody = "应用推荐"
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.type = "vnd.android-dir/mms-sms"
                    intent.putExtra("sms_body", smsBody)
                    startActivity(Intent.createChooser(intent, "短信分享"))
                }
                4 -> {
                    //系统帮助
                    Tt.showShort("系统帮助")
                }
            }
        }
        // 添加监听，可点击呼出菜单
        findViewById<LinearLayout>(R.id.head_layout_back).setOnClickListener { dl.open() }

        //切换用户
        menu_logout.setOnClickListener {
            DialogPanel.dialogOperate(this, getString(R.string.exit_info), getString(R.string.tip),
                "确定", DialogInterface.OnClickListener { dialog, which ->
                    Tt.showShort("确定")
                    dialog.dismiss()
                    finishWithAnimation()
                },
               "取消",
                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() }
            )
        }
        //底部导航栏选择
        bottomBar.setOnTabSelectListener {
            //it  代表tableid
            val transaction = supportFragmentManager.beginTransaction()
            when(it){
                R.id.tab_home -> FragmentUtil.fragmentUtil.getFragment(0)?.let { it1 -> transaction.replace(R.id.container, it1,it.toString()) }
                R.id.tab_mv -> FragmentUtil.fragmentUtil.getFragment(1)?.let { it1 -> transaction.replace(R.id.container, it1,it.toString()) }
                R.id.tab_vbang -> FragmentUtil.fragmentUtil.getFragment(2)?.let { it1 -> transaction.replace(R.id.container, it1,it.toString()) }
                R.id.tab_yuedan -> FragmentUtil.fragmentUtil.getFragment(3)?.let { it1 -> transaction.replace(R.id.container, it1,it.toString()) }
            }

            transaction.commit()
        }
    }

    override fun initData() {
        // 生成测试菜单选项
        menu_listview.adapter = SimpleAdapter(this, menuItems, R.layout.item, arrayOf("menu_text", "menu_ico"), intArrayOf(R.id.menu_text, R.id.menu_ico))
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Tt.showShort(getString(R.string.exit_info))
                exitTime = System.currentTimeMillis()
            } else {
                CompleteQuit.getInstance()!!.exitAll(true)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
