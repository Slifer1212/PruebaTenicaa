package com.inventory.pruebatecnica.service.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public record CreateSalesRequest(

        @NotNull
        Long customerId,

        @NotNull
        Long productId,

        @NotNull
        @Valid
        List<SaleItemRequest> items

) {}
