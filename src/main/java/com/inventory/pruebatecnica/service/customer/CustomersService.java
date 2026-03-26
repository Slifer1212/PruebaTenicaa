package com.inventory.pruebatecnica.service.customer;

import com.inventory.pruebatecnica.domain.entities.Customer;
import com.inventory.pruebatecnica.domain.sterotype.BaseService;
import com.inventory.pruebatecnica.domain.dto.request.customer.CreateCustomerRequest;

public interface CustomersService extends BaseService<Customer> {

    Customer create(CreateCustomerRequest request);

}
