package com.viniciusdev.project_performance.features.customer.dtos;

import java.util.UUID;

public record CustomerResponse(UUID id, String firstName, String lastName) { }
