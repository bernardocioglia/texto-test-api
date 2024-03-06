package com.texto.textotestapi.services;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.texto.textotestapi.entities.Movie;
import com.texto.textotestapi.helpers.CSVHelper;
import com.texto.textotestapi.repositories.MovieRepository;

@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class MovieServiceTests {

	@Autowired
	private MovieRepository repository;

	public MovieServiceTests() {
		// TODO Auto-generated constructor stub
	}

	@Test
	@Order(value = 2)
	public void loadMoviesCSV() {

		final URL url = ClassLoader.getSystemResource("movielist.csv");
		Assertions.assertNotNull(url);
		final URI uri = Assertions.assertDoesNotThrow(() -> url.toURI());
		final InputStream inputStream = Assertions.assertDoesNotThrow(() -> Files.newInputStream(Paths.get(uri)));
		final List<Movie> csvMovies = Assertions.assertDoesNotThrow(() -> CSVHelper.csvToMovies(inputStream));
		Assertions.assertDoesNotThrow(() -> this.repository.saveAll(csvMovies));
		final List<Movie> dbMovies = Assertions.assertDoesNotThrow(() -> this.repository.findAll());
		Assertions.assertEquals(dbMovies.size(), csvMovies.size());

//		URL url;
//		url = ClassLoader.getSystemResource("movielist.csv");
//		if (url == null) {
//			// throw new RuntimeException("fail to find CSV file movielist.csv");
//			Assertions.assertTrue(false, "fail to find CSV file movielist.csv");
//		}
//
//		URI uri;
//		uri = Assertions.assertDoesNotThrow(() -> url.toURI());
//		try {
//			uri = url.toURI();
//		} catch (final URISyntaxException uriSyntaxException) {
//			throw new RuntimeException("fail to convert CSV file URL into URI: " + uriSyntaxException.getMessage());
//		}
//
//		InputStream inputStream;
//		try {
//			inputStream = Files.newInputStream(Paths.get(uri));
//		} catch (final IOException ioException) {
//			throw new RuntimeException("fail to open CSV file: " + ioException.getMessage());
//		}
//
//		final List<Movie> csvMovies = CSVHelper.csvToMovies(inputStream);
//
//		try {
//			this.repository.saveAll(csvMovies);
//		} catch (final IllegalArgumentException illegalArgumentException) {
//			throw new RuntimeException("fail to store CSV file: " + illegalArgumentException.getMessage());
//		}

//		final List<Movie> dbMovies = this.repository.findAll();
//
//		Assertions.assertEquals(dbMovies.size(), csvMovies.size());
	}
}
