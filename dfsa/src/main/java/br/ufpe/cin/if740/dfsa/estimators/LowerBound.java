package br.ufpe.cin.if740.dfsa.estimators;

import br.ufpe.cin.if740.dfsa.enums.EstimatorType;

public class LowerBound implements Estimator{

	public int estimate(int success, int collision, int empty) {
		return collision * 2;
	}

	@Override
	public EstimatorType getType() {
		return EstimatorType.LOWER_BOUND;
	}
}
