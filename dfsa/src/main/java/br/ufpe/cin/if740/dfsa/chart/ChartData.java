package br.ufpe.cin.if740.dfsa.chart;

public class ChartData {
	
	private String title;
	private double[] xAxis;
	private double[] total;
	private double[] empty;
	private double[] collision;
	private double[] efficiency;
	private double[] time;
	
	public ChartData(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double[] getTotal() {
		return total;
	}

	public void setTotal(double[] total) {
		this.total = total;
	}

	public double[] getEmpty() {
		return empty;
	}

	public void setEmpty(double[] empty) {
		this.empty = empty;
	}

	public double[] getCollision() {
		return collision;
	}

	public void setCollision(double[] collision) {
		this.collision = collision;
	}

	public double[] getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(double[] efficiency) {
		this.efficiency = efficiency;
	}

	public double[] getTime() {
		return time;
	}

	public void setTime(double[] time) {
		this.time = time;
	}

	public double[] getxAxis() {
		return xAxis;
	}

	public void setxAxis(double[] xAxis) {
		this.xAxis = xAxis;
	} 
	
}
