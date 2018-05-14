package br.ufpe.cin.if740.dfsa.domain;

public class Estimate {

	private double avgTotalSlots;
	private double avgSuccessSlots;
	private double avgCollisionSlots;
	private double avgEmptySlots;
	private double avgTime;
	private double efficiency;
	
	public Estimate(double avgSuccessSlots, double avgCollisionSlots, double avgEmptySlots, double avgTime) {
		this.avgTotalSlots = avgSuccessSlots + avgCollisionSlots + avgEmptySlots;
		this.avgSuccessSlots = avgSuccessSlots;
		this.avgCollisionSlots = avgCollisionSlots;
		this.avgEmptySlots = avgEmptySlots;
		this.avgTime = avgTime;
		this.efficiency = avgSuccessSlots/avgTotalSlots;
	}

	public double getAvgTotalSlots() {
		return avgTotalSlots;
	}
	
	public void setAvgTotalSlots(double avgTotalSlots) {
		this.avgTotalSlots = avgTotalSlots;
	}
	
	public double getAvgSuccessSlots() {
		return avgSuccessSlots;
	}
	
	public void setAvgSuccessSlots(double avgSuccessSlots) {
		this.avgSuccessSlots = avgSuccessSlots;
	}
	
	public double getAvgCollisionSlots() {
		return avgCollisionSlots;
	}
	
	public void setAvgCollisionSlots(double avgCollisionSlots) {
		this.avgCollisionSlots = avgCollisionSlots;
	}
	
	public double getAvgEmptySlots() {
		return avgEmptySlots;
	}
	
	public void setAvgEmptySlots(double avgEmptySlots) {
		this.avgEmptySlots = avgEmptySlots;
	}
	
	public double getAvgTime() {
		return avgTime;
	}
	
	public void setAvgTime(double avgTime) {
		this.avgTime = avgTime;
	}
	
	public double getEfficiency() {
		return efficiency;
	}
	
	public void setEfficiency(double efficiency) {
		this.efficiency = efficiency;
	}
}
