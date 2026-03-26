package com.inventory.pruebatecnica.resources;

import com.inventory.pruebatecnica.domain.dto.request.product.CreateProductRequest;
import com.inventory.pruebatecnica.domain.dto.request.product.UpdateProductRequest;
import com.inventory.pruebatecnica.domain.dto.response.ProductResponse;
import com.inventory.pruebatecnica.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@AllArgsConstructor
@RequestMapping("/product")
public class ProductResource {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(this.productService.findAll(pageable).map(ProductResponse::of));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ProductResponse.of(this.productService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody CreateProductRequest request) {
        return ResponseEntity.ok(ProductResponse.of(this.productService.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @RequestBody UpdateProductRequest request) {
        return ResponseEntity.ok(ProductResponse.of(this.productService.update(id, request)));
    }

}
