package qzl.com.fileuploadanddownload.common;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Environment;
import android.view.View;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import qzl.com.basecommon.base.BaseLargeImgActivity;
import qzl.com.basecommon.arouter.ARouterPath;
import qzl.com.basecommon.ui.java.LoadingDialog;
import qzl.com.fileuploadanddownload.intf.DownloadFileSetDataIntf;
import qzl.com.fileuploadanddownload.intf.DownloadFileSetDataIntfUtil;
import qzl.com.fileuploadanddownload.popuWindow.ExpandablePopupWindow;
import qzl.com.tools.utils.FileUtil;
import qzl.com.tools.utils.MyLogUtils;
import utilclass.Tt;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Qzl
 * @desc 文件下载
 * @email 2538096489@qq.com
 * @time 2019-03-23 09:25
 */
public class DownloadFile {
    private static boolean isOpenFile;
    private static Dialog progressDialog;
    private static String mFilePath;
    /**
     * @desc 文件下载
     * @author 强周亮
     * @time 2019-03-23 09:29
     */
    public static void downloadFile(final Activity activity, final String fileName, final String fileUrl, View windowParentView, final String fileDir){
        List<String> list = new ArrayList<>();
        list.add("打开");
        list.add("下载");
        list.add("取消");
        new ExpandablePopupWindow(activity,windowParentView,list,new ExpandablePopupWindow.OnSelectedItemListener() {
            @Override
            public void onSelectedValue(String str) {
                isOpenFile = false;
                String filePath = Environment.getExternalStorageDirectory().getPath()+ File.separator+"hzz/file/"+fileDir+"/";
                    if ("打开".equals(str)){
                        isOpenFile = true;
                        if (fileName.contains("jpg")|| fileName.contains("JPG") ||fileName.contains("jpeg")|| fileName.contains("JPEG") || fileName.contains("PNG") || fileName.contains("png")){
                            //打开图片
                            openImage(activity,fileUrl);
                        }else if (fileName.contains("mp4")) {
                            openVideos(fileUrl);
                        }else {
                            //先下载，再打开
                            download(filePath, fileUrl,activity, fileName);
                        }
                    }else if ("下载".equals(str)){
                        //下载
                        download(filePath, fileUrl, activity, fileName);
                    }
                }
            });

        /**
         * 在需要删除查看后的文件的activity里面写上这个，就可以删掉当前查看的文件
         * override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         *         super.onActivityResult(requestCode, resultCode, data)
         *         if (requestCode == 10000) {
         *             DownloadFileSetDataIntfUtil.setOpenFileLaterDeleteFile()
         *         }
         *     }
         */
        DownloadFileSetDataIntfUtil.setDownloadFileSetDataIntf(new DownloadFileSetDataIntf() {
            @Override
            public void openFileLaterDeleteFile() {
                File file1 = new File(mFilePath);
                if (file1.exists()){
                    file1.delete();
                }
            }
        });
    }

    private static void download(String filePath, String fileUrl, final Activity finalMActivity, String fileName) {
        MyLogUtils.e("download "+fileUrl);
        File file = new File(filePath);
        if (!file.exists()){
            file.mkdir();
        }else {
            mFilePath =filePath+fileName;
            File file1 = new File(mFilePath);
            if (file1.exists() && !file1.isDirectory()){
                if (isOpenFile){
                    FileUtil.openFile(finalMActivity,mFilePath);
                }else {
                    Tt.showLong("文件已在"+mFilePath+"目录下存在");
                }
                return;
            }
        }
        progressDialog = LoadingDialog.createLoadingDialog(finalMActivity, "文件下载中...");
        progressDialog.show();
        HttpUtils utils = new HttpUtils();
        utils.download(fileUrl, filePath + fileName, new RequestCallBack<File>() {
            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
                mFilePath = responseInfo.result.getPath();
                if (isOpenFile){
                    FileUtil.openFile(finalMActivity,mFilePath);
                }else {
                    Tt.showLong("下载完成,文件存储位置为：" + mFilePath);
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Tt.showLong("下载失败");
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        });
    }

    /**
     * @desc 打开视频
     * @author 强周亮
     * @time 2019-03-23 11:55
     */
    private static void openVideos(String VideoUrl) {
        ARouter.getInstance().build(ARouterPath.VIDEO_PLAYER)
                .withString("videoUrl", VideoUrl)
                .navigation();
    }

    /**
     * @desc 打开图片
     * @author 强周亮
     * @time 2019-03-23 10:01
     */
    private static void openImage(Activity activity,String imageUrl) {
        if (activity != null) {
            Intent intent = new Intent();
            intent.putExtra("imgUrl", imageUrl);
            intent.setClass(activity, BaseLargeImgActivity.class);
            activity.startActivity(intent);
        }else {
            MyLogUtils.e("DownloadFile，mActivity 为null");
        }
    }
}
