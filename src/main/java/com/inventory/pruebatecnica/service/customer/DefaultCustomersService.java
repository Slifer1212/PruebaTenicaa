package com.inventory.pruebatecnica.service.customer;

import com.inventory.pruebatecnica.domain.entities.Customers;
import com.inventory.pruebatecnica.domain.sterotype.DefaultBaseService;
import com.inventory.pruebatecnica.repository.CustomersRepository;
import com.inventory.pruebatecnica.service.dto.request.CreateCustomerRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Service
@AllArgsConstructor
public class DefaultCustomersService extends DefaultBaseService<Customers> implements CustomersService {

    private final CustomersRepository repository;


    @Override
    @Transactional
    public Customers create(CreateCustomerRequest request) {
        Customers customer = buildCustomer(request);
        return super.save(customer);
    }

    private Customers buildCustomer(CreateCustomerRequest request) {
        return Customers.builder()
                .name(request.name())
                .email(request.email())
                .phone(request.phone())
                .build();
    }

}