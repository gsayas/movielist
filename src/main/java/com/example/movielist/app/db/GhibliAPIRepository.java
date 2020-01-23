package com.example.movielist.app.db;

import org.springframework.data.repository.CrudRepository;

public interface GhibliAPIRepository extends CrudRepository<GhibliAPIRecord, String> {
  public GhibliAPIRecord findByEndpoint(String endpoint);
}
