package org.nizzydumb.bistradebackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductOrder extends BaseEntity {

    @ManyToOne(optional = false)
    private Product product;
    @ManyToOne(optional = false)
    private Order order;
    private Integer quantity;
    private Double price;

}
