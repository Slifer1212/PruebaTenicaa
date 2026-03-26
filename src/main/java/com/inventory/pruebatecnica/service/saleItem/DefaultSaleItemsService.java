package com.inventory.pruebatecnica.service.saleItem;

import com.inventory.pruebatecnica.domain.dto.request.SaleItemRequest;
import com.inventory.pruebatecnica.domain.entities.Product;
import com.inventory.pruebatecnica.domain.entities.SaleItem;
import com.inventory.pruebatecnica.domain.sterotype.DefaultBaseService;
import com.inventory.pruebatecnica.repository.SaleItemRepository;
import com.inventory.pruebatecnica.service.product.ProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Getter
@Service
@AllArgsConstructor
public class DefaultSaleItemsService extends DefaultBaseService<SaleItem> implements SaleItemService {

    private final SaleItemRepository repository;
    private final ProductService productService;

    @Override
    @Transactional
    public SaleItem create(SaleItemRequest request) {
        final Product product = productService.findById(request.productId());
        return SaleItem.builder()
                .product(product)
                .quantity(request.quantity())
                .build();
    }

}