package com.example.movielist.app;

import com.example.movielist.domain.MovieSummary;
import com.example.movielist.usecases.MoviesUseCases;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movies",
    method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE)
public class MoviesController {

  @Autowired
  private
  MoviesUseCases useCases;

  @GetMapping
  public ResponseEntity<List<MovieSummary>> findAll() {
    return ResponseEntity.ok(useCases.getAllMoviesWithPeople());
  }

}
