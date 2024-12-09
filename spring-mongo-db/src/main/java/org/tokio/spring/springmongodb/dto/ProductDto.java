package org.tokio.spring.springmongodb.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
@JsonSerialize
public class ProductDto {
    private String id;
    private String name;
    private String description;
    private String category;
    private BigDecimal price;
    private BigDecimal taxes;
    private BigDecimal discount;
    private int stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
