package com.example.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yi18.R;
import com.example.bean.Food;
import com.example.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/4/2.
 */
public class FoodAdapter extends PagerAdapter{
    private Context context;
    private List<Food> data;
    private List<View> views;

    public FoodAdapter(Context context, List<Food> data) {
        this.context = context;
        this.data = data;
        init();
    }

    public void init(){
        views=new ArrayList<View>();
        views.clear();
        if (data.size()<5){
            return;
        }

        for (int i = 0; i <5; i++) {
            View v=LayoutInflater.from(context).inflate(R.layout.topview_item,null);
            ImageView image= (ImageView) v.findViewById(R.id.topView_image);
            TextView text= (TextView) v.findViewById(R.id.topView_text);
            text.setText(data.get(i).getName());
            ImageLoader.loadImage(context,data.get(i).getImage(),image);
            views.add(v);
        }

    }

    @Override
    public void notifyDataSetChanged() {
        init();
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v=views.get(position);
        container.addView(v);
        return v;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }
}
