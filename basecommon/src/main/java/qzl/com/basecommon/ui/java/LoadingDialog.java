package qzl.com.basecommon.ui.java;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import qzl.com.basecommon.R;
import qzl.com.basecommon.net.domain.VersionInfo;

/**
 * @author 强周亮(Qzl)
 * @desc 加载数据界面
 * @email 2538096489@qq.com
 * @time 2019-05-27 17:19
 * @class LoadingDialog
 * @package qzl.com.basecommon.ui
 */
public class LoadingDialog {
    /**
     * 得到自定义的progressDialog
     * @param context
     * @param msg
     * @return
     */
    public static Dialog createLoadingDialog(Context context, String msg) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
        // main.xml中的ImageView
        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        // 加载动画
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                context, R.anim.loading_animation);
        // 使用ImageView显示动画
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);
        tipTextView.setText(msg);// 设置加载信息

        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
        loadingDialog.setCancelable(false);// 不可以用“返回键”取消
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT));// 设置布局
        return loadingDialog;

    }

    public static Dialog initVersionDialog(Context context, VersionInfo versionInfo, View.OnClickListener confirmClickListener, View.OnClickListener cancaleClickListener) {
        Dialog loadingDialog = new Dialog(context, R.style.version_dialog);// 创建自定义样式dialog
        loadingDialog.setCancelable(false);// 不可以用“返回键”取消
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.version_dialog, null);// 得到加载view
        View confirmBtn = (View) v.findViewById(R.id.version_confirm_btn);//确认更新按钮
        confirmBtn.setOnClickListener(confirmClickListener);
        if(!versionInfo.isForceUpdate()){
            View centerLine = (View) v.findViewById(R.id.version_center_line);
            View cancaleBtn = (View) v.findViewById(R.id.version_cancle_btn);//取消更新按钮
            centerLine.setVisibility(View.VISIBLE);
            cancaleBtn.setVisibility(View.VISIBLE);
            cancaleBtn.setOnClickListener(cancaleClickListener);
        }
        TextView titleTextView = (TextView) v.findViewById(R.id.version_title);// 内容
        titleTextView.setText("发现新版本["+versionInfo.getVersion()+"]");
        TextView contentTextView = (TextView) v.findViewById(R.id.version_content);// 内容
        String content = versionInfo.getDescription().replace("|","\n");
        contentTextView.setText(content);// 设置内容
        loadingDialog.setContentView(v);// 设置布局
        return loadingDialog;
    }
}
