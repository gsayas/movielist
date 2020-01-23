package com.example.movielist;

import com.example.movielist.app.Factory;
import com.example.movielist.dao.MovieDAO;
import com.example.movielist.dao.MovieLocalDAO;
import com.example.movielist.dao.MovieRestDAO;
import com.example.movielist.usecases.MoviesUseCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MovielistApplication {

	@Autowired
	private ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(MovielistApplication.class, args);
	}

	@Bean
	public Factory factory() {
		return new Factory(context);
	}

	@Bean
	public MoviesUseCases moviesUseCases() {
		return new MoviesUseCases();
	}

	@Bean
	public MovieDAO movieDao() {
		return new MovieLocalDAO(factory());
	}

}
