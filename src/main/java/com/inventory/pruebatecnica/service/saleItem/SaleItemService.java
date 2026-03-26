package com.inventory.pruebatecnica.service.saleItem;

import com.inventory.pruebatecnica.domain.dto.request.SaleItemRequest;
import com.inventory.pruebatecnica.domain.entities.SaleItem;
import com.inventory.pruebatecnica.domain.sterotype.BaseService;

public interface SaleItemService extends BaseService<SaleItem> {

    SaleItem create(SaleItemRequest request);

}