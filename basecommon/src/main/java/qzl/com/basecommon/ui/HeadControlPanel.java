package qzl.com.basecommon.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import qzl.com.basecommon.R;

/**
 * Created by 魏荣 on 2015/05/15.
 */
public class HeadControlPanel extends RelativeLayout {
    private Context mContext;
    private TextView mMidleTitle;
    private ImageView mLeftImage;
    private TextView mRightText;
    private ImageView mRightImg;
    private TextView mLeftText;
    private ImageView mLeftImg;
    private LinearLayout headLayoutBack;
    private View head_view;
    private static final float middle_title_size = 20f;
    private static final float title_size = 18f;
    private static final int default_background_color = Color.rgb(99, 220, 215);

    public HeadControlPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMidleTitle = (TextView) findViewById(R.id.midle_title);
        mRightText = (TextView) findViewById(R.id.right_btn_text);
        mLeftText = (TextView) findViewById(R.id.left_btn_text);
        mLeftImage = (ImageView) findViewById(R.id.menu_btn_img);
        mRightImg = (ImageView) findViewById(R.id.right_btn_img);
        mLeftImg = (ImageView) findViewById(R.id.left_btn_img);
        headLayoutBack = (LinearLayout) findViewById(R.id.head_layout_back);
        head_view = (View) findViewById(R.id.head_view);
        if (!isInEditMode()){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (head_view != null) {
                    head_view.setVisibility(VISIBLE);
                }
            }
        }
        //setBackgroundResource(R.color.cell_ordinary_m);
    }

    public void initHeadPanel() {
        if (mMidleTitle != null) {
            setMiddleTitle("首页");
        }
    }

    public void setMiddleTitle(String s) {
        if ("河长制移动平台".equals(s)) {
            s = "河长制移动平台";
        }
        if ("河长制移动平台".equals(s)) {
            mMidleTitle.setTextSize(middle_title_size);
            mMidleTitle.setText(s);
        } else {
            mMidleTitle.setTextSize(title_size);
            if(s.length() == 2){
               s = s.charAt(0) + " " + s.charAt(1);
            }
            mMidleTitle.setText(s);
        }
    }

    public ImageView getmLeftImage() {
        return mLeftImage;
    }

    public TextView getmRightText() {
        return mRightText;
    }
    public ImageView getmRightImg() {
        return mRightImg;
    }

    public TextView getLeftText() {
        return mLeftText;
    }

    public ImageView getLeftImg() {
        return mLeftImg;
    }

    public LinearLayout getHeadLayoutBack() {
        return headLayoutBack;
    }
}
