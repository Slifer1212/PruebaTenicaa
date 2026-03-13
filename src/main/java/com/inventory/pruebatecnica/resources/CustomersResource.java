package com.inventory.pruebatecnica.resources;

import com.inventory.pruebatecnica.domain.entities.Customers;
import com.inventory.pruebatecnica.service.customer.CustomersService;
import com.inventory.pruebatecnica.service.dto.request.CreateCustomerRequest;
import com.inventory.pruebatecnica.service.dto.response.CustomersResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping(value = "/customers")
public class CustomersResource {

    private final CustomersService service;

    @PostMapping
    public Customers create(@Valid @RequestBody CreateCustomerRequest request) {
        return service.create(request);
    }

    @GetMapping
    public ResponseEntity<Page<CustomersResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(this.service.findAll(pageable).map(CustomersResponse::of));
    }

}