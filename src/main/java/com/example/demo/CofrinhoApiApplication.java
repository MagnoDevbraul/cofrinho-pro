package com.example.demo;

import com.example.demo.model.*;
import com.example.demo.repository.MoedaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * CLASSE PRINCIPAL (Entry Point)
 * A anotação @SpringBootApplication habilita a configuração automática,
 * o escaneamento de componentes e permite definir configurações extras.
 */
@SpringBootApplication
public class CofrinhoApiApplication {

    public static void main(String[] args) {
        // Inicializa toda a infraestrutura do Spring Framework
        SpringApplication.run(CofrinhoApiApplication.class, args);
    }

    /**
     * BEAN DE INICIALIZAÇÃO (Database Seeding)
     * O CommandLineRunner executa este bloco, de código, logo após o contexto
     * da aplicação ser carregado, mas antes dela terminar de iniciar.
     */
    @Bean
    CommandLineRunner init(MoedaRepository repository) {
        return args -> {
            // Deixamos vazio porque agora as moedas são criadas no cadastro!
            System.out.println(">>> Servidor Online e aguardando cadastros...");
        };
    }

    // Aproveite e cole o Bean do RestTemplate logo abaixo (necessário para a cotação)
    @Bean
    public org.springframework.web.client.RestTemplate restTemplate() {
        return new org.springframework.web.client.RestTemplate();
    }

}