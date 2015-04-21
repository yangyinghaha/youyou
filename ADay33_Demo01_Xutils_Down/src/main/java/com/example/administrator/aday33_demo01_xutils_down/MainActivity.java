package com.example.administrator.aday33_demo01_xutils_down;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import java.io.File;

public class MainActivity extends ActionBarActivity {
private String path="http://10.0.151.215:8080/Day29_Demo01_UpFileServer/timewhere.mp3";
    private HttpHandler<File> handler;
    private ProgressBar progressBar;
    private Button button;
    private HttpUtils utils;
    private ImageView imageView;
    private String upPath="http://10.0.151.215:8080/Day29_Demo01_UpFileServer/UpFileServlet";
    private String imagePath="http://images.china.cn/attachement/jpg/site1000/20150410/7427ea210c5416914d0c07.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        button = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.imageView);
        button.setText("暂停");
        utils = new HttpUtils();
        BitmapUtils bitmapUtils=new BitmapUtils(this,getExternalCacheDir().getPath(),0.35f,20*1024*1024);
        bitmapUtils.configDefaultLoadingImage(R.drawable.ic_launcher);
        bitmapUtils.configMemoryCacheEnabled(true);
        bitmapUtils.configThreadPoolSize(3);
        bitmapUtils.display(imageView,imagePath);
        downLoadUtils(utils);
        //upLoadUtils(utils);
    }


    public void upLoadUtils(HttpUtils utils){
        RequestParams params=new RequestParams();
        params.addBodyParameter("files",getFile());
        utils.send(HttpRequest.HttpMethod.POST,upPath,params,new RequestCallBack<Object>() {
            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                super.onLoading(total, current, isUploading);
                progressBar.setMax((int) total);
                progressBar.setProgress((int) current);
            }

            @Override
            public void onSuccess(ResponseInfo<Object> objectResponseInfo) {

            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });

    }


        public void downLoadUtils(HttpUtils utils){
        handler = utils.download(path,
                getFile().getPath(), true, true, new RequestCallBack<File>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                        progressBar.setMax((int) total);
                        progressBar.setProgress((int) current);
                    }

                    @Override
                    public void onSuccess(ResponseInfo<File> fileResponseInfo) {
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Log.i("erro", s);
                    }
                });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (handler.isPaused()) {
                    handler.resume();
                    button.setText("暂停");
                } else {
                    handler.pause();
                    button.setText("恢复");
                }
            }
        });

    }
//    public void downLoadUtils(HttpUtils utils){
//        utils.download(path,getFile().getPath(),true,true,new RequestCallBack<File>() {
//            @Override
//            public void onLoading(long total, long current, boolean isUploading) {
//                super.onLoading(total, current, isUploading);
//                progressBar.setMax((int) total);
//                progressBar.setProgress((int) current);
//            }
//
//            @Override
//            public void onSuccess(ResponseInfo<File> fileResponseInfo) {
//
//            }
//
//            @Override
//            public void onFailure(HttpException e, String s) {
//
//            }
//        });
//    }
    public File getFile(){
        File sd= Environment.getExternalStorageDirectory();
        File floder=new File(sd,getResources().getString(R.string.app_name));
        floder.mkdir();
        File f=new File(floder,"aa");
        return f;
    }
}
