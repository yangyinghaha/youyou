package com.example.administrator.yi18;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.utils.NetJSONUtils;


public class DetailsActivity extends ActionBarActivity {
    private String path="http://api.yi18.net/news/show?";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView tv= (TextView) findViewById(R.id.details_text);
    }
    public void getDetailsData(int id){
        NetJSONUtils.loadJson(this,path+"id="+id,new NetJSONUtils.OnJsonDown() {
            @Override
            public void ok(String json) {

            }

            @Override
            public void error(String error) {

            }
        });
    }


}
