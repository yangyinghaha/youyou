package com.qianfeng.alipay.bean;

public class Food {
	private String foodName;//����
	private double foodPrice;//����
	private int number;//����
	
	public Food(){
		
	}
	public Food(String foodName,double foodPrice,int number){
		this.foodName=foodName;
		this.foodPrice=foodPrice;
		this.number=number;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public double getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(double foodPrice) {
		this.foodPrice = foodPrice;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	

}
