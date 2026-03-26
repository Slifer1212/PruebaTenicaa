package com.inventory.pruebatecnica.mcp;

import com.inventory.pruebatecnica.domain.entities.Product;
import com.inventory.pruebatecnica.domain.dto.request.product.CreateProductRequest;
import com.inventory.pruebatecnica.domain.dto.request.product.UpdateProductRequest;
import com.inventory.pruebatecnica.domain.dto.response.ProductResponse;
import com.inventory.pruebatecnica.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@AllArgsConstructor
public class ProductTool {

    private final ProductService productService;


    @Tool(description = "create a product")
    public ProductResponse createProduct(@ToolParam String name, @ToolParam BigDecimal price, @ToolParam Integer stock) {
        CreateProductRequest request = new CreateProductRequest(name, price, stock);
        Product product = productService.create(request);
        return ProductResponse.of(product);
    }
    @Tool(description = "find a product by id")
    public ProductResponse findById(@ToolParam Long id) {
        Product product = productService.findById(id);
        return ProductResponse.of(product);
    }

    @Tool(description = "List all products")
    public List<Product> findAll(@ToolParam(description = "Page number starting from 0") int page, @ToolParam(description = "Page size") int size) {
        return productService.findAll(PageRequest.of(page, size)).getContent();
    }

    @Tool(description = "update product")
    public ProductResponse updateProduct(@ToolParam Long id, @ToolParam String name, @ToolParam BigDecimal price, @ToolParam Integer stock) {
        UpdateProductRequest request = new UpdateProductRequest(name, price, stock);
        Product product = productService.update(id, request);
        return ProductResponse.of(product);
    }

}
