package qzl.com.fileuploadanddownload.activity

import android.content.Intent
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.activity_file.*
import qzl.com.basecommon.base.BaseActivity
import qzl.com.basecommon.common.ARouterPath
import qzl.com.fileuploadanddownload.R
import qzl.com.fileuploadanddownload.adapter.EventReportImageAdapter
import qzl.com.fileuploadanddownload.common.DownloadFile
import java.util.*

@Route(path = ARouterPath.FILE)
class FileActivity : BaseActivity(), View.OnClickListener {
    val imageList by lazy { ArrayList<String>() }
    private var eventReportImageAdapter: EventReportImageAdapter? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_file
    }

    override fun initView() {
        this.initHead(R.id.head_layout,"文件上传和下载", View.OnClickListener { finishWithAnimation()})
        eventReportImageAdapter = EventReportImageAdapter(this, imageList)
        image.adapter = eventReportImageAdapter
    }

    override fun initListener() {
        download_img.setOnClickListener(this)
        download_video.setOnClickListener(this)
    }

    protected override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            val filePath = data.getStringExtra("filePath")
            /*FileUploadUtil().upLoadByAsyncHttpClient(this, filePath, "video", busId, "eventReport", "bas_file_event", "")
            FileUploadUtil.UpLoadFileIntfUtil.setUpLoadFileIntf(object : FileUploadUtil.UpLoadFileIntf() {
                fun setData(state: Boolean, map: Map<*, *>) {
                    if (state) {
                        videoNames.add(OracleAndMysqlGetValue.getStrValue(map, "msg"))
                        videoList.add(OracleAndMysqlGetValue.getStrValue(map, "filePath"))
                        eventReportVideoAdapter.setmDatas(videoList)
                        Tt.showShort(getString(R.string.upload_success))
                    } else {
                        Tt.showShort(OracleAndMysqlGetValue.getStrValue(map, "msg"))
                    }
                }
            })*/
        } else if (requestCode == 200 && resultCode == RESULT_OK) {
            /*FileUploadUtil().upLoadByAsyncHttpClient(this, EventReportImageAdapter.photoPath, "img", busId, "eventReport", "bas_file_event", "")
            FileUploadUtil.UpLoadFileIntfUtil.setUpLoadFileIntf(object : FileUploadUtil.UpLoadFileIntf() {
                fun setData(state: Boolean, map: Map<*, *>) {
                    if (state) {
                        imageNames.add(OracleAndMysqlGetValue.getStrValue(map, "msg"))
                        imageList.add(OracleAndMysqlGetValue.getStrValue(map, "filePath"))
                        eventReportImageAdapter.setmDatas(imageList)
                        Tt.showShort(getString(R.string.upload_success))
                    } else {
                        Tt.showShort(OracleAndMysqlGetValue.getStrValue(map, "msg"))
                    }
                }
            })*/
        } else if (requestCode == 300 && resultCode == RESULT_OK) {
            val filePathList = data!!.getStringArrayListExtra("filePathList")
            for (filePath in filePathList) {
                /*FileUploadUtil().upLoadByAsyncHttpClient(this, filePath, "img", busId, "eventReport", "bas_file_event", "")
                FileUploadUtil.UpLoadFileIntfUtil.setUpLoadFileIntf(object : FileUploadUtil.UpLoadFileIntf() {
                    fun setData(state: Boolean, map: Map<*, *>) {
                        if (state) {
                            imageNames.add(OracleAndMysqlGetValue.getStrValue(map, "msg"))
                            imageList.add(OracleAndMysqlGetValue.getStrValue(map, "filePath"))
                            eventReportImageAdapter.setmDatas(imageList)
                            Tt.showShort(getString(R.string.upload_success))
                        } else {
                            Tt.showShort(OracleAndMysqlGetValue.getStrValue(map, "msg"))
                        }
                    }
                })*/
            }
        }
    }
    override fun onClick(v: View) {
        when(v.id){
            //下载
            R.id.download_img -> {
                DownloadFile.downloadFile(this, "1-1Z120161S9.jpg", "https://img.gsdlcn.com/uploads/allimg/190120/1-1Z120161S9.jpg", parent_ll, "newsNotice")
            }
            R.id.download_video -> {
                DownloadFile.downloadFile(this, "0FBB016ADECDDFF86FF7D6E8CE792DCC.mp4", "http://hc.yinyuetai.com/uploads/videos/common/0FBB016ADECDDFF86FF7D6E8CE792DCC.mp4?sc=c19481d47147e188&br=785&rd=Android", parent_ll, "video")
            }
        }
    }
}
