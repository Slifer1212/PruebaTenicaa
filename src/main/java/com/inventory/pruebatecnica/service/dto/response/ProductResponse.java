package com.inventory.pruebatecnica.service.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inventory.pruebatecnica.domain.entities.Product;
import com.inventory.pruebatecnica.domain.sterotype.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Optional;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse extends BaseResponse {

    private Long id;
    private String name;
    private BigDecimal price;
    private int stock;

    protected ProductResponse(Product product) {
        super(product);
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.stock = product.getStock();
    }

    public static ProductResponse of(Product product) {
        return Optional.ofNullable(product).map(ProductResponse::new).orElse(null);
    }

}
