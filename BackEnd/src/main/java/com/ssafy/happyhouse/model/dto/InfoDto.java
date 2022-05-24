package com.ssafy.happyhouse.model.dto;

public class InfoDto {
	private String guName;
	private int popul;
	private int density;
	private int market;
	private int medical;
	private int park;
	private int library;
	private int welfare;
	private int child;
	
	public InfoDto() {
		super();
	}
	public InfoDto(String guName, int popul, int density, int market, int medical, int park, int library, int welfare,
			int child) {
		super();
		this.guName = guName;
		this.popul = popul;
		this.density = density;
		this.market = market;
		this.medical = medical;
		this.park = park;
		this.library = library;
		this.welfare = welfare;
		this.child = child;
	}
	
	
	public String getGuName() {
		return guName;
	}
	public void setGuName(String guName) {
		this.guName = guName;
	}
	public int getPopul() {
		return popul;
	}
	public void setPopul(int popul) {
		this.popul = popul;
	}
	public int getDensity() {
		return density;
	}
	public void setDensity(int density) {
		this.density = density;
	}
	public int getMarket() {
		return market;
	}
	public void setMarket(int market) {
		this.market = market;
	}
	public int getMedical() {
		return medical;
	}
	public void setMedical(int medical) {
		this.medical = medical;
	}
	public int getPark() {
		return park;
	}
	public void setPark(int park) {
		this.park = park;
	}
	public int getLibrary() {
		return library;
	}
	public void setLibrary(int library) {
		this.library = library;
	}
	public int getWelfare() {
		return welfare;
	}
	public void setWelfare(int welfare) {
		this.welfare = welfare;
	}
	public int getChild() {
		return child;
	}
	public void setChild(int child) {
		this.child = child;
	}
	
	
	@Override
	public String toString() {
		return "InfoDto [guName=" + guName + ", popul=" + popul + ", density=" + density + ", market=" + market
				+ ", medical=" + medical + ", park=" + park + ", library=" + library + ", welfare=" + welfare
				+ ", child=" + child + "]";
	}
	
	

}
