package com.texto.textotestapi.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.texto.textotestapi.entities.Movie;

public class CSVHelper {

	public static List<Movie> csvToMovies(final InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		//// @formatter:off
				CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT
						.withDelimiter(';')
						.withIgnoreHeaderCase()
						.withFirstRecordAsHeader()
						.withTrim());) {
			// @formatter:on

			final List<Movie> movies = new ArrayList<Movie>();

			final Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (final CSVRecord csvRecord : csvRecords) {
				final Movie movie = new Movie(null, Integer.valueOf(csvRecord.get("year")), csvRecord.get("title"),
						csvRecord.get("studios"), csvRecord.get("producers"), csvRecord.get("winner"));

				movies.add(movie);
			}

			return movies;
		} catch (final IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

}