package qzl.com.tools.utils;

import android.app.Activity;
import android.os.Handler;
import android.widget.TextView;

/**
 * @desc android定时器
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2018-06-28 11:13
 * @class hzz
 * @package gsww.com.riverckeckmodule.util
 */
public class Timer {
    /**
     * 每隔1s执行一次.
     */
    private int TIME = 1000;
    /**
     * 巡检总用时
     */
    private double totalTime = 0;
    private TextView mTextView;
    private Activity mActivity;
    /**
     * 是否巡检结束的标志
     */
    private boolean timeFlag;
    Handler handler = null;

    public Timer() {}

    /**
     * @desc 使用定时器实时显示时间
     * @author Qzl
     * @time 2018-06-28 11:37
     * @param activity 上下文activity
     * @param TIME 定时器隔多长时间执行一次
     * @param textView 显示时间
     * @param time 已经走了多长时间
     */
    public Timer(Activity activity, int TIME, TextView textView, double time) {
        this.mActivity = activity;
        this.TIME = TIME;
        if (handler == null){
            handler = new Handler();
        }else {
            destory();
            handler = new Handler();
        }
        this.mTextView = textView;
        if (mTextView != null) {
            mTextView.setText(FormatNum.formatTimeStr(time));
        };
        timeFlag = true;
        totalTime = time;
        TimeIntfUtil.doSetIntervalTime(totalTime);

        // 在初始化方法里.
        handler.postDelayed(runnable, TIME);
    }

    public void checkNumTimer(double time){
        totalTime = time;
        TimeIntfUtil.doSetIntervalTime(totalTime);
        if (handler == null){
            handler = new Handler();
        }
        if (totalTime > 0) {
            // 在初始化方法里.
            handler.postDelayed(CheckNumRunnable, 1000);
        }
    }
    /**
     * @desc 返回巡检总用时 ms
     * @author Qzl
     * @time 2018-06-28 11:39
     */
    public double getTime(){
        return totalTime;
    }

    /**
     * @desc 是否停止巡检
     * @author Qzl
     * @time 2018-06-28 11:45
     */
    public void stop(){
        this.timeFlag = false;
    }
    public void destory(){
        this.timeFlag = false;
        if (handler != null) {
            handler.removeCallbacks(runnable);
            handler.removeCallbacks(CheckNumRunnable);
            handler = null;
        }
    }
    public void continueCheck(){
        timeFlag = true;
        handler.postDelayed(runnable, TIME);
    }
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                if (timeFlag) {
                    //totalTime = System.currentTimeMillis() - startTime + time;
                    totalTime += 1;
                    TimeIntfUtil.doSetIntervalTime(totalTime);
                    if (mActivity != null) {
                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (mTextView != null) {
                                    mTextView.setText(FormatNum.formatTimeStr(totalTime));
                                }
                            }
                        });
                    }
                    handler.postDelayed(this, TIME);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    Runnable CheckNumRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                totalTime -= 1;
                TimeIntfUtil.doSetIntervalTime(totalTime);
                if (totalTime > 0) {
                    handler.postDelayed(this, 1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    public interface TimeIntf{
        void setIntervalTime(double time);
    }
    public static class TimeIntfUtil{
        static TimeIntf sTimeIntf;

        public static void setTimeIntf(TimeIntf timeIntf) {
            sTimeIntf = timeIntf;
        }
        public static void doSetIntervalTime(double time){
            if (sTimeIntf != null){
                sTimeIntf.setIntervalTime(time);
            }else {
                MyLogUtils.e("接口： sTimeIntf 未初始化");
            }
        }
    }
}
