package com.texto.textotestapi.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.texto.textotestapi.entities.Movie;
import com.texto.textotestapi.entities.Producer;
import com.texto.textotestapi.resources.MinMaxProducers;

@Service
public class MovieService {

	public MinMaxProducers extractMinMaxProducers(final List<Movie> winners) {
		final HashMap<String, ArrayList<Integer>> producerYearsHashtable = new HashMap<>();
		final List<Producer> producers = new ArrayList<Producer>();

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
						producers.add(producer);
					}

				} else {
					producerYearsHashtable.put(producerName, new ArrayList<Integer>(List.of(movie.getReleaseYear())));
				}
			}

		}

		List<Producer> maxList = new ArrayList<Producer>();
		List<Producer> minList = new ArrayList<Producer>();

		if (producers.size() > 0) {

			// ordernar a lista
			producers.sort(Comparator.comparing(Producer::getInterval));

			// filtrar apenas os maiores
			final Integer maxInterval = producers.get(producers.size() - 1).getInterval();
			maxList = producers.stream().filter(p -> p.getInterval() == maxInterval).collect(Collectors.toList());

			// filtrar apenas os menores
			final Integer minInterval = producers.get(0).getInterval();
			minList = producers.stream().filter(p -> p.getInterval() == minInterval).collect(Collectors.toList());

		}
		final MinMaxProducers minMaxProducers = new MinMaxProducers(minList, maxList);
		return minMaxProducers;
	}

}
