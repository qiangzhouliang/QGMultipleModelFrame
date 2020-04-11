package com.zdww.login.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Regex;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.zdww.login.R;
import com.zdww.login.presenter.ChangePwdPresenterImpl;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

import qzl.com.basecommon.arouter.ARouterPath;
import qzl.com.basecommon.base.BaseActivity;
import qzl.com.basecommon.common.Constant;
import qzl.com.basecommon.common.SysAccount;
import qzl.com.basecommon.net.base.BaseView;
import qzl.com.model.common.CommonModel;
import qzl.com.tools.operate.CompleteQuit;
import qzl.com.tools.utils.MD5;
import utilclass.Tt;

/**
 * @author 强周亮(qiangzhouliang)
 * @desc 修改密码页面
 * @email 2538096489@qq.com
 * @time 2019/11/5 14:15
 * @class MenuModifyPwdActivity
 * @package com.zdww.loginmodel.activity
 */
@Route(path = ARouterPath.Login.CHANGE_PASSWORD)
public class MenuModifyPwdActivity extends BaseActivity implements Validator.ValidationListener, BaseView<CommonModel> {

    @Required(order = 1, message = "原密码不能为空")
    private EditText pwdCurrent;
    @Password(order = 2, message = "新密码不能为空")
    @Regex(order = 3, pattern = Constant.REGULAR_PASSWORD, message = "密码格式不正确，密码长度6-20，必须包含数字、字母、特殊字符！")
    private EditText pwdNew;
    @ConfirmPassword(order = 4, message = "两次密码输入不匹配")
    private EditText pwdConfirm;
    private Button modifyPwdBtn;
    private TextView menuModCur;
    private LinearLayout mPwdCurrentParent;

    private String newPwd;
    private String curPwd;
    private Validator validator;
    /**
     * 是否忘记密码 true 是  false 否
     */
    private boolean isForgetPass;
    private String userName;
    private String telPhone;
    private ChangePwdPresenterImpl changePwdPresenter;
    @Override
    protected void initView() {
        changePwdPresenter = new ChangePwdPresenterImpl(this,this);
        isForgetPass = getIntent().getBooleanExtra("isForgetPassword",false);
        mPwdCurrentParent = (LinearLayout) findViewById(R.id.ll_pwd_current_parent);
        if (isForgetPass){
            //如果是忘记密码
            mPwdCurrentParent.setVisibility(View.GONE);
            userName = getIntent().getStringExtra("userName");
            telPhone = getIntent().getStringExtra("userTelephone");
        }else{
            mPwdCurrentParent.setVisibility(View.VISIBLE);
        }

        pwdCurrent = (EditText) findViewById(R.id.pwd_current);
        pwdNew = (EditText) findViewById(R.id.pwd_new);
        pwdConfirm = (EditText) findViewById(R.id.pwd_confirm);
        modifyPwdBtn = (Button) findViewById(R.id.modifyPwdBtn);
        //原密码
        menuModCur = (TextView) findViewById(R.id.menu_mod_cur);
        initHead(R.id.head_layout, "修改密码", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishWithAnimation();
            }
        });
    }

    @Override
    protected void initListener() {
        validator = new Validator(this);
        validator.setValidationListener(this);
        modifyPwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });
    }

    @Override
    public void onValidationSucceeded() {
        curPwd = pwdCurrent.getText().toString().trim();
        newPwd = pwdNew.getText().toString().trim();
        HashMap map = new HashMap();
        map.put("newPwd", new MD5().getMD5ofStr(newPwd));
        if (isForgetPass){
            map.put("phoneLoginFlag","1");
            map.put("userName",userName);
            map.put("oldPwd",telPhone);
        }else {
            map.put("phoneLoginFlag","0");
            map.put("userName", SysAccount.getUserInfo(this).getLoginAccount());
            map.put("oldPwd", new MD5().getMD5ofStr(curPwd));
        }
        changePwdPresenter.loadDatas(map);
    }

    @Override
    public void onValidationFailed(View view, Rule<?> rule) {
        String message = rule.getFailureMessage();

        if (view instanceof EditText) {
            view.requestFocus();
            ((EditText) view).setError(message);
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.p_menu_modify_pwd;
    }

    @Override
    public void onError(@Nullable String message) {
        Tt.showShort("修改密码失败，请重新修改");
    }

    @Override
    public void loadSuccess(@Nullable CommonModel result) {
        Tt.showShort(result.getMessage());
        if (result.getSuccess()){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            CompleteQuit.getInstance().exitAll(false);
        }
    }
}
