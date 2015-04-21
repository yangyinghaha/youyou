package com.example.administrator.yi18;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.adapter.AMyAdapter;
import com.example.adapter.FoodAdapter;
import com.example.bean.Food;
import com.example.bean.Yi18First;
import com.example.utils.NetJSONUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/31.
 */
public class AFragment extends Fragment implements ViewPager.OnPageChangeListener, AdapterView.OnItemClickListener {
    ArrayList<Yi18First> data;
    AMyAdapter adapter;
    List<Food> foods;
    private FoodAdapter fAdapter;
    private RadioGroup topGroup;

    public AFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_item_list,container,false);
        View empty= v.findViewById(android.R.id.empty);
        ListView lv= (ListView) v.findViewById(R.id.list_fragment);
        lv.setEmptyView(empty);
        data = new ArrayList<Yi18First>();
        adapter = new AMyAdapter( getActivity(),data,inflater);
        lv.setAdapter(adapter);
        getListViewData();
        //topView
        View topView=inflater.inflate(R.layout.topview,null);
        topGroup = (RadioGroup) topView.findViewById(R.id.radio_top);
        ViewPager pager= (ViewPager) topView.findViewById(R.id.pager_top);
        fAdapter = new FoodAdapter(getActivity(),foods);
        getTopViewData();
        pager.setAdapter(fAdapter);
        //lv.addHeaderView(topView);
        pager.setOnPageChangeListener(this);

        lv.setOnItemClickListener(this);
        return v;
    }


        public void getTopViewData(){
        //topview json数据解析
        NetJSONUtils.loadJson(getActivity(),"http://api.yi18.net/food/list?page=1&limit=20",new NetJSONUtils.OnJsonDown() {
            @Override
            public void ok(String json) {

                try {
                    Log.i("12315",json);
                    JSONObject obj =new JSONObject(json);
                    JSONArray array=obj.getJSONArray("yi18");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj2=array.getJSONObject(i);
                        Food f=new Food();
                        f.setName(obj2.getString("name"));
                        f.setImage(obj2.getString("image"));
                        foods.add(f);
                    }
                    fAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String error) {

            }
        });
    }

       public void getListViewData(){
           //第一页json解析数据
           NetJSONUtils.loadJson(getActivity(),"http://api.yi18.net/news/list?page=1&limit=20",new NetJSONUtils.OnJsonDown() {
               @Override
               public void ok(String json) {
                   try {
                       JSONObject obj = new JSONObject(json);
                       JSONArray array = obj.getJSONArray("yi18");

                       for (int i = 0; i < array.length(); i++) {
                           JSONObject jobj = array.getJSONObject(i);
                           Yi18First yi = new Yi18First();
                           yi.setTitle(jobj.getString("title"));
                           yi.setTag(jobj.getString("tag"));
                           yi.setCount(jobj.getInt("count"));
                           yi.setFcount(jobj.getInt("fcount"));
                           yi.setRcount(jobj.getInt("rcount"));
                           yi.setFocal(jobj.getInt("focal"));
                           yi.setId(jobj.getInt("id"));
                           yi.setAuthor(jobj.getString("author"));
                           yi.setMd(jobj.getString("md"));
                           yi.setTime(jobj.getString("time"));
                           if (jobj.has("img")) {
                               yi.setImg(jobj.getString("img"));
                           }
                           data.add(yi);
                           // Log.i("123",data+"");
                       }

                       adapter.notifyDataSetChanged();

                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
               }
               @Override
               public void error(String error) {
                   Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
               }
           });
       }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        RadioButton btn= (RadioButton) topGroup.getChildAt(position);
        btn.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent();
       // intent.setClass()
    }
}

