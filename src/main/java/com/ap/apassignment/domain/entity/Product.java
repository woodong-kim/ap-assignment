package com.ap.apassignment.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString(of = {"id","brandName","productName","productPrice"})
public class Product {

    @Id
    @Column(name = "product_no")
    private Long id;

    private String brandName;

    private String productName;

    private BigDecimal productPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_no")
    private Category category;

}
