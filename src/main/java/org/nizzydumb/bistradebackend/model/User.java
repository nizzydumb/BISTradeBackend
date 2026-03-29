package org.nizzydumb.bistradebackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User extends BaseEntity {

    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

}
