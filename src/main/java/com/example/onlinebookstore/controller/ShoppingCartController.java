package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.dto.shoppingcart.CartItemDto;
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

    private Long getUserId(Principal principal) {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("User is not found"));
        return user.getId();
    }

    @Operation(summary = "Get shopping cart of user")
    @GetMapping
    public ShoppingCartDto getCart(Principal principal) {
        return shoppingCartService.getShoppingCart(getUserId(principal));
    }

    @Operation(summary = "Add new cart item")
    @PostMapping
    public ShoppingCartDto addCartItem(
            @RequestBody @Valid CartItemDto cartItemDto, Principal principal) {
        return shoppingCartService.addCartItem(getUserId(principal), cartItemDto);
    }

    @Operation(summary = "Update cart item quantity")
    @PutMapping("/cart-items/{cartItemId}")
    public ShoppingCartDto updateCartItem(@PathVariable @Positive Long cartItemId,
            @RequestBody @Valid CartItemDto cartItemDto) {
        return shoppingCartService.updateCartItem(cartItemId, cartItemDto.quantity());
    }

    @Operation(summary = "Delete cart-item by id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/cart-items/{id}")
    public void delete(@PathVariable @Positive Long id) {
        shoppingCartService.deleteCartItem(id);
    }
}
