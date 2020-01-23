package com.example.movielist.app.db;

import org.springframework.data.repository.CrudRepository;

public interface APIRepository extends CrudRepository<APIRecord, String> {
  APIRecord findByEndpoint(String endpoint);
}
