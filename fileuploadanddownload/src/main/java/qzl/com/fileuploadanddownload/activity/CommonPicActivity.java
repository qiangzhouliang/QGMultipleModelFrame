package qzl.com.fileuploadanddownload.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import qzl.com.basecommon.base.BaseActivity;
import qzl.com.fileuploadanddownload.common.AlbumHelper;
import qzl.com.fileuploadanddownload.R;
import qzl.com.fileuploadanddownload.adapter.ImageBucketAdapter;
import qzl.com.fileuploadanddownload.bean.ImageBucket;

import java.io.Serializable;
import java.util.List;

/**
 * @desc  图片选择activity
 * @author 强周亮（Qzl）
 * @email 2538096489@qq.com
 * @time 2018-09-04 16:18
 * @class CommonPicActivity
 * @package com.gsww.hzz.materialselect.photo
 */
public class CommonPicActivity extends BaseActivity {
    List<ImageBucket> dataList;
    GridView gridView;
    /**
     * 自定义的适配器
     */
    ImageBucketAdapter adapter;
    AlbumHelper helper;
    public static final String EXTRA_IMAGE_LIST = "imageList";
    public static Bitmap bitmap;
    private int maxPhotoNum=9;
    @Override
    public int getLayoutId() {
        return R.layout.activity_image_bucket;
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData(){
        dataList = helper.getImagesBucketList(false);
        adapter = new ImageBucketAdapter(CommonPicActivity.this, dataList);
        gridView.setAdapter(adapter);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_addpic_unfocused);
    }
    /**
     * 初始化view视图
     */
    @Override
    public void initView() {
        initHead(R.id.head_layout, "相册", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishWithAnimation();
            }
        });
        if(getIntent()!=null){
            maxPhotoNum = getIntent().getIntExtra("maxNum",9);
        }
        helper = AlbumHelper.getHelper();
        helper.init(getApplicationContext());
        gridView = (GridView) findViewById(R.id.gridview);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CommonPicActivity.this, ImageGridActivity.class);
                intent.putExtra("maxNum",maxPhotoNum);
                intent.putExtra(CommonPicActivity.EXTRA_IMAGE_LIST, (Serializable) dataList.get(position).imageList);
                startActivityForResult(intent, RESULT_FIRST_USER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null){
            setResult(RESULT_OK, data);
            finish();
        }
    }

}
