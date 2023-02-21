package com.predictbit.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Users {
    @Id
    private String id;
    private String pw;
    @Enumerated(EnumType.STRING)
    private Role _role;
    private boolean enabled;
}
