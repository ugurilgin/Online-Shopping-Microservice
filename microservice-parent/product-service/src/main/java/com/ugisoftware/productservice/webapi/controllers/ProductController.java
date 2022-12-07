package com.ugisoftware.productservice.webapi.controllers;

import com.ugisoftware.productservice.business.abstracts.ProductService;
import com.ugisoftware.productservice.business.concretes.ProductManager;
import com.ugisoftware.productservice.entities.dto.request.ProductRequest;
import com.ugisoftware.productservice.entities.dto.response.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping( "/api/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ProductController implements ProductService {
    private final ProductManager productService;
   public ProductController(ProductManager productService)
   {
       this.productService=productService;
   }
   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@RequestBody ProductRequest productRequest) {
        return productService.create(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAll() {
        return productService.getAll();
    }
}
