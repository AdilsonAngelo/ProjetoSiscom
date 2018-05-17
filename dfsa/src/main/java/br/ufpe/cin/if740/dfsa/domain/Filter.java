package br.ufpe.cin.if740.dfsa.domain;

public class Filter {
	private int numTags;
	private int maxTags;
	private int step;
	private int iterations;
	private int initialFrameSize;
	private boolean lb;
	private boolean el;
	private boolean q;

	public Filter(){
		super();
	}

	public Filter(int numTags, int maxTags, int step, int iterations, int initialFrameSize, boolean lb, boolean el,
			boolean q) {
		this.numTags = numTags;
		this.maxTags = maxTags;
		this.step = step;
		this.iterations = iterations;
		this.initialFrameSize = initialFrameSize;
		this.lb = lb;
		this.el = el;
		this.q = q;
	}

	public int getMaxTags() {
		return maxTags;
	}

	public void setMaxTags(int maxTags) {
		this.maxTags = maxTags;
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

	public void setStep(int step) {
		this.step = step;
	}

	public int getIterations() {
		return iterations;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	public int getInitialFrameSize() {
		return initialFrameSize;
	}

	public void setInitialFrameSize(int initialFrameSize) {
		this.initialFrameSize = initialFrameSize;
	}

	public boolean isLb() {
		return lb;
	}

	public void setLb(boolean lb) {
		this.lb = lb;
	}

	public boolean isEl() {
		return el;
	}

	public void setEl(boolean el) {
		this.el = el;
	}

	public boolean isQ() {
		return q;
	}

	public void setQ(boolean q) {
		this.q = q;
	}

}
