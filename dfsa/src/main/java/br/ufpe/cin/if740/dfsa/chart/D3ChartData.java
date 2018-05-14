package br.ufpe.cin.if740.dfsa.chart;

public class D3ChartData {
	private double[] dataX;
	private double[] dataY;
	private String estimatorName;
	
	public D3ChartData(double[] dataX, double[] dataY, String estimatorName) {
		this.dataX = dataX;
		this.dataY = dataY;
		this.setEstimatorName(estimatorName);
	}
	
	public double[] getDataX() {
		return dataX;
	}

	public void setDataX(double[] dataX) {
		this.dataX = dataX;
	}

	public double[] getDataY() {
		return dataY;
	}

	public void setDataY(double[] dataY) {
		this.dataY = dataY;
	}

	public String getEstimatorName() {
		return estimatorName;
	}

	public void setEstimatorName(String estimatorName) {
		this.estimatorName = estimatorName;
	}

}
