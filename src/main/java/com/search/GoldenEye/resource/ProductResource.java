package com.search.GoldenEye.resource;

import com.search.GoldenEye.domain.Product;
import com.search.GoldenEye.service.ProductService;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductResource {

  @Autowired
  private ProductService productService;

  @GetMapping(value = "/products/search")
  public ResponseEntity<List<Product>> searchProducts(@RequestParam String query) {
    return ResponseEntity.ok(productService.searchProducts(query));
  }

}
