package com.example.onlinebookstore.service;

import com.example.onlinebookstore.dto.order.OrderDto;
import com.example.onlinebookstore.dto.order.OrderItemDto;
import com.example.onlinebookstore.dto.order.OrderRequestDto;
import com.example.onlinebookstore.dto.order.OrderUpdateRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    List<OrderDto> findAll(Pageable pageable, Long userId);

    OrderDto create(OrderRequestDto requestDto, Long userId);

    OrderDto update(OrderUpdateRequestDto updateDto, Long userId);

    List<OrderItemDto> findAllOrderItemsByOrderId(Pageable pageable, Long orderId);

    OrderItemDto findOrderItemById(Long itemId);
}
