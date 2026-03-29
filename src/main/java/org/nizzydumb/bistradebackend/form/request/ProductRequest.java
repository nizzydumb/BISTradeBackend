package org.nizzydumb.bistradebackend.form.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductRequest {

    private String name;
    private String description;
    private Double price;
    private Long categoryId;
    private String imageURL;
    private List<ProductAttributeRequest> attributes;

}
