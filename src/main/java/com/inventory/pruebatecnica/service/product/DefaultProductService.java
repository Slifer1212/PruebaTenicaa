package com.inventory.pruebatecnica.service.product;

import com.inventory.pruebatecnica.domain.entities.Product;
import com.inventory.pruebatecnica.domain.sterotype.DefaultBaseService;
import com.inventory.pruebatecnica.repository.ProductRepository;
import com.inventory.pruebatecnica.domain.dto.request.product.CreateProductRequest;
import com.inventory.pruebatecnica.domain.dto.request.product.UpdateProductRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Getter
@Service
@AllArgsConstructor
public class DefaultProductService extends DefaultBaseService<Product> implements ProductService {

    private final ProductRepository repository;

    /**
     * Creates a new product in the inventory system based on the provided request data.
     *
     * This method builds a Product entity from the CreateProductRequest, validates the input,
     * and persists it to the database within a transactional context. If the transaction fails,
     * all changes will be rolled back.
     *
     * @param request the CreateProductRequest containing the product information (name, price, and stock)
     * @return the newly created and persisted Product entity with its generated identifier
     */
    @Override
    @Transactional
    public Product create(CreateProductRequest request) {
        Product product = buildProduct(request);
        return super.save(product);
    }

    /**
     * Updates an existing product with the provided information.
     *
     * Retrieves the product by ID, updates its name, price, and stock fields
     * with the values from the request, and persists the changes to the database.
     * This operation is executed within a transactional context.
     *
     * @param id the unique identifier of the product to update
     * @param request the update request containing the new product data (name, price, and stock)
     * @return the updated Product entity with persisted changes
     * @throws jakarta.persistence.EntityNotFoundException if no product exists with the given ID
     */
    @Override
    @Transactional
    public Product update(Long id, UpdateProductRequest request) {
        final Product product = super.findById(id);
        product.setName(request.name());
        product.setPrice(request.price());
        product.setStock(request.stock());
        return super.save(product);
    }

    /**
     * Builds a Product entity from the provided CreateProductRequest data transfer object.
     * Maps the request fields (name, price, stock) to a new Product instance using the builder pattern.
     *
     * @param request the CreateProductRequest containing the product information to be mapped
     * @return a newly constructed Product entity with properties populated from the request
     */
    private Product buildProduct(CreateProductRequest request) {
        return Product.builder()
                .name(request.name())
                .price(request.price())
                .stock(request.stock())
                .build();
    }

}