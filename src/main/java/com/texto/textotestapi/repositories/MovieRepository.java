package com.texto.textotestapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.texto.textotestapi.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
