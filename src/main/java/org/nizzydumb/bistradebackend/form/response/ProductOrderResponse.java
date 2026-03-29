package org.nizzydumb.bistradebackend.form.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.nizzydumb.bistradebackend.model.ProductOrder;

@Getter
@Setter
@Builder
public class ProductOrderResponse {

    private Long id;
    private Long productId;
    private Integer quantity;
    private Double price;

    public static ProductOrderResponse from(ProductOrder productOrder) {
        return ProductOrderResponse.builder()
                .id(productOrder.getId())
                .productId(productOrder.getProduct().getId())
                .quantity(productOrder.getQuantity())
                .price(productOrder.getPrice())
                .build();
    }

}
