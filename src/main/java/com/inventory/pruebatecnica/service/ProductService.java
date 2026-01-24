package com.inventory.pruebatecnica.service;

import com.inventory.pruebatecnica.service.dto.request.CreateProductRequest;
import com.inventory.pruebatecnica.service.dto.request.UpdateProductRequest;
import com.inventory.pruebatecnica.service.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interface that defines business operations
 * related to Products.
 *
 * Implementations of this interface handle application
 * logic and act as a bridge between controllers and repositories.
 */
public interface ProductService {

    /**
     * Creates a new product.
     *
     * @param request DTO containing product creation data
     * @return created product as {@link ProductResponse}
     */
    ProductResponse create(CreateProductRequest request);

    /**
     * Retrieves all products with pagination support.
     *
     * @param pageable pagination information
     * @return paginated list of {@link ProductResponse}
     */
    Page<ProductResponse> findAll(Pageable pageable);

    /**
     * Retrieves a product by its id.
     *
     * @param id product identifier
     * @return product data as {@link ProductResponse}
     */
    ProductResponse findById(Long id);

    /**
     * Updates an existing product.
     *
     * @param id product identifier
     * @param request DTO containing updated product data
     * @return updated product as {@link ProductResponse}
     */
    ProductResponse update(Long id, UpdateProductRequest request);

    /**
     * Deletes a product by its id.
     *
     * @param id product identifier
     */
    void delete(Long id);

}
