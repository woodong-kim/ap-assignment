package com.ap.apassignment.repository;

import com.ap.apassignment.domain.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest                                                                    // JPA 테스트 관련 컴포넌트만 Import
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 실제 db 사용
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void read(){

        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            System.out.println("product = " + product.toString());
        }
    }

}