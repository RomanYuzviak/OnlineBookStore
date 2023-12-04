package com.example.onlinebookstore.service.impl;

import com.example.onlinebookstore.dto.order.OrderDto;
import com.example.onlinebookstore.dto.order.OrderRequestDto;
import com.example.onlinebookstore.dto.order.OrderUpdateDto;
import com.example.onlinebookstore.exception.EntityNotFoundException;
import com.example.onlinebookstore.mapper.OrderItemMapper;
import com.example.onlinebookstore.mapper.OrderMapper;
import com.example.onlinebookstore.model.CartItem;
import com.example.onlinebookstore.model.Order;
import com.example.onlinebookstore.model.OrderItem;
import com.example.onlinebookstore.model.ShoppingCart;
import com.example.onlinebookstore.repository.BookRepository;
import com.example.onlinebookstore.repository.OrderRepository;
import com.example.onlinebookstore.repository.ShoppingCartRepository;
import com.example.onlinebookstore.repository.UserRepository;
import com.example.onlinebookstore.service.OrderService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderItemMapper orderItemMapper;
    private final BookRepository bookRepository;

    @Override
    public List<OrderDto> findAll(Pageable pageable, Long userId) {
        return orderRepository.findAll(pageable).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public OrderDto save(OrderRequestDto requestDto, Long userId) {
        Order newOrder = orderMapper.toOrder(requestDto);
        newOrder.setUser(userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User is not found")));
        newOrder.setStatus(Order.STATUS.UNPAID);
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
                ).toList();
        newOrder.setOrderItems(orderItems);
        newOrder.setTotal(orderItems.stream()
                .map(oi -> oi.getPrice().multiply(BigDecimal.valueOf(oi.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
        return orderMapper.toDto(
                orderRepository.save(newOrder)
        );
    }

    @Override
    public OrderDto save(OrderUpdateDto updateDto, Long orderId) {
        return null;
    }
}
