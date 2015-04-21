package org.mobiletrain.www.aday35_demo01_share;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.weibo.TencentWeibo;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ShareSDK.initSDK(this);
        Platform sina = ShareSDK.getPlatform(this, TencentWeibo.NAME);
        sina.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> stringObjectHashMap) {
                if(i==Platform.ACTION_AUTHORIZING){
                    Platform.ShareParams p=new Platform.ShareParams();
                    p.text="AAAAAA";
                  //  p.setImageUrl("http://img4.imgtn.bdimg.com/it/u=2849177189,1879813179&fm=21&gp=0.jpg");
                    platform.share(p);
                }
                if(i==Platform.ACTION_SHARE){
                    Toast.makeText(MainActivity.this,"OK",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                if(i==Platform.ACTION_SHARE){
//                    Toast.makeText(MainActivity.this,"ERROR",Toast.LENGTH_SHORT).show();
                  //  Log.i()
                }
            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });
        sina.authorize();


    }


}
