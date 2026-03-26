package com.inventory.pruebatecnica.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inventory.pruebatecnica.domain.entities.Customer;
import com.inventory.pruebatecnica.domain.entities.Sale;
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
    private Customer customer;

    protected SalesResponse(Sale sale) {
        super(sale);
        this.total = sale.getTotal();
        this.saleDate = sale.getSaleDate();
        this.customer = sale.getCustomer();
    }

    public static SalesResponse of(Sale sale) {
        return Optional.ofNullable(sale).map(SalesResponse::new).orElse(null);
    }

}
