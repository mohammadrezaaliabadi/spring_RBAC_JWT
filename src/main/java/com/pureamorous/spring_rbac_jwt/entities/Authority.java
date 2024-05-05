package com.pureamorous.spring_rbac_jwt.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "authorities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue
    @Column(name = "authority_id")
    private Integer id;

    @Column(name = "authority_name")
    @NotBlank(message = "authority name can't be blank")
    private String authority;

    public Authority(String authority) {
        this.authority = authority;
    }
    @Override
    public String getAuthority() {
        return authority;
    }
}
