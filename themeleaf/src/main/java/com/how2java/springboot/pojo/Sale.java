package com.how2java.springboot.pojo;

public class Sale {
	
	private int        tiid; 
	private int        slid;
	private String     sname;
	private String     slti;
	public Sale() {
		super();
	}
	public int getTiid() {
		return tiid;
	}
	public void setTiid(int tiid) {
		this.tiid = tiid;
	}
	public int getSlid() {
		return slid;
	}
	public void setSlid(int slid) {
		this.slid = slid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSlti() {
		return slti;
	}
	public void setSlti(String slti) {
		this.slti = slti;
	}

}
