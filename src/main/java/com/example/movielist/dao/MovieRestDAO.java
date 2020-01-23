package com.example.movielist.dao;

import com.example.movielist.dao.util.JSONMapper;
import com.example.movielist.domain.Movie;
import com.example.movielist.domain.People;
import java.util.List;
import org.springframework.web.client.RestTemplate;

public class MovieRestDAO implements MovieDAO {

  private static final String studio_url_films = "https://ghibliapi.herokuapp.com/films";
  private static final String studio_url_people = "https://ghibliapi.herokuapp.com/people";

  private RestTemplate restTemplate = new RestTemplate();

  @Override
  public List<Movie> getAllMovies() {
    JSONMapper<Movie> mapper = new JSONMapper<>();
    return mapper.map(this::getRawMovies, Movie.class);
  }

  @Override
  public List<People> getAllPeople() {
    JSONMapper<People> mapper = new JSONMapper<>();
    return mapper.map(this::getRawPeople, People.class);
  }

  public String getRawMovies() {
    return executeCall(studio_url_films);
  }

  public String getRawPeople() {
    return executeCall(studio_url_people);
  }

  private String executeCall(String url) {
    return restTemplate.getForObject(url, String.class);
  }

}
