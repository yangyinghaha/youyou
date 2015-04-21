package com.example.administrator.fellingmenu;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ViewPager pager;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = (ViewPager) findViewById(R.id.pager_main);
        fragments = new ArrayList<Fragment>();
        for (int i = 0; i <5 ; i++) {
            Fragment f=AFragment.newInstance("","");
            fragments.add(f);
        }
        pager.setAdapter(new MyPagerAdapter(this.getSupportFragmentManager()));
    }

    class MyPagerAdapter extends FragmentPagerAdapter{

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


}
