package com.inventory.pruebatecnica.mcp;

import com.inventory.pruebatecnica.service.ProductService;
import com.inventory.pruebatecnica.service.dto.request.CreateProductRequest;
import com.inventory.pruebatecnica.service.dto.request.UpdateProductRequest;
import com.inventory.pruebatecnica.service.dto.response.ProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class ProductTool {

    private final ProductService productService;


    @Tool(description = "create a product")
    public ProductResponse createProduct(@ToolParam String name, @ToolParam BigDecimal price, @ToolParam Integer stock) {
        CreateProductRequest request = new CreateProductRequest(name, price, stock);
        return productService.create(request);
    }

    @Tool(description = "find a product by id")
    public ProductResponse findById(@ToolParam Long id) {
        return productService.findById(id);
    }

    @Tool(description = "list all products")
    public Page<ProductResponse> findAll(@ToolParam Pageable pageable) {
        return productService.findAll(pageable);
    }

    @Tool(description = "update product")
    public ProductResponse updateProduct(@ToolParam Long id, @ToolParam String name, @ToolParam BigDecimal price, @ToolParam Integer stock) {
        UpdateProductRequest request = new UpdateProductRequest( name, price, stock);
        return productService.update(id, request);
    }

}
