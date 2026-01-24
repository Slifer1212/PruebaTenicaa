package com.inventory.pruebatecnica.controller;

import com.inventory.pruebatecnica.service.ProductService;
import com.inventory.pruebatecnica.service.dto.request.CreateProductRequest;
import com.inventory.pruebatecnica.service.dto.request.UpdateProductRequest;
import com.inventory.pruebatecnica.service.dto.response.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller exposing CRUD operations for products.
 *
 * <p>Endpoints:
 * - GET  /product           : list products (paged)
 * - POST /product           : create a new product
 * - GET  /product/{id}      : get a product by id
 * - PUT  /product/{id}      : update a product by id
 * - DELETE /product/{id}    : delete a product by id</p>
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Retrieve a page of products.
     *
     * @param pageable pagination information (page, size, sort)
     * @return page of {@link ProductResponse} wrapped in 200 OK
     */
    @GetMapping()
    public ResponseEntity<Page<ProductResponse>> findAll(Pageable pageable){
        // Delegate to service to fetch a paginated list
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    /**
     * Create a new product.
     *
     * @param request validated create request body
     * @return created {@link ProductResponse} with HTTP 201 (Created)
     */
    @PostMapping
    public ResponseEntity<ProductResponse> create(
            @Valid @RequestBody CreateProductRequest request) {

        // Create product and return with CREATED status
        ProductResponse response = productService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Find a product by its id.
     *
     * @param id product identifier
     * @return {@link ProductResponse} with HTTP 200 (OK)
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
        // Fetch product or propagate not-found exception
        return ResponseEntity.ok(productService.findById(id));
    }

    /**
     * Update an existing product.
     *
     * @param id product identifier to update
     * @param request validated update request body
     * @return updated {@link ProductResponse} with HTTP 200 (OK)
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequest request) {

        // Delegate update operation to service
        return ResponseEntity.ok(productService.update(id, request));
    }

    /**
     * Delete a product by id.
     *
     * @param id product identifier to delete
     * @return no content with HTTP 204 when deletion succeeds
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        // Perform delete; method returns 204 on success
        productService.delete(id);
    }
}
