package br.ufpe.cin.if740.dfsa.estimators;

public interface Estimator {
	
	int estimate (int success, int collision, int empty);

}
