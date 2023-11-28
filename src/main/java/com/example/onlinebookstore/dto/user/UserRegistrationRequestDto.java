package com.example.onlinebookstore.dto.user;

import com.example.onlinebookstore.validation.FieldMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

@FieldMatch(
        field = "password",
        repeatedField = "repeatedPassword"
)
public record UserRegistrationRequestDto(
        @Email @NotNull
        String email,
        @Size(min = 2, max = 32) @NotNull
        String firstName,
        @Size(min = 2, max = 32) @NotNull
        String lastName,
        @Size(max = 128) @NotNull
        String shippingAddress,
        @Length(min = 8) @NotNull
        String password,
        String repeatedPassword) {
}
