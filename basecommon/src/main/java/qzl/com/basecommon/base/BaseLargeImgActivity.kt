package qzl.com.basecommon.base

import kotlinx.android.synthetic.main.p_img.*
import qzl.com.basecommon.R
import qzl.com.basecommon.utils.GlideUtils

/**
 * @desc  显示大图activity
 * @author 强周亮（Qzl）
 * @email 2538096489@qq.com
 * @time 2018-09-06 17:37
 * @class ImgActivity
 * @package com.gsww.mapmodule.activity
 */
class BaseLargeImgActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.p_img
    }

    override fun initData() {
        val url = intent.getStringExtra("imgUrl")
        GlideUtils.loadImgAnim(this, large_img, url)
    }

    override fun initListener() {
        img_back.setOnClickListener {
            finishWithAnimation()
        }
    }
}
