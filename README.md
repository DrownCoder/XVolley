XVolley-基于Volley的封装的工具类
===================
前面几篇博客分析了Volley的源码，加上最近在看《Head First设计模式》这本书（不得不说老外的书是真的不错，推荐），于是基于鸿洋大神的OkhttpUtil的源码和OkHttp的源码，加上自己的理解，封装了这么一个工具类。
### 一、功能
1.GET  
2.POST  
3.post传json  
4.post传文件（不支持大文件类型）  
5.自定义拦截器  
6.gson  
7.配置URL  
8.全局URL,HEADER,拦截器  

### 二、优化
1.单例模式保证全局请求队列  
2.weakreference持有Activity，支持onFinish()回调，防止内存泄漏  
3.支持全局配置URL,HEADER  
4.链式调用  
5.增加过滤器  

### 三、未来构思
1.支持cookie  
2.支持大文件  
3.支持上传文件进度回调  
4.支持https  
5.构思ing...  

# How to：
**Step 1. Add the JitPack repository to your build file** 
Add it in your root build.gradle at the end of repositories:

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency

```
dependencies {
	        compile 'com.github.sdfdzx:XVolley:v1.0.0'
	}
```
### Usage
#### 1.在Application中初始化请求队列。

```
 XVolley.create(getApplicationContext());
```

#### 2.全局配置Header,URL,Intercepter.(可选)
通过调用init(Config config)方法，Config通过BaseConfigBuilder构建。

```
Map<String, String> header = new HashMap<>();
header.put("user-token", "AEUHY98QIASUDH");
BaseConfigBuilder config = new BaseConfigBuilder();
config.baseUrl("http://127.0.0.1/get.php")
      .addParam("client", "Android")
      .addParam("position", "北京")
      .addHeaders(header)
      .addIntercept(new Interceptor() {
          @Override
          public Request intercept(Chain chain) {
              Request request = chain.request();        LogUtil.log("intercept","baseIntercept:"+request.getUrl());
                        return request;
          }
       });
XVolley.create(getApplicationContext())
       .init(config.build());
```

#### 3.GET请求

```
XVolley.getInstance()
       .doGet()
       .url("http://127.0.0.1/get.php")
       .addParam("name", "xuan")
       .build()
       .execute(this, new CallBack<String>() {
           @Override
           public void onSuccess(Context context, String response) {
           }
       });
```

**这里的.url()方法会覆盖掉全局配置的URL**,如果使用全局的URL，直接调用addParam方法即可，根据自己需求使用。

#### 4.POST请求
 

```
		XVolley.getInstance()
                .doPost()
                .url("http://127.0.0.1/post.php")
                .addParam("user", "xuan")
                .build()
                .execute(this, new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        Log.e("Success", response);
                    }
                });
```

#### 5.POST请求传String字符串
 

```
		weather weather = new weather();
        weather.setCity("北京");
        weather.setMessage("这是一条带json的post请求");

        //post请求带json
        XVolley.getInstance()
                .doPostString()
                .url("http://127.0.0.1/stringpost.php")
                .content(new Gson().toJson(weather))
                .build()
                .execute(this,new CallBack<String>(){
                    @Override
                    public void onSuccess(Context context, String response) {
                        Log.e("Success", response);
                    }
                });
```

#### 6.上传文件（小文件，字节流实现方式）
支持多文件，多参数。

```
XVolley.getInstance()
       .doPostFile()
       .url("http://127.0.0.1/filex.php")
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
```

#### 7.Gson解析
只需要在链式调用中加上goGson(Class c)方法，传入Gson对应的class类型。所有类型请求都可以。

```
XVolley.getInstance()       
		.doGet()
		.url(		"http://www.sojson.com/open/api/weather/json.shtml")
       .addParam("city", "北京")
       .addHeaders(header)
       .goGson(weather.class)
       .build()
	.execute(MainActivity.this, new CallBack<weather>() {
        @Override
        public void onSuccess(Context context, weather response) {
        Log.e("Success", response.getCity());
	        }
        });
```

#### 8.过滤器。
实现Interceptor接口的intercept方法。
 

```
public interface Interceptor {
    Request intercept(Chain chain);

    interface Chain {
        Request request();
    }
	}
```

(1)支持全局过滤器
 

```
Map<String, String> header = new HashMap<>();
header.put("user-token", "AEUHY98QIASUDH");
BaseConfigBuilder config = new BaseConfigBuilder();
        config.baseUrl("http://192.168.117.102/get.php")
      .addParam("client", "Android")
      .addParam("position", "北京")
      .addHeaders(header)
      .addIntercept(new Interceptor() {
          @Override
          public Request intercept(Chain chain) {
	           Request request = chain.request();         LogUtil.log("intercept","baseIntercept:"+request.getUrl());
                        return request;
               }
       });
XVolley.create(getApplicationContext())
       .init(config.build());
```

(2)支持单独过滤器
 

```
Map<String, String> header = new HashMap<>();
header.put("APP-Secret", "ad12msa234das232in");
XVolley.getInstance()
       .doGet()
       .url(
"http://www.sojson.com/open/api/weather/json.shtml")
       .addParam("city", "北京")
       .addHeaders(header)
       .goGson(weather.class)
       .build()
       .addInterceptor(new Interceptor() {
           @Override
           public Request intercept(Chain chain) {                      LogUtil.log("intercept","custom");
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
        });
```

(3)支持全局+单独
递归调用，先调用单独的过滤器再调用全局过滤器

#### 9.CallBack
可以实现ICallBack接口实现所有方法

```
public interface ICallBack<T> {
    void onBefore();
    void onSuccess(Context context, T response);
    void onError(VolleyError error);
    void onAfter();
    void onFinish();
	}
```

或者实现CallBack类，实现对应需要的方法。

```
public abstract class CallBack<T> implements ICallBack<T> {
    @Override
    public void onBefore() {
    }
    @Override
    public void onSuccess(Context context, T response) {
    }
    @Override
    public void onError(VolleyError error) {
    }
    @Override
    public void onAfter() {
    }
    @Override
    public void onFinish() {
    }
	}
```
**onFinish方法对应请求过程中Activity销毁的回调，可用于回收资源，防止内存泄漏。**

#### 10.Listener
内部Listener使用WeakReference持有Activity对象，防止内存泄漏。

```
public abstract class WeakListener{
    final WeakReference<Activity> activityWeakReference;
    final ICallBack callBack;
    public WeakListener(Context activity, ICallBack callBack) {
        activityWeakReference = new WeakReference<Activity>((Activity) activity);
        this.callBack = callBack;
    }
	}
```

