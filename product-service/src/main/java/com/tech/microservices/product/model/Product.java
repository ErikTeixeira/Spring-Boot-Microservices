package com.tech.microservices.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

// Define a coleção do MongoDB associada a esta classe
@Document(value = "product")
// Gera um construtor com todos os argumentos
@AllArgsConstructor
// Gera um construtor sem argumentos
@NoArgsConstructor
// Facilita a criação de objetos com o padrão Builder - facilita a criação de objetos complexos
@Builder
// Gera automaticamente os métodos getter, setter, toString, equals e hashCode
@Data
public class Product {

    // No mongo db o id é string
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;

}
