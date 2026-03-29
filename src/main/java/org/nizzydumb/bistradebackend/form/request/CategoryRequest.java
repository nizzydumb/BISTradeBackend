package org.nizzydumb.bistradebackend.form.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {

    private String name;
    private String description;
    private String imageURL;

}
