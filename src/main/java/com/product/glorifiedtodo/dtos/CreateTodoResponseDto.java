package com.product.glorifiedtodo.dtos;

import com.product.glorifiedtodo.enums.Status;

import java.time.LocalDateTime;

public record CreateTodoResponseDto(
        Long id,
        String name,
        String description,
        Status status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
