package com.example.onlinebookstore.dto.order;

import com.example.onlinebookstore.model.Order;
import jakarta.validation.constraints.NotBlank;

public record OrderUpdateRequestDto(
        @NotBlank
        String shippingAddress,

        Order.Status status
) {
}
