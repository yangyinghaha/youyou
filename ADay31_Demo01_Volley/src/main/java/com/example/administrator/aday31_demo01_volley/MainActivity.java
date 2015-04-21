package com.example.administrator.aday31_demo01_volley;

import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String path="http://g.hiphotos.baidu.com/image/pic/item/7c1ed21b0ef41bd5631ed9c452da81cb39db3db4.jpg";
        RequestQueue queue=Volley.newRequestQueue(this);
        ImageRequest request=new ImageRequest(path,new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
              ImageView imageView= (ImageView) findViewById(R.id.imageView);
              imageView.setImageBitmap(response);
            }
        },0,0, Bitmap.Config.ARGB_8888,new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(request);

    }

}
