package com.tech.microservices.product;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;
import org.hamcrest.Matchers; // Importação para validações de resposta

@Import(TestcontainersConfiguration.class) // Configuração para o uso do Testcontainers

// Configuração do Spring Boot para rodar os testes em uma porta aleatória
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	// Declaração de um MongoDB em um container de teste usando o Testcontainers
	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	// Captura a porta aleatória onde o servidor Spring Boot está rodando para configurar o RestAssured
	@LocalServerPort
	private Integer port;

	// Configura a base URI do RestAssured antes de cada teste
	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	// Inicia o MongoDB container antes de rodar os testes
	static {
		mongoDBContainer.start();
	}

	// Teste para verificar a criação de um produto
	@Test
	void shouldCreateProduct() {
		// Definição do corpo da requisição para criar um produto
		String requestBody = """
                {
                    "name": "Iphone 15",
                    "description": "Iphone 15 is a smartphone from Apple",
                    "price": 1000
                }
                """;

		// Envia a requisição POST e valida a resposta
		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/product") 									// Rota de criação do produto
				.then()
				.statusCode(201) 											// Verifica que a resposta foi "Created"
				.body("id", Matchers.notNullValue()) 					// Verifica que o ID não é nulo
				.body("name", Matchers.equalTo("Iphone 15")) 	// Verifica o nome do produto
				.body("description", Matchers.equalTo("Iphone 15 is a smartphone from Apple")) 	// Verifica a descrição
				.body("price", Matchers.equalTo(1000)); 			// Verifica o preço
	}
}
