package com.zdww.login.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * @author 强周亮(qiangzhouliang)
 * @desc 自定义ViewPager
 * @email 2538096489@qq.com
 * @time 2019/10/30 14:44
 * @class SpecialTouchViewPager
 * @package com.zdww.basecommon.ui.java
 */
public class SpecialTouchViewPager extends ViewPager {
    public SpecialTouchViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpecialTouchViewPager(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    private float startX = 0;
    private float startY = 0;
    private float endX = 0;
    private float endY = 0;
    private boolean isLastPage = false;
    private onLastPageListener listener;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isLastPage) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = event.getX();
                    startY = event.getY();
                    int i = 0;
                    break;
                case MotionEvent.ACTION_MOVE:
                    endX = event.getX();
                    endY = event.getY();
                    float dX = endX - startX;
                    float dY = endY - startY;
                    if (dX < -10) {
                        listener.onLast();
//                        return true;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    break;
                default:
                    break;
            }
        }
        return super.onTouchEvent(event);
    }

    public void setLastPageTag(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public void setOnLastPageListener(onLastPageListener listener) {
        this.listener = listener;
    }

    public interface onLastPageListener {
        void onLast();
    }
}
