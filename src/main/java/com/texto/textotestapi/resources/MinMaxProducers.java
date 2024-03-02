package com.texto.textotestapi.resources;

import java.util.List;

public class MinMaxProducers {

	private List<Producer> min;
	private List<Producer> max;

	/**
	 * @param min
	 * @param max
	 */
	public MinMaxProducers(final List<Producer> min, final List<Producer> max) {
		this.min = min;
		this.max = max;
	}

	public MinMaxProducers() {
		// TODO Auto-generated constructor stub
	}

	public List<Producer> getMin() {
		return this.min;
	}

	public void setMin(final List<Producer> min) {
		this.min = min;
	}

	public List<Producer> getMax() {
		return this.max;
	}

	public void setMax(final List<Producer> max) {
		this.max = max;
	}

}
