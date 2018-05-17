package br.ufpe.cin.if740.dfsa.simulators;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import br.ufpe.cin.if740.dfsa.domain.Estimate;
import br.ufpe.cin.if740.dfsa.domain.Result;
import br.ufpe.cin.if740.dfsa.estimators.Estimator;

public class Simulator implements Runnable{
	
	private int numTags;
	private final int step;
	private final int maxTags;
	private final int iterations;
	private final int initialFrameSize;
	private Estimator estimator;
	private Result result;

	public Simulator(Estimator estimator, int numTags, int step, int maxTags, int iterations, int initialFrameSize) {
		this.estimator = estimator;
		this.numTags = numTags;
		this.step = step;
		this.maxTags = maxTags;
		this.iterations = iterations;
		this.initialFrameSize = initialFrameSize;
	}

	public void run() {
		System.out.println("\nInitializing " + this.estimator.getClass().getSimpleName() + "\n");
		
		List<Estimate> estimates = new LinkedList<Estimate>();
		for(; numTags <= maxTags; numTags += step) {
			int totalEmpty = 0;
			int totalSuccess = 0;
			int totalCollision = 0;
			long totalTime = 0;
			
			for(int i = 0; i < iterations; i++) {
				long begining = new Date().getTime();
				
				int success, collision, empty;
				int	frameSize = initialFrameSize;
				int	tagsRemaining = numTags;

				while(tagsRemaining > 0) {
					
					success = 0; collision = 0; empty = 0;

					int[] frame = new int[frameSize];

					for(int j = 0; j < tagsRemaining; j++)
						frame[randomInt(0, frameSize-1)]++;

					for(int j = 0; j < frame.length; j++)
						if(frame[j] == 1)
							success++;
						else if(frame[j] > 1)
							collision++;
						else if(frame[j] < 1)
							empty++;

					tagsRemaining -= success;

					frameSize = this.estimator.estimate(success, collision, empty);
					
					totalEmpty += empty;
					totalSuccess += success;
					totalCollision += collision;
				}
				long end = new Date().getTime();
				
				totalTime += end - begining;
			}

			double avgSuccess = (double) totalSuccess/iterations;
			double avgEmpty = (double) totalEmpty/iterations;
			double avgCollision = (double) totalCollision/iterations;
			double avgTime = (double) totalTime/iterations;
			
			System.out.println(this.estimator.getClass().getSimpleName() + " -> " + avgSuccess + " " + avgEmpty + " " + avgCollision + " " + avgTime);
			
			estimates.add(new Estimate(avgSuccess, avgCollision, avgEmpty, avgTime));
		}
		this.setResult(new Result(this.estimator.getClass().getSimpleName(), estimates));
	}
	
	public static int randomInt(int min, int max) {
		if (min > max) throw new IllegalArgumentException(max + "(max) < " + min + "(min)");
		return new Random().nextInt((max-min) + 1) + min;
	}

	public int getNumTags() {
		return numTags;
	}

	public void setNumTags(int numTags) {
		this.numTags = numTags;
	}

	public int getStep() {
		return step;
	}

	public int getMaxTags() {
		return maxTags;
	}

	public int getIterations() {
		return iterations;
	}

	public int getInitialFrameSize() {
		return initialFrameSize;
	}

	public Estimator getEstimator() {
		return estimator;
	}

	public void setEstimator(Estimator estimator) {
		this.estimator = estimator;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

}
