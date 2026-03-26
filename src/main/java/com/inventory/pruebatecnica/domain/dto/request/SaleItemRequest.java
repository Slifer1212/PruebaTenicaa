package com.inventory.pruebatecnica.domain.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SaleItemRequest(

        @NotNull
        Long productId,

        @Min(1)
        @NotNull
        int quantity

) {}