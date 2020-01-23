package com.example.movielist.usecases;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.movielist.MovielistApplication;
import com.example.movielist.dao.MovieRestDAO;
import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.StopWatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MovielistApplication.class)
@AutoConfigureMockMvc
public class MoviesUseCasesIT {

  private final static Logger LOGGER = LoggerFactory.getLogger(MoviesUseCasesIT.class);

  private MoviesUseCases underTest = new MoviesUseCases();

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void E2ESmokeTest() throws Exception {

      mockMvc.perform(MockMvcRequestBuilders.get("/movies"))
          .andExpect(status().isOk());

  }

  @Test
  public void testServiceLayerResponseTimesWithRestDAO() {
    underTest.setMovieDAO(new MovieRestDAO());
    StopWatch stopWatch = new StopWatch("Testing Service Layer performances with RestDAO");

    for (int i = 1; i <= 5; i++) {
      stopWatch.start("Test iteration " + i);
      underTest.getAllMoviesWithPeople();
      stopWatch.stop();
    }
    LOGGER.info(stopWatch::prettyPrint);
  }

}
