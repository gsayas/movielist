package com.example.movielist.app;

import com.example.movielist.domain.MovieSummary;
import com.example.movielist.usecases.MoviesUseCases;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/movies",
    method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE)
public class MoviesController {

  @Autowired
  private
  MoviesUseCases useCases;

  @GetMapping()
  public ModelAndView showAll() {
    ModelAndView modelAndView = new ModelAndView("movies");
    modelAndView.addObject("movieSummaryList", useCases.getAllMoviesWithPeople());
    return modelAndView;
  }

}
