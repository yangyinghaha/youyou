package com.example.utils;

import android.content.Context;
import android.widget.ImageView;

import com.example.administrator.yi18.R;
import com.lidroid.xutils.BitmapUtils;

/**
 * Created by Administrator on 2015/4/10.
 */
public class LoadImage {


    public static void loadImage(Context context,String path,ImageView imageView){
        BitmapUtils utils = new BitmapUtils(context,context.getExternalCacheDir().getPath(),0.35f,20*1024*1024);
        utils.configThreadPoolSize(3);
        utils.configDefaultLoadingImage(R.drawable.ic_launcher);
        utils.configDefaultLoadFailedImage(R.drawable.ic_launcher);
        utils.configMemoryCacheEnabled(true);
        utils.display(imageView, path);
    }
}
