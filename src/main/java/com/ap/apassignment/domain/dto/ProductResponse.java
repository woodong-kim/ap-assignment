package com.ap.apassignment.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private Long id;
    private String brandName;
    private String productName;
    private BigDecimal productPrice;
    private Integer categoryId;
    private String categoryName;

}
