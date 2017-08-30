package com.study.xuan.xvolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.study.xuan.xvolleyutil.XVolley;
import com.study.xuan.xvolleyutil.callback.CallBack;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get请求
        /*XVolley.getInstance()
                .goGson(weather.class)
                .doGet()
                .url("http://www.sojson.com/open/api/weather/json.shtml")
                .addParams("city", "北京")
                .build()
                .execute(this, new CallBack<weather>() {
                    @Override
                    public void onSuccess(weather response) {
                        Log.e("Success", response.getCity());
                    }
                });*/
        //post请求带参数
        XVolley.getInstance()
                .doPost()
                .url("http://192.168.117.102/post.php")
                .addParams("user", "xuan")
                .build()
                .execute(this, new CallBack<String>() {
                    @Override
                    public void onSuccess(String response) {
                        Log.e("Success", response);
                    }
                });
        weather weather = new weather();
        weather.setCity("北京");
        weather.setMessage("这是一条带json的post请求");


        OkHttpUtils
                .post()
                .url("http://192.168.117.102/post.php")
                .addParams("user", "hyman")
                .addParams("password", "123")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("OKSuccess", response);
                    }
                });
       /* //post请求带json
        XVolley.getInstance()
                .doPostString()
                .url("http://192.168.117.102/poststring.php")
                .content(new Gson().toJson(weather))
                .build()
                .execute(this,new CallBack<String>(){
                    @Override
                    public void onSuccess(String response) {
                        Log.e("Success", response);
                    }
                });*/


    }
}
