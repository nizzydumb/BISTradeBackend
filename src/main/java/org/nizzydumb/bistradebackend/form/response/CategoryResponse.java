package org.nizzydumb.bistradebackend.form.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.nizzydumb.bistradebackend.model.Category;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CategoryResponse {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String name;
    private String description;
    private String imageURL;

    public static CategoryResponse convertFromCategory(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .name(category.getName())
                .description(category.getDescription())
                .imageURL(category.getImageURL())
                .build();
    }

}
