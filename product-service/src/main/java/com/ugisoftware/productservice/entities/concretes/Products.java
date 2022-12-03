package com.ugisoftware.productservice.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value="products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Products {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
