package com.example.movielist.dao;

import com.example.movielist.app.Factory;
import com.example.movielist.dao.util.JSONMapper;
import com.example.movielist.domain.Movie;
import com.example.movielist.domain.People;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MovieLocalDAO implements MovieDAO {

  public static final String MOVIES_TABLE_NAME = "MOVIES";
  public static final String PEOPLE_TABLE_NAME = "PEOPLE";
  private Factory factory;

  public MovieLocalDAO(Factory factory) {
    this.factory = factory;
  }

  @Override
  public List<Movie> getAllMovies() {
    JSONMapper<Movie> mapper = new JSONMapper<>();
    return mapper.map(() -> findAll(MOVIES_TABLE_NAME), Movie.class);
  }

  @Override
  public List<People> getAllPeople() {
    JSONMapper<People> mapper = new JSONMapper<>();
    return mapper.map(() -> findAll(PEOPLE_TABLE_NAME), People.class);
  }

  private String findAll(String entityName) {
    return factory.getRepository().findByEndpoint(entityName).getResponse();
  }
}
