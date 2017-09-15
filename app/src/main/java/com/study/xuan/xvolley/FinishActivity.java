package com.study.xuan.xvolley;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.study.xuan.xvolleyutil.base.XVolley;
import com.study.xuan.xvolleyutil.callback.CallBack;
import com.study.xuan.xvolleyutil.utils.LogUtil;


public class FinishActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XVolley.getInstance()
                .doGet()
                .build()
                .execute(FinishActivity.this, new CallBack() {
                    @Override
                    public void onSuccess(Context context, Object response) {
                        super.onSuccess(context, response);
                    }

                    @Override
                    public void onFinish() {
                        LogUtil.log("finish", "activity has been finished");
                        super.onFinish();
                    }
                });
    }
}
