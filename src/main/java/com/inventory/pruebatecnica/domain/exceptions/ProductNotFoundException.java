package com.inventory.pruebatecnica.domain.exceptions;

/**
 * Exception thrown when a requested product cannot be found.
 *
 * <p>Used by service and controller layers to indicate a missing product
 * resource; typically translated to an HTTP 404 response by an exception
 * handler.</p>
 */
public class ProductNotFoundException extends RuntimeException {

    /**
     * Create a new exception for the given product id.
     *
     * @param id the id of the product that was not found
     */
    public ProductNotFoundException(Long id) {
        // Include the id in the message to aid debugging and client responses
        super("Product not found with Id: " + id);
    }
}
