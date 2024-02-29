package com.texto.textotestapi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.texto.textotestapi.entities.Movie;
import com.texto.textotestapi.repositories.MovieRepository;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {

	@Autowired
	private MovieRepository repository;

	public MovieResource() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping
	public ResponseEntity<List<Movie>> findAll() {
		final List<Movie> list = this.repository.findAll();
		return ResponseEntity.ok(list);

	}

}
