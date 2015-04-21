package com.example.administrator.yi18;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.BMyAdapter;
import com.example.bean.Y18Second;
import com.example.utils.NetJSONUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/4/2.
 */
public class BFragment extends Fragment {
    List<Y18Second> data;
    BMyAdapter adapter;

    public BFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_item_list,container,false);

        ListView lv= (ListView) v.findViewById(R.id.list_fragment);
        View emptyView=v.findViewById(android.R.id.empty);
        lv.setEmptyView(emptyView);
        adapter=new BMyAdapter(getActivity(),data,inflater);
        getListViewData();
        lv.setAdapter(adapter);


        return v;
    }
    public void getListViewData(){
        NetJSONUtils.loadJson(getActivity(),"http://api.yi18.net/top/list",new NetJSONUtils.OnJsonDown() {
            @Override
            public void ok(String json) {
                data=new ArrayList<Y18Second>();
                try {
                    JSONObject obj=new JSONObject(json);
                    JSONArray arr=obj.getJSONArray("yi18");
                    for (int i = 0; i <arr.length() ; i++) {
                        JSONObject obj2=arr.getJSONObject(i);
                        Y18Second yi=new Y18Second();
                        yi.setTitle(obj2.getString("title"));
                        yi.setTitle(obj2.getString("img"));
                        yi.setTitle(obj2.getString("keywords"));
                        yi.setTitle(obj2.getString("from"));
                        data.add(yi);
                        Log.i("123",data.toString());
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String error) {
                Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
