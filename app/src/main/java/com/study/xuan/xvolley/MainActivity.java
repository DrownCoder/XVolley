package com.study.xuan.xvolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.study.xuan.xvolleyutil.XVolley;
import com.study.xuan.xvolleyutil.callback.CallBack;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XVolley.getInstance()
                .goGson(String.class)
                .doGet()
                .url("http://www.sojson.com/open/api/weather/json.shtml")
                .addParams("city", "北京")
                .build()
                .execute(this, new CallBack<String>() {
                    @Override
                    public void onSuccess(String response) {
                        Log.e("Success", response);
                    }
                });
    }
}
