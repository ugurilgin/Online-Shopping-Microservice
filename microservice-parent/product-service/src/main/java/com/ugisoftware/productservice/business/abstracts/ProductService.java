package com.ugisoftware.productservice.business.abstracts;

import com.ugisoftware.productservice.entities.dto.request.ProductRequest;
import com.ugisoftware.productservice.entities.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
public ProductResponse create(ProductRequest productRequest);
public List<ProductResponse> getAll();

}
