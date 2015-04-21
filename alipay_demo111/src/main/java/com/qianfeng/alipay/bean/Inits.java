package com.qianfeng.alipay.bean;

import java.util.ArrayList;
import java.util.List;

public class Inits {
	
	public static List<Food> init() {
		 List<Food> list = new ArrayList<Food>();
		Food food1 = new Food();
		food1.setFoodName("羊排");
		food1.setFoodPrice(50.0);
		food1.setNumber(0);
		list.add(food1);
		Food food2 = new Food();
		food2.setFoodPrice(12.0);
		food2.setNumber(0);
		food2.setFoodName("醋溜土豆");
		list.add(food2);
		Food food3 = new Food();
		food3.setFoodName("红烧茄子");
		food3.setFoodPrice(10.0);
		food3.setNumber(0);
		list.add(food3);
		Food food4 = new Food();
		food4.setFoodName("红烧带鱼");
		food4.setFoodPrice(20.0);
		food4.setNumber(0);
		list.add(food4);
		Food food5 = new Food();
		food5.setFoodName("炭烧里脊");
		food5.setFoodPrice(20.0);
		food5.setNumber(0);
		list.add(food5);
		Food food6 = new Food();
		food6.setFoodName("木须肉");
		food6.setFoodPrice(32.0);
		food6.setNumber(0);
		list.add(food6);
		Food food7 = new Food();
		food7.setFoodName("西红柿炒蛋");
		food7.setFoodPrice(12.5);
		food7.setNumber(0);
		list.add(food7);
		Food food8 = new Food();
		food8.setFoodName("虎皮尖椒");
		food8.setFoodPrice(7.0);
		food8.setNumber(0);
		list.add(food8);
		Food food9 = new Food();
		food9.setFoodName("东坡肉");
		food9.setFoodPrice(10.0);
		food9.setNumber(0);
		list.add(food9);
		Food food10 = new Food();
		food10.setFoodName("红烧肘子");
		food10.setFoodPrice(2.5);
		food10.setNumber(0);
		list.add(food10);
		Food food11 = new Food();
		food11.setFoodName("清蒸鲫鱼");
		food11.setFoodPrice(2.5);
		food11.setNumber(0);
		list.add(food11);
		Food food12 = new Food();
		food12.setFoodName("阳春白雪");
		food12.setFoodPrice(2.5);
		food12.setNumber(0);
		list.add(food12);
		Food food13 = new Food();
		food13.setFoodName("宫保鸡丁");
		food13.setFoodPrice(2.5);
		food13.setNumber(0);
		list.add(food13);
		Food food14 = new Food();
		food14.setFoodName("宫保肉丁");
		food14.setFoodPrice(2.5);
		food14.setNumber(0);
		list.add(food14);
		Food food15 = new Food();
		food15.setFoodName("椒盐排骨");
		food15.setFoodPrice(2.5);
		food15.setNumber(0);
		list.add(food15);
		
		return list;
	}
}
