package com.example.movielist.app;

import com.example.movielist.app.db.APIRepository;
import com.example.movielist.dao.MovieDAO;
import com.example.movielist.dao.MovieLocalDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.ApplicationContext;

@AllArgsConstructor
@Getter
public class Factory {

  private ApplicationContext context;

  public ApplicationContext getContext() {
    return context;
  }

  public MovieDAO getMovieDAO() {
    return new MovieLocalDAO(this);
  }

  public APIRepository getRepository() {
    return this.getContext().getBean(APIRepository.class);
  }

}
