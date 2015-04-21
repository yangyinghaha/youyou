package com.qianfeng.alipay;


import java.util.ArrayList;
import java.util.List;

import com.alipay.sdk.pay.demo.PayDemoActivity;
import com.alipay.sdk.pay.demo.R;
import com.qianfeng.alipay.bean.Food;
import com.qianfeng.alipay.bean.Inits;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{
	
	private TextView tv_money2; // 消费金额
	private TextView tv_listNumb1; // 单号
	private EditText ed_tableNmb; // 桌号
	private ListView lv;
	private List<Food> list;
	private MyBaseAdapter adapter;
	private double totalMoney;//总价格
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv_money2 = (TextView) findViewById(R.id.tv_money2);
		tv_listNumb1 = (TextView) findViewById(R.id.tv_listNumb1);
		ed_tableNmb = (EditText) findViewById(R.id.ed_tableNmb);
		lv = (ListView) findViewById(R.id.lv);
		
		list=new ArrayList<Food>();
		list=Inits.init();
		adapter=new MyBaseAdapter();
		lv.setAdapter(adapter);
	}
	
	
	
	
	
	public class MyBaseAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder=null;
			if(convertView==null){
				convertView=LayoutInflater.from(MainActivity.this).inflate(R.layout.item, null);
				viewHolder=new ViewHolder();
				viewHolder.tv_foodName=(TextView) convertView.findViewById(R.id.foodName);
				viewHolder.tv_foodPrice=(TextView) convertView.findViewById(R.id.foodprice);
				viewHolder.tv_foodNumber=(TextView) convertView.findViewById(R.id.food_number);
				viewHolder.btn_add=(Button) convertView.findViewById(R.id.add);
				viewHolder.btn_sub=(Button) convertView.findViewById(R.id.sub);
				convertView.setTag(viewHolder);
			}else{
				viewHolder=(ViewHolder) convertView.getTag();
			}
			
			viewHolder.btn_add.setTag(position);
			viewHolder.btn_sub.setTag(position);
			
			viewHolder.btn_add.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Button button=(Button) v;
					int index=Integer.parseInt(button.getTag().toString());
					int number=list.get(index).getNumber();
					//动态获得显示food数量的textview然后显示最新的数量
					RelativeLayout relativeLayout=(RelativeLayout) button.getParent();
					TextView tv=(TextView) relativeLayout.findViewById(R.id.food_number);
					int newNumber=Integer.parseInt(tv.getText().toString());
					tv.setText((number+newNumber+1)+"");
					//计算总价格
					double money=list.get(index).getFoodPrice();
					totalMoney+=money;
					tv_money2.setText(totalMoney+"");
				}
			});
			
			viewHolder.btn_sub.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Button button=(Button) v;
					int index=Integer.parseInt(button.getTag().toString());
					//动态获得显示food数量的textview然后显示最新的数量
					RelativeLayout relativeLayout=(RelativeLayout) button.getParent();
					TextView tv=(TextView) relativeLayout.findViewById(R.id.food_number);
					int newNumber=Integer.parseInt(tv.getText().toString());
					if(newNumber==0){
						newNumber=0;
					}else{
						newNumber-=1;
					}
					tv.setText(newNumber+"");
					//计算总价格
					double money=list.get(index).getFoodPrice();
					if(money>0){
						totalMoney-=money;
					}
					tv_money2.setText(totalMoney+"");
				}
			});
			
			Food food=new Food();
			food=list.get(position);
			viewHolder.tv_foodName.setText(food.getFoodName());
			viewHolder.tv_foodPrice.setText(food.getFoodPrice()+"");
			viewHolder.tv_foodNumber.setText(food.getNumber()+"");
			return convertView;
		}
	}
	static class ViewHolder{
		TextView tv_foodName;
		TextView tv_foodPrice;
		TextView tv_foodNumber;
		Button btn_add;
		Button btn_sub;
	}
	
	public void onclick(View v) {
		switch (v.getId()) {
		case R.id.btn_resert1:
			tv_listNumb1.setText("1");
			break;
		case R.id.btn_resert:
			ed_tableNmb.setText("");//桌号清空
			for(int i=0;i<list.size();i++){//循环将所有的number清空
				list.get(i).setNumber(0);
			}
			tv_money2.setText(0+"");
			totalMoney=0;
			adapter.notifyDataSetChanged();
			break;
		case R.id.btn_sure:
			String table=ed_tableNmb.getText().toString();
			double money=Double.parseDouble(tv_money2.getText().toString());
			if(table==""){
				Toast.makeText(MainActivity.this,"桌号不能为空", Toast.LENGTH_SHORT).show();
			}else if(money==0.0){
				Toast.makeText(MainActivity.this,"请点餐", Toast.LENGTH_SHORT).show();
			}else{
				Intent intent=new Intent(MainActivity.this,PayDemoActivity.class);
				//商品的描述  商品的名称   支付的金额
				intent.putExtra("totalMoney", totalMoney);
				startActivity(intent);
			}
			break;

		}
	}
}

















