package com.example.utils;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2015/3/30.
 */
public class NetJSONUtils {

    private static Handler handelr;

    public static void loadJson(Context context, final String path, final OnJsonDown callback) {

        if (handelr == null) {
            handelr = new Handler();
        }

        if (!isNetOK(context)){
            callback.error("手机没网哦，亲~");
        }

        new Thread() {
            @Override
            public void run() {
                super.run();
                ByteArrayOutputStream out = null;
                InputStream in = null;
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn =
                            (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(5000);

                    conn.connect();
                    in = conn.getInputStream();

                    out = new ByteArrayOutputStream();
                    byte[] b = new byte[4 * 1024];
                    int len ;

                    while ((len = in.read(b)) != -1) {
                        out.write(b, 0, len);
                        out.flush();
                    }
                    final String json = new String(out.toByteArray());
                  //  Log.i("123",json);

                    handelr.post(new Runnable() {
                        @Override
                        public void run() {
                            if (json==null){
                                return;
                            }
                            callback.ok(json);
                            Log.i("123",json);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    handelr.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.error("网络异常");
                        }
                    });
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (out != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }
    private static ExecutorService threadpool;

    public static boolean isNetOK(Context context) {

        ConnectivityManager mgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = mgr.getActiveNetworkInfo();
        if (info != null && info.isAvailable())
            return true;

        return false;
    }


    public static void load(final Context context, final String path, final ImageView image) {

        if (threadpool == null)
            threadpool = Executors.newFixedThreadPool(3);

       // image.setTag(path);

        threadpool.execute(new Runnable() {
            //工作线程
            @Override
            public void run() {
                InputStream in = null;
                FileOutputStream out = null;
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn =
                            (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(5000);
                    in = conn.getInputStream();

                    final Bitmap bitmap = BitmapFactory.decodeStream(in);

                    //放入LruCache缓存
                   //ImageLoader.putImageToCahce(path, bitmap);
                    handelr.post(new Runnable() {
                        @Override
                        public void run() {
                            String tag = (String) image.getTag();
                            if (tag.equals(path)) {
                                image.setImageBitmap(bitmap);

                            }
                        }
                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


            }
        });


    }

    public interface OnJsonDown {
        public void ok(String json);

        public void error(String error);
    }



}
