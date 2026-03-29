package org.nizzydumb.bistradebackend.form.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.nizzydumb.bistradebackend.model.Order;
import org.nizzydumb.bistradebackend.model.OrderStatus;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderResponse {

    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private List<ProductOrderResponse> productOrders;
    private OrderStatus status;

    public static OrderResponse from(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .name(order.getName())
                .surname(order.getSurname())
                .phone(order.getPhone())
                .email(order.getEmail())
                .productOrders(order.getProductOrders().stream().map(ProductOrderResponse::from).toList())
                .status(order.getStatus())
                .build();
    }
}
