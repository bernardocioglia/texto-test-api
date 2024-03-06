package com.texto.textotestapi.components;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.texto.textotestapi.entities.Movie;
import com.texto.textotestapi.helpers.CSVHelper;
import com.texto.textotestapi.repositories.MovieRepository;

@Profile("!test")
@Component
public class CSVCommandLineRunner implements CommandLineRunner {

	@Autowired
	MovieRepository repository;

	@Override
	public void run(final String... args) {
		try {
			final URI uri = ClassLoader.getSystemResource("movielist.csv").toURI();
			final InputStream inputStream = Files.newInputStream(Paths.get(uri));
			final List<Movie> movies = CSVHelper.csvToMovies(inputStream);
			this.repository.saveAll(movies);
		} catch (final IOException | URISyntaxException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}
}
