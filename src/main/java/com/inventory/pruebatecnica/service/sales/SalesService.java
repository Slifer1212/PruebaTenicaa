package com.inventory.pruebatecnica.service.sales;

import com.inventory.pruebatecnica.domain.entities.Sales;
import com.inventory.pruebatecnica.domain.sterotype.BaseService;
import com.inventory.pruebatecnica.service.dto.request.CreateSalesRequest;

public interface SalesService extends BaseService<Sales> {

    Sales create(CreateSalesRequest request);

    Sales update(Long id, UpdateSalesRequest request);


}
