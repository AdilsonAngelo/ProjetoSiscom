package br.ufpe.cin.if740.dfsa.domain;

public class Estimate {

	private double total;
	private double success;
	private double collision;
	private double empty;
	private double time;
	private double efficiency;
	
	public Estimate(double success, double collision, double empty, double time) {
		this.total = success + collision + empty;
		this.setSuccess(success);
		this.setCollision(collision);
		this.setEmpty(empty);
		this.setTime(time);
		this.setEfficiency(100 * (success/total));
	}

	public double getSuccess() {
		return success;
	}

	public void setSuccess(double success) {
		this.success = success;
	}

	public double getCollision() {
		return collision;
	}

	public void setCollision(double collision) {
		this.collision = collision;
	}

	public double getEmpty() {
		return empty;
	}

	public void setEmpty(double empty) {
		this.empty = empty;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public double getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(double efficiency) {
		this.efficiency = efficiency;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
