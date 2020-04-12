package com.maning.mndialoglibrary.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.maning.mndialoglibrary.R;

public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
    }

    @Override
    public void onBackPressed() {
//        MProgressDialog.showProgress(this, "");
//        new MStatusDialog(this).show("保存成功", getResources().getDrawable(R.drawable.mn_icon_dialog_ok));
        super.onBackPressed();
    }
}
