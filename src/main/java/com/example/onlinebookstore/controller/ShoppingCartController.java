package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.dto.shoppingcart.CartItemRequestDto;
import com.example.onlinebookstore.dto.shoppingcart.ShoppingCartDto;
import com.example.onlinebookstore.exception.EntityNotFoundException;
import com.example.onlinebookstore.model.User;
import com.example.onlinebookstore.repository.UserRepository;
import com.example.onlinebookstore.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping cart management", description = "Endpoints for managing shopping cart")
@Validated
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserRepository userRepository;

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get shopping cart of user")
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ShoppingCartDto getCart(Principal principal) {
        return shoppingCartService.getShoppingCart(getUserId(principal));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add new cart item")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping
    public ShoppingCartDto addCartItem(
            @RequestBody @Valid CartItemRequestDto cartItemRequestDto, Principal principal) {
        return shoppingCartService.addCartItem(getUserId(principal), cartItemRequestDto);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update cart item quantity")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping("/cart-items/{cartItemId}")
    public ShoppingCartDto updateCartItem(@PathVariable @Positive Long cartItemId,
            @RequestBody @Valid CartItemRequestDto cartItemRequestDto) {
        return shoppingCartService.updateCartItem(cartItemId, cartItemRequestDto.quantity());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete cart-item by id")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("/cart-items/{id}")
    public void delete(@PathVariable @Positive Long id) {
        shoppingCartService.deleteCartItem(id);
    }

    private Long getUserId(Principal principal) {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("User is not found"));
        return user.getId();
    }
}
