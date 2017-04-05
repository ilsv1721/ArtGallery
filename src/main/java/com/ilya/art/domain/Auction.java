package com.ilya.art.domain;

import java.util.Date;

public class Auction {
		
	private int id;
	
	private Date starts = new Date();

	private Date ends = new Date();
	
	private double intialprice;
	
	private double buyOutPrice;
	
	private Painting painting;
	
	private double soldFor;
	
	public Auction() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStarts() {
		return starts;
	}

	public void setStarts(Date starts) {
		this.starts = starts;
	}

	public Date getEnds() {
		return ends;
	}

	public void setEnds(Date ends) {
		this.ends = ends;
	}

	public double getIntialprice() {
		return intialprice;
	}

	public void setIntialprice(double intialprice) {
		this.intialprice = intialprice;
	}

	public double getBuyOutPrice() {
		return buyOutPrice;
	}

	public void setBuyOutPrice(double buyOutPrice) {
		this.buyOutPrice = buyOutPrice;
	}

	public Painting getPainting() {
		return painting;
	}

	public void setPainting(Painting painting) {
		this.painting = painting;
	}

	public double getSoldFor() {
		return soldFor;
	}

	public void setSoldFor(double soldFor) {
		this.soldFor = soldFor;
	}
	
	
	
	

}
