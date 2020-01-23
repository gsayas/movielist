package com.example.movielist.domain;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MovieSummary {

  private String title;
  private List<String> peopleNames;

}
