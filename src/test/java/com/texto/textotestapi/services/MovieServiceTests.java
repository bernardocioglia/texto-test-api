package com.texto.textotestapi.services;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import com.texto.textotestapi.entities.Movie;
import com.texto.textotestapi.helpers.CSVHelper;
import com.texto.textotestapi.repositories.MovieRepository;

@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MovieServiceTests {

	@LocalServerPort
	private int port;
	@Autowired
	private MovieRepository repository;

	public MovieServiceTests() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void loadMoviesCSV() {

		final URL url = ClassLoader.getSystemResource("movielist.csv");
		Assertions.assertNotNull(url);
		final URI uri = Assertions.assertDoesNotThrow(() -> url.toURI());
		final InputStream inputStream = Assertions.assertDoesNotThrow(() -> Files.newInputStream(Paths.get(uri)));
		final List<Movie> csvMovies = Assertions.assertDoesNotThrow(() -> CSVHelper.csvToMovies(inputStream));
		Assertions.assertDoesNotThrow(() -> this.repository.saveAll(csvMovies));
		final List<Movie> dbMovies = Assertions.assertDoesNotThrow(() -> this.repository.findAll());
		Assertions.assertEquals(dbMovies.size(), csvMovies.size());
		org.assertj.core.api.Assertions.assertThat(this.restTemplate
				.getForObject("http://localhost:" + this.port + "/movies", List.class).size() == csvMovies.size());

	}
}
