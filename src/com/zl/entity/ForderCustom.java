package com.zl.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ForderCustom extends Forder{
	private User user;
	private Status status;
	private List<Sorder> sorders = new ArrayList<Sorder>();
	
	/** default constructor */
	public ForderCustom() {
	}

	/** minimal constructor */
	public ForderCustom(Date date) {
		this.setDate(date); 
	}

	public ForderCustom(List<Sorder> sorders) {
		super();
		this.sorders = sorders;
	}

	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public List<Sorder> getSorders() {
		return sorders;
	}
	public void setSorders(List<Sorder> sorders) {
		this.sorders = sorders;
	}
	
	
}
