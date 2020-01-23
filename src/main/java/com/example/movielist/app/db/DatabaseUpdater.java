package com.example.movielist.app.db;

import com.example.movielist.dao.MovieRestDAO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUpdater {

  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter
      .ofPattern("HH:mm:ss");
  private final Logger logger = LoggerFactory.getLogger(DatabaseUpdater.class);
  @Autowired
  private
  ApplicationContext context;

  @Scheduled(fixedRate = 5000)
  public void updateDatabase() {
    logger.info("Updating API Database :: Execution Time - {}",
        dateTimeFormatter.format(LocalDateTime.now()));
    GhibliAPIRepository repository = context.getBean(GhibliAPIRepository.class);
    MovieRestDAO restDao = new MovieRestDAO();
    repository.save(new GhibliAPIRecord("MOVIES", restDao.getRawMovies(), LocalDateTime.now()));
    repository.save(new GhibliAPIRecord("PEOPLE", restDao.getRawPeople(), LocalDateTime.now()));
  }

}
