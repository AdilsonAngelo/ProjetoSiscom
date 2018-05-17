package br.ufpe.cin.if740.dfsa.domain;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import br.ufpe.cin.if740.dfsa.enums.EstimatorType;
import br.ufpe.cin.if740.dfsa.estimators.Estimator;
import br.ufpe.cin.if740.dfsa.factories.EstimatorFactory;

public class SimulatorWrapper {
	public static List<Result> getResults(Filter filter) {
		List<Estimator> estimators = new LinkedList<Estimator>();
		
		if(filter.isLb())
			estimators.add(EstimatorFactory.getEstimator(EstimatorType.LOWER_BOUND));
		if(filter.isEl())
			estimators.add(EstimatorFactory.getEstimator(EstimatorType.EOM_LEE));
//		if(filter.isQ())
//			estimators.add(EstimatorFactory.getEstimator(EstimatorType.Q_C1_G2));
		
		long begining = new Date().getTime();

		List<Thread> threads = new LinkedList<Thread>();
		List<Simulator> sims = new LinkedList<Simulator>();

		for (Estimator e : estimators) {
			Simulator s;
			
			if(e.getType().equals(EstimatorType.Q_C1_G2)) {
				s = new SimulatorQ(filter.getNumTags(), filter.getStep(), filter.getMaxTags(), filter.getIterations(), filter.getInitialFrameSize());
			} else {
				s = new Simulator(e, filter.getNumTags(), filter.getStep(), filter.getMaxTags(), filter.getIterations(), filter.getInitialFrameSize());
			}
			
			sims.add(s);
			Thread t = new Thread(s);
			threads.add(t);
			t.run();
		}

		for(Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				System.err.println(e);
			}
		}

		List<Result> results = new LinkedList<Result>();

		for(Simulator sim : sims) {
			results.add(sim.getResult());
		}

		long totalTime = new Date().getTime() - begining;
		long min = TimeUnit.MILLISECONDS.toMinutes(totalTime);

		System.out.println("> Tempo total do programa: " + min + " min " + (TimeUnit.MILLISECONDS.toSeconds(totalTime) - min*60) + " s");
		return results;
	}
}
