package com.example.administrator.aday31_demo02_netimageloader;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import java.lang.ref.SoftReference;
import java.util.HashMap;


public class MainActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final HashMap<String,SoftReference<Bitmap>> map=new HashMap<String,SoftReference<Bitmap>>();
        //创建LruCache，实现两个方法
        int max= (int) Runtime.getRuntime().maxMemory();
        final LruCache<String,Bitmap> cache=new LruCache<String,Bitmap>(max/8){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //图片的大小
                return value.getRowBytes()*value.getHeight();
            }

            @Override
            protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
                super.entryRemoved(evicted, key, oldValue, newValue);
                if (evicted){
                    //被挤出的添加到map中
                    SoftReference<Bitmap>  soft=new SoftReference<Bitmap>(oldValue);
                    map.put(key,soft);
                }
            }
        };



        NetworkImageView netImage= (NetworkImageView) findViewById(R.id.netImage);
        RequestQueue queue= Volley.newRequestQueue(this);
        ImageLoader loader=new ImageLoader(queue,new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                Bitmap bitmap=cache.get(url);
                if (bitmap!=null){
                    return bitmap;
                }
                SoftReference<Bitmap> sf=map.get(url);
                if (sf!=null){
                    return sf.get();
                }
                return bitmap;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url,bitmap);
            }
        });




        String path="http://g.hiphotos.baidu.com/image/h%3D200/sign=2e8e9d25bf315c605c956cefbdb1cbe6/7c1ed21b0ef41bd5631ed9c452da81cb39db3db4.jpg";
        netImage.setDefaultImageResId(R.drawable.ic_launcher);
        netImage.setErrorImageResId(R.drawable.ic_launcher);
        netImage.setImageUrl(path,loader);
    }

}
