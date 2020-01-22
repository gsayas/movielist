package com.example.movielist.dao;

import com.example.movielist.domain.Movie;
import com.example.movielist.domain.People;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.web.client.RestTemplate;

public class MovieRestDAO implements MovieDAO {

  private static final String studio_url_films = "https://ghibliapi.herokuapp.com/films";
  private static final String studio_url_people = "https://ghibliapi.herokuapp.com/people";

  private RestTemplate restTemplate = new RestTemplate();
  private ObjectMapper objectMapper = new ObjectMapper();

  public MovieRestDAO() {
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Override
  public List<Movie> getAllMovies() {
    try {
      List<Movie> movies = objectMapper
          .readValue(getRawMovies(), new TypeReference<List<Movie>>() {
          });

      return movies;
    } catch (Exception ex) {
      System.out.println("Problem parsing movie JSON");
      return null;
    }
  }

  @Override
  public List<People> getAllPeople() {
    try {
      List<People> people = objectMapper
          .readValue(getRawPeople(), new TypeReference<List<People>>() {
          });

      return people;
    } catch (Exception ex) {
      System.out.println("Problem parsing people JSON");
      return null;
    }
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
