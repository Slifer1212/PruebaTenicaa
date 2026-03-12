package com.inventory.pruebatecnica.service.product;

import com.inventory.pruebatecnica.domain.entities.Product;
import com.inventory.pruebatecnica.domain.sterotype.DefaultBaseService;
import com.inventory.pruebatecnica.repository.ProductRepository;
import com.inventory.pruebatecnica.service.dto.request.CreateProductRequest;
import com.inventory.pruebatecnica.service.dto.request.UpdateProductRequest;
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

    @Override
    @Transactional
    public Product create(CreateProductRequest request) {
        Product product = buildProduct(request);
        return super.save(product);
    }

    private Product buildProduct(CreateProductRequest request) {
        return Product.builder()
                .name(request.name())
                .price(request.price())
                .stock(request.stock())
                .build();
    }

    @Override
    @Transactional
    public Product update(Long id, UpdateProductRequest request) {
        final Product product = super.findById(id);
        product.setName(request.name());
        product.setPrice(request.price());
        product.setStock(request.stock());
        return super.save(product);
    }

}