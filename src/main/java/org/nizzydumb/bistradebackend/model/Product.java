package org.nizzydumb.bistradebackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Product extends BaseEntity {

    private String name;
    @Lob
    private String description;
    private Double price;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductAttribute> attributes;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOrder> orders;
    @ManyToOne(optional = false)
    private Category category;
    private String imageURL;

}
