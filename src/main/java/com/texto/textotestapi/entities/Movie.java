package com.texto.textotestapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_movie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// @Column(name = "\"YEAR\"")
	private Integer releaseYear;
	private String title;
	private String studios;
	private String producers;
	private String winner;

	/**
	 * @param id
	 * @param releaseDate
	 * @param title
	 * @param studios
	 * @param producers
	 * @param winner
	 */
	public Movie(final Long id, final Integer releaseDate, final String title, final String studios,
			final String producers, final String winner) {
		this.id = id;
		this.releaseYear = releaseDate;
		this.title = title;
		this.studios = studios;
		this.producers = producers;
		this.winner = winner;
	}

	public Movie() {
		// TODO Auto-generated constructor stub
	}

	public Integer getReleaseYear() {
		return this.releaseYear;
	}

	public void setReleaseYear(final Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getStudios() {
		return this.studios;
	}

	public void setStudios(final String studios) {
		this.studios = studios;
	}

	public String getProducers() {
		return this.producers;
	}

	public void setProducers(final String producers) {
		this.producers = producers;
	}

	public String getWinner() {
		return this.winner;
	}

	public void setWinner(final String winner) {
		this.winner = winner;
	}

}
