package com.inventory.pruebatecnica.service.product;

import com.inventory.pruebatecnica.domain.entities.Product;
import com.inventory.pruebatecnica.domain.sterotype.BaseService;
import com.inventory.pruebatecnica.service.dto.request.CreateProductRequest;
import com.inventory.pruebatecnica.service.dto.request.UpdateProductRequest;

public interface ProductService extends BaseService<Product> {

    Product create (CreateProductRequest request);

    Product update (Long id, UpdateProductRequest request);

}
