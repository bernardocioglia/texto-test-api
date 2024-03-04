package com.texto.textotestapi.resources;

import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

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

	@GetMapping(value = "/producers")
	public ResponseEntity<MinMaxProducers> findProducers() {

		// TODO: avaliar usar uma busca ordenada para reduzir o custo da iteração
		final List<Movie> winners = this.repository.findAllWinners();

		final Hashtable<String, Producer> min = new Hashtable<>();
		final Hashtable<String, Producer> max = new Hashtable<>();
		for (final Movie movie : winners) {

			// separa os nomes do produtores
			final String[] producersNames = movie.getProducers().split(",");

			for (String producerName : producersNames) {
				producerName = producerName.trim();

				// TODO refatorar e extrair os metodos
				// MAX
				// criar os objetos producer
				if (max.containsKey(producerName)) {
					final Producer producer = max.get(producerName);
					final Integer newInterval = producer.getPreviousWin() - movie.getReleaseYear();
					if (Math.abs(newInterval) > producer.getInterval()) {
						if (newInterval > 0) {
							producer.setFollowingWin(producer.getPreviousWin());
							producer.setPreviousWin(movie.getReleaseYear());
						} else {
							producer.setFollowingWin(movie.getReleaseYear());
						}
						producer.setInterval(Math.abs(newInterval));
					}
				} else {
					final Producer producer = new Producer();
					producer.setProducer(producerName);
					producer.setInterval(Integer.MIN_VALUE);
					producer.setPreviousWin(movie.getReleaseYear());
					max.put(producerName, producer);
				}

				// MIN
				// criar os objetos producer
				if (min.containsKey(producerName)) {
					final Producer producer = min.get(producerName);
					final Integer newInterval = producer.getPreviousWin() - movie.getReleaseYear();
					if (Math.abs(newInterval) < producer.getInterval()) {
						if (newInterval > 0) {
							producer.setFollowingWin(producer.getPreviousWin());
							producer.setPreviousWin(movie.getReleaseYear());
						} else {
							producer.setFollowingWin(movie.getReleaseYear());
						}
						producer.setInterval(Math.abs(newInterval));
					}

				} else {
					final Producer producer = new Producer();
					producer.setProducer(producerName);
					producer.setInterval(Integer.MAX_VALUE);
					producer.setPreviousWin(movie.getReleaseYear());
					min.put(producerName, producer);
				}

			}

		}

		// filtrar apenas os maiores
		final List<Producer> maxList = max.values().stream().filter(p -> p.getFollowingWin() != null)
				.sorted(Comparator.comparing(Producer::getInterval)).collect(Collectors.toList());
		final Integer maxInterval = maxList.get(maxList.size() - 1).getInterval();
		maxList.removeIf(p -> p.getInterval() < maxInterval);

		// filtrar apenas os menores
		final List<Producer> minList = min.values().stream().filter(p -> p.getFollowingWin() != null)
				.sorted(Comparator.comparing(Producer::getInterval)).collect(Collectors.toList());
		final Integer minInterval = minList.get(0).getInterval();
		minList.removeIf(p -> p.getInterval() > minInterval);

		final MinMaxProducers minMaxProducers = new MinMaxProducers(minList, maxList);
		return ResponseEntity.ok(minMaxProducers);
	}

	@GetMapping(value = "/winners")
	public ResponseEntity<List<Movie>> findWinners() {
		final List<Movie> winners = this.repository.findAllWinners();

		return ResponseEntity.ok(winners);
	}
}
