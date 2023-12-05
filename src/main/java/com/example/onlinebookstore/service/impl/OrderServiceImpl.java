package com.example.onlinebookstore.service.impl;

import com.example.onlinebookstore.dto.order.OrderDto;
import com.example.onlinebookstore.dto.order.OrderRequestDto;
import com.example.onlinebookstore.dto.order.OrderUpdateDto;
import com.example.onlinebookstore.exception.EntityNotFoundException;
import com.example.onlinebookstore.mapper.OrderItemMapper;
import com.example.onlinebookstore.mapper.OrderMapper;
import com.example.onlinebookstore.model.Order;
import com.example.onlinebookstore.model.OrderItem;
import com.example.onlinebookstore.model.ShoppingCart;
import com.example.onlinebookstore.repository.BookRepository;
import com.example.onlinebookstore.repository.OrderItemRepository;
import com.example.onlinebookstore.repository.OrderRepository;
import com.example.onlinebookstore.repository.ShoppingCartRepository;
import com.example.onlinebookstore.repository.UserRepository;
import com.example.onlinebookstore.service.OrderService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderItemMapper orderItemMapper;
    private final BookRepository bookRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public List<OrderDto> findAll(Pageable pageable, Long userId) {
        return orderRepository.findAll(pageable).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public OrderDto save(OrderRequestDto requestDto, Long userId) {
        Order newOrder = orderMapper.toOrder(requestDto);
        newOrder.setUser(userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User is not found")));
        newOrder.setStatus(Order.Status.UNPAID);
        newOrder.setOrderDate(LocalDateTime.now());
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId).orElseThrow(
                () -> new EntityNotFoundException("Shopping cart is not found"));
        List<OrderItem> orderItems = shoppingCart.getCartItems()
                .stream()
                .map(orderItemMapper::toOrderItem)
                .map(oi -> {
                            oi.setPrice(
                                    bookRepository.getById(oi.getBook().getId())
                                            .getPrice()
                            );
                            return oi;
                            }
                )
                .toList();
        newOrder.setTotal(orderItems.stream()
                .map(oi -> oi.getPrice().multiply(BigDecimal.valueOf(oi.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
        orderItems.forEach(oi -> oi.setOrder(newOrder));
        newOrder.setOrderItems(orderItems);
        return orderMapper.toDto(
                orderRepository.save(newOrder)
        );
    }

    @Override
    public OrderDto save(OrderUpdateDto updateDto, Long orderId) {

        Order currentOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order is not found"));
        orderMapper.updateOrder(updateDto, currentOrder);

        return orderMapper.toDto(
                orderRepository.save(currentOrder)
        );
    }
}
