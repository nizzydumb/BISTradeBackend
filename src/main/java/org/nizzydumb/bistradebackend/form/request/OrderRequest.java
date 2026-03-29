package org.nizzydumb.bistradebackend.form.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private String name;
    private String surname;
    private String phone;
    private String email;
    private List<ProductOrderRequest> productOrders;

}
