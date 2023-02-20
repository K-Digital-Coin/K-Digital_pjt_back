package com.predictbit.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    private String id;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean enabled;
}
