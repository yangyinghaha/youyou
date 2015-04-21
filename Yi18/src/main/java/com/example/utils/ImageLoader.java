package com.example.utils;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;
import java.io.File;
import java.lang.ref.SoftReference;
import java.util.HashMap;
/**
 * Created by Administrator on 2015/3/31.
 */
public class ImageLoader {
   static LruCache<String,Bitmap> lruCache;

    public static void loadImage(Context context,String path,ImageView imageView){

        //1  LruCache  运行时内存缓存
//        lruCache=new LruCache<String,Bitmap>(4*1024*1024);
//        Bitmap bitmap = lruCache.get(path);
//        if (bitmap != null) {
//            imageView.setImageBitmap(bitmap);
//            return;
//        }

        //网络下载图片
        NetJSONUtils.load(context, "http://www.yi18.net/"+ path, imageView);

    }
        //将图片加入缓存
        public static void putImageToCahce(String path, Bitmap bitmap) {
            lruCache.put(path, bitmap);
        }

    //获取文件名
    public static String getFileName(String path){
        String [] sqlit=path.split("/");
        //下标从0开始
        return sqlit[sqlit.length-1];
    }
}
