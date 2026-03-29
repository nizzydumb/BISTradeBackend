package org.nizzydumb.bistradebackend.form.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductAttributeResponse {

    private Long id;
    private String name;
    private String value;

}
