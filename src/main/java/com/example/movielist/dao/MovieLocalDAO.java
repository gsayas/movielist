package com.example.movielist.dao;

import com.example.movielist.app.Factory;
import com.example.movielist.app.db.GhibliAPIRepository;
import com.example.movielist.domain.Movie;
import com.example.movielist.domain.People;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MovieLocalDAO implements MovieDAO {

  private ObjectMapper objectMapper = new ObjectMapper();
  private Factory factory;

  public MovieLocalDAO(Factory factory) {
    this.factory = factory;
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Override
  public List<Movie> getAllMovies() {
    GhibliAPIRepository repository = factory.getContext().getBean(GhibliAPIRepository.class);
    try {
      List<Movie> movies = objectMapper.readValue(repository.findByEndpoint("MOVIES").getResponse(),
          new TypeReference<List<Movie>>() {
          });

      return movies;
    } catch (Exception ex) {
      System.out.println("Problem parsing movie JSON");
      return null;
    }
  }

  @Override
  public List<People> getAllPeople() {
    GhibliAPIRepository repository = factory.getContext().getBean(GhibliAPIRepository.class);
    try {
      List<People> people = objectMapper
          .readValue(repository.findByEndpoint("PEOPLE").getResponse(),
              new TypeReference<List<People>>() {
              });

      return people;
    } catch (Exception ex) {
      System.out.println("Problem parsing people JSON");
      return null;
    }
  }
}
