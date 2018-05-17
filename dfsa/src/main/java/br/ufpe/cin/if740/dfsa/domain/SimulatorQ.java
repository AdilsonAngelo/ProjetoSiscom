package br.ufpe.cin.if740.dfsa.domain;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import br.ufpe.cin.if740.dfsa.enums.EstimatorType;
import br.ufpe.cin.if740.dfsa.factories.EstimatorFactory;

public class SimulatorQ extends Simulator implements Runnable{

	private int q;
	private double qfp;
	private double c;
	private int[] tagsSN;

	public SimulatorQ(int numTags, int step, int maxTags, int iterations, int initialFrameSize) {
		super(EstimatorFactory.getEstimator(EstimatorType.Q_C1_G2), numTags, step, maxTags, iterations, initialFrameSize);

		this.qfp = 4.0;
		this.q = (int) Math.round(this.qfp);
		this.c = randomDouble(0.1, 0.5);
	}

	@Override
	public void run() {
		System.out.println("\nInitializing SimulatorQ\n");

		List<Estimate> estimates = new LinkedList<Estimate>();

		for(int numTags = getNumTags(); numTags <= getMaxTags(); numTags += getStep()) {

			int totalEmpty = 0;
			int totalSuccess = 0;
			int totalCollision = 0;
			long totalTime = 0;

			for(int i = 0; i < getIterations(); i++) {
				long begining = new Date().getTime();

				int tagsRemaining = numTags;
				tagsSN = new int[numTags];

				query();
				int check = checkQuery();

				while(tagsRemaining > 0) {
					switch (check) {
					case 0:
						queryAdj(false);

						totalEmpty++;
						break;
					case 1:
						tagsSN = new int[--tagsRemaining];
						queryRep();

						totalSuccess++;
						break;
					default:
						queryAdj(true);

						totalCollision++;
						break;
					}

					check = checkQuery();
				}
				long end = new Date().getTime();
				totalTime += end - begining;
			}

			double avgSuccess = (double) totalSuccess/this.getIterations();
			double avgEmpty = (double) totalEmpty/this.getIterations();
			double avgCollision = (double) totalCollision/this.getIterations();
			double avgTime = (double) totalTime/this.getIterations();

			System.out.println(this.getEstimator().getClass().getSimpleName() + " -> " + avgSuccess + " " + avgEmpty + " " + avgCollision + " " + avgTime);

			estimates.add(new Estimate(avgSuccess, avgCollision, avgEmpty, avgTime));
		}
		this.setResult(new Result(this.getEstimator().getClass().getSimpleName(), estimates));
	}

	private int checkQuery() {
		int zeros = 0;
		for(int i = 0; i < tagsSN.length; i++) {
			if(tagsSN[i] == 0) zeros++;
			if(zeros > 1) break;
		}
		return zeros;
	}

	private void query() {
		for(int i = 0; i < tagsSN.length; i++) {
			tagsSN[i] = randomInt(0, (int)Math.pow(2, this.q) - 1);
		}
	}

	private void queryAdj(boolean collision){
		if(collision) {
			qfp = Math.min(15, qfp + c);
			q = (int) Math.round(qfp);
		} else {
			qfp = Math.max(0, qfp - c);
			q = (int) Math.round(qfp);
		}

		query();
	}

	private void queryRep() {
		for(int i = 0; i < tagsSN.length; i++) {
			tagsSN[i] -= 1;
		}
	}

	private double randomDouble(double min, double max) {
		return new Random().nextDouble() * Math.abs(max-min) + min;
	}

}
