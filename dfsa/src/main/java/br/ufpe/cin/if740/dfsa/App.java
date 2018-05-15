package br.ufpe.cin.if740.dfsa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import br.ufpe.cin.if740.dfsa.chart.ChartGenerator;
import br.ufpe.cin.if740.dfsa.domain.Estimate;
import br.ufpe.cin.if740.dfsa.domain.Simulator;
import br.ufpe.cin.if740.dfsa.enums.EstimatorType;
import br.ufpe.cin.if740.dfsa.estimators.Estimator;
import br.ufpe.cin.if740.dfsa.factories.EstimatorFactory;

public class App {
	
	static final int NUM_TAGS = 100;
	static final int STEP = 100;
	static final int MAX_TAGS = 1000;
	static final int ITERATIONS = 2000;
	static final int INITIAL_FRAME_SIZE = 64;

	public static void main( String[] args ) {
		setOutputFile();

		Map<String, List<Estimate>> results = new HashMap<String, List<Estimate>>();

		List<Estimator> estimators = new LinkedList<Estimator>();
		estimators.add(EstimatorFactory.getEstimator(EstimatorType.LOWER_BOUND));
		estimators.add(EstimatorFactory.getEstimator(EstimatorType.EOM_LEE));
		
		List<Thread> threads = new LinkedList<Thread>();
		List<Simulator> sims = new LinkedList<Simulator>();

		for (Estimator e : estimators) {
			Simulator s = new Simulator(e, NUM_TAGS, STEP, MAX_TAGS, ITERATIONS, INITIAL_FRAME_SIZE);
			sims.add(s);
			Thread t = new Thread(s);
			threads.add(t);
			t.run();
		}

//		Simulator s = new SimulatorQ(NUM_TAGS, STEP, MAX_TAGS, ITERATIONS);
//		sims.add(s);
//		Thread thr = new Thread(s);
//		threads.add(thr);
//		thr.run();
		
		for(Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}

		for(Simulator sim : sims) {
			results.put(sim.getEstimator().getClass().getSimpleName(), sim.getResults());
		}

		DecimalFormat df = new DecimalFormat("#.##");
		for(Entry<String, List<Estimate>> entry : results.entrySet()) {
			System.out.println("\n--> " + entry.getKey());
			int tempTags = NUM_TAGS;
			for(Estimate e : entry.getValue()) {
				System.out.println("########################## " + tempTags + " TAGS ##########################");
				System.out.println("#");
				System.out.println("#	Total slots: " + df.format(e.getAvgTotalSlots()));
				System.out.println("#	Total empty: " + df.format(e.getAvgEmptySlots()));
				System.out.println("#	Total collision: " + df.format(e.getAvgCollisionSlots()));
				System.out.println("#	Efficiency: " + df.format(e.getEfficiency() * 100) + " %");
				System.out.println("#	Total time: " + df.format(e.getAvgTime()) + " ms");
				System.out.println("#");
				tempTags += STEP;
			}
		}

		ChartGenerator.createChart(results, NUM_TAGS, MAX_TAGS, STEP);
	}

	public static int randomInt(int min, int max) {
		if (min >= max) throw new IllegalArgumentException("max < min");
		return new Random().nextInt((max-min) + 1) + min;
	}

	static void setOutputFile() {
		String logPath = "assets" + File.separator + "log" + File.separator;
		File output = new File(logPath);
		if(!output.exists()) output.mkdirs();

		try {
			System.setOut(new PrintStream(new File(logPath + "out.log")));
			System.setErr(new PrintStream(new File(logPath + "out.log")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
