package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.dto.order.OrderDto;
import com.example.onlinebookstore.dto.order.OrderRequestDto;
import com.example.onlinebookstore.dto.order.OrderUpdateDto;
import com.example.onlinebookstore.exception.EntityNotFoundException;
import com.example.onlinebookstore.model.User;
import com.example.onlinebookstore.repository.UserRepository;
import com.example.onlinebookstore.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Orders API", description = "Endpoints to manage orders")
@RestController
@Validated
@RequestMapping(value = "/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserRepository userRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "get all orders for the user")
    public List<OrderDto> getAll(Pageable pageable, Principal principal) {

        return orderService.findAll(pageable, getUserId(principal));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "create a new order for the user")
    public OrderDto createOrder(@RequestBody @Valid OrderRequestDto requestDto,
                                Principal principal) {
        return orderService.save(requestDto, getUserId(principal));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "update an order")
    public OrderDto patchOrder(@PathVariable @Positive Long id,
                               @RequestBody @Valid OrderUpdateDto updateDto) {
        return orderService.save(updateDto, id);
    }

    private Long getUserId(Principal principal) {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("User is not found"));
        return user.getId();
    }
}
