package br.ufpe.cin.if740.dfsa.domain;

import java.util.List;

public class Result {
	private String estimator;
	private List<Estimate> estimates;
	
	public Result(String estimator, List<Estimate> estimates) {
		this.estimator = estimator;
		this.estimates = estimates;
	}

	public List<Estimate> getEstimates() {
		return estimates;
	}
	
	public void setEstimates(List<Estimate> estimates) {
		this.estimates = estimates;
	}
	
	public String getEstimator() {
		return estimator;
	}
	
	public void setEstimator(String estimator) {
		this.estimator = estimator;
	}
}
