package com.product.glorifiedtodo.dtos;

import java.time.LocalDateTime;
import java.util.Map;

public record ApiErrorResponseDto(
        String message,
        String path,
        int statusCode,
        LocalDateTime timestamp,
        Map<String ,String> errors
) {
}
