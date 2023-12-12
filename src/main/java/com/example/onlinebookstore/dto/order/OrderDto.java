package com.example.onlinebookstore.dto.order;

import com.example.onlinebookstore.model.Order;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(
        Long id,
        Long userId,
        List<OrderItemDto> orderItems,
        LocalDateTime orderDate,
        Long total,
        String shippingAddress,
        Order.Status status
) {}
