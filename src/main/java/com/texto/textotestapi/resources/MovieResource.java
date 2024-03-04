package com.texto.textotestapi.resources;

import java.util.ArrayList;
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

		final Hashtable<String, ArrayList<Integer>> producerYearsHashtable = new Hashtable<>();
		final List<Producer> producersList = new ArrayList<Producer>();

		for (final Movie movie : winners) {

			// separa os nomes do produtores
			final String[] producersNames = movie.getProducers().split(",|and");

			for (String producerName : producersNames) {
				producerName = producerName.trim();

				if (producerYearsHashtable.containsKey(producerName)) {
					final ArrayList<Integer> years = producerYearsHashtable.get(producerName);
					years.add(movie.getReleaseYear());
					if (years.size() >= 2) {
						final Integer previousWin = years.get(years.size() - 2);
						final Integer followingWin = years.get(years.size() - 1);
						final Integer interval = followingWin - previousWin;
						final Producer producer = new Producer(producerName, interval, previousWin, followingWin);
						producersList.add(producer);
					}

				} else {
					producerYearsHashtable.put(producerName, new ArrayList<Integer>(List.of(movie.getReleaseYear())));
				}
			}

		}

		List<Producer> maxList = new ArrayList<Producer>();
		List<Producer> minList = new ArrayList<Producer>();
		if (producersList.size() > 0) {

			// ordernar a lista
			producersList.sort(Comparator.comparing(Producer::getInterval));

			// filtrar apenas os maiores
			final Integer maxInterval = producersList.get(producersList.size() - 1).getInterval();
			maxList = producersList.stream().filter(p -> p.getInterval() == maxInterval).collect(Collectors.toList());

			// filtrar apenas os menores
			final Integer minInterval = producersList.get(0).getInterval();
			minList = producersList.stream().filter(p -> p.getInterval() == minInterval).collect(Collectors.toList());

		}
		final MinMaxProducers minMaxProducers = new MinMaxProducers(minList, maxList);
		return ResponseEntity.ok(minMaxProducers);
	}

	@GetMapping(value = "/winners")
	public ResponseEntity<List<Movie>> findWinners() {
		final List<Movie> winners = this.repository.findAllWinners();

		return ResponseEntity.ok(winners);
	}
}
