package com.example.adapter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.yi18.R;
import com.example.bean.Y18Second;
import com.example.utils.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2015/4/2.
 */
public class BMyAdapter extends BaseAdapter {
    private Context context;
    private List<Y18Second> data;
    private LayoutInflater inflater;

    public BMyAdapter(Context context, List<Y18Second> data, LayoutInflater inflater) {
        this.context = context;
        this.data = data;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        Log.i("123",data.size()+"");
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            holder.iv= (ImageView) convertView.findViewById(R.id.image_b);
            holder.tv1= (TextView) convertView.findViewById(R.id.tv1_b);
            holder.tv1= (TextView) convertView.findViewById(R.id.tv2_b);
            holder.tv1= (TextView) convertView.findViewById(R.id.tv3_b);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        ImageLoader.loadImage(context,data.get(position).getImg(),holder.iv);
        holder.tv1.setText(data.get(position).getTitle());
        holder.tv2.setText(data.get(position).getKeywords());
        holder.tv3.setText(data.get(position).getFrom());

        return convertView;
    }
    class ViewHolder{
        ImageView iv;
        TextView tv1;
        TextView tv2;
        TextView tv3;
    }
}
