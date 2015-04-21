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
import com.example.bean.Yi18First;
import com.example.utils.ImageLoader;

import java.util.List;
/**
 * Created by Administrator on 2015/3/31.
 */
public class AMyAdapter extends BaseAdapter {
    List<Yi18First> list;
    Context context;
    LayoutInflater inflate;

    public AMyAdapter( Context context,List<Yi18First> list, LayoutInflater inflate) {
        this.list = list;
        this.context = context;
        this.inflate = inflate.from(context);
    }

    @Override
    public int getCount() {
        if (list!=null){
           // Log.i("123",list.size()+"");
            return list.size();
        }
        return 0;
    }

    @Override
    public Yi18First getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=inflate
                    .inflate(R.layout.fragment_list_item_1, null, false);
            holder.iv= (ImageView) convertView.findViewById(R.id.image_pager);
            holder.tv1= (TextView) convertView.findViewById(R.id.tv1);
            holder.tv2= (TextView) convertView.findViewById(R.id.tv2);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.iv.setImageResource(R.drawable.ic_launcher);

        ImageLoader.loadImage(context, list.get(position).getImg(), holder.iv);
        holder.tv1.setText(list.get(position).getTitle());
        holder.tv2.setText(list.get(position).getTime());

        if (list.get(position).getImg()!=null){
            holder.iv.setVisibility(View.VISIBLE);
            holder.iv.setTag("http://www.yi18.net/"+list.get(position).getImg());
            ImageLoader.loadImage(context,list.get(position).getImg(), holder.iv);
        }else {
            holder.iv.setVisibility(View.GONE);

        }
        return convertView;
    }
    class ViewHolder{
        ImageView iv;
        TextView tv1;
        TextView tv2;

    }
}
