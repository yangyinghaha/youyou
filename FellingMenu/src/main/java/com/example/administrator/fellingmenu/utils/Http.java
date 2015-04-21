package com.example.administrator.fellingmenu.utils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
/**
 * Created by Administrator on 2015/4/14.
 */
public class Http {
    public void getString(String path,Callback callback){
        HttpUtils utils=new HttpUtils();
        HttpHandler handler=utils.send(HttpRequest.HttpMethod.GET, path, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> objectResponseInfo) {

            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });

    }
    interface Callback{
        public void ok(String result);
        public void erro(String erro);
    }
}
