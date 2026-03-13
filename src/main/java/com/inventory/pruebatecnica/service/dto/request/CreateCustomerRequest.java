package com.inventory.pruebatecnica.service.dto.request;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCustomerRequest(

        @NotNull
        @Size(min = 3, max = 100)
        String name,

        @Nonnull
        @Email
        String email,

        @NotNull
        @Size(min = 10, max = 15)
        String phone

) {}
