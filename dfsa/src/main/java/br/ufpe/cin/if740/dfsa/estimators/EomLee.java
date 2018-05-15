package br.ufpe.cin.if740.dfsa.estimators;

public class EomLee implements Estimator{
	
	private double threshold;
	
	public EomLee() {
		this.setThreshold(0.001);
	}

	public int estimate(int success, int collision, int empty) throws ArithmeticException{
		double gama = EomLeeGamaIteration(success, collision, empty);
		
		return (int) Math.ceil(gama * collision);
	}
	
	private double EomLeeGamaIteration(int success, int collision, int empty) {
		double beta, currentGama, previousGama;
		currentGama = 2;
		previousGama = Double.MAX_VALUE;
		beta = Double.MAX_VALUE;
		
		while(Math.abs(previousGama - currentGama) >= this.threshold) {
			previousGama = currentGama;

			beta = EomLeeBeta(success, collision, empty, previousGama);
			currentGama = EomLeeGama(beta);
		}
		
		return currentGama;
	}
	
	private double EomLeeGama(double beta) {
		return (1 - Math.pow(Math.E, -(1/beta))) / (beta * (1 - (1 + 1/beta) * Math.pow(Math.E, -(1/beta))));
	}
	
	private double EomLeeBeta(int success, int collision, int empty, double previousGama) {
		int frameSize = success + collision + empty;

		return frameSize / (previousGama * collision + success);
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

}
