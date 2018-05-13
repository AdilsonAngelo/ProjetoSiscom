package br.ufpe.cin.if740.dfsa.estimators;

public class LowerBound implements Estimator{

	public int estimate(int success, int collision, int empty) {
		int res;
		
		res = collision * 2;
		
		return res;
	}

}
