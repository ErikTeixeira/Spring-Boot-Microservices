package com.tech.microservices.product.controller;

import com.tech.microservices.product.dto.ProductRequest;
import com.tech.microservices.product.dto.ProductResponse;
import com.tech.microservices.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
// Do lombok - faz a mesma coisa do autowired, mas não precisa colocar em todos
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // começa aqui no controller, faz um de cada vez, faz o create aqui ai faz lógica lá no service

    @PostMapping
    // Definir o código de status HTTP que será retornado quando o método for executado com sucesso
    @ResponseStatus( HttpStatus.CREATED )
    // No java 21 pode usar um record ao contrário de uma classe
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest ) {

        // Business Logic
        return productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus( HttpStatus.OK )
    public List<ProductResponse> getAllProducts() {

        return productService.getAllProducts();
    }

}
