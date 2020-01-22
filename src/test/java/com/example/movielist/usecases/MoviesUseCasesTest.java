package com.example.movielist.usecases;

import com.example.movielist.dao.MovieDAO;
import com.example.movielist.domain.Movie;
import com.example.movielist.domain.MovieSummary;
import com.example.movielist.domain.People;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MoviesUseCasesTest {

  @Mock
  private
  MovieDAO movieDAO;

  @InjectMocks
  private
  MoviesUseCases underTest = new MoviesUseCases();

  @BeforeEach
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void OnValidMovieData_ReturnMovieSummaryList() {
    Mockito.when(movieDAO.getAllMovies()).thenReturn(getMockMovieList());
    Mockito.when(movieDAO.getAllPeople()).thenReturn(getMockPeopleList());

    List<MovieSummary> result = underTest.getAllMoviesWithPeople();
    Assertions.assertEquals(2, result.size());
  }

  private List<People> getMockPeopleList() {
    List<String> filmsOne = Arrays.asList(new String[]{"movie id 1"});
    List<String> filmsTwo = Arrays.asList(new String[]{"movie id 2"});
    People[] people = new People[]{new People("People one", filmsOne),
        new People("People two", filmsTwo)};
    return Arrays.asList(people);
  }

  private List<Movie> getMockMovieList() {
    Movie[] movies = new Movie[]{new Movie("Movie one", "id1"), new Movie("Movie two", "id2")};
    return Arrays.asList(movies);
  }

}
