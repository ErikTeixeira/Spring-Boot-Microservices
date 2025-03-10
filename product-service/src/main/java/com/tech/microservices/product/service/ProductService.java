package com.tech.microservices.product.service;

// separar a lógica de negócios do controlador

import com.tech.microservices.product.dto.ProductRequest;
import com.tech.microservices.product.dto.ProductResponse;
import com.tech.microservices.product.model.Product;
import com.tech.microservices.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// Do lombok - faz a mesma coisa do autowired, mas não precisa colocar em todos
@RequiredArgsConstructor
// Do lombok - faz o log funcionar
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;


    // Utiliza o DTO (Data Transfer Object) para receber os dados da requisição
    public ProductResponse createProduct(ProductRequest productRequest) {

        // Cria um objeto Product utilizando o padrão Builder com os dados do DTO -- que é o builder que colocou na classe - @Builder
        Product product = Product.builder()
                .name(productRequest.name())                 // Define o nome do produto
                .description(productRequest.description())   // Define a descrição do produto
                .price(productRequest.price())               // Define o preço do produto
                .build();

        // Salva o objeto Product no repositório (persistência no banco de dados)
        productRepository.save(product);

        log.info("Product created");
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    /**
     * Retorna uma lista de produtos convertidos para ProductResponse.
     *
     *
     * Usamos ProductResponse em vez de retornar a entidade Product diretamente para:
     * - Desacoplar a camada de persistência da API.
     * - Evitar expor informações sensíveis ou desnecessárias.
     * - Permitir ajustes na resposta sem alterar a entidade de domínio.
     */
    public List<ProductResponse> getAllProducts() {
        // Busca todos os produtos do repositório e mapeia cada um para um ProductResponse.
        return productRepository.findAll()
                /*
                * .stream() --> converte a coleção (no caso, a lista retornada por findAll()) em um fluxo (stream) de elementos,
                * permitindo usar operações funcionais, como map, filter, e reduce
                * */
                .stream()
                .map(product -> new ProductResponse(
                        product.getId(),          // Mapeia o ID do produto.
                        product.getName(),        // Mapeia o nome do produto.
                        product.getDescription(), // Mapeia a descrição do produto.
                        product.getPrice()        // Mapeia o preço do produto.
                ))
                .toList(); // Converte o stream em uma lista.
    }



}
