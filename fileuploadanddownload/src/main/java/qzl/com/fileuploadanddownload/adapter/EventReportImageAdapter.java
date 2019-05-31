package qzl.com.fileuploadanddownload.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import qzl.com.fileuploadanddownload.Common.Bimp;
import qzl.com.fileuploadanddownload.R;
import qzl.com.fileuploadanddownload.activity.CommonPicActivity;
import qzl.com.fileuploadanddownload.popuWindow.ExpandablePopupWindow;
import qzl.com.tools.utils.ScreenUtil;
import utilclass.Tt;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 强周亮(Qzl)
 * @desc 事件上报图片适配器
 * @email 2538096489@qq.com
 * @time 2018-12-03 12:07
 * @class EventReportImageAdapter
 * @package com.gsww.hzz.event.adapter
 */
public class EventReportImageAdapter extends BaseAdapter {
    private final int mPhoto9 = 9;
    private final String mPhotograph = "拍照";
    private Activity activity;
    private List<String> mDatas;
    public static String photoPath;
    private int curPosition = 0;
    private boolean flag;

    public EventReportImageAdapter(Activity activity, List<String> mDatas) {
        this.activity = activity;
        this.mDatas = mDatas;
    }

    public void setmDatas(List<String> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
        this.flag = true;
    }

    /**
     * @desc 给adapter设置数据
     * @author Qzl
     * @time 2018-08-09 19:56
     * @param mDatas 数据集
     * @param flag 标志加载网络图片还是加载本地图片，true,本地图片，false：网络图片
     */
    public void setmDatas(List<String> mDatas,boolean flag) {
        this.flag = flag;
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        if (mDatas != null && !mDatas.isEmpty()) {
            if (mDatas.size() < mPhoto9) {
                return mDatas.size() + 1;
            } else {
                return mDatas.size();
            }
        } else {
            return 1;
        }
    }

    @Override
    public Object getItem(int position) {
        if (mDatas.size() == position) {
            return null;
        } else {
            return mDatas.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.i_material_adapter, parent, false);
            holder = new ViewHolder();
            holder.materialDel = (ImageView) convertView.findViewById(R.id.material_del);
            holder.materialImg = (ImageView) convertView.findViewById(R.id.material_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.materialDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curPosition = position;
                Tt.showShort("删除");
            }
        });
        if (position == mDatas.size()) {
            holder.materialDel.setVisibility(View.GONE);
            holder.materialImg.setImageResource(R.drawable.icon_addpic_unfocused);
            holder.materialImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    //清除前面选择的缓存
                    Bimp.drr.clear();
                    List<String> list = new ArrayList<>();
                    list.add(mPhotograph);
                    list.add("从相册中选择");
                    list.add("取消");
                    final ExpandablePopupWindow expandablePopupWindow = new ExpandablePopupWindow(activity, list);
                    expandablePopupWindow.setOnSelectedItemListener(new ExpandablePopupWindow.OnSelectedItemListener() {
                        @Override
                        public void onSelectedValue(String str) {
                            if (mPhotograph.equals(str)) {
                                releaseResource();
                            } else if (("从相册中选择").equals(str)){
                                Intent intent = new Intent(activity, CommonPicActivity.class);
                                intent.putExtra("maxNum", 9 - mDatas.size());
                                activity.startActivityForResult(intent, 300);
                            }else {
                                expandablePopupWindow.dismiss();
                            }
                        }
                    });
                    LinearLayout parentView = (LinearLayout) activity.findViewById(R.id.parent_ll);
                    expandablePopupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, ScreenUtil.getVirtualBarHeight(activity));
                }
            });
        } else {
            holder.materialDel.setVisibility(View.VISIBLE);
            /*if (flag) {
                BitmapHelper.displayFileImage(StringHelper.toString(mDatas.get(position)), holder.materialImg);
            }else {
                BitmapHelper.displayListImage(StringHelper.toString(mDatas.get(position)),holder.materialImg);
            }*/
        }
        return convertView;
    }


    private void releaseResource() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            File dir = new File(Environment.getExternalStorageDirectory() + File.separator + "hzz/video/");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File file = new File(dir, String.valueOf(System.currentTimeMillis()) + ".jpg");
            photoPath = file.getPath();
            Uri imageUri = Uri.fromFile(file);
            openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            openCameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
            activity.startActivityForResult(openCameraIntent, 200);
        }
    }

    public class ViewHolder {
        ImageView materialImg;
        ImageView materialDel;
    }
}
