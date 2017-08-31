package com.study.xuan.xvolley;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.study.xuan.xvolleyutil.XVolley;
import com.study.xuan.xvolleyutil.callback.CallBack;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        /*XVolley.getInstance()
                .doPost()
                .url("http://192.168.117.102/post.php")
                .addParams("user", "xuan")
                .build()
                .execute(this, new CallBack<String>() {
                    @Override
                    public void onSuccess(String response) {
                        Log.e("Success", response);
                    }
                });*/
        weather weather = new weather();
        weather.setCity("北京");
        weather.setMessage("这是一条带json的post请求");


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
                });*/
        //post请求带json
       /* XVolley.getInstance()
                .doPostString()
                .url("http://192.168.117.102/stringpost.php")
                .content(new Gson().toJson(weather))
                .build()
                .execute(this,new CallBack<String>(){
                    @Override
                    public void onSuccess(String response) {
                        Log.e("Success", response);
                    }
                });

        OkHttpUtils
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
        Resources res = getResources();

        Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.lion);
        XVolley.getInstance()
                .doPostFile()
                .url("http://192.168.117.102/filepost.php")
                .addFile("pic",compressImage(bmp))
                .build()
                .execute(this, new CallBack<String>() {
                    @Override
                    public void onSuccess(String response) {
                        super.onSuccess(response);
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

    public static File compressImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 500) {  //循环判断如果压缩后图片是否大于500kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            long length = baos.toByteArray().length;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        String filename = format.format(date);
        File file = new File(Environment.getExternalStorageDirectory(),filename+".png");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            try {
                fos.write(baos.toByteArray());
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        recycleBitmap(bitmap);
        return file;
    }
    public static void recycleBitmap(Bitmap... bitmaps) {
        if (bitmaps==null) {
            return;
        }
        for (Bitmap bm : bitmaps) {
            if (null != bm && !bm.isRecycled()) {
                bm.recycle();
            }
        }
    }
}
