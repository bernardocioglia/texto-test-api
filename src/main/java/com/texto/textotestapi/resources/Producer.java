package com.texto.textotestapi.resources;

public class Producer {

	private String producer;
	private Integer interval;
	private Integer previousWin;
	private Integer followingWin;

	public Producer() {
	}

	/**
	 * @param producer
	 * @param interval
	 * @param previousWin
	 * @param followingWin
	 */
	public Producer(final String producer, final Integer interval, final Integer previousWin,
			final Integer followingWin) {
		this.producer = producer;
		this.interval = interval;
		this.previousWin = previousWin;
		this.followingWin = followingWin;
	}

	public String getProducer() {
		return this.producer;
	}

	public void setProducer(final String producer) {
		this.producer = producer;
	}

	public Integer getInterval() {
		return this.interval;
	}

	public void setInterval(final Integer interval) {
		this.interval = interval;
	}

	public Integer getPreviousWin() {
		return this.previousWin;
	}

	public void setPreviousWin(final Integer previousWin) {
		this.previousWin = previousWin;
	}

	public Integer getFollowingWin() {
		return this.followingWin;
	}

	public void setFollowingWin(final Integer followingWin) {
		this.followingWin = followingWin;
	}

	public void update(final Integer releaseYear) {
		final Integer newInterval = this.previousWin - releaseYear;
		if (Math.abs(newInterval) < this.interval) {
			if (newInterval > 0) {
				this.followingWin = this.previousWin;
				this.previousWin = releaseYear;
			} else {
				this.followingWin = releaseYear;
			}
			this.interval = Math.abs(newInterval);
		}

	}

}
