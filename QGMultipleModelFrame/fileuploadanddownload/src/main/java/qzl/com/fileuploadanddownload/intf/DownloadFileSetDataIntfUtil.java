package qzl.com.fileuploadanddownload.intf;

/**
 * @author Qzl
 * @desc 下载文件界面所需参数传递
 * @email 2538096489@qq.com
 * @time 2019-03-23 13:16
 */
public class DownloadFileSetDataIntfUtil {
    public static DownloadFileSetDataIntf mDownloadFileSetDataIntf;

    public static void setDownloadFileSetDataIntf(DownloadFileSetDataIntf downloadFileSetDataIntf) {
        mDownloadFileSetDataIntf = downloadFileSetDataIntf;
    }

    public static void setOpenFileLaterDeleteFile(){
        if (mDownloadFileSetDataIntf != null){
            mDownloadFileSetDataIntf.openFileLaterDeleteFile();
        }
    }
}
