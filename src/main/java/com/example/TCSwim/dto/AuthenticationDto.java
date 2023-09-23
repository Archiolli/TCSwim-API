package com.example.TCSwim.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthenticationDto(@NotBlank String userName,
                                @NotBlank String password) {
}
