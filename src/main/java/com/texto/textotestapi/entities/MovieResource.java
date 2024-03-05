package com.texto.textotestapi.entities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.texto.textotestapi.repositories.MovieRepository;
import com.texto.textotestapi.resources.MinMaxProducers;
import com.texto.textotestapi.services.MovieService;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {

	@Autowired
	private MovieRepository repository;

	@Autowired
	private MovieService movieService;

	public MovieResource() {
	}

	@GetMapping
	public ResponseEntity<List<Movie>> findAll() {
		final List<Movie> list = this.repository.findAll();
		return ResponseEntity.ok(list);

	}

	@GetMapping(value = "/producers")
	public ResponseEntity<MinMaxProducers> findProducers() {

		final List<Movie> winners = this.repository.findAllWinners();

		final MinMaxProducers minMaxProducers = this.movieService.extractMinMaxProducers(winners);
		return ResponseEntity.ok(minMaxProducers);
	}

	@GetMapping(value = "/winners")
	public ResponseEntity<List<Movie>> findWinners() {
		final List<Movie> winners = this.repository.findAllWinners();

		return ResponseEntity.ok(winners);
	}
}
