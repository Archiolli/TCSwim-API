package com.example.TCSwim.dto;

import com.example.TCSwim.domain.UserRoles;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record UsersDto(@NotBlank Long userId,
                       @NotBlank String userName,
                       @NotBlank String password,
                       @NotBlank UserRoles role) {
}
