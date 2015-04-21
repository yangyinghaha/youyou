package com.example.administrator.yi18;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements  ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    private HorizontalScrollView scrollView;
    private RadioGroup group;
    private View view;
    private ViewPager navigationPager;
    private LinearLayout.LayoutParams lp;
    private ArrayList<Fragment> fragments;
    private DisplayMetrics metrics;
    private DrawerLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollView = (HorizontalScrollView) findViewById(R.id.navigation_tab);
        group = (RadioGroup) findViewById(R.id.navigation_radiogroup);
        view = findViewById(R.id.navigation_selected);
        navigationPager = (ViewPager) findViewById(R.id.navigation_fragment);

        fragments = new ArrayList<Fragment>();
        for (int i = 0; i <group.getChildCount() ; i++) {
            Fragment f=new AFragment();
            fragments.add(f);
        }
//        Fragment f1=new AFragment();
//        fragments.add(f1);
//        Fragment f2=new BFragment();
//        fragments.add(f2);

        lp = (LinearLayout.LayoutParams) view.getLayoutParams();

        MyPagerAdapter adapter=new MyPagerAdapter(getSupportFragmentManager());
        navigationPager.setAdapter(adapter);
        navigationPager.setOnPageChangeListener(this);
        //获得屏幕宽度
        metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        //设置默认选中
        ((RadioButton)group.getChildAt(0)).setChecked(true);
        group.setOnCheckedChangeListener(this);

        layout = (DrawerLayout)findViewById(R.id.navigation_drawer);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //按钮
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, layout,R.string.app_name,R.string.app_name);
        layout.setDrawerListener(toggle);
        //同步
        toggle.syncState();
    }
    //控制抽屉
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            if (layout.isDrawerOpen(Gravity.START)){
                layout.closeDrawer(Gravity.START);
            }else{
                layout.openDrawer(Gravity.START);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    //positionOffset 滑动距离的百分比
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        lp.setMargins((int) (lp.width*(position+positionOffset)),0,0,0);
        view.setLayoutParams(lp);
    }

    @Override
    public void onPageSelected(int position) {
        RadioButton radio= (RadioButton) group.getChildAt(position);
        radio.setChecked(true);
        //让RadioButton滚到屏幕中心
        int x=radio.getLeft()-metrics.widthPixels/2+radio.getWidth()/2;
        scrollView.smoothScrollTo(x,0);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group,
                                 int checkedId) {
        switch (checkedId){
            case R.id.navigation_tab0:
                 navigationPager.setCurrentItem(0);
                break;
            case R.id.navigation_tab1:
                navigationPager.setCurrentItem(1);
                break;
            case R.id.navigation_tab2:
                navigationPager.setCurrentItem(2);
                break;
            case R.id.navigation_tab3:
                navigationPager.setCurrentItem(3);
                break;
            case R.id.navigation_tab4:
                navigationPager.setCurrentItem(4);
                break;
        }
    }


    public class MyPagerAdapter extends FragmentPagerAdapter {
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
