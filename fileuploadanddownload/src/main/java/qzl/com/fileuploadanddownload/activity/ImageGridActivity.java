package qzl.com.fileuploadanddownload.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import qzl.com.basecommon.base.BaseActivity;
import qzl.com.fileuploadanddownload.common.AlbumHelper;
import qzl.com.fileuploadanddownload.common.Bimp;
import qzl.com.fileuploadanddownload.R;
import qzl.com.fileuploadanddownload.adapter.ImageGridAdapter;
import qzl.com.fileuploadanddownload.bean.ImageItem;
import utilclass.Tt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ImageGridActivity extends BaseActivity {
	public static final String EXTRA_IMAGE_LIST = "imageList";
	List<ImageItem> dataList;
	GridView gridView;
	ImageGridAdapter adapter;
	AlbumHelper helper;
	TextView submitView;
	private ImageView backView;
	private int maxNum=9;
	private ArrayList<String> filePathList = new ArrayList<>();
	@Override
	public int getLayoutId() {
		return R.layout.activity_image_grid;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_image_grid);

		initView();
	}
	@Override
	public void initView() {
		helper = AlbumHelper.getHelper();
		helper.init(getApplicationContext());
		if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
			findViewById(R.id.ll_select_phote).setVisibility(View.GONE);
		}

		dataList = (List<ImageItem>) getIntent().getSerializableExtra(EXTRA_IMAGE_LIST);
		if(getIntent()!=null  ){
			maxNum =  getIntent().getIntExtra("maxNum",9);
		}
		submitView = (TextView) findViewById(R.id.tv_submit);
		backView = (ImageView) findViewById(R.id.tv_back);

		backView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		submitView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ArrayList<String> list = new ArrayList<String>();
				ArrayList<String> removeList = new ArrayList<String>();
				Collection<String> c = adapter.map.values();
				Iterator<String> it = c.iterator();
				for (; it.hasNext();) {
					list.add(it.next());
				}
				for (int i = 0; i < list.size(); i++) {
					if (Bimp.drr.size() < maxNum) {
						Bimp.drr.add(list.get(i));
					}
				}
				Collection<String> rm = adapter.removeMap.values();
				Iterator<String> rmIt = rm.iterator();
				for (; rmIt.hasNext();) {
					removeList.add(rmIt.next());
				}
				for (int i = 0; i < removeList.size(); i++) {
					if(Bimp.drr!=null && Bimp.drr.size()>0){
						for(int j=0;j<Bimp.drr.size();j++){
							if(removeList.get(i)!=null && removeList.get(i).equals(Bimp.drr.get(j))){
								Bimp.drr.remove(j);
								Bimp.max -=1;
								continue;
							}
						}
					}
				}
				Intent intent = new Intent();
				filePathList = (ArrayList<String>) Bimp.drr;
				intent.putStringArrayListExtra("filePathList",filePathList);
				setResult(RESULT_OK, intent);
				finish();
			}

		});
		gridView = (GridView) findViewById(R.id.gridview);
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		adapter = new ImageGridAdapter(ImageGridActivity.this, dataList, mHandler,maxNum);
		gridView.setAdapter(adapter);
		adapter.setTextCallback(new ImageGridAdapter.TextCallback() {
			@Override
			public void onListen(int count) {
				if (count>0) {
					submitView.setText("确认" + "(" + count + ")");
				}else if (count == 0) {
					submitView.setText("确认");
				}
			}
		});

		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				if (dataList.get(position).isSelected()) {
					dataList.get(position).setIsSelected(false);
				} else {
					dataList.get(position).setIsSelected(true);
				}
				adapter.notifyDataSetChanged();
			}

		});

	}
	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case 0:
					Tt.showShort("最多选择"+maxNum+"张图片");
					break;

				default:
					break;
			}
		}
	};

}
