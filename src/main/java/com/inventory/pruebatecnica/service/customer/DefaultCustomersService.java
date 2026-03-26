package com.inventory.pruebatecnica.service.customer;

import com.inventory.pruebatecnica.domain.entities.Customer;
import com.inventory.pruebatecnica.domain.sterotype.DefaultBaseService;
import com.inventory.pruebatecnica.repository.CustomersRepository;
import com.inventory.pruebatecnica.domain.dto.request.customer.CreateCustomerRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Service
@AllArgsConstructor
public class DefaultCustomersService extends DefaultBaseService<Customer> implements CustomersService {

    private final CustomersRepository repository;


    @Override
    @Transactional
    public Customer create(CreateCustomerRequest request) {
        Customer customer = buildCustomer(request);
        return super.save(customer);
    }

    private Customer buildCustomer(CreateCustomerRequest request) {
        return Customer.builder()
                .name(request.name())
                .email(request.email())
                .phone(request.phone())
                .build();
    }

}