package org.nizzydumb.bistradebackend.form.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.nizzydumb.bistradebackend.model.Product;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ProductResponse {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String name;
    private String description;
    private Double price;
    private Long categoryId;
    private String imageURL;
    private List<ProductAttributeResponse> attributes;

    public static ProductResponse convertFromProduct(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .categoryId(product.getCategory().getId())
                .imageURL(product.getImageURL())
                .attributes(product.getAttributes().stream().map(attribute -> ProductAttributeResponse.builder()
                        .id(attribute.getId())
                        .name(attribute.getName())
                        .value(attribute.getValue())
                        .build()).toList())
                .build();
    }

}
