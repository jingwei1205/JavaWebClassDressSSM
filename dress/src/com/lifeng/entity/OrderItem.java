package com.lifeng.entity;

import java.io.Serializable;

public class OrderItem implements Serializable{
	
	private int id;
	private Order order;
	private Dress dress;
	private int buynum;
	private int dressId;
	private int ostate;
	public int getDressId(){
		return  dressId;
	}
	public void setDressId(){
		dressId=dress.getDressid();

	}
	public int getOS(){
		return  ostate;
	}
	public void setOS(int ostate){
		this.ostate=ostate;

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public Dress getDress() {
		return dress;
	}
	public void setDress(Dress dress) {
		this.dress = dress;
	}
	public int getBuynum() {
		return buynum;
	}
	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}

	
}
