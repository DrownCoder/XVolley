package com.study.xuan.xvolley;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.google.gson.Gson;
import com.study.xuan.xvolleyutil.base.XVolley;
import com.study.xuan.xvolleyutil.callback.CallBack;
import com.study.xuan.xvolleyutil.interceptor.Interceptor;
import com.study.xuan.xvolleyutil.utils.LogUtil;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get请求
        TextView tv_get = (TextView) findViewById(R.id.tv_get);
        tv_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Map<String, String> header = new HashMap<>();
        header.put("APP-Secret", "ad12msa234das232in");
        XVolley.getInstance()
                .doGet()
                .url("http://www.sojson.com/open/api/weather/json.shtml")
                .addParam("city", "北京")
                .addHeaders(header)
                .goGson(weather.class)
                .build()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Request intercept(Chain chain) {
                        LogUtil.log("intercept","custom");
                        Request request = chain.request();
                        String url = request.getUrl();
                        /*if (url.contains("sojson")) {
                            request.cancel();
                            return request;
                        }*/
                        try {
                            LogUtil.log("intercept",request.getHeaders().toString());
                        } catch (AuthFailureError authFailureError) {
                            authFailureError.printStackTrace();
                        }
                        return request;
                    }
                })
                .execute(MainActivity.this, new CallBack<weather>() {
                    @Override
                    public void onSuccess(Context context, weather response) {
                        Log.e("Success", response.getCity());
                    }
                    @Override
                    public void onFinish() {
                        LogUtil.log("finish", "activity has been finished");
                        super.onFinish();
                    }
                });

        /*XVolley.getInstance()
                .doGet()
                .addParam("name", "xuan")
                .build()
                .execute(this, new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        Log.e("Success", response);
                    }
                });*/

        //post请求带参数
        XVolley.getInstance()
                .doPost()
                .url("http://192.168.117.102/post.php")
                .addParam("user", "xuan")
                .build()
                .execute(this, new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        Log.e("Success", response);
                    }
                });
        weather weather = new weather();
        weather.setCity("北京");
        weather.setMessage("这是一条带json的post请求");

        //post请求带json
        XVolley.getInstance()
                .doPostString()
                .url("http://192.168.117.102/stringpost.php")
                .content(new Gson().toJson(weather))
                .build()
                .execute(this,new CallBack<String>(){
                    @Override
                    public void onSuccess(Context context, String response) {
                        Log.e("Success", response);
                    }
                });
        /*OkHttpUtils
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
                });*//**//*


        *//**//*OkHttpUtils
                .postString()
                .url("http://192.168.117.102/string" +
                        "post.php")
                .content(new Gson().toJson(weather))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("OKSuccess", response);
                    }
                });*/
        //上传文件
        TextView tvPostFile = (TextView) findViewById(R.id.tv_post_file);

        tvPostFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XVolley.getInstance()
                        .doPostFile()
                        .url("http://192.168.117.102/filex.php")
                        .addParam("name","xuan")
                        .addFile("txt", "bb.txt", Environment.getExternalStorageDirectory() + "/bb" +
                                ".txt")
                        .addFile("png", "aa.txt", Environment.getExternalStorageDirectory() + "/aa" +
                                ".png")
                        .build()
                        .execute(MainActivity.this, new CallBack<String>() {
                            @Override
                            public void onSuccess(Context context, String response) {
                                LogUtil.log("success",response);
                            }
                        });
            }
        });
        //onFinish回调
        TextView tv_Finish = (TextView) findViewById(R.id.tv_finish);
        tv_Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FinishActivity.class);
                startActivity(intent);
            }
        });


        /*OkHttpUtils.post()//
                .addFile("pic", "pic", compressImage(bmp))
                .url("http://192.168.117.102/filepost.php")
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("OKSuccess", response);
                    }
                });*/

    }
}
