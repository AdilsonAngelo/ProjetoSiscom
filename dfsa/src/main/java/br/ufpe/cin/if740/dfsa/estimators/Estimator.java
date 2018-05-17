package br.ufpe.cin.if740.dfsa.estimators;

import br.ufpe.cin.if740.dfsa.enums.EstimatorType;

public interface Estimator {
	
	int estimate (int success, int collision, int empty);

	EstimatorType getType();
}
