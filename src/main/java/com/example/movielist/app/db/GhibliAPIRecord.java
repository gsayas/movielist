package com.example.movielist.app.db;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GhibliAPIRecord {

  @Id
  private String endpoint;

  @Lob
  private String response;

  private LocalDateTime lastUpdate;

}
