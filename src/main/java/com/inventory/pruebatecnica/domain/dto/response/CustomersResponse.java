package com.inventory.pruebatecnica.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inventory.pruebatecnica.domain.entities.Customer;
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

    protected CustomersResponse(Customer customer){
        super(customer);
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
        this.sales = customer.getSales().stream().map(SalesResponse::of).toList();
    }

    public static CustomersResponse of(Customer customer){
        return Optional.ofNullable(customer).map(CustomersResponse::new).orElse(null);
    }

}
