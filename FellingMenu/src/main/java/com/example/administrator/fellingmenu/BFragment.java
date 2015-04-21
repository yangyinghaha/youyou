package com.example.administrator.fellingmenu;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.administrator.fellingmenu.R;
import com.example.administrator.fellingmenu.adapter.TopViewAdapter;

import java.util.ArrayList;

public class BFragment extends Fragment {

    private ViewPager viewPager;
    private ArrayList<ImageView> images;
    public BFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View v=inflater.inflate(R.layout.fragment_b, container, false);
         viewPager = (ViewPager) v.findViewById(R.id.pager_a);
        images = new ArrayList<ImageView>();
        for (int i = 0; i <9 ; i++) {
            ImageView image=new ImageView(getActivity());
            images.add(image);
        }
        viewPager.setAdapter(new TopViewAdapter(images,getActivity()));
        return v;
    }


}
