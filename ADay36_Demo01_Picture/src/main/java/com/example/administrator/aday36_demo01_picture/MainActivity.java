package com.example.administrator.aday36_demo01_picture;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;


public class MainActivity extends ActionBarActivity {
    private File f;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                f = new File(getExternalCacheDir(),"aa.png");
                i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                startActivityForResult(i,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==0){
            imageView = (ImageView) findViewById(R.id.imageView);
            BitmapFactory.Options options=new BitmapFactory.Options();
            //加载宽高
            options.inJustDecodeBounds=true;
            BitmapFactory.decodeFile(f.getPath(),options);


            int h=options.outHeight;
            int w=options.outWidth;
            int width=200;
            //缩放比
            int s=w/width;
            options.inSampleSize=s;
            Bitmap bitmap= BitmapFactory.decodeFile(f.getPath(), options);
            imageView.setImageBitmap(bitmap);


            Intent intent=new Intent();
            intent.setAction("com.android.camera.action.CROP");
            intent.setDataAndType(Uri.fromFile(f),"image/*");
            intent.putExtra("crop",true);
            intent.putExtra("aspectX",1);
            intent.putExtra("aspectY",1);
            intent.putExtra("outputX",200);
            intent.putExtra("outputY",200);
            intent.putExtra("return-data",true);
            startActivityForResult(intent,10);

        }
        if (requestCode==10){
            Bitmap bitmap=data.getParcelableExtra("data");
            imageView.setImageBitmap(bitmap);
        }

    }
}
