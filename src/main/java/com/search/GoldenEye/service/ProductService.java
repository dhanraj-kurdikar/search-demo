package com.search.GoldenEye.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.GoldenEye.domain.Product;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired
  private ObjectMapper objectMapper;

  private static final String CHAIR_RESOURCE = "chairs.json";
  private static final String TABLE_RESOURCE = "table.json";
  private static final String LAMP_RESOURCE = "lamp.json";
  private static final String SOFA_RESOURCE = "sofa.json";
  private static final String WASHING_MACHINE_RESOURCE = "washing-machine.json";


  @SneakyThrows
  public List<Product> searchProducts(final String query) {
    String resourceName = "";
    if (isChair(query)) {
      resourceName = CHAIR_RESOURCE;
    } else if (isLamp(query)) {
      resourceName = LAMP_RESOURCE;
    } else if (isSofa(query)) {
      resourceName = SOFA_RESOURCE;
    } else if (isTable(query)) {
      resourceName = TABLE_RESOURCE;
    } else if (isWashingMachine(query)) {
      resourceName = WASHING_MACHINE_RESOURCE;
    }
    return readFileFromResources(resourceName);
  }

  @SneakyThrows
  private List<Product> readFileFromResources(final String filename) {
    URL resource = ProductService.class.getClassLoader().getResource("static/" + filename);
    byte[] bytes = Files.readAllBytes(Paths.get(resource.toURI()));
    return objectMapper.readValue(new String(bytes), new TypeReference<List<Product>>() {
    });
  }

  private boolean isChair(final String query) {
    Pattern pattern = Pattern.compile("chair", Pattern.CASE_INSENSITIVE);
    return pattern.matcher(query).find();
  }

  private boolean isTable(final String query) {
    Pattern pattern = Pattern.compile("table", Pattern.CASE_INSENSITIVE);
    return pattern.matcher(query).find();
  }

  private boolean isLamp(final String query) {
    Pattern pattern = Pattern.compile("lamp", Pattern.CASE_INSENSITIVE);
    return pattern.matcher(query).find();
  }

  private boolean isWashingMachine(final String query) {
    Pattern pattern = Pattern.compile("washingMachine", Pattern.CASE_INSENSITIVE);
    return pattern.matcher(query).find();
  }

  private boolean isSofa(final String query) {
    Pattern pattern = Pattern.compile("sofa", Pattern.CASE_INSENSITIVE);
    return pattern.matcher(query).find();
  }

}