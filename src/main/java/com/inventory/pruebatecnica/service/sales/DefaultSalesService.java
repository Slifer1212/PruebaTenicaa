package com.inventory.pruebatecnica.service.sales;

import com.inventory.pruebatecnica.domain.dto.request.CreateSalesRequest;
import com.inventory.pruebatecnica.domain.entities.Customer;
import com.inventory.pruebatecnica.domain.entities.Sale;
import com.inventory.pruebatecnica.domain.sterotype.DefaultBaseService;
import com.inventory.pruebatecnica.repository.SalesRepository;
import com.inventory.pruebatecnica.service.customer.CustomersService;
import com.inventory.pruebatecnica.service.product.ProductService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Getter
@Service
@AllArgsConstructor
public class DefaultSalesService extends DefaultBaseService<Sale> implements SalesService {

    private final SalesRepository repository;
    private final CustomersService customersService;
    private final ProductService productService;

    @Override
    public Sale create(CreateSalesRequest request) {
        return null;
    }

    private Sale buildSales(CreateSalesRequest request) {
        final Customer customer = customersService.findById(request.customerId());
        return Sale.builder()
                .customer(customer)
                .build();
    }

}