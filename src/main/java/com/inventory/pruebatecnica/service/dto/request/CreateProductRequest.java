package com.inventory.pruebatecnica.service.dto.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * DTO used to receive data when creating a new Product.
 *
 * This record applies validation rules to ensure
 * the incoming request data is valid before reaching
 * the service or persistence layer.
 */
public record CreateProductRequest(

        /**
         * Name of the product.
         * - Must not be null or empty
         * - Must contain at least 3 characters
         */
        @NotBlank(message = "the name of the product is required")
        @Size(min = 3, message = "Name must be greater than 3 characters")
        String name,

        /**
         * Price of the product.
         * - Must not be null
         * - Must be greater than 0
         */
        @NotNull(message = "the price of the product is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "the price must be greater than 0")
        BigDecimal price,

        /**
         * Available stock quantity.
         * - Must be 0 or greater
         */
        @Min(value = 0, message = "Stock must be higher than 0")
        int stock
) {
}
