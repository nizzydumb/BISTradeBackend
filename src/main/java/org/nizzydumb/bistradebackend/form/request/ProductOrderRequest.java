package org.nizzydumb.bistradebackend.form.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOrderRequest {

    private Long productId;
    private Integer quantity;
    private Double price;

}
