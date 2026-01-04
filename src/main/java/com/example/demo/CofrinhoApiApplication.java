package com.example.demo;

import com.example.demo.model.*;
import com.example.demo.repository.MoedaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CofrinhoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CofrinhoApiApplication.class, args);
    }

    @Bean
    CommandLineRunner init(MoedaRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                // Criamos instâncias das classes específicas que herdam de Moeda
                repository.saveAll(List.of(
                        new Real(0.0),    // Certifique-se que essas classes existam
                        new Dolar(0.0),
                        new Euro(0.0),
                        new Iene(0.0),
                        new Yuan(0.0),
                        new Rupia(0.0),
                        new Won(0.0),
                        new Shekel(0.0)
                ));
                System.out.println("✅ Moedas iniciais criadas com sucesso!");
            }
        };
    }
}