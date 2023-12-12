package com.example.onlinebookstore.dto.order;

import java.math.BigDecimal;

public record OrderItemDto(
        Long id,
        Long bookId,
        Integer quantity,
        BigDecimal price
) {
}
