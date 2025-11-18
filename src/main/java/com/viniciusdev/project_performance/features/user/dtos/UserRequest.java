package com.viniciusdev.project_performance.features.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(@NotBlank String name,
                          @Email @NotBlank String email,
                          @NotBlank @Size(min = 8) String password) {
}
