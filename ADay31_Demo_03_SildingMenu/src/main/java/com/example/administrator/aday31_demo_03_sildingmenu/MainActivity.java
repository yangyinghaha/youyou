package com.example.administrator.aday31_demo_03_sildingmenu;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SlidingMenu slidingMenu=new SlidingMenu(this);
        slidingMenu.setAboveOffset(500);
        slidingMenu.setBehindOffset(500);
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        slidingMenu.setMenu(R.layout.menu);
        slidingMenu.setSecondaryMenu(R.layout.secondmenu);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        //
        slidingMenu.attachToActivity(this,SlidingMenu.SLIDING_WINDOW);
    }

}
