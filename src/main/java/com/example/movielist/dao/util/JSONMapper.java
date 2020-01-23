package com.example.movielist.dao.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSONMapper<T> {

  private ObjectMapper objectMapper;
  private Logger logger = LoggerFactory.getLogger(JSONMapper.class);

  public JSONMapper(){
    objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public List<T> map(Supplier<String> supplier, Class<T> contentClass){
    try {
      JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, contentClass);
      return objectMapper.readValue(supplier.get(), type);
    }catch (Exception ex){
      logger.error("Problem parsing JSON");
      return null;
    }
  }

}
