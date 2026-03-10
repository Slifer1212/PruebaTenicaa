package com.inventory.pruebatecnica.service.impl;

import com.inventory.pruebatecnica.domain.entities.Product;
import com.inventory.pruebatecnica.domain.exceptions.ProductNotFoundException;
import com.inventory.pruebatecnica.repository.ProductRepository;
import com.inventory.pruebatecnica.service.ProductService;
import com.inventory.pruebatecnica.service.dto.request.CreateProductRequest;
import com.inventory.pruebatecnica.service.dto.request.UpdateProductRequest;
import com.inventory.pruebatecnica.service.dto.response.ProductResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link ProductService}.
 * <p>
 * This service handles business logic related to Products
 * and coordinates between the repository and DTO mapping.
 */
@Service
@AllArgsConstructor
public class DefaulltProductService implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(DefaulltProductService.class);

    private final ProductRepository productRepository;


    /**
     * Creates a new product.
     *
     * @param request DTO containing product creation data
     * @return created product as {@link ProductResponse}
     */
    @Override
    @Transactional
    public ProductResponse create(CreateProductRequest request) {
        Product product = buildProduct(request);
        return mapToResponse(productRepository.save(product));
    }

    /**
     * Retrieves all products using pagination.
     *
     * @param pageable pagination information
     * @return paginated list of {@link ProductResponse}
     */
    @Override
    public Page<ProductResponse> findAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    /**
     * Retrieves a product by its id.
     *
     * @param id product identifier
     * @return product data as {@link ProductResponse}
     * @throws ProductNotFoundException if product does not exist
     */
    @Override
    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return mapToResponse(product);
    }

    /**
     * Updates an existing product.
     *
     * @param id      product identifier
     * @param request DTO containing updated product data
     * @return updated product as {@link ProductResponse}
     * @throws ProductNotFoundException if product does not exist
     */
    @Override
    @Transactional
    public ProductResponse update(Long id, UpdateProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        product.setName(request.name());
        product.setPrice(request.price());
        product.setStock(request.stock());

        Product updated = productRepository.save(product);

        log.info("Product updated with id {}", updated.getId());

        return mapToResponse(updated);
    }

    /**
     * Deletes a product by its id.
     *
     * @param id product identifier
     * @throws ProductNotFoundException if product does not exist
     */
    @Override
    public void delete(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        productRepository.delete(product);

        log.info("Product deleted with id {}", id);
    }

    /**
     * Builds a Product entity from a CreateProductRequest DTO.
     */
    private Product buildProduct(CreateProductRequest request) {
        return Product.builder()
                .name(request.name())
                .price(request.price())
                .stock(request.stock())
                .build();
    }

    /**
     * Maps a Product entity to a ProductResponse DTO.
     */
    private ProductResponse mapToResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock()
        );
    }
}
