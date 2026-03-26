package com.inventory.pruebatecnica.service.sales;

import com.inventory.pruebatecnica.domain.entities.Sale;
import com.inventory.pruebatecnica.domain.sterotype.BaseService;
import com.inventory.pruebatecnica.domain.dto.request.CreateSalesRequest;

public interface SalesService extends BaseService<Sale> {

    Sale create(CreateSalesRequest request);

}
