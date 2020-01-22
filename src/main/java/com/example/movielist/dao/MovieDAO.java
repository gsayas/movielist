package com.example.movielist.dao;

import com.example.movielist.domain.Movie;
import com.example.movielist.domain.People;
import java.util.List;

public interface MovieDAO {

  List<Movie> getAllMovies();

  List<People> getAllPeople();

}
