package com.example.movielist.usecases;

import com.example.movielist.dao.MovieDAO;
import com.example.movielist.domain.Movie;
import com.example.movielist.domain.MovieSummary;
import com.example.movielist.domain.People;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public class MoviesUseCases {

  private MovieDAO movieDAO;
  private Map<String, List<String>> peopleByMovie;

  public List<MovieSummary> getAllMoviesWithPeople() {
    peopleByMovie = mapMovieToPeople();
    return movieDAO.getAllMovies().stream()
        .map(this::mapToSummary)
        .collect(Collectors.toList());
  }

  private MovieSummary mapToSummary(Movie movie) {
    return MovieSummary.builder()
        .title(movie.getTitle())
        .peopleNames(peopleByMovie.get(movie.getId()))
        .build();
  }

  private Map<String, List<String>> mapMovieToPeople() {
    Map<String, List<String>> movieToPeople = new HashMap<>();
    final List<People> allPeople = movieDAO.getAllPeople();

    for (People person : allPeople) {
      for (String movieUrl : person.getFilms()) {
        movieToPeople.computeIfAbsent(getIdFromUrl(movieUrl),
            (movieId) -> new ArrayList<>()).add(person.getName());
      }
    }

    return movieToPeople;
  }

  private String getIdFromUrl(String movieUrl) {
    return movieUrl.substring(movieUrl.lastIndexOf("/") + 1);
  }

  @Autowired
  void setMovieDAO(MovieDAO movieDAO) {
    this.movieDAO = movieDAO;
  }

}
