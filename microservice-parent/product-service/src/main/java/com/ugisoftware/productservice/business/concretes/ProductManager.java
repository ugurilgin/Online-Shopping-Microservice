package com.ugisoftware.productservice.business.concretes;

import com.ugisoftware.productservice.business.abstracts.ProductService;
import com.ugisoftware.productservice.dataAccess.repositories.ProductRepository;
import com.ugisoftware.productservice.entities.concretes.Products;
import com.ugisoftware.productservice.entities.dto.request.ProductRequest;
import com.ugisoftware.productservice.entities.dto.response.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductManager implements ProductService {
    private ProductRepository productRepository;
  public ProductManager(ProductRepository productRepository)
   {
     this.productRepository=productRepository;
   }
    @Override
    public ProductResponse create(ProductRequest productRequest) {
    Products products=    Products.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
        .build();
    productRepository.save(products);
    return mapToProductResponse(products);
  }

    @Override
    public List<ProductResponse> getAll() {
        List<Products> productsList=productRepository.findAll();
        return productsList.stream().map(this::mapToProductResponse).toList();
    }
    private ProductResponse mapToProductResponse(Products products)
    {
        return  ProductResponse.builder()
                .id(products.getId())
                .name(products.getName())
                .description(products.getDescription())
                .price(products.getPrice())
                .build();
    }
}
