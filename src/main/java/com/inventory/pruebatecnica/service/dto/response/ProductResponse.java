package com.inventory.pruebatecnica.service.dto.response;

import java.math.BigDecimal;

/**
 * DTO used to send Product data back to the client.
 *
 * This response represents the current state of a Product
 * after creation, update, or retrieval operations.
 */
public record ProductResponse(

        /**
         * Unique identifier of the product.
         */
        Long id,

        /**
         * Name of the product.
         */
        String name,

        /**
         * Price of the product.
         */
        BigDecimal price,

        /**
         * Available stock quantity.
         */
        int stock
) {
}
