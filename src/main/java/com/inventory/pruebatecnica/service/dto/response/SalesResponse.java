package com.inventory.pruebatecnica.service.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inventory.pruebatecnica.domain.entities.Customers;
import com.inventory.pruebatecnica.domain.entities.Sales;
import com.inventory.pruebatecnica.domain.sterotype.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalesResponse extends BaseResponse {

    private BigDecimal total;
    private LocalDateTime saleDate;
    private Customers customer;

    protected SalesResponse(Sales sales) {
        super(sales);
        this.total = sales.getTotal();
        this.saleDate = sales.getSaleDate();
        this.customer = sales.getCustomer();
    }

    public static SalesResponse of(Sales sales) {
        return Optional.ofNullable(sales).map(SalesResponse::new).orElse(null);
    }

}
