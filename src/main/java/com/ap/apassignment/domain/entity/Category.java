package com.ap.apassignment.domain.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString(of = {"id","categoryName","parentNo","depth"})
public class Category {

    @Id
    @Column(name = "category_no")
    private Integer id;

    private String categoryName;

    private Integer parentNo;

    private Integer depth;

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();

}
