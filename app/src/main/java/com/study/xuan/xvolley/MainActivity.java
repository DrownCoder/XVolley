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
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {
    static final String PARAM = "[a-zA-Z][a-zA-Z0-9_-]*";
    static final Pattern PARAM_URL_REGEX = Pattern.compile("\\{(" + PARAM + ")\\}");
    static final Pattern PARAM_NAME_REGEX = Pattern.compile(PARAM);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String path = "\\{city}\\";
        Set param = parsePathParameters(path);
        Log.i("TAG", param.toString());
        //get请求
        TextView tv_get = (TextView) findViewById(R.id.tv_get);
        tv_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XVolley.getInstance()
                        .doGet()
                        .url("http://192.168.117.102/get.php")
                        .addParam("name", "xuan")
                        .build()
                        .execute(MainActivity.this, new CallBack<String>() {
                            @Override
                            public void onSuccess(Context context, String response) {
                                Log.e("Success", response);
                            }
                        });
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
                        if (url.contains("sojson")) {
                            request.cancel();
                            return request;
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

        XVolley.getInstance()
                .doGet()
                .url("http://192.168.117.102/get.php")
                .addParam("name", "xuan")
                .addParam("psd","123")
                .build()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Request intercept(Chain chain) {
                        Request request = chain.request();
                        Log.i("URL", request.getUrl());
                        return request;
                    }
                })
                .execute(this, new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        Log.e("Success", response);
                    }
                });

        //post请求带参数
        XVolley.getInstance()
                .doPost()
                .url("http://192.168.117.102/post.php")
                .addParam("user", "xuan")
                .addParam("psd", "123")
                .build()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Request intercept(Chain chain) {
                        Request request = chain.request();
                        try {
                            Log.i("TAG", request.getPostBody().toString());
                        } catch (AuthFailureError authFailureError) {
                            authFailureError.printStackTrace();
                        }
                        return request;
                    }
                })
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

    }
    static Set<String> parsePathParameters(String path) {
        //这里利用Set数据结构的特点，保证url中的路径参数(PathParameters)有且仅使用一次，不会出现重复的路径参数
        Matcher m = PARAM_URL_REGEX.matcher(path);
        Set<String> patterns = new LinkedHashSet<>();
        while (m.find()) {
            patterns.add(m.group(1));
        }
        return patterns;
    }
}
