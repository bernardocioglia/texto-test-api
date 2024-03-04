package com.texto.textotestapi.components;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.texto.textotestapi.entities.Movie;
import com.texto.textotestapi.helpers.CSVHelper;
import com.texto.textotestapi.repositories.MovieRepository;

@Component
public class CSVCommandLineRunner implements CommandLineRunner {

	@Autowired
	MovieRepository repository;

	@Override
	public void run(final String... args) throws Exception {
		try {
			final URI uri = ClassLoader.getSystemResource("movielist.csv").toURI();
			final InputStream inputStream = Files.newInputStream(Paths.get(uri));
			final List<Movie> movies = CSVHelper.csvToMovies(inputStream);
			this.repository.saveAll(movies);
		} catch (final IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}
}
