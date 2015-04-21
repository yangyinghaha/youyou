package com.example.administrator.aday32_demo03_xutilsdb;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import java.util.List;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbUtils dbUtils=DbUtils.create(this);
        Student s=new Student(1,"咔咔",20,2,99,8);
        try {
            dbUtils.save(s);
        } catch (DbException e) {
            e.printStackTrace();
        }

        try {

           List<Student> all= dbUtils.findAll(Student.class);
            for (int i = 0; i <all.size() ; i++) {
                Log.i("all",all.get(i).toString());
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


}
