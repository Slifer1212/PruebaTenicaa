package com.inventory.pruebatecnica.controller;

import com.inventory.pruebatecnica.domain.entities.Product;
import com.inventory.pruebatecnica.repository.ProductRepository;
import com.inventory.pruebatecnica.service.ProductService;
import com.inventory.pruebatecnica.service.dto.request.CreateProductRequest;
import com.inventory.pruebatecnica.service.dto.request.UpdateProductRequest;
import com.inventory.pruebatecnica.service.dto.response.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for Product functionality.
 *
 * <p>These tests verify the complete flow from service to database,
 * including validation, business logic, and data persistence.</p>
 */
@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("Should create product successfully")
    void shouldCreateProduct() {
        // Arrange
        CreateProductRequest request = new CreateProductRequest(
                "Laptop Dell XPS",
                new BigDecimal("1299.99"),
                10
        );

        // Act
        ProductResponse response = productService.create(request);

        // Assert
        assertNotNull(response);
        assertNotNull(response.id());
        assertEquals("Laptop Dell XPS", response.name());
        assertEquals(new BigDecimal("1299.99"), response.price());
        assertEquals(10, response.stock());

        // Verify in database
        assertTrue(productRepository.existsById(response.id()));
    }

    @Test
    @DisplayName("Should find product by id")
    void shouldFindProductById() {
        // Arrange
        Product product = Product.builder()
                .name("Test Product")
                .price(new BigDecimal("99.99"))
                .stock(5)
                .build();
        Product saved = productRepository.save(product);

        // Act
        ProductResponse response = productService.findById(saved.getId());

        // Assert
        assertNotNull(response);
        assertEquals(saved.getId(), response.id());
        assertEquals("Test Product", response.name());
        assertEquals(new BigDecimal("99.99"), response.price());
        assertEquals(5, response.stock());
    }

    @Test
    @DisplayName("Should throw exception when product not found")
    void shouldThrowExceptionWhenProductNotFound() {
        // Act & Assert
        assertThrows(Exception.class, () -> {
            productService.findById(999L);
        });
    }

    @Test
    @DisplayName("Should update product successfully")
    void shouldUpdateProduct() {
        // Arrange
        Product product = Product.builder()
                .name("Original Name")
                .price(new BigDecimal("100.00"))
                .stock(10)
                .build();
        Product saved = productRepository.save(product);

        UpdateProductRequest updateRequest = new UpdateProductRequest(
                "Updated Name",
                new BigDecimal("150.00"),
                20
        );

        // Act
        ProductResponse response = productService.update(saved.getId(), updateRequest);

        // Assert
        assertEquals(saved.getId(), response.id());
        assertEquals("Updated Name", response.name());
        assertEquals(new BigDecimal("150.00"), response.price());
        assertEquals(20, response.stock());

        // Verify in database
        Product updated = productRepository.findById(saved.getId()).orElseThrow();
        assertEquals("Updated Name", updated.getName());
    }

    @Test
    @DisplayName("Should delete product successfully")
    void shouldDeleteProduct() {
        // Arrange
        Product product = Product.builder()
                .name("To Delete")
                .price(new BigDecimal("50.00"))
                .stock(5)
                .build();
        Product saved = productRepository.save(product);
        Long productId = saved.getId();

        // Act
        productService.delete(productId);

        // Assert
        assertFalse(productRepository.existsById(productId));
    }

    @Test
    @DisplayName("Should throw exception when deleting non-existent product")
    void shouldThrowExceptionWhenDeletingNonExistentProduct() {
        // Act & Assert
        assertThrows(Exception.class, () -> {
            productService.delete(999L);
        });
    }

    @Test
    @DisplayName("Should get all products with pagination")
    void shouldGetAllProductsWithPagination() {
        // Arrange
        productRepository.save(Product.builder()
                .name("Product 1")
                .price(new BigDecimal("10.00"))
                .stock(5)
                .build());

        productRepository.save(Product.builder()
                .name("Product 2")
                .price(new BigDecimal("20.00"))
                .stock(10)
                .build());

        productRepository.save(Product.builder()
                .name("Product 3")
                .price(new BigDecimal("30.00"))
                .stock(15)
                .build());

        // Act
        Page<ProductResponse> page = productService.findAll(PageRequest.of(0, 2));

        // Assert
        assertNotNull(page);
        assertEquals(2, page.getContent().size());
        assertEquals(3, page.getTotalElements());
        assertEquals(2, page.getTotalPages());
    }

    @Test
    @DisplayName("Should return empty page when no products exist")
    void shouldReturnEmptyPageWhenNoProducts() {
        // Act
        Page<ProductResponse> page = productService.findAll(PageRequest.of(0, 10));

        // Assert
        assertNotNull(page);
        assertEquals(0, page.getContent().size());
        assertEquals(0, page.getTotalElements());
    }

    @Test
    @DisplayName("Should create multiple products")
    void shouldCreateMultipleProducts() {
        // Arrange & Act
        CreateProductRequest request1 = new CreateProductRequest(
                "Product 1",
                new BigDecimal("100.00"),
                10
        );
        CreateProductRequest request2 = new CreateProductRequest(
                "Product 2",
                new BigDecimal("200.00"),
                20
        );

        ProductResponse response1 = productService.create(request1);
        ProductResponse response2 = productService.create(request2);

        // Assert
        assertNotNull(response1.id());
        assertNotNull(response2.id());
        assertNotEquals(response1.id(), response2.id());
        assertEquals(2, productRepository.count());
    }

    @Test
    @DisplayName("Should handle product with zero stock")
    void shouldHandleProductWithZeroStock() {
        // Arrange
        CreateProductRequest request = new CreateProductRequest(
                "Out of Stock Product",
                new BigDecimal("50.00"),
                0
        );

        // Act
        ProductResponse response = productService.create(request);

        // Assert
        assertEquals(0, response.stock());
    }

    @Test
    @DisplayName("Should update only specific fields")
    void shouldUpdateOnlySpecificFields() {
        // Arrange
        Product product = Product.builder()
                .name("Original")
                .price(new BigDecimal("100.00"))
                .stock(10)
                .build();
        Product saved = productRepository.save(product);

        UpdateProductRequest updateRequest = new UpdateProductRequest(
                "Original",  // Same name
                new BigDecimal("200.00"),  // New price
                10  // Same stock
        );

        // Act
        ProductResponse response = productService.update(saved.getId(), updateRequest);

        // Assert
        assertEquals("Original", response.name());
        assertEquals(new BigDecimal("200.00"), response.price());
        assertEquals(10, response.stock());
    }

    @Test
    @DisplayName("Should maintain data integrity after multiple operations")
    void shouldMaintainDataIntegrityAfterMultipleOperations() {
        // Arrange & Act
        CreateProductRequest createRequest = new CreateProductRequest(
                "Test Product",
                new BigDecimal("100.00"),
                10
        );
        ProductResponse created = productService.create(createRequest);

        UpdateProductRequest updateRequest = new UpdateProductRequest(
                "Updated Product",
                new BigDecimal("150.00"),
                15
        );
        ProductResponse updated = productService.update(created.id(), updateRequest);

        ProductResponse found = productService.findById(created.id());

        // Assert
        assertEquals(created.id(), updated.id());
        assertEquals(updated.id(), found.id());
        assertEquals("Updated Product", found.name());
        assertEquals(new BigDecimal("150.00"), found.price());
        assertEquals(15, found.stock());
    }
}