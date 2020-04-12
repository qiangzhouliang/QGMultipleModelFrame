package qzl.com.fileuploadanddownload.adapter

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import org.jetbrains.anko.startActivity
import qzl.com.basecommon.base.BaseLargeImgActivity
import qzl.com.basecommon.common.Constant
import qzl.com.basecommon.net.base.BaseView
import qzl.com.basecommon.popuwindow.ExpandablePopupWindow
import qzl.com.basecommon.utils.GlideUtils.loadImgAnim
import qzl.com.fileuploadanddownload.R
import qzl.com.fileuploadanddownload.activity.CommonPicActivity
import qzl.com.fileuploadanddownload.common.Bimp
import qzl.com.fileuploadanddownload.presenter.DeleteFilePresenterImpl
import qzl.com.model.common.CommonModel
import qzl.com.model.file_operate.FileList
import qzl.com.tools.utils.StringHelper
import utilclass.Tt
import java.io.File

/**
 * @author 强周亮(Qzl)
 * @desc 事件上报图片适配器
 * @email 2538096489@qq.com
 * @time 2018-12-03 12:07
 */
class EventReportImageAdapter(private val activity: Activity, private var mDatas: ArrayList<FileList>?,
                              private val mView: View) : BaseAdapter() {
    private val mPhoto9 = 9
    private val mPhotograph = "拍照"
    private var curPosition = 0
    val deleteFilePresenterImpl by lazy { DeleteFilePresenterImpl(activity,deleteFileListener) }
    fun setmDatas(mDatas: ArrayList<FileList>) {
        this.mDatas?.addAll(mDatas)
        notifyDataSetChanged()
    }
    override fun getCount(): Int {
        return if (mDatas != null && !mDatas!!.isEmpty()) {
            if (mDatas!!.size < mPhoto9) {
                mDatas!!.size + 1
            } else {
                mDatas!!.size
            }
        } else {
            1
        }
    }

    override fun getItem(position: Int): Any? {
        return if (mDatas!!.size == position) {
            null
        } else {
            mDatas!![position]
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = LayoutInflater.from(activity).inflate(R.layout.i_material_adapter, parent, false)
        val holder = ViewHolder(convertView)
        holder.materialDel.setOnClickListener {
            curPosition = position
            val map = hashMapOf<String,String?>("busId" to mDatas!![position].busId,"table" to mDatas!![position].table,"fileNewName" to mDatas!![position].fileNewName)
            deleteFilePresenterImpl.loadDatas(map)
        }
        if (position == mDatas!!.size && mDatas!!.size < 9) {
            holder.materialDel.visibility = View.GONE
            holder.materialImg.setImageResource(R.drawable.icon_addpic_unfocused)
            holder.materialImg.setOnClickListener {
                //清除前面选择的缓存
                Bimp.drr.clear()
                val list: MutableList<String> = ArrayList()
                list.add(mPhotograph)
                list.add("从相册中选择")
                list.add("取消")
                ExpandablePopupWindow(activity, list, mView).setOnSelectedItemListener(object :
                    ExpandablePopupWindow.OnSelectedItemListener {
                    override fun onSelectedValue(str: String, position: Int) {
                        if (mPhotograph == str) {
                            releaseResource()
                        } else if ("从相册中选择" == str) {
                            val intent = Intent(activity, CommonPicActivity::class.java)
                            intent.putExtra("maxNum", 9 - mDatas!!.size)
                            activity.startActivityForResult(intent, 300)
                        }
                    }
                })
            }
        } else {
            holder.materialDel.visibility = View.VISIBLE
            if (StringHelper.isEmptyString(mDatas?.get(position)?.table)) {
                loadImgAnim(
                    activity,
                    holder.materialImg,
                    Constant.getShowImgPress(mDatas?.get(position)?.fileUrl!!+"/"+mDatas?.get(position)?.fileNewName, "1"),
                    R.drawable.common_rotate_pro,
                    R.drawable.no_img_1,
                    true
                )
                holder.materialImg.setOnClickListener {
                    activity.startActivity<BaseLargeImgActivity>("imgUrl" to Constant.getShowImgPress(mDatas?.get(position)?.fileUrl!!+"/"+mDatas?.get(position)?.fileNewName,"2"))
                }
            } else {
                loadImgAnim(
                    activity,
                    holder.materialImg,
                    Constant.getShowImgPress(mDatas?.get(position)?.fileUrl!!, "1"),
                    R.drawable.common_rotate_pro,
                    R.drawable.no_img_1,
                    true
                )
                holder.materialImg.setOnClickListener {
                    activity.startActivity<BaseLargeImgActivity>("imgUrl" to Constant.getShowImgPress(mDatas?.get(position)?.fileUrl!!,"2"))
                }
            }
        }
        return convertView
    }

    private fun releaseResource() {
        val status = Environment.getExternalStorageState()
        if (status == Environment.MEDIA_MOUNTED) {
            val dir = File(Environment.getExternalStorageDirectory().toString() + File.separator + "lzshzz/img/")
            if (!dir.exists()) {
                dir.mkdirs()
            }
            val openCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val file = File(dir, System.currentTimeMillis().toString() + ".jpg")
            photoPath = file.path
            val imageUri = Uri.fromFile(file)
            openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            openCameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0)
            activity.startActivityForResult(openCameraIntent, 200)
        }
    }

    inner class ViewHolder(convertView: View) {
        var materialImg: ImageView = convertView.findViewById<ImageView>(R.id.material_img)
        var materialDel: ImageView = convertView.findViewById<ImageView>(R.id.material_del)
    }

    companion object {
        var photoPath: String? = null
    }
    //删除文件
    val deleteFileListener = object : BaseView<CommonModel> {
        override fun onError(message: String?) {
            Tt.showShort("删除文件失败")
        }

        override fun loadSuccess(result: CommonModel?) {
            Tt.showShort(result?.message)
            if (result?.success == true){
                mDatas?.removeAt(curPosition)
                notifyDataSetChanged()
            }
        }
    }
}