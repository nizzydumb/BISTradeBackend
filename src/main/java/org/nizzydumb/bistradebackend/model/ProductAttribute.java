package org.nizzydumb.bistradebackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductAttribute extends BaseEntity {

    @ManyToOne(optional = false)
    private Product product;
    private String name;
    private String value;

}
