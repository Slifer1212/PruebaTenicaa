package com.inventory.pruebatecnica.service.dto.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * DTO used to receive data when updating an existing Product.
 *
 * Validation rules ensure that updated values
 * meet business and data integrity requirements.
 */
public record UpdateProductRequest(

        /**
         * Updated name of the product.
         * - Must not be null or empty
         * - Must contain at least 3 characters
         */
        @NotBlank(message = "Name is required")
        @Size(min = 3, message = "Name must be greater than 3 characters")
        String name,

        /**
         * Updated price of the product.
         * - Must not be null
         * - Must be greater than 0
         */
        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        BigDecimal price,

        /**
         * Updated stock quantity.
         * - Must be 0 or greater
         */
        @Min(value = 0, message = "Stock must be higher than 0")
        int stock
) {
}
