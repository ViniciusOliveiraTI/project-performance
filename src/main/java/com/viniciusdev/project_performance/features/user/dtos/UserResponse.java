package com.viniciusdev.project_performance.features.user.dtos;

import java.util.UUID;

public record UserResponse(UUID id, String name, String email) {
}
