package com.inventory.pruebatecnica.service.customer;

import com.inventory.pruebatecnica.domain.entities.Customers;
import com.inventory.pruebatecnica.domain.sterotype.BaseService;
import com.inventory.pruebatecnica.service.dto.request.CreateCustomerRequest;

public interface CustomersService extends BaseService<Customers> {

    Customers create(CreateCustomerRequest request);

}
