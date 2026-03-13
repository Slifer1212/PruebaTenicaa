package com.inventory.pruebatecnica.service.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inventory.pruebatecnica.domain.entities.Customers;
import com.inventory.pruebatecnica.domain.sterotype.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomersResponse extends BaseResponse {

    private String name;
    private String email;
    private String phone;
    private List<SalesResponse> sales;

    protected CustomersResponse(Customers customers){
        super(customers);
        this.name = customers.getName();
        this.email = customers.getEmail();
        this.phone = customers.getPhone();
        this.sales = customers.getSales().stream().map(SalesResponse::of).toList();
    }

    public static CustomersResponse of(Customers customers){
        return Optional.ofNullable(customers).map(CustomersResponse::new).orElse(null);
    }

}
