package br.ufpe.cin.if740.dfsa.estimators;

import br.ufpe.cin.if740.dfsa.enums.EstimatorType;

public class QC1G2 implements Estimator{

	public int estimate(int success, int collision, int empty) {
		return 1;
	}

	@Override
	public EstimatorType getType() {
		return EstimatorType.Q_C1_G2;
	}
	
}
