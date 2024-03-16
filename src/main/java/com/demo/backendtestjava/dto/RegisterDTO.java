package com.demo.backendtestjava.dto;

import com.demo.backendtestjava.entities.enums.UserRole;

public record RegisterDTO(String name, String login, String password, UserRole role) {
}
