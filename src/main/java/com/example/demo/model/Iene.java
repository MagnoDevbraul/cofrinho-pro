package com.example.demo.model; // Ajuste para o seu pacote

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Iene") // Identifica no banco que esta linha é um Iene
public class Iene extends Moeda {

    private static final double TAXA_CONVERSAO = 0.36;

    // Construtor vazio obrigatório para o Hibernate/JPA
    public Iene() {
        super();
    }

    public Iene(double valor) {
        super(valor);
    }

    @Override
    public double converter() {

        return valor * TAXA_CONVERSAO;
    }
}