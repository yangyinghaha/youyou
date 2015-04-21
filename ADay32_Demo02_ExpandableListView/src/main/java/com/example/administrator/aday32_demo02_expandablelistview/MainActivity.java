package com.example.administrator.aday32_demo02_expandablelistview;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.aday32_demo02_expandablelistview.bean.Friends;
import com.example.administrator.aday32_demo02_expandablelistview.bean.Groups;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private ArrayList<Groups> groups;
    private ArrayList<ArrayList<Friends>> friends;
    private ArrayList<Friends> grof;
    private ExpandableListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ExpandableListView) findViewById(R.id.expand_listView);
        groups = new ArrayList<Groups>();

        for (int i = 0; i <10 ; i++) {
            groups.add(new Groups("分组"+(i+1),i));
        }

         friends=new ArrayList<ArrayList<Friends>>();
        for (int i = 0; i <groups.size() ; i++) {
            grof = new ArrayList<Friends>();
            for (int j = 0; j <10 ; j++) {
                grof.add(new Friends(i,"美女"+j+"号","123"+j));
            }
            friends.add(grof);
        }

        listView.setAdapter(new MyAdapter());
    }
  class MyAdapter extends BaseExpandableListAdapter{

      @Override
      public int getGroupCount() {
          return groups.size();
      }

      @Override
      public int getChildrenCount(int groupPosition) {
          return friends.get(groupPosition).size();
      }

      @Override
      public Object getGroup(int groupPosition) {
          return groups.get(groupPosition);
      }

      @Override
      public Object getChild(int groupPosition, int childPosition) {
          return friends.get(groupPosition).get(childPosition);
      }

      @Override
      public long getGroupId(int groupPosition) {
          return groupPosition;
      }

      @Override
      public long getChildId(int groupPosition, int childPosition) {
          return childPosition;
      }

      @Override
      public boolean hasStableIds() {
          return false;
      }

      @Override
      public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
          if (convertView==null){
             convertView=getLayoutInflater().inflate(R.layout.group_item,null);
              TextView tv= (TextView) convertView.findViewById(R.id.text_group);
              tv.setText(groups.get(groupPosition).getName());
              ImageView imageView= (ImageView) convertView.findViewById(R.id.image);
              if (listView.isGroupExpanded(groupPosition)){
                  imageView.setImageResource(R.drawable.ic_launcher);
              }else {
                  imageView.setImageResource(R.drawable.abc_btn_radio_material);
              }

          }
          return convertView;
      }

      @Override
      public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
          if (convertView==null){
              convertView=getLayoutInflater().inflate(android.R.layout.simple_list_item_2,null);
              TextView tv1= (TextView) convertView.findViewById(android.R.id.text1);
              TextView tv2= (TextView) convertView.findViewById(android.R.id.text2);

              tv1.setText(friends.get(groupPosition).get(childPosition).getName());
              tv2.setText(friends.get(groupPosition).get(childPosition).getNumb());
          }

          return convertView;
      }

      @Override
      public boolean isChildSelectable(int groupPosition, int childPosition) {
          return true;
      }
  }

}
