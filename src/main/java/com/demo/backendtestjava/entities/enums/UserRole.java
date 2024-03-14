package com.demo.backendtestjava.entities.enums;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public enum UserRole  {

     ADMIN("ADMIN"),
     USER("USER");

    private String role;
    UserRole(String role) {
        this.role = role;
    }




}
