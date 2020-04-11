package qzl.com.basecommon.utils

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.GrayscaleTransformation
import jp.wasabeef.glide.transformations.MaskTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import qzl.com.basecommon.R


/**
 * @author Qzl
 * @desc 加载图片工具类
 * @email 2538096489@qq.com
 * @time 2019-05-23 17:44
 */
object GlideUtils {
    /**
     * @desc 加载图片，带有加载中动画
     * @author 强周亮
     * @time 2019-05-23 17:51
     */
    fun loadImgAnim(mContext: Context, iv: ImageView, imgUrl: String, rotate: Int = R.drawable.common_rotate_pro,errorImg:Int = R.drawable.no_img_1,isShowAnim:Boolean = true) {
        val options = RequestOptions().placeholder(rotate)
        loadImgAnimCommom(
            mContext,
            iv,
            imgUrl,
            options,
            errorImg,
            isShowAnim
        )
    }

    /**
     * @desc 加载图片，使图片模糊
     * @author 强周亮
     * @time 2019-05-23 17:51
     */
    fun loadImgBlurTrans(mContext: Context, iv: ImageView, imgUrl: String,radius:Int,sampling:Int,errorImg:Int = R.drawable.no_img_1) {
        val options = RequestOptions.bitmapTransform(BlurTransformation(radius,sampling))
        loadImgAnimCommom(
            mContext,
            iv,
            imgUrl,
            options,
            errorImg
        )
    }
    /**
     * @desc 加载圆角图片
     * @author 强周亮
     * @time 2019-05-23 17:51
     */
    fun loadImgRoundedCorners(mContext: Context, iv: ImageView, imgUrl: String,radius:Int,margin:Int,errorImg:Int = R.drawable.no_img_1) {
        val options = RequestOptions.bitmapTransform(
            RoundedCornersTransformation(radius, margin, RoundedCornersTransformation.CornerType.ALL)
        )
        loadImgAnimCommom(
            mContext,
            iv,
            imgUrl,
            options,
            errorImg
        )
    }
    /**
     * @desc 图片上添加遮盖
     * @author 强周亮
     * @time 2019-05-23 17:51
     */
    fun loadImgMask(mContext: Context, iv: ImageView, imgUrl: String,maskId:Int,errorImg:Int = R.drawable.no_img_1) {
        val options = RequestOptions.bitmapTransform(MaskTransformation(maskId))
        loadImgAnimCommom(
            mContext,
            iv,
            imgUrl,
            options,
            errorImg
        )
    }
    /**
     * @desc 图片灰度处理
     * @author 强周亮
     * @time 2019-05-23 17:51
     */
    fun loadImgGrayscale(mContext: Context, iv: ImageView, imgUrl: String,errorImg:Int = R.drawable.no_img_1) {
        val options = RequestOptions.bitmapTransform(GrayscaleTransformation())
        loadImgAnimCommom(
            mContext,
            iv,
            imgUrl,
            options,
            errorImg
        )
    }
    /**
     * @desc 加载圆形图片
     * @author 强周亮
     * @time 2019-05-23 17:51
     */
    fun loadImgCropCircle(mContext: Context, iv: ImageView, imgUrl: String,errorImg:Int = R.drawable.no_img_1,isShowAnim:Boolean = true) {
        val options = RequestOptions.circleCropTransform()
        loadImgAnimCommom(
            mContext,
            iv,
            imgUrl,
            options,
            errorImg,
            isShowAnim
        )
    }
    /**
     * @desc 加载图片，带有加载中动画
     * @author 强周亮
     * @time 2019-05-23 17:51
     */
    fun loadImgAnimCommom(mContext: Context, iv: ImageView, imgUrl: String, options: RequestOptions,errorImg:Int = R.drawable.no_img_1,isShowAnim:Boolean = true) {
        val anim = objectAnimator(iv)
        if (isShowAnim){
            anim.start()
        }

        options.error(errorImg)
        Glide.with(mContext).load(imgUrl).apply(options)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    anim.cancel()
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    anim.cancel()
                    return false
                }

            }).into(iv)
    }

    /**
     * @desc 设置动画
     * @author 强周亮
     * @time 2019-05-23 19:23
     */
    private fun objectAnimator(iv: ImageView): ObjectAnimator {
        val anim = ObjectAnimator.ofInt(iv, "ImageLevel", 0, 10000)
        anim.duration = 800
        anim.repeatCount = ObjectAnimator.INFINITE
        return anim
    }
}
