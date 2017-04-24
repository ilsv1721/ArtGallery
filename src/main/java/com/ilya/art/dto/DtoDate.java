package com.ilya.art.dto;

import java.io.Serializable;

public class DtoDate implements Serializable {

	private static final long serialVersionUID = 132532682520489634L;

	private int year;
	private int month;
	private int day;

	public DtoDate() {
	}

	public DtoDate(int year, int month, int day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

}
