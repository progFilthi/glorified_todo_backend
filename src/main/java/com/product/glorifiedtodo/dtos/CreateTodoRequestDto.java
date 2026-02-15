package com.product.glorifiedtodo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTodoRequestDto(
        @NotBlank(message = "Todo name is required.")
        String name,

        @Size(max = 500, message = "Todo description should be only 500 characters.")
        String description
) {
}
