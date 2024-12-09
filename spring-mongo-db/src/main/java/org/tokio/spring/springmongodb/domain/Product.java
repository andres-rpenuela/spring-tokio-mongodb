package org.tokio.spring.springmongodb.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(value="products")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Product {
    @Id
    private String id;
    @Field
    private String name;
    @Field
    private String description;
    @Field
    private String category;
    @Field
    private BigDecimal price;
    @Field
    private BigDecimal taxes;
    @Field
    private BigDecimal discount;
    @Field(name="stock_quantity")
    private int stock;
    @Field(name="create_ate")
    private LocalDateTime createdAt;
    @Field(name = "update_at")
    private LocalDateTime updatedAt;
}
