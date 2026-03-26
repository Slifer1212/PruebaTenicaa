package com.inventory.pruebatecnica.domain.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public record CreateSalesRequest(

        @NotNull
        Long customerId,

        @NotNull
        @Valid
        List<SaleItemRequest> items

) {}
