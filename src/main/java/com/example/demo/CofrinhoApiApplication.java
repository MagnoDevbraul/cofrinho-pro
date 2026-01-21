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
            // Verifica se a tabela de moedas está vazia no PostgreSQL
            if (repository.count() == 0) {

                /* * POLIMORFISMO EM AÇÃO:
                 * Criei instâncias de classes específicas (Real, Dolar, etc.)
                 * que herdam da classe mãe 'Moeda'. Isso permite que cada moeda
                 * tenha seu comportamento próprio enquanto são tratadas como 'Moeda'
                 * pelo repositório.
                 */
                repository.saveAll(List.of(
                        new Real(0.0),    // Saldo inicial zerado
                        new Dolar(0.0),
                        new Euro(0.0),
                        new Iene(0.0),
                        new Yuan(0.0),
                        new Rupia(0.0),
                        new Won(0.0),
                        new Shekel(0.0)
                ));

                // Log de confirmação no console do IntelliJ
                System.out.println("✅ Moedas iniciais criadas com sucesso!");
            }
        };
    }
}