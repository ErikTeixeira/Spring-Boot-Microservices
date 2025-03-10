package com.tech.microservices.product.dto;

import java.math.BigDecimal;

/**
 * DTO (Data Transfer Object) para representar os dados de uma requisição
 * relacionada a um produto. Esse objeto é utilizado para transferir
 * informações entre a camada de apresentação e a camada de serviço,
 * garantindo encapsulamento e organização dos dados recebidos.
 * -- Padrão de design usado para transferir dados entre camadas de uma aplicação,
 * como entre o cliente e o servidor ou entre diferentes microserviços
 * Encapsula os dados necessários para uma operação específica,
 * garantindo que apenas as informações relevantes sejam enviadas
 *          TEM O REQUEST E RESPONSE no DTO
 */
public record ProductRequest(String id, String name, String description, BigDecimal price ) {


}
