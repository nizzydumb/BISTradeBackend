package org.nizzydumb.bistradebackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order extends BaseEntity {

    private String name;
    private String surname;
    private String phone;
    private String email;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<ProductOrder> productOrders;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}
