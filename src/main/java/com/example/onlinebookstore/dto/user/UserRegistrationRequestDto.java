package com.example.onlinebookstore.dto.user;

import com.example.onlinebookstore.validation.FieldMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

@FieldMatch(
        field = "password",
        repeatedField = "repeatedPassword"
)
public record UserRegistrationRequestDto(
        @Email @NotBlank
        String email,
        @Size(min = 2, max = 32) @NotBlank
        String firstName,
        @Size(min = 2, max = 32) @NotBlank
        String lastName,
        @Size(max = 128) @NotBlank
        String shippingAddress,
        @Length(min = 8) @NotBlank
        String password,
        @Length(min = 8) @NotBlank
        String repeatedPassword) {
}
