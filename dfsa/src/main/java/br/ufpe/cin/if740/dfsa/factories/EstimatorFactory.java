package br.ufpe.cin.if740.dfsa.factories;

import br.ufpe.cin.if740.dfsa.enums.EstimatorType;
import br.ufpe.cin.if740.dfsa.estimators.EomLee;
import br.ufpe.cin.if740.dfsa.estimators.Estimator;
import br.ufpe.cin.if740.dfsa.estimators.LowerBound;

public class EstimatorFactory {

	public static Estimator getEstimator(EstimatorType et) {
		switch (et) {
			case LOWER_BOUND:
				return new LowerBound();
			case EOM_LEE:
				return new EomLee();
			default:
				return null;
		}
	}

}
